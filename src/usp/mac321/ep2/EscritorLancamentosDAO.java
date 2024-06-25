package usp.mac321.ep2;

import java.util.*;

public interface EscritorLancamentosDAO {
	public void escreveLancamentos(List<Lancamento> l, String nomeArquivoLancamentos);
}
