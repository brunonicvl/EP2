package usp.mac321.ep2;

import java.io.*;
import java.util.*;

public class EscritorUsuarios implements EscritorUsuariosDAO {

	@Override
	public void escreveUsuarios(List<Usuario> l, String nomeArquivoUsuarios) {
		try {
			PrintWriter writer = new PrintWriter(nomeArquivoUsuarios, "UTF-8");
			writer.println("Apelido,Nome");
			for(Usuario i: l) {
				writer.println(i.getApelido() + "," + i.getNome());
			}
			writer.close();
		}
		catch(FileNotFoundException | UnsupportedEncodingException e) {
			System.out.println(e);
		} 
	}

}
