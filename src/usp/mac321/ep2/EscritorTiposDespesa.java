package usp.mac321.ep2;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class EscritorTiposDespesa implements EscritorTiposDespesaDAO {
	
	@Override
	public void escreveTiposDespesa(List<TipoDespesa> l, String nomeArquivoUsuarios) {
		try {
			PrintWriter writer = new PrintWriter(nomeArquivoUsuarios, "UTF-8");
			writer.println("Categoria,SubCategoria");
			for(TipoDespesa i: l) {
				if(!i.isSub()) {
					try {
						for(TipoDespesa j: i.getSubcategorias()) {
							writer.print(i.getNome());
							writer.println("," + j.getNome());
						}
					} catch (SubcategoriasInexistentesException e) {
						writer.println(i.getNome());
					}
				}
			}
			writer.close();
		}
		catch(FileNotFoundException | UnsupportedEncodingException e) {
			System.out.println(e);
		} 
	}
}
