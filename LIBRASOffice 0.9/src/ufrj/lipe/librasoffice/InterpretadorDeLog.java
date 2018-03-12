package ufrj.lipe.librasoffice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.ImageIcon;

import ufrj.lipe.librasoffice.librasgui.TiposWidget;
import ufrj.lipe.librasoffice.librasgui.TooltipEmLIBRAS;

/**
 * Classe InterpretadorDeLog Monitora o log de saída gerado pelo Iniciador
 */

public class InterpretadorDeLog implements Runnable {

	// Atributos

	/** The abriu. */
	private boolean aberto = false;

	/** The comando. */
	private String comandoLIBRAS;
	private AvaliadorSemantico aval;
	private InputStreamReader lasoBackLog;


	// Construtores e Destrutores
	public InterpretadorDeLog(InputStreamReader logReader) {
		aval = new AvaliadorSemantico();
		this.lasoBackLog = logReader;
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
		return comandoLIBRAS;
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
					comandoLIBRAS = aval.avaliar(line);
					System.err.println("LINHA VAZIA? "+line.isEmpty());
					System.err.println("COMANDO RETORNADO: "+comandoLIBRAS);
					if (comandoLIBRAS.equals("NULO")) {}
					else if (!comandoLIBRAS.equals("SUMIR")) {
						Iniciador.janelaIndisp.getJanelaPrincipal().setVisible(false);
						
						Iniciador.janelaLIBRAS.getJanelaPrincipal().setVisible(true);
						Iniciador.janelaLIBRAS.setAssistente(false);
						
						if (comandoLIBRAS.equals("ASSIST_FORMULA")) Iniciador.janelaLIBRAS.setAssistente(true);
						try { Iniciador.janelaLIBRAS.getGIF().setIcon(new ImageIcon(TooltipEmLIBRAS.class
									.getResource("/ufrj/lipe/librasoffice/sinais/" + comandoLIBRAS + ".gif"))); }
						catch (Exception e) { e.printStackTrace(); }
						
						Iniciador.janelaLIBRAS.getLegenda().setText(aval.getComando());
					} else {
						Iniciador.janelaLIBRAS.getJanelaPrincipal().setVisible(false);
						Iniciador.janelaLIBRAS.getGIF().setIcon(null);
						
						if (comandoLIBRAS.equals("SUMIR")) {
							Iniciador.janelaIndisp.getJanelaPrincipal().setVisible(true);
							Iniciador.janelaIndisp.getLegenda().setText(aval.getComando());
						}						
						else Iniciador.janelaIndisp.getJanelaPrincipal().setVisible(false);
					}
				}
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}