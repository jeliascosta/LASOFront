package ufrj.lipe.librasoffice;

// 

/**
 * A classe AvaliadorSemantico relaciona os nomes do comandos ao comandos em si
 * no menu do programa.
 */
public class AvaliadorSemantico {
	private String comando;
	private String texto;

	public String avaliar(String str) {
		comando = "SUMA";
		this.texto = str.trim().toLowerCase();
		System.out.println(texto.contains("linha"));
		if (texto.contains("abrir")) {
			comando = "ABRIR";
		} else if (texto.contains("caixa") && texto.contains("texto")) {
			comando = "CAIXA_DE_TEXTO";
		} else if (texto.contains("colar")) {
			comando = "COLAR";
		} else if (texto.contains("copiar")) {
			comando = "COPIAR";
		} else if (texto.contains("cortar")) {
			comando = "CORTAR";
		} else if (texto.contains("data")) {
			comando = "DATA_240";
		} else if (texto.contains("desfazer") || texto.contains("voltar")) {
			comando = "DESFAZER";
		} else if (texto.contains("moeda")) {
			comando = "DINHEIRO_240";
		} else if (texto.contains("formas") && texto.contains("simples")) {
			comando = "FORMAS_SIMPLES";
		} else if (texto.contains("grafico")) {
			comando = "GRAFICO";
		} else if (texto.contains("salvar") || texto.contains("guardar")) {
			comando = "GUARDAR";
		} else if (texto.contains("imprimir")) {
			comando = "IMPRIMIR";
		} else if (texto.contains("inserir") && texto.contains("tabela")) {
			comando = "INSERIR_TABELA";
		} else if (texto.contains("localizar")) {
			comando = "LOCALIZAR";
		} else if (texto.contains("novo")) {
			comando = "NOVO";
		} else if (texto.contains("número")) {
			comando = "NUMERO_240";
		}
		else if(texto.contains("funcoes") || texto.contains("funç")){
			comando = "OPCAO_240";			
		}
		else if (texto.contains("pdf")) {
			comando = "PDF";
		} else if (texto.contains("porcent")) {
			comando = "PORCENTAGEM_240";
		} else if (texto.contains("procurar")) {
			comando = "PROCURAR_240";
		} else if (texto.contains("refazer")) {
			comando = "REFAZER";
		} else if (texto.contains("tamanho") && texto.contains("fonte")) {
			comando = "TAMANHO_DA_FONTE";
		} else if (texto.contains("visualizar") && texto.contains("impressão")) {
			comando = "VISUALIZAR_IMPRESSAO";
		}
		return comando;
	}
}
