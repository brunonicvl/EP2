package usp.mac321.ep2;

import java.util.List;

public class TipoDespesa implements TipoOperacao {
	private String nomeDoTipo;
	private boolean sub;
	private TipoDespesa tipoDerivado;
	private List<TipoDespesa> subcategorias;
	
	TipoDespesa(String n){
		nomeDoTipo = n;
		sub = false;
	}
	
	TipoDespesa(String n, List<TipoDespesa> t){
		nomeDoTipo = n;
		subcategorias = t;
		sub = true;
	}
	
	TipoDespesa(String n, boolean b){
		nomeDoTipo = n;
		sub = b;
	}
	
	public void setTipoDerivado(TipoDespesa t) {
		tipoDerivado = t;
	}
	
	public void setSubcategorias(List<TipoDespesa> t) {
		subcategorias = t;
	}
	
	@Override
	public String getNome() {
		return nomeDoTipo;
	}
	public String getSubcategorias() { // Renomear isso depois
		String sc = "Categoria: " + this.getNome() + " Subcategorias: ";
		
		for (TipoDespesa t: subcategorias) {
			sc += t.getNome() +  " ";
		}
		
		return sc;
	}

	public boolean isSub() {
		return sub;
	}
	
	@Override
	public String toString() {
		if(sub) {
			return tipoDerivado.toString() + ": " + nomeDoTipo;
		}
		
		return nomeDoTipo;
	}
}
