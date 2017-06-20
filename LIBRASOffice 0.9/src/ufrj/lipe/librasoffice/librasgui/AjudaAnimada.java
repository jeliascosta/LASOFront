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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class AjudaAnimada {

	private JFrame frame;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	/*public void run() {
		try {
			AjudaAnimada window = new AjudaAnimada();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the application.
	 */
	public AjudaAnimada() {
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

    private void labelPropertyChange(java.beans.PropertyChangeEvent evt) {                                       
        Point mouse = MouseInfo.getPointerInfo().getLocation(); 
        Rectangle dim = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        int minLeft=0,maxRight,minTop=0,maxBottom;
        maxRight = (int) (dim.getWidth()-270);
        maxBottom = (int) (dim.getHeight()-240);
        int X = (int)mouse.getX()-135;
        int Y = (int)mouse.getY()+20;
        
        if (X < minLeft) X = minLeft;
        else if (X > maxRight) X = maxRight;
        if (Y < minTop) Y = minTop+20;
        else if (Y > maxBottom) Y = maxBottom+20;
        
        frame.setBounds(0, 0, 270, 240);
        frame.setLocation(X, Y);
        System.err.println(X+" "+Y);
        try{
        	System.out.println(label.getIcon());
        	if(!(label.getIcon().equals(null)))
        		frame.setVisible(true);
        }
        catch (NullPointerException e){
            System.err.println("SOME");
            frame.setVisible(false);
        	System.err.println(e.toString());
        }
    }   
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
        frame.setBounds(0, 0, 270, 240);
		frame.setMaximumSize(new Dimension(270, 240));
        frame.setPreferredSize(new Dimension(0, 0));
        //frame.getContentPane().setMaximumSize(new Dimension(270, 240));
		//frame.getContentPane().setPreferredSize(new Dimension(0, 0));
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.setAlwaysOnTop(true);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(
		    AjudaAnimada.class.getResource("/javax/swing/plaf/metal/icons/Question.gif")
		));		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		label = new JLabel("");
		label.setBounds(0, 0, 270, 240);
		label.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				labelPropertyChange(evt);
			}
		});
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setMaximumSize(new Dimension(270, 240));
		label.setPreferredSize(new Dimension(270, 240));
		label.setToolTipText("");
		frame.getContentPane().add(label);
	}
	
	public JFrame getFrame() { return frame; }
	public JLabel getLabel() { return label; }
}
