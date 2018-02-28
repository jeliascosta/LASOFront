package ufrj.lipe.librasoffice.librasgui;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import ufrj.lipe.librasoffice.ControladorUNO;

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
		
		btnAssistente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.err.println("CLICOU NO BOTÃO");
				if (!AssistenteFormulas.aberto) {
					AssistenteFormulas AF = new AssistenteFormulas();
					AF.setVisible(true);
				}
				janelaPrincipal.setVisible(false);
			}
			public void mouseExited(MouseEvent e) {
				System.err.println("SAIU DO BOTÃO");
				escondeJanela();
			}
		});
		
		janelaPrincipal.getContentPane().add(btnAssistente);
	}

	public void setAssistente(boolean set) {
		assistenciado = set;
	}

}
