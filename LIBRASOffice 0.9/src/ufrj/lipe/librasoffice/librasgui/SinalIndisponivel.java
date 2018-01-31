package ufrj.lipe.librasoffice.librasgui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SinalIndisponivel {

	private JFrame janelaPrincipal;
	private JLabel legendaPortugues;


	/**
	 * Launch the application.
	 */
	/*
	 * public void run() { try { AjudaEmLIBRAS window = new AjudaEmLIBRAS();
	 * window.frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); }
	 * }
	 */

	/**
	 * Create the application.
	 */
	public SinalIndisponivel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initialize();
	}

	private void labelPropertyChange(java.beans.PropertyChangeEvent evt) {
			Point mouse = MouseInfo.getPointerInfo().getLocation();
			Rectangle dim = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
			int minLeft = 0, maxRight, minTop = 0, maxBottom;


			maxRight = (int) (dim.getWidth() - janelaPrincipal.getWidth());
			maxBottom = (int) (dim.getHeight() - janelaPrincipal.getHeight());
			int X = (int) mouse.getX() - janelaPrincipal.getWidth() / 2;
			int Y = (int) mouse.getY() + 20;

			if (X < minLeft)
				X = minLeft;
			else if (X > maxRight)
				X = maxRight;
			if (Y < minTop)
				Y = minTop + 20;
			else if (Y > maxBottom)
				Y = maxBottom + 20;
			
			janelaPrincipal.setLocation(X, Y);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		janelaPrincipal = new JFrame();
		janelaPrincipal.setBounds(0, 0, 251, 150);
		// frame.setMaximumSize(new Dimension(270, 240));
		janelaPrincipal.setPreferredSize(new Dimension(0, 0));
		// frame.getContentPane().setMaximumSize(new Dimension(270, 240));
		// frame.getContentPane().setPreferredSize(new Dimension(0, 0));
		janelaPrincipal.setResizable(false);
		janelaPrincipal.setUndecorated(true);
		janelaPrincipal.setAlwaysOnTop(true);
		janelaPrincipal.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(SinalIndisponivel.class.getResource("/javax/swing/plaf/metal/icons/Question.gif")));
		janelaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janelaPrincipal.getContentPane().setLayout(null);

				legendaPortugues = new JLabel("Legenda");
				legendaPortugues.addPropertyChangeListener(new PropertyChangeListener() {
					public void propertyChange(PropertyChangeEvent evt) {
						labelPropertyChange(evt);
					}
				});
				legendaPortugues.setAlignmentX(Component.CENTER_ALIGNMENT);
				legendaPortugues.setBounds(10, 13, 220, 40);
				janelaPrincipal.getContentPane().add(legendaPortugues);
				// legendaPortugues.setAlignmentX(JLabel.CENTER_ALIGNMENT);
				legendaPortugues.setHorizontalAlignment(SwingConstants.CENTER);
				
				JLabel lblNewLabel = new JLabel("NÃO TEMOS SINAL PARA ESTA OPÇÃO!");
				lblNewLabel.setBounds(10, 65, 228, 16);
				janelaPrincipal.getContentPane().add(lblNewLabel);
				
				JButton btnEnvieParaNs = new JButton("ENVIE SINAL PARA NÓS!");
				btnEnvieParaNs.setBounds(36, 108, 173, 25);
				janelaPrincipal.getContentPane().add(btnEnvieParaNs);
		// label.setMaximumSize(new Dimension(270, 240));
		// label.setPreferredSize(new Dimension(270, 240));
	}

	public JFrame getJanelaPrincipal() {
		return janelaPrincipal;
	}

	public JLabel getLegenda() {
		return legendaPortugues;
	}
}
