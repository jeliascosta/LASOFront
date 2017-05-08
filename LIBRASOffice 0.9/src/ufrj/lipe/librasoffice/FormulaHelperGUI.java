package ufrj.lipe.librasoffice;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormulaHelperGUI {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the application.
	 */
	public FormulaHelperGUI() {
	       try {
			UIManager.setLookAndFeel(
			           UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 407, 177);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Célula Inicial");
		lblNewLabel.setBounds(32, 11, 102, 19);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Célula Final");
		lblNewLabel_1.setBounds(144, 11, 93, 19);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(29, 39, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(136, 39, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(2);
		
		JButton btnNewButton = new JButton("Soma");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalcAdapter.soma(textField.getText(), textField_1.getText(), textField_2.getText());
			}
		});
		btnNewButton.setBounds(10, 80, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Multiplicação");
		btnNewButton_1.setBounds(120, 80, 102, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblClulaResultado = new JLabel("Célula Resultado");
		lblClulaResultado.setBounds(266, 11, 93, 19);
		frame.getContentPane().add(lblClulaResultado);
		
		textField_2 = new JTextField();
		textField_2.setColumns(2);
		textField_2.setBounds(258, 39, 86, 20);
		frame.getContentPane().add(textField_2);
	}

	public JFrame getFrame() {
		// TODO Auto-generated method stub
		return frame;
	}
}
