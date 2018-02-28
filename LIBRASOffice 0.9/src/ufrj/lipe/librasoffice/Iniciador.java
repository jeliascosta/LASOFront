/*
 * 
 */
package ufrj.lipe.librasoffice;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ufrj.lipe.librasoffice.librasgui.AjudaEmLIBRAS;
import ufrj.lipe.librasoffice.librasgui.SinalIndisponivel;

// 
public class Iniciador {
	// Objeto necessário para conectar o LibrasOffice no LibreOffice
	// static Object LODesktop = null;
	// static XDispatchHelper xDHelper = null;

	/** AjudaEmLIBRAS é a classe onde estão os gifs de libras. */
	public static AjudaEmLIBRAS janelaLIBRAS;
	public static SinalIndisponivel janelaIndisp;
	public static InterpretadorDeLog cLog;

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
		FileReader logReader = null;
		// Verificando se arquivo de log existe ....
		try {
			logReader = new FileReader(LASO_LOG_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return logReader;
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		// Caso o arquivo não tenha sido aberto é impressa uma mensagem de erro ao executar.
		FileReader logReader = getLog();
		
		if (logReader == null) {
			System.err.println("LASO.log não foi encontrado!\nSaindo ....");
			System.exit(1);
		}
		// Caso o arquivo tenha sido aberto, é impressa a mensagem de que foi encontrado e aberto.
		System.err.println("LASO.log foi encontrado e aberto!");

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		// Um novo objeto "AjudaEmLIBRAS" é instanciado.
		janelaLIBRAS = new AjudaEmLIBRAS();
		janelaIndisp = new SinalIndisponivel();

		cLog = new InterpretadorDeLog(logReader);
		Thread tCL = new Thread(cLog);
		tCL.start();
		
	}
}