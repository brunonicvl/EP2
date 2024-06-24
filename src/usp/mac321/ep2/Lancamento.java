package usp.mac321.ep2;

public class Lancamento {
	private static long contadorDeLancamentos;
	long identificador;
	int dia, mes, ano;
	Usuario user;
	boolean receitaOuDespesa;
	TipoOperacao tipo;
	String descricao;
	double valor;
	EstadoLancamento launchState;
	
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
	}}
