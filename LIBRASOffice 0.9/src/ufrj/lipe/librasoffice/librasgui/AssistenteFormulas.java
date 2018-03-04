package ufrj.lipe.librasoffice.librasgui;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
	JTextPane txtStartFormula;
	JTextPane txtEndFormula;
	JTextPane txtRange;
	boolean noArgs = false;
	
	private void exibirCampos(boolean enableRange, String formulaLiteral) {
		opcoesDeFormula.setVisible(false);
		camposDaFormula.setVisible(true);
		initialCell.setEnabled(enableRange);
		finalCell.setEnabled(enableRange);
		isCellRange.setEnabled(enableRange);
		if (noArgs) {
			txtRange.setText("");
		}
		txtStartFormula.setText("="+formulaLiteral+"(");
		txtEndFormula.setText(")");
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
		setBounds(100, 100, 485, 282);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);		

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AssistenteFormulas.class.getResource("/ufrj/lipe/librasoffice/sinais/ALINHAR_BAIXO.gif")));
		lblNewLabel.setBounds(277, 13, 190, 190);
		getContentPane().add(lblNewLabel);
		
		opcoesDeFormula = new JPanel();
		opcoesDeFormula.setLayout(null);
		opcoesDeFormula.setBounds(12, 13, 231, 229);
		getContentPane().add(opcoesDeFormula);
		
		JTextPane lblLegendaFormula = new JTextPane();
		lblLegendaFormula.setText("Bem-vindo ao assistente de fórmulas do LIBRASOffice");

		
		JButton button = new JButton("MULTIPLICAR");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formula = "product";
				exibirCampos(true, "MULT");
				lblLegendaFormula.setText("Multiplica as células.");
			}
		});
		button.setBounds(-1, 62, 110, 20);
		opcoesDeFormula.add(button);
		
		JButton btnMdia = new JButton("MÉDIA");
		btnMdia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formula = "average";
				exibirCampos(true, btnMdia.getText());
				lblLegendaFormula.setText("Calcula a média das células.");
			}
		});
		btnMdia.setBounds(121, 0, 110, 20);
		opcoesDeFormula.add(btnMdia);
		
		JButton btnMximo = new JButton("MÁXIMO");
		btnMximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formula = "max";
				exibirCampos(true, btnMximo.getText());
				lblLegendaFormula.setText("Calcula o maior número entre as células.");
			}
		});
		btnMximo.setBounds(-1, 133, 110, 20);
		opcoesDeFormula.add(btnMximo);
		
		JButton btnArredeondar = new JButton("AGORA");
		btnArredeondar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				formula = "now";
				noArgs=true;
				exibirCampos(false,btnArredeondar.getText());
				lblLegendaFormula.setText("Escreve a data e hora do momento.");
			}
		});
		btnArredeondar.setBounds(-1, 196, 110, 20);
		opcoesDeFormula.add(btnArredeondar);
		
		JButton btnMnimo = new JButton("MÍNIMO");
		btnMnimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formula = "min";
				exibirCampos(true, btnMnimo.getText());
				lblLegendaFormula.setText("Calcula o menor número entre as células.");
			}
		});
		btnMnimo.setBounds(121, 133, 110, 20);
		opcoesDeFormula.add(btnMnimo);
		
		JButton btnArredondar = new JButton("MEDIANA");
		btnArredondar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formula = "median";
				exibirCampos(true, "MED");
				lblLegendaFormula.setText("Calcula a mediana das células.");
			}
		});
		btnArredondar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnArredondar.setBounds(121, 62, 110, 20);
		opcoesDeFormula.add(btnArredondar);
		
		JButton btnHoje = new JButton("HOJE");
		btnHoje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				formula = "today";
				noArgs=true;
				exibirCampos(false,btnHoje.getText());
				lblLegendaFormula.setText("Escreve a data de hoje.");
			}
		});
		btnHoje.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnHoje.setBounds(121, 196, 110, 20);
		opcoesDeFormula.add(btnHoje);
		
		JButton button_2 = new JButton("SOMAR");
		button_2.setBounds(0, 0, 110, 20);
		opcoesDeFormula.add(button_2);
		
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
							resultCell.getText(), formula, isCellRange.isSelected(), noArgs);
				} catch (IndexOutOfBoundsException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		camposDaFormula.add(btnVoltar);
		
		txtStartFormula = new JTextPane();
		txtStartFormula.setEditable(false);
		txtStartFormula.setText("=");
		txtStartFormula.setBounds(34, 151, 88, 27);
		camposDaFormula.add(txtStartFormula);
		
		JLabel lblFrmulaMontada = new JLabel("Fórmula montada:");
		lblFrmulaMontada.setBounds(24, 132, 106, 16);
		camposDaFormula.add(lblFrmulaMontada);
		
		isCellRange = new JCheckBox("Faixa de células");
		isCellRange.setSelected(true);
		isCellRange.setBounds(101, 100, 118, 25);
		camposDaFormula.add(isCellRange);
		
		JTextPane txtCell1 = new JTextPane();
		txtCell1.setEditable(false);
		txtCell1.setBounds(126, 151, 26, 27);
		camposDaFormula.add(txtCell1);
		
		initialCell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txtCell1.setText(initialCell.getText());
			}
		});
		
		txtRange = new JTextPane();
		txtRange.setText(";");
		txtRange.setEditable(false);
		txtRange.setBounds(156, 151, 26, 27);
		camposDaFormula.add(txtRange);
		
		isCellRange.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(isCellRange.isSelected()) txtRange.setText(":");
				else txtRange.setText(";");
			}
		});
		
		JTextPane txtCell2 = new JTextPane();
		txtCell2.setEditable(false);
		txtCell2.setBounds(186, 151, 26, 27);
		camposDaFormula.add(txtCell2);
		
		finalCell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txtCell2.setText(finalCell.getText());
			}
		});
		
		txtEndFormula = new JTextPane();
		txtEndFormula.setEditable(false);
		txtEndFormula.setBounds(216, 151, 15, 27);
		camposDaFormula.add(txtEndFormula);
		
		JLabel lblCellResult = new JLabel("");
		lblCellResult.setBounds(5, 155, 25, 16);
		camposDaFormula.add(lblCellResult);
		
		lblLegendaFormula.setEnabled(false);
		lblLegendaFormula.setBounds(277, 206, 202, 36);
		getContentPane().add(lblLegendaFormula);
		
		resultCell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				lblCellResult.setText(resultCell.getText());
			}
		});
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formula = "sum";
				exibirCampos(true,"SOMA");
				lblLegendaFormula.setText("Soma as células.");
			}
		});
	}
}
