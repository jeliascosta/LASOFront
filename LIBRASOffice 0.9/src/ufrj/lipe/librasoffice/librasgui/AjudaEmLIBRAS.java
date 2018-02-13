package ufrj.lipe.librasoffice.librasgui;

import java.awt.Component;

import javax.swing.JButton;

public class AjudaEmLIBRAS extends JanelaFlutuanteLIBRAS {

	/**
	 * Create the application.
	 */
	public AjudaEmLIBRAS() {
		setUILook();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		initSuper();

		gifLIBRAS.setBounds(0, 0, 220, 190);
		
		btnAssistente = new JButton("ASSISTENTE");
		btnAssistente.setBounds(0, 230, 220, 30);
		btnAssistente.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAssistente.setIcon(null);
		
		janelaPrincipal.getContentPane().add(btnAssistente);
	}

	public void setAssistente(boolean set) {
		assistenciado = set;
	}

}
