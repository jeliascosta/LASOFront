package ufrj.lipe.librasoffice.librasgui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;

import ufrj.lipe.librasoffice.external.ControladorGDrive;

public class SinalIndisponivel extends JanelaFlutuanteLIBRAS {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the application.
	 */
	public SinalIndisponivel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		initSuper();
		
		int width = 250;
		legendaPortugues.setBounds(0, 0, width, 50);
		
		JLabel lblNewLabel = new JLabel("NÃO TEMOS SINAL PARA ESTA OPÇÃO!");
		lblNewLabel.setBounds(10, 65, width, 16);
				
		JButton btnEnvieParaNs = new JButton("ENVIE SINAL PARA NÓS!");
		btnEnvieParaNs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new AssistenteDeEnvio(legendaPortugues.getText());
			}
		});
		btnEnvieParaNs.setBounds(36, 108, 173, 25);
	
		getContentPane().add(btnEnvieParaNs);
		getContentPane().add(lblNewLabel);
		setBounds(0,0,width,140);
	}
}
