/*
 * 
 */
package ufrj.lipe.librasoffice;

import java.io.FileNotFoundException;
import java.io.FileReader;

import ufrj.lipe.librasoffice.librasgui.AjudaEmLIBRAS;

// 
public class Iniciador {
	// Objeto necessário para conectar o LibrasOffice no LibreOffice
	// static Object LODesktop = null;
	// static XDispatchHelper xDHelper = null;

	/** AjudaEmLIBRAS é a classe onde estão os gifs de libras. */
	static AjudaEmLIBRAS janelaLIBRAS;

	/**
	 * Gets the log.
	 *
	 * @return the log
	 */
	private static FileReader getLog() {
		String LASO_LOG_PATH = "C:\\ProgramData\\LASO.log";
		String soAtual = System.getProperty("os.name").toLowerCase();
		System.err.println(soAtual);
		if (soAtual.equals("linux") || soAtual.equals("unix"))
			LASO_LOG_PATH = "/tmp/LASO.log";
			
		/**
		 * Para abrir o arquivo Laso.log, é criada a variável fr, que se o arquivo for
		 * aberto corretamente será retornado quando for impressa essa variável.
		 */
		FileReader fr = null;
		// Verificando se arquivo de log existe ....
		try {
			fr = new FileReader(LASO_LOG_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return fr;
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		// Caso o arquivo não tenha sido aberto é impressa uma mensagem de erro ao executar.
		FileReader fr = getLog();
		if (fr == null) {
			System.err.println("LASO.log não foi encontrado!\nSaindo ....");
			System.exit(1);
		}
		
		// Caso o arquivo tenha sido aberto, é impressa a mensagem de que foi encontrado e aberto.
		System.err.println("LASO.log foi encontrado e aberto!");
		
		// Um novo objeto "AjudaEmLIBRAS" é armazenado.
		janelaLIBRAS = new AjudaEmLIBRAS();
		
		LeitorDeLog cLog = new LeitorDeLog(fr);
		Thread tCL = new Thread(cLog);
		tCL.start();
	}
}