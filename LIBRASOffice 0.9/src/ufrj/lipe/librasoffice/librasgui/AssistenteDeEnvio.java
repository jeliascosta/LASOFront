package ufrj.lipe.librasoffice.librasgui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import ufrj.lipe.librasoffice.Iniciador;
import ufrj.lipe.librasoffice.external.TransmissorGDrive;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.SystemColor;

public class AssistenteDeEnvio extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txtMediaPath;
	java.io.File arquivoMidia;
	java.io.File arquivoZIP;
	String caminhoArquivoZIP;
	private JTextField txtSeuNome;
	private JTextField txtEmQueEstado;
	private JTextField txtEmQueCidade;
	private JTextField txtSeTiverEmail;
	JLabel gifLIBRAS;

	private java.io.File selecionaArquivo () {
		System.err.println("Chamou Chooser");
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new java.io.File(System.getProperty("user.home")));
		int choice = fileChooser.showOpenDialog(this);
		//result1 == JFileChooser.APPROVE_OPTION
		java.io.File selectedFile = null;
		selectedFile = fileChooser.getSelectedFile();
		return selectedFile;
	}
	
	/**
	 * Create the frame.
	 */
	public AssistenteDeEnvio(String legenda) {
		setTitle("Assistente de envio de sinais do LIBRASOffice");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 707, 438);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_6 = new JPanel();
		panel_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setLibras("LEGENDA");
			}
		});
		panel_6.setBounds(20, 260, 657, 16);
		contentPane.add(panel_6);
		panel_6.setLayout(null);
		
		JLabel legendaPortugues = new JLabel("");
		legendaPortugues.setBounds(164, 0, 493, 16);
		panel_6.add(legendaPortugues);
		legendaPortugues.setFont(legendaPortugues.getFont().deriveFont(legendaPortugues.getFont().getStyle() | Font.BOLD));
		legendaPortugues.setText(legenda);
		
		JLabel lblTextoEmPortugus = new JLabel("Legenda em português:");
		lblTextoEmPortugus.setBounds(0, 0, 152, 16);
		panel_6.add(lblTextoEmPortugus);
		
		JPanel panel_4 = new JPanel();
		panel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setLibras("CONFIRMA_VOCE");
			}
		});
		panel_4.setBounds(12, 148, 459, 38);
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		
		JCheckBox chkEuConfirmo = new JCheckBox("Eu confirmo");
		chkEuConfirmo.setBounds(362, 0, 97, 25);
		panel_4.add(chkEuConfirmo);
		
		JEditorPane dtrpnVocConfirmaQue = new JEditorPane();
		dtrpnVocConfirmaQue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setLibras("CONFIRMA_VOCE");
			}
		});
		dtrpnVocConfirmaQue.setEnabled(false);
		dtrpnVocConfirmaQue.setBackground(SystemColor.menu);
		dtrpnVocConfirmaQue.setBounds(0, 0, 354, 38);
		panel_4.add(dtrpnVocConfirmaQue);
		dtrpnVocConfirmaQue.setText("Você confirma que é você mesmo que aparece no vídeo que será enviado?");
		dtrpnVocConfirmaQue.setEditable(false);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setBounds(137, 336, 540, 28);
		contentPane.add(progressBar);
		
		txtMediaPath = new JTextField();
		txtMediaPath.setEditable(false);
		txtMediaPath.setBounds(339, 301, 338, 22);
		contentPane.add(txtMediaPath);
		txtMediaPath.setColumns(10);
		
		JLabel lblCaminhoDoArquivo = new JLabel("Caminho do arquivo:");
		lblCaminhoDoArquivo.setBounds(213, 304, 129, 16);
		contentPane.add(lblCaminhoDoArquivo);
		
		JButton btnNewButton = new JButton("Escolher vídeo Libras");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setLibras("ESCOLHER_VIDEO");
			}
		});
		btnNewButton.setIcon(new ImageIcon(AssistenteDeEnvio.class.getResource("/javax/swing/plaf/metal/icons/ocean/file.gif")));
		btnNewButton.setSelectedIcon(new ImageIcon(AssistenteDeEnvio.class.getResource("/com/sun/java/swing/plaf/windows/icons/Question.gif")));
		btnNewButton.setBounds(12, 299, 189, 24);
		contentPane.add(btnNewButton);
		
		gifLIBRAS = new JLabel("");
		gifLIBRAS.setIcon(null);
		gifLIBRAS.setBounds(483, 13, 194, 207);
		contentPane.add(gifLIBRAS);
		
		JLabel lblProgressBar = new JLabel("");
		lblProgressBar.setHorizontalAlignment(SwingConstants.CENTER);
		lblProgressBar.setBounds(184, 371, 460, 16);
		contentPane.add(lblProgressBar);
		
		JButton btnNewButton_1 = new JButton("Enviar");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setLibras("ENVIAR");
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				TransmissorGDrive lipeDrive = new TransmissorGDrive(arquivoZIP, progressBar, lblProgressBar);
				Thread tCL = new Thread(lipeDrive);
				tCL.start();
			}
		});
		btnNewButton_1.setBounds(12, 339, 97, 25);
		contentPane.add(btnNewButton_1);
		
		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setLibras("SEU_NOME");
			}
		});
		panel.setBounds(12, 13, 331, 22);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblSeuNome = new JLabel("Qual é o seu nome:");
		lblSeuNome.setBounds(0, 3, 116, 16);
		panel.add(lblSeuNome);
		
		txtSeuNome = new JTextField();
		txtSeuNome.setBounds(125, 0, 206, 22);
		panel.add(txtSeuNome);
		txtSeuNome.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setLibras("QUE_ESTADO");
			}
		});
		panel_1.setBounds(12, 45, 331, 22);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblEmQueEstado = new JLabel("Em que estado você mora?");
		lblEmQueEstado.setBounds(0, 3, 160, 16);
		panel_1.add(lblEmQueEstado);
		
		txtEmQueEstado = new JTextField();
		txtEmQueEstado.setBounds(169, 0, 162, 22);
		panel_1.add(txtEmQueEstado);
		txtEmQueEstado.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setLibras("QUE_CIDADE");
			}
		});
		panel_2.setBounds(12, 77, 331, 22);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblEmQueCidade = new JLabel("Em que cidade você mora?");
		lblEmQueCidade.setBounds(0, 3, 160, 16);
		panel_2.add(lblEmQueCidade);
		
		txtEmQueCidade = new JTextField();
		txtEmQueCidade.setBounds(169, 0, 162, 22);
		panel_2.add(txtEmQueCidade);
		txtEmQueCidade.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setLibras("SE_EMAIL");
			}
		});
		panel_3.setBounds(12, 109, 331, 22);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblSeTiverEmail = new JLabel("Se tiver e-mail, digite aqui:");
		lblSeTiverEmail.setBounds(0, 3, 160, 16);
		panel_3.add(lblSeTiverEmail);
		
		txtSeTiverEmail = new JTextField();
		txtSeTiverEmail.setBounds(169, 0, 162, 22);
		panel_3.add(txtSeTiverEmail);
		txtSeTiverEmail.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		panel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setLibras("AUTORIZA_PROJETO");
			}
		});
		panel_5.setBounds(12, 188, 451, 59);
		contentPane.add(panel_5);
		panel_5.setLayout(null);
		
		JEditorPane dtrpnVocAutorizaO = new JEditorPane();
		dtrpnVocAutorizaO.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setLibras("AUTORIZA_PROJETO");
			}
		});
		dtrpnVocAutorizaO.setEnabled(false);
		dtrpnVocAutorizaO.setBackground(SystemColor.menu);
		dtrpnVocAutorizaO.setBounds(0, 0, 354, 59);
		panel_5.add(dtrpnVocAutorizaO);
		dtrpnVocAutorizaO.setText("Você autoriza os desenvolvedores do LIBRASOffice a utilizarem sua imagem, sem restrições, dentro do programa?");
		dtrpnVocAutorizaO.setEditable(false);
		
		JCheckBox chkEuAutorizo = new JCheckBox("Eu autorizo");
		chkEuAutorizo.setBounds(362, 12, 89, 25);
		panel_5.add(chkEuAutorizo);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				arquivoMidia = selecionaArquivo();
				try {
					txtMediaPath.setText(arquivoMidia.getCanonicalPath());					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				PrintWriter writer = null;
				try {
					writer = new PrintWriter(Iniciador.getTmpPath()+"LASO_SEND_DETAIL.txt", "UTF-8");
				} catch (FileNotFoundException | UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				writer.println(legendaPortugues.getText());
				writer.println(txtSeuNome.getText());
				writer.println(txtEmQueEstado.getText());
				writer.println(txtEmQueCidade.getText());
				writer.println(txtSeTiverEmail.getText());
				writer.println(chkEuAutorizo.getText()+" - "+chkEuAutorizo.isSelected());
				writer.println(chkEuConfirmo.getText()+" - "+chkEuConfirmo.isSelected());
				writer.close();
				
				String caminhoArquivoZIP = Iniciador.getTmpPath()+"LASO_SEND_"+legendaPortugues.getText().substring(0, 10)+"_"+txtSeuNome.getText()+"_"+txtEmQueCidade.getText()+".zip";		
				String[] pacoteDrive = { Iniciador.getTmpPath()+"LASO_SEND_DETAIL.txt", txtMediaPath.getText() };

				try {
					FileOutputStream fileOutStream = new FileOutputStream(caminhoArquivoZIP);
					ZipOutputStream zipStream = new ZipOutputStream(fileOutStream);
					byte[] buffer = new byte[4096];
				
					for (int i=0; i < pacoteDrive.length; i++) {
						File arquivoDrive = new File(pacoteDrive[i]);
						FileInputStream fileInStream = new FileInputStream(arquivoDrive);
						zipStream.putNextEntry(new ZipEntry(arquivoDrive.getName()));
						int bufferLength = fileInStream.read(buffer);
						while (bufferLength > 0) {
							zipStream.write(buffer, 0, bufferLength);
							bufferLength = fileInStream.read(buffer);
						}
						zipStream.closeEntry();
						fileInStream.close();
					}
					zipStream.close();
					arquivoZIP = new File(caminhoArquivoZIP);
					}
				catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		setVisible(true);
	}
	
	protected void setLibras(String comandoLIBRAS) {
		gifLIBRAS.setIcon(new ImageIcon(TooltipEmLIBRAS.class
				.getResource("/ufrj/lipe/librasoffice/sinais/" + comandoLIBRAS + ".gif")));
	}
}
