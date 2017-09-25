package ufrj.lipe.librasoffice;

import java.io.FileNotFoundException;
import java.io.FileReader;

import ufrj.lipe.librasoffice.librasgui.AjudaEmLIBRAS;
// 
public class LASOFront {  
    //static Object LODesktop = null;   // Classe necessária para conectar o LibrasOffice no LibreOffice
    //static XDispatchHelper xDHelper = null; //""
	static AjudaEmLIBRAS janelaLIBRAS;  //AjudaEmLIBRAS é a classe onde estão os gifs de libras
    
	private static FileReader getLog(){  /*Para abrir o arquivo Laso.log, é criada a variável fr,
	que se o arquivo for aberto corretamente será retornado quando for impressa essa variável*/
		FileReader fr = null;
		//Verificando se arquivo de log existe ....
		try { fr = new FileReader("C:\\ProgramData\\LASO.log"); }
		catch (FileNotFoundException e) { e.printStackTrace(); }
		return fr;
	}
	
    public static void main(String[] args) {   /*Caso o arquivo não tenha sido aberto
    é impressa uma mensagem de erro ao executar.*/
    	FileReader fr = getLog();
        if (fr == null) {
        	System.err.println("LASO.log não foi encontrado!\nSaindo ...."); 
        	System.exit(1);
        }

		System.err.println("LASO.log foi encontrado e aberto!"); /*Caso o arquivo tenha sido aberto,
		é impressa a mensagem de que foi encontrado e aberto.*/
		janelaLIBRAS = new AjudaEmLIBRAS();  //Um novo objeto "AjudaEmLIBRAS" é armazenado 
		//Thread tAA = new Thread(janelaLIBRAS);
		//tAA.start();
		
		LeitorDeLog cLog = new LeitorDeLog(fr); 
		Thread tCL = new Thread(cLog);
		tCL.start();
		
		//JanelaLIBRAS.getFrame().setVisible(true);
    }
}