package ufrj.lipe.librasoffice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.ImageIcon;

import ufrj.lipe.librasoffice.librasgui.AjudaAnimada;

/**
 * Classe ChecaLog
 * Monitora o log de saída gerado pelo LASOFront
 */
public class ChecaLog implements Runnable {

  // Atributos

  private boolean abriu = false;
  private String comando = "";
  private AjudaAnimada janelaLIBRAS;
  private FormulaHelperGUI fgui = null;
  private AvaliadorSemantico aval;
  FileReader lasoLog;

  // Construtores e Destrutores
  
  public ChecaLog (FileReader fr) {
	  janelaLIBRAS = LASOFront.janelaLIBRAS;
	  aval = new AvaliadorSemantico(); 
	  this.lasoLog = fr;
  }
  
  // Métodos

  
  // Métodos de Acesso

  /**
   * Get the value of abriu
   * @return the value of abriu
   */
  public boolean getOpen () {
    return abriu;
  }

  /**
   * Get the value of comando
   * @return the value of comando
   */
  public String getCommand () {
    return comando;
  }

  /**
   * @return the fgui
   */
  public FormulaHelperGUI getFgui() {
  	return fgui;
  }

  /**
   * @param fgui the fgui to set
   */
  public void setFgui(FormulaHelperGUI fgui) {
  	this.fgui = fgui;
  }
  
  // Other methods
  
@Override
public void run() {
	try {
        abriu = true;
        
        BufferedReader br = new BufferedReader(lasoLog);
        while (true) {
         String line = br.readLine();
         if (line == null) Thread.sleep(300);
         else {
             System.out.println(line);
             //CalcAdapter.getGUI().getFrame().setVisible(false);
             comando = aval.avaliar(line);
             System.out.println(comando);
             if (comando != "SUMA"){
            	 try {
            		 	janelaLIBRAS.getLabel().setIcon(
            		     new ImageIcon(AjudaAnimada.class.getResource("/ufrj/lipe/librasoffice/gif240/"+comando+"_240.gif"))
            		    );
            		 }
            	 catch (Exception e){e.printStackTrace();};
             }
             else {
            	 janelaLIBRAS.getLabel().setIcon(null);
            	 janelaLIBRAS.getFrame().setVisible(false);
             }
         }
        }
    }
	catch (IOException | InterruptedException e){
		e.printStackTrace();
	}
  }
}