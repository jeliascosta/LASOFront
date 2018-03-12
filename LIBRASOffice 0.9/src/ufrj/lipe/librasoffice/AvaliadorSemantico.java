package ufrj.lipe.librasoffice;

// 

/**
 * A classe AvaliadorSemantico relaciona os nomes do comandos em Libras ao comandos em si
 * nos elementos gráficos do programa.
 */
public class AvaliadorSemantico {
	private String comando;
	private String linhaLog;
	private String tipoWidget;
	private String comandoLibras;

	public String avaliar(String str) {
		linhaLog = str.trim();
		System.out.println(linhaLog);
		String[] dadosLog = linhaLog.split(":");
		tipoWidget = dadosLog[0].replaceAll(":", "").trim().toLowerCase();
		comandoLibras = "SUMIR";
		System.err.println(dadosLog.length);
		if (dadosLog.length > 1) comando = dadosLog[1].trim();
		else { comandoLibras = "NULO"; return comandoLibras; }
		System.err.println("AVALIADOR INCIAL: "+comandoLibras);
		String comando_lwrc = comando.toLowerCase();
		if (comando_lwrc.contains("abrir")) {
			comandoLibras = "ABRIR";
		} else if (comando_lwrc.contains("adicionar") && comando_lwrc.contains("casa") && comando_lwrc.contains("decimal")) {
			comandoLibras = "ADICIONAR_CASA_DECIMAL";
		} else if (comando_lwrc.contains("alinhar") && comando_lwrc.contains("cima")){
			comandoLibras = "ALINHAR_CIMA";
		} else if (comando_lwrc.contains("alinhar") && comando_lwrc.contains("baixo")){
			comandoLibras = "ALINHAR_BAIXO";
		} else if (comando_lwrc.contains("ajuda")){
			comandoLibras = "AJUDA";
		} else if (comando_lwrc.contains("anotação")){
			comandoLibras = "ANOTACAO";
		} else if (comando_lwrc.contains("área") && comando_lwrc.contains("impressão")){
			comandoLibras = "AREA_IMPRESSAO";		
		} else if (comando_lwrc.contains("arquivo")){
			comandoLibras = "ARQUIVO";
		} else if (comando_lwrc.contains("aumentar")){
			comandoLibras = "AUMENTAR";
		} else if (comando_lwrc.contains("borda")){
			comandoLibras = "BORDA";
			if (comando_lwrc.contains("cor"))
				comandoLibras = "COR DA BORDA";
		} else if (comando_lwrc.contains("caixa") && comando_lwrc.contains("texto")) {
			comandoLibras = "CAIXA_DE_TEXTO";
		} else if (comando_lwrc.contains("centralizar") && comando_lwrc.contains("horizontal")) {
			comandoLibras = "CENTRALIZAR_HORIZONTAL";
		} else if (comando_lwrc.contains("colar")) {
			comandoLibras = "COLAR";
		} else if (comando_lwrc.contains("congelar") && comando_lwrc.contains("linhas") && comando_lwrc.contains("colunas")) {
			comandoLibras = "CONGELAR_COLUNAS_LINHAS";
		} else if (comando_lwrc.contains("copiar")) {
			comandoLibras = "COPIAR";
		} else if (comando_lwrc.contains("cortar")) {
			comandoLibras = "CORTAR";
		} else if (comando_lwrc.contains("dado")){
			comandoLibras = "DADO";
		} else if (comando_lwrc.contains("da") && comando_lwrc.contains("direita") && comando_lwrc.contains("esquerda")) {
			comandoLibras = "DA_DIREITA_ESQUERDA";
		} else if (comando_lwrc.contains("da") && comando_lwrc.contains("esquerda") && comando_lwrc.contains("direita")) {
			comandoLibras = "DA_ESQUERDA_DIREITA";
		}else if (comando_lwrc.contains("data")) {
			comandoLibras = "DATA";
		} else if (comando_lwrc.contains("desfazer") || comando_lwrc.contains("voltar")) {
			comandoLibras = "DESFAZER";
		} else if (comando_lwrc.contains("diminuir")) {
			comandoLibras = "DIMINUIR";
		} else if (comando_lwrc.contains("dividir")) {
			comandoLibras = "DIV_240";
		} else if (comando_lwrc.contains("documento")) {
			comandoLibras = "DOCUMENTO";
		} else if (comando_lwrc.contains("editar")) {
			comandoLibras = "EDITAR";
		} else if (comando_lwrc.contains("estilo") && comando_lwrc.contains("borda")) {
			comandoLibras = "ESTILO_BORDA";
		} else if (comando_lwrc.contains("excluir") && comando_lwrc.contains("casa") && comando_lwrc.contains("decimal")) {
			comandoLibras = "EXCLUIR_CASA_DECIMAL";
		} else if (comando_lwrc.contains("excluir") && comando_lwrc.contains("colunas")) {
			comandoLibras = "EXCLUIR_COLUNAS";
		} else if (comando_lwrc.contains("exibir")) {
			comandoLibras = "EXIBIR";
		} else if (comando_lwrc.contains("ferramenta")){
			comandoLibras = "FERRAMENTA";
		} else if (comando_lwrc.contains("figura")) {
			comandoLibras = "FIGURA";
		} else if (comando_lwrc.contains("formas") && comando_lwrc.contains("simples")) {
			comandoLibras = "FORMAS_SIMPLES";
		} else if (comando_lwrc.equals("formatar")){
			comandoLibras = "FORMATAR";
		} else if (comando_lwrc.contains("grafico")) {
			comandoLibras = "GRAFICO";
		} else if (comando_lwrc.contains("salvar") || comando_lwrc.contains("guardar")) {
			comandoLibras = "GUARDAR";
		} else if (comando_lwrc.contains("imprimir")) {
			comandoLibras = "IMPRIMIR";
		} else if (comando_lwrc.equals("inserir")){
			comandoLibras = "INSERIR";
			if (comando_lwrc.contains("coluna") && comando_lwrc.contains("esquerda")) 
				comandoLibras = "INSERIR_COLUNA_ESQUERDA";
			else if (comando_lwrc.contains("linha") && comando_lwrc.contains("cima"))
				comandoLibras = "INSERIR_LINHA_ACIMA";
		} else if (comando_lwrc.contains("janela")){
			comandoLibras = "JANELA";
		} else if (comando_lwrc.contains("linha") && comando_lwrc.contains("entrada")) {
			comandoLibras = "NULO";
		} else if (comando_lwrc.contains("localizar") || comando_lwrc.contains("procurar")) {
			comandoLibras = "LOCALIZAR";
		} else if (comando_lwrc.contains("moldar") && comando_lwrc.contains("texto")) {
			comandoLibras = "MOLDAR_TEXTO";
		} else if (comando_lwrc.contains("novo")) {
			comandoLibras = "NOVO";
		} else if (comando_lwrc.contains("número")) {
			comandoLibras = "NUMERO";
		} else if(comando_lwrc.contains("assist") && comando_lwrc.contains("funç")){
			comandoLibras = "ASSIST_FORMULA";
		} else if(comando_lwrc.contains("ordenar")){
			comandoLibras = "ORDENAR";
			if (comando_lwrc.contains("decrescente"))
				comandoLibras = "ORDENAR DESCRECENTE";
			else if (comando_lwrc.contains("ordenar") || comando_lwrc.contains("crescente"))
				comandoLibras = "ORDENAR_CRESCENTE";
		} else if (comando_lwrc.contains("planilha")){
			comandoLibras = "PLANILHA";
		} else if (comando_lwrc.contains("exportar") && comando_lwrc.contains("pdf")) {
			comandoLibras = "PDF";
		} else if (comando_lwrc.contains("refazer")) {
			comandoLibras = "REFAZER";
		} else if (comando_lwrc.contains("simbolo")) {
			comandoLibras = "SIMBOLO";
		} else if (comando_lwrc.contains("soma")) {
			comandoLibras = "SOMAR";
		} else if (comando_lwrc.contains("tabela") || comando_lwrc.contains("planilha")) {
			comandoLibras = "TABELA";
			if (comando_lwrc.contains("dinâmica"))
				comandoLibras = "TABELA_DINAMICA";
		} else if (comando_lwrc.contains("tamanho") && comando_lwrc.contains("fonte")) {
			comandoLibras = "TAMANHO_DA_FONTE";
		} else if (comando_lwrc.contains("visualizar") && comando_lwrc.contains("impressão")) {
			comandoLibras = "VISUALIZAR_IMPRESSAO";
		}
		return comandoLibras;
	}
	
	public String getComando() {
		return comando;
	}

	public String getTipoWidget() {
		return tipoWidget;
	}
}