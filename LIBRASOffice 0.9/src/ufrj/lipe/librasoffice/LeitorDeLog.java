package ufrj.lipe.librasoffice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.ImageIcon;

import ufrj.lipe.librasoffice.librasgui.AjudaEmLIBRAS;
import ufrj.lipe.librasoffice.librasgui.SinalIndisponivel;
//import ufrj.lipe.librasoffice.toDo.CalcAdapter;
//import ufrj.lipe.librasoffice.toDo.FormulaHelperGUI;

/**
 * Classe LeitorDeLog Monitora o log de saída gerado pelo Iniciador
 */

public class LeitorDeLog implements Runnable {

	// Atributos

	/** The abriu. */
	private boolean abriu = false;

	/** The comando. */
	private String comando = "";
	private AjudaEmLIBRAS janelaLIBRAS;
	private SinalIndisponivel janelaIndisp;
	// private FormulaHelperGUI fgui = null;
	private AvaliadorSemantico aval;
	FileReader lasoLog;

	// Construtores e Destrutores

	public LeitorDeLog(FileReader fr) {
		janelaLIBRAS = Iniciador.janelaLIBRAS;
		janelaIndisp = Iniciador.janelaIndisp;
		aval = new AvaliadorSemantico();
		this.lasoLog = fr;
	}

	// Métodos

	// Métodos de Acesso

	/**
	 * Get the value of abriu
	 * 
	 * @return the value of abriu
	 */
	public boolean getOpen() {
		return abriu;
	}

	/**
	 * Get the value of comando
	 * 
	 * @return the value of comando
	 */
	public String getCommand() {
		return comando;
	}

	// Other methods

	@Override
	public void run() {
		try {
			abriu = true;

			BufferedReader br = new BufferedReader(lasoLog);
			while (true) {
				String line = br.readLine();
				if (line == null)
					Thread.sleep(300);
				else {
					System.out.println(line);
					comando = aval.avaliar(line);
					System.err.println(line.isEmpty());
					System.out.println(comando);
					if (comando != "SUMA") {
						janelaIndisp.getJanelaPrincipal().setVisible(false);
						janelaLIBRAS.getJanelaPrincipal().setVisible(true);
						janelaLIBRAS.getLegenda().setText(line);
						janelaLIBRAS.setAssistente(false);
						if (comando == "OPCAO_240")
							janelaLIBRAS.setAssistente(true);
						try { janelaLIBRAS.getGIF().setIcon(new ImageIcon(AjudaEmLIBRAS.class
									.getResource("/ufrj/lipe/librasoffice/sinais/" + comando + ".gif"))); }
						catch (Exception e) { e.printStackTrace(); }
					} else {
						janelaLIBRAS.getJanelaPrincipal().setVisible(false);
						janelaLIBRAS.getGIF().setIcon(null);
						if (!line.isEmpty()) {
							janelaIndisp.getJanelaPrincipal().setVisible(true);
							janelaIndisp.getLegenda().setText(line);
						}
						else janelaIndisp.getJanelaPrincipal().setVisible(false);
					}
				}
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}