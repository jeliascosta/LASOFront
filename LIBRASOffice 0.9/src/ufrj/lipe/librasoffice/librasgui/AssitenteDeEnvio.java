package ufrj.lipe.librasoffice.librasgui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JPasswordField;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Label;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class AssitenteDeEnvio extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField textField;
	java.io.File arquivoMidia;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

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
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AssitenteDeEnvio frame = new AssitenteDeEnvio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AssitenteDeEnvio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 707, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(137, 336, 421, 28);
		contentPane.add(progressBar);
		
		textField = new JTextField();
		textField.setBounds(294, 301, 264, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblCaminhoDoArquivo = new JLabel("Caminho do arquivo:");
		lblCaminhoDoArquivo.setBounds(168, 304, 129, 16);
		contentPane.add(lblCaminhoDoArquivo);
		
		JButton btnNewButton = new JButton("Escolher vídeo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				arquivoMidia = selecionaArquivo();
				try {
					textField.setText(arquivoMidia.getCanonicalPath());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(AssitenteDeEnvio.class.getResource("/com/sun/java/swing/plaf/windows/icons/Inform.gif")));
		btnNewButton.setSelectedIcon(new ImageIcon(AssitenteDeEnvio.class.getResource("/com/sun/java/swing/plaf/windows/icons/Question.gif")));
		btnNewButton.setBounds(12, 299, 151, 24);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AssitenteDeEnvio.class.getResource("/ufrj/lipe/librasoffice/sinais/ABRIR.gif")));
		lblNewLabel.setBounds(483, 13, 194, 207);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Enviar");
		btnNewButton_1.setBounds(12, 339, 97, 25);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNomeDoSinal = new JLabel("Qual é o seu nome:");
		lblNomeDoSinal.setBounds(12, 50, 116, 16);
		contentPane.add(lblNomeDoSinal);
		
		JLabel lblPrecisamos = new JLabel("Português:");
		lblPrecisamos.setBounds(12, 21, 69, 16);
		contentPane.add(lblPrecisamos);
		
		JLabel lblNewLabel_1 = new JLabel("TEXTO EM PORTGUÊS");
		lblNewLabel_1.setBounds(91, 21, 134, 16);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(137, 47, 160, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblSeuEstado = new JLabel("Em que estado você mora?");
		lblSeuEstado.setBounds(12, 82, 160, 16);
		contentPane.add(lblSeuEstado);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(181, 79, 116, 22);
		contentPane.add(textField_2);
		
		JLabel lblEmQueCidade = new JLabel("Em que cidade você mora?");
		lblEmQueCidade.setBounds(12, 114, 160, 16);
		contentPane.add(lblEmQueCidade);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(181, 111, 116, 22);
		contentPane.add(textField_3);
		
		JLabel lblSeTiverEmail = new JLabel("Se tiver e-mail, digite aqui:");
		lblSeTiverEmail.setBounds(12, 146, 160, 16);
		contentPane.add(lblSeTiverEmail);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(181, 143, 116, 22);
		contentPane.add(textField_4);
		
		JEditorPane dtrpnVocAutorizaO = new JEditorPane();
		dtrpnVocAutorizaO.setText("Você autoriza os desenvolvedores do LIBRASOffice a utilizarem sua imagem, sem restrições, dentro do programa?");
		dtrpnVocAutorizaO.setEnabled(false);
		dtrpnVocAutorizaO.setEditable(false);
		dtrpnVocAutorizaO.setBounds(12, 175, 367, 59);
		contentPane.add(dtrpnVocAutorizaO);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Eu autorizo");
		chckbxNewCheckBox.setBounds(378, 177, 97, 25);
		contentPane.add(chckbxNewCheckBox);
		
		JEditorPane dtrpnVocConfirmaQue = new JEditorPane();
		dtrpnVocConfirmaQue.setText("Você confirma que é você mesmo que aparece no vídeo que será enviado?");
		dtrpnVocConfirmaQue.setEnabled(false);
		dtrpnVocConfirmaQue.setEditable(false);
		dtrpnVocConfirmaQue.setBounds(12, 236, 331, 38);
		contentPane.add(dtrpnVocConfirmaQue);
		
		JCheckBox chckbxEuConfirmo = new JCheckBox("Eu confirmo");
		chckbxEuConfirmo.setBounds(376, 236, 111, 25);
		contentPane.add(chckbxEuConfirmo);
	}
}
