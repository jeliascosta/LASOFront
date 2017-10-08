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

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class AjudaEmLIBRAS {

	private JFrame janelaPrincipal;
	private JLabel gifLIBRAS, legendaPortugues;

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
	public AjudaEmLIBRAS() {
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
		if (gifLIBRAS.getIcon() != null) {
			Point mouse = MouseInfo.getPointerInfo().getLocation();
			Rectangle dim = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
			int minLeft = 0, maxRight, minTop = 0, maxBottom;
			int gifWidth = gifLIBRAS.getIcon().getIconWidth();
			int gifHeight = gifLIBRAS.getIcon().getIconHeight();
			int legendHeight = legendaPortugues.getHeight();
			int legendWidth = legendaPortugues.getWidth();
			int optPanelHeight = legendaPortugues.getHeight();
			int optPanelWidth = legendaPortugues.getWidth();
			System.out.println(legendHeight+" - "+legendWidth);
			
			maxRight = (int) (dim.getWidth() - gifWidth);
			maxBottom = (int) (dim.getHeight() - gifHeight);
			int X = (int) mouse.getX() - gifWidth / 2;
			int Y = (int) mouse.getY() + 20;

			if (X < minLeft)
				X = minLeft;
			else if (X > maxRight)
				X = maxRight;
			if (Y < minTop)
				Y = minTop + 20;
			else if (Y > maxBottom)
				Y = maxBottom + 20;

			janelaPrincipal.setBounds(0, 0, gifWidth, gifHeight + legendHeight);
			gifLIBRAS.setBounds(0, 0, gifWidth, gifHeight);
			legendaPortugues.setBounds(0, gifHeight, legendWidth, legendHeight);

			janelaPrincipal.setLocation(X, Y);
			System.err.println(X + " " + Y);

			try {
				System.out.println(gifLIBRAS.getIcon());
				if (!(gifLIBRAS.getIcon().equals(null)))
					janelaPrincipal.setVisible(true);
			} catch (NullPointerException e) {
				System.err.println("SOME");
				janelaPrincipal.setVisible(false);
				System.err.println(e.toString());
			}
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		janelaPrincipal = new JFrame();
		janelaPrincipal.setBounds(0, 0, 220, 450);
		// frame.setMaximumSize(new Dimension(270, 240));
		janelaPrincipal.setPreferredSize(new Dimension(0, 0));
		// frame.getContentPane().setMaximumSize(new Dimension(270, 240));
		// frame.getContentPane().setPreferredSize(new Dimension(0, 0));
		janelaPrincipal.setResizable(false);
		janelaPrincipal.setUndecorated(true);
		janelaPrincipal.setAlwaysOnTop(true);
		janelaPrincipal.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(AjudaEmLIBRAS.class.getResource("/javax/swing/plaf/metal/icons/Question.gif")));
		janelaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janelaPrincipal.getContentPane().setLayout(null);

		gifLIBRAS = new JLabel("");
		gifLIBRAS.setBounds(0, 0, 220, 400);
		gifLIBRAS.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				labelPropertyChange(evt);
			}
		});
		
		JButton btnNB = new JButton("wefewfe");
		btnNB.setBounds(109, 400, 75, 50);
		janelaPrincipal.getContentPane().add(btnNB);
		btnNB.setIcon(null);
		gifLIBRAS.setAlignmentX(Component.CENTER_ALIGNMENT);
		// label.setMaximumSize(new Dimension(270, 240));
		// label.setPreferredSize(new Dimension(270, 240));
		gifLIBRAS.setToolTipText("");
		janelaPrincipal.getContentPane().add(gifLIBRAS);

		legendaPortugues = new JLabel("TESTE");
		// legendaPortugues.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		legendaPortugues.setHorizontalAlignment(SwingConstants.CENTER);

		legendaPortugues.setBounds(0, 400, 113, 50);
		janelaPrincipal.getContentPane().add(legendaPortugues);
	}

	public JFrame getJanelaPrincipal() {
		return janelaPrincipal;
	}

	public JLabel getGIF() {
		return gifLIBRAS;
	}

	public JLabel getLegenda() {
		return legendaPortugues;
	}
}
