/*
 * 
 */
package ufrj.lipe.librasoffice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ufrj.lipe.librasoffice.librasgui.SinalIndisponivel;
import ufrj.lipe.librasoffice.librasgui.TooltipEmLIBRAS;

// 
public class Iniciador {
	private static String LASO_TMP_PATH="C:\\ProgramData\\";

	private static void setTmpPath() {
		String soAtual = System.getProperty("os.name").toLowerCase();
		System.err.println(soAtual);
		if (soAtual.equals("linux") || soAtual.equals("unix"))
			LASO_TMP_PATH = "/tmp/";
	}
	
	public static String getTmpPath(){
		return LASO_TMP_PATH;
	}
	
	/** TooltipEmLIBRAS é a classe onde estão os gifs de libras. */
	public static TooltipEmLIBRAS janelaLIBRAS;
	public static SinalIndisponivel janelaIndisp;
	public static InterpretadorDeLog cLog;

	/**
	 * Gets the log.
	 *
	 * @return the log
	 * @throws FileNotFoundException 
	 * @throws UnsupportedEncodingException 
	 */
	private static InputStreamReader findLog() {
		String LASO_LOG_PATH = LASO_TMP_PATH+"LASO.log";
		System.err.println(LASO_LOG_PATH);
		/**
		 * Para abrir o arquivo Laso.log, é criada a variável fr, que se o arquivo for
		 * aberto corretamente será retornado quando for impressa essa variável.
		 */
		InputStreamReader logReader = null;
		// Verificando se arquivo de log existe ....
		try {
			logReader = new InputStreamReader(new FileInputStream(LASO_LOG_PATH), "UTF8");
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			// TODO Auto-generated catch block
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
		setTmpPath();
		// Caso o arquivo não tenha sido aberto é impressa uma mensagem de erro ao executar.
		InputStreamReader logReader = findLog();
		
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
		
		// Um novo objeto "TooltipEmLIBRAS" é instanciado.
		janelaLIBRAS = new TooltipEmLIBRAS();
		janelaIndisp = new SinalIndisponivel();

		cLog = new InterpretadorDeLog(logReader);
		Thread tCL = new Thread(cLog);
		tCL.start();		
	}
}