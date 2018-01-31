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
		if (texto.contains("abrir")) {
			comando = "ABRIR";
		} else if (texto.contains("adicionar") && texto.contains("casa") && texto.contains("decimal")) {
			comando = "ADICIONAR_CASA_DECIMAL";
		} else if (texto.contains("alinhar") && texto.contains("cima")){
			comando = "ALINHAR_CIMA";
		} else if (texto.contains("alinhar") && texto.contains("baixo")){
			comando = "ALINHAR_BAIXO";
		} else if (texto.contains("anotação")){
			comando = "ANOTACAO";
		} else if (texto.contains("área") && texto.contains("impressão")){
			comando = "AREA_IMPRESSAO";
		} else if (texto.contains("aumentar")){
			comando = "AUMENTAR";
		} else if (texto.contains("borda")){
			comando = "BORDA";
			if (texto.contains("cor"))
				comando = "COR DA BORDA";
		} else if (texto.contains("caixa") && texto.contains("texto")) {
			comando = "CAIXA_DE_TEXTO";
		} else if (texto.contains("centralizar") && texto.contains("horizontal")) {
			comando = "CENTRALIZAR_HORIZONTAL";
		} else if (texto.contains("colar")) {
			comando = "COLAR";
		} else if (texto.contains("congelar") && texto.contains("linhas") && texto.contains("colunas")) {
			comando = "CONGELAR_COLUNAS_LINHAS";
		} else if (texto.contains("copiar")) {
			comando = "COPIAR";
		} else if (texto.contains("cortar")) {
			comando = "CORTAR";
		} else if (texto.contains("da") && texto.contains("direita") && texto.contains("esquerda")) {
			comando = "DA_DIREITA_ESQUERDA";
		} else if (texto.contains("da") && texto.contains("esquerda") && texto.contains("direita")) {
			comando = "DA_ESQUERDA_DIREITA";
		}else if (texto.contains("data")) {
			comando = "DATA";
		} else if (texto.contains("desfazer") || texto.contains("voltar")) {
			comando = "DESFAZER";
		} else if (texto.contains("diminuir")) {
			comando = "DIMINUIR";
		} else if (texto.contains("dinheiro")) {
			comando = "DINHEIRO_240";
		} else if (texto.contains("dividir")) {
			comando = "DIV_240";
		} else if (texto.contains("documento")) {
			comando = "DOCUMENTO";
		} else if (texto.contains("estilo") && texto.contains("borda")) {
			comando = "ESTILO_BORDA";
		} else if (texto.contains("excluir") && texto.contains("casa") && texto.contains("decimal")) {
			comando = "EXCLUIR_CASA_DECIMAL";
		} else if (texto.contains("excluir") && texto.contains("colunas")) {
			comando = "EXCLUIR_COLUNAS";
		} else if (texto.contains("excluir") && texto.contains("linhas")) {
			comando = "EXCLUIR_LINHAS";
		} else if (texto.contains("figura")) {
			comando = "FIGURA";
		} else if (texto.contains("formas") && texto.contains("simples")) {
			comando = "FORMAS_SIMPLES";
		} else if (texto.contains("grafico")) {
			comando = "GRAFICO";
		} else if (texto.contains("salvar") || texto.contains("guardar")) {
			comando = "GUARDAR";
		} else if (texto.contains("imprimir")) {
			comando = "IMPRIMIR";
		} else if (texto.contains("inserir") && texto.contains("coluna") && texto.contains("esquerda")) {
			comando = "INSERIR_COLUNA_ESQUERDA";
		} else if (texto.contains("inserir") && texto.contains("linha") && texto.contains("cima")) {
			comando = "INSERIR_LINHA_ACIMA";
		} else if (texto.contains("localizar")) {
			comando = "LOCALIZAR";
		} else if (texto.contains("moldar") && texto.contains("texto")) {
			comando = "MOLDAR_TEXTO";
		} else if (texto.contains("novo")) {
			comando = "NOVO";
		} else if (texto.contains("número")) {
			comando = "NUMERO";
		} else if(texto.contains("funcoes") || texto.contains("funç")){
			comando = "OPCAO_240";
		} else if(texto.contains("ordenar")){
			comando = "ORDENAR";
			if (texto.contains("decrescente"))
				comando = "ORDENAR DESCRECENTE";
			else if (texto.contains("ordenar") || texto.contains("crescente"))
				comando = "ORDENAR_CRESCENTE";
		} else if (texto.contains("pdf")) {
			comando = "PDF";
		} else if (texto.contains("porcent")) {
			comando = "PORCENTAGEM_240";
		} else if (texto.contains("procurar")) {
			comando = "PROCURAR_240";
		} else if (texto.contains("refazer")) {
			comando = "REFAZER";
		} else if (texto.contains("simbolo")) {
			comando = "SIMBOLO";
		} else if (texto.contains("soma")) {
			comando = "SOMA_240";
		} else if (texto.contains("subtração")) {
			comando = "SUB_240";
		} else if (texto.contains("tabela") || texto.contains("planilha")) {
			comando = "TABELA";
			if (texto.contains("dinâmica"))
				comando = "TABELA_DINAMICA";
		} else if (texto.contains("tamanho") && texto.contains("fonte")) {
			comando = "TAMANHO_DA_FONTE";
		} else if (texto.contains("texto")) {
			comando = "TEXTO_240";
		} else if (texto.contains("visualizar") && texto.contains("impressão")) {
			comando = "VISUALIZAR_IMPRESSAO";
		}
		return comando;
	}
}