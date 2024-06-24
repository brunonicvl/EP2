package usp.mac321.ep2;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class EscritorTiposReceita implements EscritorTiposReceitaDAO {
	
	@Override
	public void escreveTiposReceita(List<TipoReceita> l, String nomeArquivoUsuarios) {
		try {
			PrintWriter writer = new PrintWriter(nomeArquivoUsuarios, "UTF-8");
			for(TipoReceita i: l) {
				if(!i.isSub()) {
					try {
						for(TipoReceita j: i.getSubcategorias()) {
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
