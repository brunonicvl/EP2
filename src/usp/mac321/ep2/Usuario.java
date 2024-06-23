package usp.mac321.ep2;

public class Usuario {
	String apelido;
	String nome;
	
	Usuario(String apelido, String nome){
		this.apelido = apelido;
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getApelido() {
		return apelido;
	}
}
