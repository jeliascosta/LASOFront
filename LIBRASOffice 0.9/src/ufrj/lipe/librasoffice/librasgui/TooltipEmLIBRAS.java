package ufrj.lipe.librasoffice.librasgui;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class TooltipEmLIBRAS extends JanelaFlutuanteLIBRAS {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the application.
	 */
	public TooltipEmLIBRAS() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		initSuper();

		gifLIBRAS.setBounds(0, 0, 220, 190);
		
		btnAssistente = new JButton("ASSISTÊNCIA LIBRAS");
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
					AF.toFront();
				}
				setVisible(false);
			}
			public void mouseExited(MouseEvent e) {
				System.err.println("SAIU DO BOTÃO");
				escondeJanela();
			}
		});
		
		getContentPane().add(btnAssistente);
	}

	public void setAssistente(boolean set) {
		assistenciado = set;
	}

}
