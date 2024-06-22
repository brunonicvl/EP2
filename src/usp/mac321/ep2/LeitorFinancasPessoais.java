package usp.mac321.ep2;

import java.util.*;

import java.io.*;
import java.text.ParseException;

public class LeitorFinancasPessoais implements LeitorFinancasPessoaisDAO {
	private static final boolean TIPO_PRINCIPAL = false;
	private static final boolean SUBCATEGORIA = true;
	
	private List<Usuario> usuarios;
	private List<TipoDespesa> despesas;
	private List<TipoReceita> receitas;
	private List<Lancamento> lancamentos;

	@Override
	public List<Usuario> leUsuarios(String nomeArquivoUsuarios) {
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(nomeArquivoUsuarios));
			String line = null;
			do {
				line = br.readLine();
				if (line != null) listaUsuarios.add(new Usuario(line.split(",")[0], line.split(",")[1]));
			} while(line != null);
			br.close();
		}
		catch (IOException e) { //pesquisar se isso se aplica a não existir nome de arquivo!!
			e.printStackTrace();
		}
		
		return listaUsuarios;
	}

	@Override
	public List<TipoDespesa> leTiposDespesas(String nomeArquivoTiposDespesas) {
		List<TipoDespesa> listaTiposDespesas = new ArrayList<TipoDespesa>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(nomeArquivoTiposDespesas));
			String line = null;
			do {
				line = br.readLine();
				//Espera-se linha do tipo "Tipo,Subcategoria,Subcategoria,Subcategoria,..."
				//Determina-se algo como Subcategoria se for descrita por algum outro tipo em qualquer linha
				if (line != null) {
					if(line.contains(",")) {
						String[] lineSplit = line.split(",");
						List<TipoDespesa> subtiposDespesa = new ArrayList<TipoDespesa>();
						
						TipoDespesa tipoPrincipal;
						try {
							tipoPrincipal = getTipoDespesaFromList(lineSplit[0], listaTiposDespesas);
						}
						catch(TipoNaoRegistradoException e) {
							tipoPrincipal = new TipoDespesa(lineSplit[0], TIPO_PRINCIPAL);
							listaTiposDespesas.add(tipoPrincipal);
						}
						
						for(int i=1; i<lineSplit.length; i++) {
							TipoDespesa novoTipo;
							try {
								novoTipo = getTipoDespesaFromList(lineSplit[i], listaTiposDespesas);
								novoTipo.sub = SUBCATEGORIA;
							}
							catch(TipoNaoRegistradoException e) {
								novoTipo = new TipoDespesa(lineSplit[i], SUBCATEGORIA);
								listaTiposDespesas.add(novoTipo);
							}
							subtiposDespesa.add(novoTipo);
							novoTipo.setTipoDerivado(tipoPrincipal);
						}
						tipoPrincipal.setSubcategorias(subtiposDespesa);
					}
					
					else {
						listaTiposDespesas.add(new TipoDespesa(line, TIPO_PRINCIPAL));
					}
				}
			} while(line != null);
			br.close();
		}
		catch (IOException e) { //pesquisar se isso se aplica a não existir nome de arquivo!!
			e.printStackTrace();
		}
		
		return listaTiposDespesas;
	}

	@Override
	public List<TipoReceita> leTiposReceitas(String nomeArquivoTiposReceitas) {
		List<TipoReceita> listaTiposReceitas = new ArrayList<TipoReceita>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(nomeArquivoTiposReceitas));
			String line = null;
			do {
				line = br.readLine();
				//Espera-se linha do tipo "Tipo,Subcategoria,Subcategoria,Subcategoria,..."
				//Determina-se algo como Subcategoria se for descrita por algum outro tipo em qualquer linha,
				//Mesmo que haja uma linha descrevendo-a como tendo suas próprias subcategorias
				if (line != null) {
					if(line.contains(",")) {
						String[] lineSplit = line.split(",");
						List<TipoReceita> subtiposReceita = new ArrayList<TipoReceita>();
						
						TipoReceita tipoPrincipal;
						try {
							tipoPrincipal = getTipoReceitaFromList(lineSplit[0], listaTiposReceitas);
						}
						catch(TipoNaoRegistradoException e) {
							tipoPrincipal = new TipoReceita(lineSplit[0], TIPO_PRINCIPAL);
							listaTiposReceitas.add(tipoPrincipal);
						}
						
						for(int i=1; i<lineSplit.length; i++) {
							TipoReceita novoTipo;
							try {
								novoTipo = getTipoReceitaFromList(lineSplit[i], listaTiposReceitas);
								novoTipo.sub = SUBCATEGORIA;
							}
							catch(TipoNaoRegistradoException e) {
								novoTipo = new TipoReceita(lineSplit[i], SUBCATEGORIA);
								listaTiposReceitas.add(novoTipo);
							}
							subtiposReceita.add(novoTipo);
							novoTipo.setTipoDerivado(tipoPrincipal);
						}
						tipoPrincipal.setSubcategorias(subtiposReceita);
					}
					
					else {
						listaTiposReceitas.add(new TipoReceita(line, TIPO_PRINCIPAL));
					}
				}
			} while(line != null);
			br.close();
		}
		catch (IOException e) { //pesquisar se isso se aplica a não existir nome de arquivo!!
			e.printStackTrace();
		}
		
		return listaTiposReceitas;
	}

	@Override
	public List<Lancamento> leLancamentos(String nomeArquivoLancamentos) {
		List<Lancamento> listaLancamentos = new ArrayList<Lancamento>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(nomeArquivoLancamentos));
			String line = null;
			do {
				line = br.readLine();
				if (line != null) {
					//Lancamentos serão salvos como uma linha do tipo
					//dia, mes, ano, user, receitaOuDespesa, tipo, descricao, valor
					String[] lineSplit = line.split(",");
					
					int dia, mes, ano;
					try {
						dia = Integer.parseInt(lineSplit[0]);
						mes = Integer.parseInt(lineSplit[1]);
						ano = Integer.parseInt(lineSplit[2]);
					}
					catch(ParseException e) { //HUH??????????????????????????????????
						throw new ParseException(lineSplit[0] + lineSplit[1] + lineSplit[2], 0);
					}
					finally {
						if(dia<=0) throw new ValorNegativoException(dia);
						if(dia<=0) throw new ValorNegativoException(mes);
						if(dia<=0) throw new ValorNegativoException(ano);
					}
					
					Usuario user;
					try {
						user = getUsuarioFromList(lineSplit[3], usuarios);
					}
					catch(UsuarioNaoExistenteException e) {
						throw new UsuarioNaoExistenteException(lineSplit[3]);
					}
					
					listaLancamentos.add(new Lancamento(line.split(",")[0], line.split(",")[1]));
				}
			} while(line != null);
			br.close();
		}
		catch (IOException e) { //pesquisar se isso se aplica a não existir nome de arquivo!!
			e.printStackTrace();
		}
		
		return listaLancamentos;
	}
	
	public TipoDespesa getTipoDespesaFromList(String nome, List<TipoDespesa> l) throws TipoNaoRegistradoException {
		for(TipoDespesa i: l) {
			if(i.getNome().equals(nome)) {
				return i;
			}
		}
		throw new TipoNaoRegistradoException(nome);
	}
	
	public TipoReceita getTipoReceitaFromList(String nome, List<TipoReceita> l) throws TipoNaoRegistradoException {
		for(TipoReceita i: l) {
			if(i.getNome().equals(nome)) {
				return i;
			}
		}
		throw new TipoNaoRegistradoException(nome);
	}
	
	public Usuario getUsuarioFromList(String apelido, List<Usuario> l) throws UsuarioNaoExistenteException {
		for(Usuario i: l) {
			if(i.getApelido().equals(apelido)) {
				return i;
			}
		}
		throw new UsuarioNaoExistenteException(apelido);
	}
}
