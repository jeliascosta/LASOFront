package ufrj.lipe.librasoffice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;

import ufrj.lipe.librasoffice.librasgui.AjudaEmLIBRAS;
import ufrj.lipe.librasoffice.librasgui.SinalIndisponivel;
import ufrj.lipe.librasoffice.librasgui.TiposWidget;

/**
 * Classe InterpretadorDeLog Monitora o log de saída gerado pelo Iniciador
 */

public class InterpretadorDeLog implements Runnable {

	// Atributos

	/** The abriu. */
	private boolean aberto = false;

	/** The comando. */
	private String comando;
	private AjudaEmLIBRAS jnAjudaLIBRAS;
	private SinalIndisponivel jnIndisp;
	private AvaliadorSemantico aval;
	private FileReader lasoBackLog;


	// Construtores e Destrutores
	public InterpretadorDeLog(FileReader fr) {
		jnAjudaLIBRAS = Iniciador.janelaLIBRAS;
		jnIndisp = Iniciador.janelaIndisp;
		aval = new AvaliadorSemantico();
		this.lasoBackLog = fr;
	}

	// Métodos de Acesso

	public TiposWidget getTipoWidget() {
		String tipo_str = aval.getTipoWidget();
		if (tipo_str.equals("menu")) return TiposWidget.MENU;
		if (tipo_str.equals("tooltip")) return TiposWidget.TOOLTIP;
		return TiposWidget.NULO;
	}
	
	/**
	 * Get the value of abriu
	 * 
	 * @return the value of abriu
	 */
	public boolean getOpen() {
		return aberto;
	}

	/**
	 * Get the value of comando
	 * 
	 * @return the value of comando
	 */
	public String getCommand() {
		return comando;
	}

	// Métodos de Interface

	@Override
	public void run() {
		try {
			aberto = true;
			BufferedReader logReader = new BufferedReader(lasoBackLog);
			while (true) {
				String line = logReader.readLine();
				if (line == null) Thread.sleep(300);
				else {
					System.out.println(line);
					comando = aval.avaliar(line);
					System.err.println(line.isEmpty());
					System.out.println(comando);
					if (!comando.equals("SUMIR") && !comando.equals("NULO")) {
						jnIndisp.getJanelaPrincipal().setVisible(false);
						
						jnAjudaLIBRAS.getJanelaPrincipal().setVisible(true);
						jnAjudaLIBRAS.setAssistente(false);
						
						if (comando.equals("OPCAO_240")) jnAjudaLIBRAS.setAssistente(true);
						try { jnAjudaLIBRAS.getGIF().setIcon(new ImageIcon(AjudaEmLIBRAS.class
									.getResource("/ufrj/lipe/librasoffice/sinais/" + comando + ".gif"))); }
						catch (Exception e) { e.printStackTrace(); }
						
						jnAjudaLIBRAS.getLegenda().setText(aval.getComando());
					} else {
						jnAjudaLIBRAS.getJanelaPrincipal().setVisible(false);
						jnAjudaLIBRAS.getGIF().setIcon(null);
						
						if (comando.equals("SUMIR")) {
							jnIndisp.getJanelaPrincipal().setVisible(true);
							jnIndisp.getLegenda().setText(aval.getComando());
						}						
						else jnIndisp.getJanelaPrincipal().setVisible(false);
					}
				}
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}