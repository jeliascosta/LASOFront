package ufrj.lipe.librasoffice.librasgui;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.sun.star.lang.IndexOutOfBoundsException;

import ufrj.lipe.librasoffice.external.ControladorUNO;

public class AssistenteFormulas extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public static boolean aberto = false;
	private JTextField initialCell;
	private JTextField finalCell;
	private JTextField resultCell;
	private String formula = ""; 
	private JPanel opcoesDeFormula;
	private JPanel camposDaFormula;
	private JCheckBox isCellRange;
	
	private void exibirCampos(boolean enableRange, String formulaLiteral) {
		opcoesDeFormula.setVisible(false);
		camposDaFormula.setVisible(true);
		isCellRange.setEnabled(enableRange);
		
	}
	
	/**
	 * Create the frame.
	 */
	public AssistenteFormulas() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AssistenteFormulas.class.getResource("/com/sun/javafx/scene/web/skin/OrderedListNumbers_16x16_JFX.png")));
		setTitle("Assistente de fórmulas do LIBRASOffice");
		aberto = true;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				aberto=false;
			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				System.err.println("ENTROU FRAME");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				System.err.println("SAIU FRAME");				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				System.err.println("PRESSIONOU FRAME");
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				System.err.println("LIBEROU FRAME");
			}
		});
		setBounds(100, 100, 467, 282);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);		
		
		camposDaFormula = new JPanel();
		camposDaFormula.setVisible(false);
		camposDaFormula.setBounds(12, 13, 231, 229);
		getContentPane().add(camposDaFormula);
		camposDaFormula.setLayout(null);
		
		initialCell = new JTextField();
		initialCell.setBounds(175, 13, 44, 22);
		camposDaFormula.add(initialCell);
		initialCell.setColumns(2);
		
		JLabel lblClulaInicial = new JLabel("Célula ou valor inicial:");
		lblClulaInicial.setBounds(12, 16, 126, 16);
		camposDaFormula.add(lblClulaInicial);
		
		JLabel lblClulaFinal = new JLabel("Célula ou valor final:");
		lblClulaFinal.setBounds(12, 45, 126, 16);
		camposDaFormula.add(lblClulaFinal);
		
		finalCell = new JTextField();
		finalCell.setColumns(2);
		finalCell.setBounds(175, 42, 44, 22);
		camposDaFormula.add(finalCell);
		
		JLabel lblClulaResultado = new JLabel("Célula de resultado:");
		lblClulaResultado.setBounds(12, 75, 118, 16);
		camposDaFormula.add(lblClulaResultado);
		
		resultCell = new JTextField();
		resultCell.setColumns(2);
		resultCell.setBounds(175, 72, 44, 22);
		camposDaFormula.add(resultCell);		
		
		JButton btnVoltar = new JButton("INSERIR FÓRMULA");
		btnVoltar.setBounds(81, 204, 150, 25);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladorUNO UNO = new ControladorUNO();
				//UNO.testarRotina();
				try {
					UNO.insereFormula(initialCell.getText(), finalCell.getText(), 
							resultCell.getText(), formula, isCellRange.isSelected());
				} catch (IndexOutOfBoundsException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		camposDaFormula.add(btnVoltar);
		
		JTextPane txtpnFrmula = new JTextPane();
		txtpnFrmula.setText("=");
		txtpnFrmula.setEditable(false);
		txtpnFrmula.setBounds(34, 151, 44, 27);
		camposDaFormula.add(txtpnFrmula);
		
		JLabel lblFrmulaMontada = new JLabel("Fórmula montada:");
		lblFrmulaMontada.setBounds(24, 132, 106, 16);
		camposDaFormula.add(lblFrmulaMontada);
		
		isCellRange = new JCheckBox("Faixa de células");
		isCellRange.setBounds(101, 100, 118, 25);
		camposDaFormula.add(isCellRange);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(81, 151, 26, 27);
		camposDaFormula.add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		textPane_1.setBounds(112, 151, 26, 27);
		camposDaFormula.add(textPane_1);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setEditable(false);
		textPane_2.setBounds(150, 151, 26, 27);
		camposDaFormula.add(textPane_2);
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setEditable(false);
		textPane_3.setBounds(188, 151, 31, 27);
		camposDaFormula.add(textPane_3);
		
		JLabel lblNewLabel_2 = new JLabel("A1");
		lblNewLabel_2.setBounds(12, 151, 15, 16);
		camposDaFormula.add(lblNewLabel_2);
		
		opcoesDeFormula = new JPanel();
		opcoesDeFormula.setLayout(null);
		opcoesDeFormula.setBounds(12, 13, 231, 229);
		getContentPane().add(opcoesDeFormula);
		
		JButton button = new JButton("MULTIPLICAR");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formula = "product";
				exibirCampos(true, "MULT");
			}
		});
		button.setBounds(0, 66, 110, 20);
		opcoesDeFormula.add(button);
		
		JButton btnMdia = new JButton("MÉDIA");
		btnMdia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formula = "average";
				exibirCampos(true, btnMdia.getText());
			}
		});
		btnMdia.setBounds(121, 0, 110, 20);
		opcoesDeFormula.add(btnMdia);
		
		JButton button_3 = new JButton("DIVIDIR");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formula = "quotient";
				exibirCampos(false, "QUOCIENTE");
			}
		});
		button_3.setBounds(0, 99, 110, 20);
		opcoesDeFormula.add(button_3);
		
		JButton btnMximo = new JButton("MÁXIMO");
		btnMximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formula = "max";
				exibirCampos(false, btnMximo.getText());
			}
		});
		btnMximo.setBounds(121, 33, 110, 20);
		opcoesDeFormula.add(btnMximo);
		
		JButton btnArredeondar = new JButton("AGORA");
		btnArredeondar.setBounds(0, 132, 110, 20);
		opcoesDeFormula.add(btnArredeondar);
		
		JButton btnMnimo = new JButton("MÍNIMO");
		btnMnimo.setBounds(121, 66, 110, 20);
		opcoesDeFormula.add(btnMnimo);
		
		JButton btnRaizQuadrada = new JButton("SUBTRAIR");
		btnRaizQuadrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formula = "sub";
				exibirCampos(false, btnRaizQuadrada.getText());
			}
		});
		btnRaizQuadrada.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnRaizQuadrada.setBounds(0, 33, 110, 20);
		opcoesDeFormula.add(btnRaizQuadrada);
		
		JButton btnArredondar = new JButton("ARREDONDAR");
		btnArredondar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnArredondar.setBounds(121, 99, 110, 20);
		opcoesDeFormula.add(btnArredondar);
		
		JButton btnRaizQuadrada_1 = new JButton("HOJE");
		btnRaizQuadrada_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnRaizQuadrada_1.setBounds(121, 132, 110, 20);
		opcoesDeFormula.add(btnRaizQuadrada_1);
		
		JButton button_2 = new JButton("SOMAR");
		button_2.setBounds(0, 0, 110, 20);
		opcoesDeFormula.add(button_2);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formula = "sum";
				exibirCampos(true,"SOMA");
			}
		});

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AssistenteFormulas.class.getResource("/ufrj/lipe/librasoffice/sinais/ALINHAR_BAIXO.gif")));
		lblNewLabel.setBounds(255, 13, 190, 190);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(255, 213, 190, 28);
		getContentPane().add(lblNewLabel_1);
	}
}
