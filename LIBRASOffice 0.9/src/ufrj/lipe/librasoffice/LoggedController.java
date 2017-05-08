package ufrj.lipe.librasoffice;

import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;

import ufrj.lipe.librasoffice.librastooltip.AnimatedTooltip;

/**
 * Class LoggedController
 */
public class LoggedController implements Runnable {

  // Fields

  private boolean open = false;
  private boolean found = true;
  private String command = "";
  private AnimatedTooltip atip = null;
  private FormulaHelperGUI fgui = null;
  
  // Constructors
  
  public LoggedController () {
	  atip = SetEnv.LIBRASHelp;
  };
  
  // Methods

  
  // Accessor methods

  /**
   * Get the value of open
   * @return the value of open
   */
  public boolean getOpen () {
    return open;
  }

  /**
   * Get the value of found
   * @return the value of found
   */
  public boolean getFound () {
    return found;
  }

  
  /**
   * Get the value of command
   * @return the value of command
   */
  public String getCommand () {
    return command;
  }

  // Other methods
  

@Override
public void run() {
	try {
		//FileReader fr = new FileReader("LIBRASOfficeLOG.txt");
        FileReader fr = new FileReader("C:\\ProgramData\\LASO.log");
        
        open = true;
        
        @SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(fr);
        while (true) {
         String line = br.readLine();
         if (line == null) Thread.sleep(300);
         else {
             System.out.println(line);
             //CalcAdapter.getGUI().getFrame().setVisible(false);
             switch(line.trim()){
                case "Novo (Ctrl+N)":{command = "NOVO";break;}
                case "Colar (Ctrl+V)":{command = "COLA";break;}
                case "Abrir (Ctrl+O)":{command = "ABRIR";break;}
                case "Salvar (Ctrl+S)":{command = "GUARDAR";break;}
                case "Imprimir (Ctrl+P)":{command = "IMPRIMIR";break;}
                case "Cortar (Ctrl+X)":{command = "CORTAR";break;}
                case "Copiar (Ctrl+C)":{command = "COPIAR";break;}
                case "Formatar como data (Ctrl+Shift+3)":{command = "DATA";break;}
                case "Formatar como número (Ctrl+Shift+1)":{command = "NUMERO";break;}
                case "Formatar como porcentagem (Ctrl+Shift+5)":{command = "PORCENTAGEM";break;}           
                case "Formatar como moeda (Ctrl+Shift+4)":{command = "DINHEIRO";break;}
                case "FUNCOES":{command = "FUNCOES";CalcAdapter.getGUI().getFrame().setVisible(true);break;}
                default: command = "SUMA";
             }
             System.out.println(command);
             if (command != "SUMA"){
            	 try{atip.getLabel().setIcon(new ImageIcon(AnimatedTooltip.class.getResource("/ufrj/lipe/librasoffice/gif240/"+command+"_240.gif")));}
            	 catch (Exception e){e.printStackTrace();};
             }
             else {
            	 atip.getLabel().setIcon(null);
             }
         }
        }
    }
	catch (IOException | InterruptedException e){
		e.printStackTrace();
		found = false;
	}
   /* catch (Exception e){
        System.err.println(e.toString());
    } */
  }
	
}