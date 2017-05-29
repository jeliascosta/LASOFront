package ufrj.lipe.librasoffice;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.sun.star.frame.XDispatchHelper;

import ufrj.lipe.librasoffice.librasgui.AjudaAnimada;

public class LASOFront {
    static Object LODesktop = null;
    static XDispatchHelper xDHelper = null;
	static AjudaAnimada janelaLIBRAS;
    
	private static FileReader getLog(){
		FileReader fr = null;
		//Verificando se arquivo de log existe ....
		try { fr = new FileReader("C:\\ProgramData\\LASO.log"); }
		catch (FileNotFoundException e) { e.printStackTrace(); }
		return fr;
	}
	
    public static void main(String[] args) {
    	FileReader fr = getLog();
        if (fr == null) {
        	System.err.println("LASO.log não foi encontrado!\nSaindo ...."); 
        	System.exit(1);
        }

		System.err.println("LASO.log foi encontrado e aberto!");

		janelaLIBRAS = new AjudaAnimada();
		Thread tAA = new Thread(janelaLIBRAS);
		tAA.start();
		
		ChecaLog cLog = new ChecaLog(fr);
		Thread tCL = new Thread(cLog);
		tCL.start();
		
		//JanelaLIBRAS.getFrame().setVisible(true);
    }
}