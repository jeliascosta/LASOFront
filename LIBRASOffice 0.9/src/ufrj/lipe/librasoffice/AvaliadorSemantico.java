package ufrj.lipe.librasoffice;

public class AvaliadorSemantico {
	private String comando;
	private String texto;
	
	public String avaliar (String str){
		comando = "SUMA";
		this.texto = str.trim().toLowerCase();
		System.out.println(texto.contains("linha"));
		if (texto.contains("novo")){
			comando = "NOVO";
		}
		else if(texto.contains("cola")){
        	comando = "COLA";
		}
		else if(texto.contains("abrir")){
        	comando = "COLA";
		}
		else if(texto.contains("salvar") ||
				texto.contains("guardar")){
        	comando = "GUARDAR";
		}
		else if(texto.contains("impr")){
        	comando = "IMPRIMIR";
		}
		else if(texto.contains("cort")){
        	comando = "CORTAR";
		}
		else if(texto.contains("data")){
        	comando = "DATA";
		}
		else if(texto.contains("porcent")){
        	comando = "PORCENTAGEM";
		}
		else if(texto.contains("numero")){
        	comando = "NUMERO";
		}
		else if(texto.contains("moeda")){
        	comando = "DINHEIRO";
		}
		else if(texto.contains("numero")){
        	comando = "NUMERO";
		}
		else if(texto.contains("funcoes") ||
				texto.contains("funç")){
        	comando = "OPCAO";
		}
		return comando;
	}
}
