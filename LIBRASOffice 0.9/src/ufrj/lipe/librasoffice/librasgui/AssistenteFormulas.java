package ufrj.lipe.librasoffice.librasgui;

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

import com.sun.star.lang.IndexOutOfBoundsException;

import ufrj.lipe.librasoffice.ControladorUNO;

public class AssistenteFormulas extends JFrame {
	
	public static boolean aberto = false;
	private JTextField initialCell;
	private JTextField finalCell;
	private JTextField resultCell;
	private String formula = ""; 
	JPanel opcoesDeFormula;
	JPanel camposDaFormula;
	
	private void TrocaPaineis() {
		opcoesDeFormula.setVisible(false);
		camposDaFormula.setVisible(true);
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
		setBounds(100, 100, 421, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);		

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\KWNSound\\Documents\\GitHub\\LASOFront\\LIBRASOffice GIFs\\src\\ufrj\\lipe\\librasoffice\\sinais\\ABRIR.gif"));
		lblNewLabel.setBounds(210, 13, 179, 210);
		getContentPane().add(lblNewLabel);
		
		opcoesDeFormula = new JPanel();
		opcoesDeFormula.setLayout(null);
		opcoesDeFormula.setBounds(12, 13, 186, 229);
		getContentPane().add(opcoesDeFormula);
		
		camposDaFormula = new JPanel();
		camposDaFormula.setVisible(false);
		camposDaFormula.setBounds(12, 13, 186, 229);
		getContentPane().add(camposDaFormula);
		camposDaFormula.setLayout(null);
		
		JCheckBox isCellRange = new JCheckBox("Faixa de células");
		isCellRange.setBounds(60, 142, 118, 25);
		camposDaFormula.add(isCellRange);
		
		JButton button = new JButton("MULTIPLICAR");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formula = "product";
				TrocaPaineis();
			}
		});
		button.setBounds(12, 86, 132, 25);
		opcoesDeFormula.add(button);
		
		JButton button_1 = new JButton("SUBTRAIR");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formula = "sub";
				TrocaPaineis();
			}
		});
		button_1.setBounds(12, 48, 132, 25);
		opcoesDeFormula.add(button_1);
		
		JButton button_2 = new JButton("SOMAR");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formula = "sum";
				TrocaPaineis();
			}
		});
		button_2.setBounds(12, 13, 132, 25);
		opcoesDeFormula.add(button_2);
		
		JButton button_3 = new JButton("DIVIDIR");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formula = "quotient";
				isCellRange.setEnabled(false);
				TrocaPaineis();
			}
		});
		button_3.setBounds(12, 124, 132, 25);
		opcoesDeFormula.add(button_3);
		
		initialCell = new JTextField();
		initialCell.setBounds(130, 35, 44, 22);
		camposDaFormula.add(initialCell);
		initialCell.setColumns(2);
		
		JLabel lblClulaInicial = new JLabel("Célula inicial:");
		lblClulaInicial.setBounds(12, 38, 86, 16);
		camposDaFormula.add(lblClulaInicial);
		
		JLabel lblClulaFinal = new JLabel("Célula final:");
		lblClulaFinal.setBounds(12, 67, 78, 16);
		camposDaFormula.add(lblClulaFinal);
		
		finalCell = new JTextField();
		finalCell.setColumns(2);
		finalCell.setBounds(130, 64, 44, 22);
		camposDaFormula.add(finalCell);
		
		JLabel lblClulaResultado = new JLabel("Célula de resultado:");
		lblClulaResultado.setBounds(12, 97, 118, 16);
		camposDaFormula.add(lblClulaResultado);
		
		resultCell = new JTextField();
		resultCell.setColumns(2);
		resultCell.setBounds(130, 94, 44, 22);
		camposDaFormula.add(resultCell);		
		
		JButton btnVoltar = new JButton("INSERIR FÓRMULA");
		btnVoltar.setBounds(24, 191, 150, 25);
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
	}
}
