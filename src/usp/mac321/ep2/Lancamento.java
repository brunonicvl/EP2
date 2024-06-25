package usp.mac321.ep2;

public class Lancamento {
	private static long contadorDeLancamentos;
	private long identificador;
	private int dia, mes, ano;
	private Usuario user;
	private boolean receitaOuDespesa;
	private TipoOperacao tipo;
	private String descricao;
	private double valor;
	private EstadoLancamento launchState;
	
	Lancamento(int dia, int mes, int ano, Usuario user, boolean receitaOuDespesa, TipoOperacao tipo, String descricao, double valor){
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
		this.user = user;
		this.receitaOuDespesa = receitaOuDespesa;
		this.tipo = tipo;
		this.descricao = descricao;
		this.valor = valor;
		identificador = contadorDeLancamentos;
		contadorDeLancamentos++;
		launchState = new Invalido();
	}
	
	Lancamento(int dia, int mes, int ano, Usuario user, boolean receitaOuDespesa, TipoOperacao tipo, String descricao, double valor, long indentificador){
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
		this.user = user;
		this.receitaOuDespesa = receitaOuDespesa;
		this.tipo = tipo;
		this.descricao = descricao;
		this.valor = valor;
		this.identificador = identificador;
		if(contadorDeLancamentos<=identificador) {
			contadorDeLancamentos=identificador+1;
		}
		launchState = new Invalido();
	}

	public long getID() {
		return identificador;
	}
	
	public String getData() {
		return dia + "/" + mes + "" + ano;
	}
	
	public Usuario getUser() {
		return user;
	}
	
	public boolean getROuD() {
		return receitaOuDespesa;
	}
	
	public TipoOperacao getTipo() {
		return tipo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public double getValor() {
		return valor;
	}
}
