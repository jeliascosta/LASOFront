package ufrj.lipe.librasoffice.librasgui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ufrj.lipe.librasoffice.Iniciador;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.BevelBorder;

public abstract class JanelaFlutuanteLIBRAS {
	
	protected JFrame janelaPrincipal;
	protected JLabel gifLIBRAS;
	protected JLabel legendaPortugues;
	protected JButton btnAssistente;
	protected boolean assistenciado = false, gifEntrou = false, gifSaiu=false, frameEntrou=false, frameSaiu=false;
	
	protected void setUILook(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void reposJanela(java.beans.PropertyChangeEvent evt) {
		Point mouse = MouseInfo.getPointerInfo().getLocation();
		int X = (int) mouse.getX() - janelaPrincipal.getWidth() / 2;
		int Y = (int) mouse.getY() + 10;
		Rectangle dim = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		int minLeft = 0, minTop = 0;
		int maxRight = (int) (dim.getWidth() - janelaPrincipal.getWidth());
		int maxBottom = (int) (dim.getHeight() - janelaPrincipal.getHeight());
		int gifWidth = 0, gifHeight = 0;

		System.err.println("TIPO DO WIDGET: "+Iniciador.cLog.getTipoWidget());

		if (gifLIBRAS.getIcon() != null) {
			System.err.println("AJUSTANDO GIF");
			gifWidth = gifLIBRAS.getIcon().getIconWidth();
			gifHeight = gifLIBRAS.getIcon().getIconHeight();
			System.err.println("Largura "+gifWidth+" - Altura "+gifHeight);
			int legendHeight = legendaPortugues.getHeight();
			int enableBtn = (assistenciado)?1:0;
			int btnHeight = btnAssistente.getHeight();
			int showBtn = btnHeight * enableBtn;

			maxRight = (int) (dim.getWidth() - gifWidth);
			maxBottom = (int) (dim.getHeight() - gifHeight);
			X = (int) mouse.getX() - gifWidth / 2;
			
			janelaPrincipal.setBounds(0, 0, gifWidth, gifHeight + legendHeight + showBtn);
			gifLIBRAS.setBounds(0, 0, gifWidth, gifHeight);
			legendaPortugues.setBounds(0, gifHeight, gifWidth, legendHeight);
			btnAssistente.setBounds(0, gifHeight + legendHeight, gifWidth, btnHeight);
		}
		else {
			//janelaPrincipal.setBounds(0, 0, gifWidth, gifHeight + legendHeight + showBtn);
		}
		
		if (X < minLeft)		X = minLeft;
		else if (X > maxRight)	X = maxRight;
		if (Y < minTop)			Y = minTop + 10;
		else if (Y > maxBottom)	Y = maxBottom + 10;
		
		if(Iniciador.cLog.getTipoWidget() == TiposWidget.TOOLTIP) 	janelaPrincipal.setLocation(X, Y);
		else if(Iniciador.cLog.getTipoWidget() == TiposWidget.MENU) janelaPrincipal.setLocation(dim.width-gifWidth, 0);
		else janelaPrincipal.setLocation(0, 0);
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void initSuper() {
		janelaPrincipal = new JFrame();
		janelaPrincipal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				System.err.println("MOUSE ENTROU NO FRAME");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				System.err.println("MOUSE SAIU DO FRAME");
				if (janelaPrincipal.getMousePosition(true) == null)
					janelaPrincipal.setVisible(false);
			}
		});

		janelaPrincipal.setVisible(false);
		janelaPrincipal.setBounds(0, 0, 220, 261);
		janelaPrincipal.setPreferredSize(new Dimension(0, 0));
		janelaPrincipal.setResizable(false);
		janelaPrincipal.setUndecorated(true);
		janelaPrincipal.setAlwaysOnTop(true);
		janelaPrincipal.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(AjudaEmLIBRAS.class.getResource("/javax/swing/plaf/metal/icons/Question.gif")));
		janelaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janelaPrincipal.getContentPane().setLayout(null);
		
		gifLIBRAS = new JLabel("");
		//gifLIBRAS.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		gifLIBRAS.setBounds(0, 0, 0, 0);
		gifLIBRAS.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		legendaPortugues = new JLabel("Legenda");
		legendaPortugues.setBounds(10, 13, 220, 40);		
		legendaPortugues.setAlignmentX(Component.CENTER_ALIGNMENT);
		legendaPortugues.setHorizontalAlignment(SwingConstants.CENTER);
		legendaPortugues.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				reposJanela(evt);
			}
		});	
				
		janelaPrincipal.getContentPane().add(legendaPortugues);
		janelaPrincipal.getContentPane().add(gifLIBRAS);
	}
	
	public Window getJanelaPrincipal() {
		return janelaPrincipal;
	}
	
	public JLabel getLegenda() {
		return legendaPortugues;
	}
	
	public JLabel getGIF() {
		return gifLIBRAS;
	}

}
