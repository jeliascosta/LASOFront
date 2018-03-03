package ufrj.lipe.librasoffice.librasgui;

import java.awt.EventQueue;
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

import javax.swing.AbstractButton;
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
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import ufrj.lipe.librasoffice.Iniciador;
import ufrj.lipe.librasoffice.InterpretadorDeLog;
import ufrj.lipe.librasoffice.external.ControladorGDrive;
import ufrj.lipe.librasoffice.external.senderGDrive;
import javax.swing.SwingConstants;
import java.awt.Font;

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

	private java.io.File selecionaArquivo () {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new java.io.File(System.getProperty("user.home")));
		int result1 = fileChooser.showOpenDialog(this);
		//result1 == JFileChooser.APPROVE_OPTION
		java.io.File selectedFile = null;
		selectedFile = fileChooser.getSelectedFile();
		return selectedFile;
	}
	
	/**
	 * Create the frame.
	 */
	public AssistenteDeEnvio(String legenda) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 707, 438);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel legendaPortugues = new JLabel("");
		legendaPortugues.setFont(legendaPortugues.getFont().deriveFont(legendaPortugues.getFont().getStyle() | Font.BOLD));
		legendaPortugues.setBounds(184, 260, 493, 16);
		legendaPortugues.setText(legenda);
		contentPane.add(legendaPortugues);
		
		JLabel lblTextoEmPortugus = new JLabel("Legenda em português:");
		lblTextoEmPortugus.setBounds(20, 260, 152, 16);
		contentPane.add(lblTextoEmPortugus);
		
		JCheckBox chkEuConfirmo = new JCheckBox("Eu confirmo");
		chkEuConfirmo.setBounds(374, 148, 97, 25);
		contentPane.add(chkEuConfirmo);
		
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
		btnNewButton.setIcon(new ImageIcon(AssistenteDeEnvio.class.getResource("/javax/swing/plaf/metal/icons/ocean/file.gif")));
		btnNewButton.setSelectedIcon(new ImageIcon(AssistenteDeEnvio.class.getResource("/com/sun/java/swing/plaf/windows/icons/Question.gif")));
		btnNewButton.setBounds(12, 299, 189, 24);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AssistenteDeEnvio.class.getResource("/ufrj/lipe/librasoffice/sinais/ABRIR.gif")));
		lblNewLabel.setBounds(483, 13, 194, 207);
		contentPane.add(lblNewLabel);
		
		JLabel lblProgressBar = new JLabel("");
		lblProgressBar.setHorizontalAlignment(SwingConstants.CENTER);
		lblProgressBar.setBounds(184, 371, 460, 16);
		contentPane.add(lblProgressBar);
		
		JButton btnNewButton_1 = new JButton("Enviar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				senderGDrive lipeDrive = new senderGDrive(arquivoZIP, progressBar, lblProgressBar);
				Thread tCL = new Thread(lipeDrive);
				tCL.start();
			}
		});
		btnNewButton_1.setBounds(12, 339, 97, 25);
		contentPane.add(btnNewButton_1);
		
		JLabel lblSeuNome = new JLabel("Qual é o seu nome:");
		lblSeuNome.setBounds(12, 16, 116, 16);
		contentPane.add(lblSeuNome);
		
		txtSeuNome = new JTextField();
		txtSeuNome.setBounds(137, 13, 206, 22);
		contentPane.add(txtSeuNome);
		txtSeuNome.setColumns(10);
		
		JLabel lblEmQueEstado = new JLabel("Em que estado você mora?");
		lblEmQueEstado.setBounds(12, 48, 160, 16);
		contentPane.add(lblEmQueEstado);
		
		txtEmQueEstado = new JTextField();
		txtEmQueEstado.setColumns(10);
		txtEmQueEstado.setBounds(181, 45, 162, 22);
		contentPane.add(txtEmQueEstado);
		
		JLabel lblEmQueCidade = new JLabel("Em que cidade você mora?");
		lblEmQueCidade.setBounds(12, 80, 160, 16);
		contentPane.add(lblEmQueCidade);
		
		txtEmQueCidade = new JTextField();
		txtEmQueCidade.setColumns(10);
		txtEmQueCidade.setBounds(181, 77, 162, 22);
		contentPane.add(txtEmQueCidade);
		
		JLabel lblSeTiverEmail = new JLabel("Se tiver e-mail, digite aqui:");
		lblSeTiverEmail.setBounds(12, 112, 160, 16);
		contentPane.add(lblSeTiverEmail);
		
		txtSeTiverEmail = new JTextField();
		txtSeTiverEmail.setColumns(10);
		txtSeTiverEmail.setBounds(181, 109, 162, 22);
		contentPane.add(txtSeTiverEmail);
		
		JEditorPane dtrpnVocAutorizaO = new JEditorPane();
		dtrpnVocAutorizaO.setEnabled(false);
		dtrpnVocAutorizaO.setText("Você autoriza os desenvolvedores do LIBRASOffice a utilizarem sua imagem, sem restrições, dentro do programa?");
		dtrpnVocAutorizaO.setEditable(false);
		dtrpnVocAutorizaO.setBounds(12, 188, 354, 59);
		contentPane.add(dtrpnVocAutorizaO);
		
		JCheckBox chkEuAutorizo = new JCheckBox("Eu autorizo");
		chkEuAutorizo.setBounds(374, 200, 89, 25);
		contentPane.add(chkEuAutorizo);
		
		JEditorPane dtrpnVocConfirmaQue = new JEditorPane();
		dtrpnVocConfirmaQue.setEnabled(false);
		dtrpnVocConfirmaQue.setText("Você confirma que é você mesmo que aparece no vídeo que será enviado?");
		dtrpnVocConfirmaQue.setEditable(false);
		dtrpnVocConfirmaQue.setBounds(12, 148, 354, 38);
		contentPane.add(dtrpnVocConfirmaQue);
		
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
}
