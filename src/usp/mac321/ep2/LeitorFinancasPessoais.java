package usp.mac321.ep2;

import java.util.*;

import java.io.*;
import java.text.ParseException;


public class LeitorFinancasPessoais implements LeitorFinancasPessoaisDAO {
	private static final boolean TIPO_PRINCIPAL = false;
	private static final boolean SUBCATEGORIA = true;
	private static final boolean DESPESA = true;
	private static final boolean RECEITA = false;
	private List<Usuario> usuarios;
	private List<TipoDespesa> despesas;
	private List<TipoReceita> receitas;
	private List<Lancamento> lancamentos;

	
	private boolean repetido(String apelido, List<Usuario> listaUsuarios) throws ApelidoRepetidoException {
		if (!listaUsuarios.isEmpty()) {
		for (int i = 0; i < listaUsuarios.size(); i++) {
			if (listaUsuarios.get(i).getApelido().equals(apelido)) {
				throw new ApelidoRepetidoException(apelido);
			}	
			
			
		}
		
		}
		return false;
		
	}
	
	
	@Override
	public List<Usuario> leUsuarios(String nomeArquivoUsuarios){
		//List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		usuarios = new ArrayList<Usuario>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(nomeArquivoUsuarios));
			String line = null;
			
			line = br.readLine();
			do {
				line = br.readLine();
				if (line != null && !repetido(line.split(",")[0],usuarios)) {
					usuarios.add(new Usuario(line.split(",")[0], line.split(",")[1]));
					
				}
				
			} 
			
			
			while(line != null);
				br.close();
		}
		
		catch (FileNotFoundException e) {
			System.err.println("Arquivo não encontrado");

		} // Não consegui encontrar se IOException também serve
		  //para isso, mas encontrei uma exceção exatamente para esse problema
		
		catch (IOException e) { //pesquisar se isso se aplica a não existir nome de arquivo!!
			e.printStackTrace();
		}
		
		catch (ApelidoRepetidoException e) {
			System.err.print(e.toString());
		}
		
		//usuarios = listaUsuarios;
		return usuarios;
	}

	@Override
	public List<TipoDespesa> leTiposDespesas(String nomeArquivoTiposDespesas) {
		//List<TipoDespesa> listaTiposDespesas = new ArrayList<TipoDespesa>();
		despesas = new ArrayList<TipoDespesa>();
		
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(nomeArquivoTiposDespesas));
			String line = null;
			
			line = br.readLine(); // Descarta a primeira linha do arquivo
			do {
				line = br.readLine();
				//Espera-se linha do tipo "Tipo,Subcategoria,Subcategoria,Subcategoria,..."
				//Determina-se algo como Subcategoria se for descrita por algum outro tipo em qualquer linha
				if (line != null) {
					if(line.contains(",")) {
						String[] lineSplit = line.split(",");
						List<TipoDespesa> subtiposDespesa = new ArrayList<TipoDespesa>();
						
						TipoDespesa tipoPrincipal;
						/*
						try {
							tipoPrincipal = getTipoDespesaFromList(lineSplit[0], listaTiposDespesas);
						}
						catch(TipoNaoRegistradoException e) {
							tipoPrincipal = new TipoDespesa(lineSplit[0], TIPO_PRINCIPAL);
							//listaTiposDespesas.add(tipoPrincipal);
						}*/
						
						tipoPrincipal = new TipoDespesa(lineSplit[0], TIPO_PRINCIPAL);
						
						for(int i=1; i<lineSplit.length; i++) {
							TipoDespesa novoTipo;
							try {
								novoTipo = getTipoDespesaFromList(lineSplit[i], despesas);
								novoTipo.sub = SUBCATEGORIA;
							}
							catch(TipoNaoRegistradoException e) {
								novoTipo = new TipoDespesa(lineSplit[i], SUBCATEGORIA);
								//listaTiposDespesas.add(novoTipo);
							}
							subtiposDespesa.add(novoTipo);
							novoTipo.setTipoDerivado(tipoPrincipal);
						}
						tipoPrincipal.setSubcategorias(subtiposDespesa);
						despesas.add(tipoPrincipal);
					}
					
					else {
						despesas.add(new TipoDespesa(line, TIPO_PRINCIPAL));
					}
				}
			} while(line != null);
			br.close();
		}
		
		catch (FileNotFoundException e) {
			System.err.println("Arquivo não encontrado");

		} 
		catch (IOException e) { //pesquisar se isso se aplica a não existir nome de arquivo!!
			e.printStackTrace();
		}
		
		return despesas;
	}

	@Override
	public List<TipoReceita> leTiposReceitas(String nomeArquivoTiposReceitas) {
		receitas = new ArrayList<TipoReceita>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(nomeArquivoTiposReceitas));
			String line = null;
			
			line = br.readLine();
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
						/*try {
							tipoPrincipal = getTipoReceitaFromList(lineSplit[0], listaTiposReceitas);
						}
						catch(TipoNaoRegistradoException e) {
							tipoPrincipal = new TipoReceita(lineSplit[0], TIPO_PRINCIPAL);
							//listaTiposReceitas.add(tipoPrincipal);
						}*/
						tipoPrincipal = new TipoReceita(lineSplit[0], TIPO_PRINCIPAL);
						
						for(int i=1; i<lineSplit.length; i++) {
							TipoReceita novoTipo;
							try {
								novoTipo = getTipoReceitaFromList(lineSplit[i], receitas);
								novoTipo.sub = SUBCATEGORIA;
							}
							catch(TipoNaoRegistradoException e) {
								novoTipo = new TipoReceita(lineSplit[i], SUBCATEGORIA);
								//listaTiposReceitas.add(novoTipo);
							}
							subtiposReceita.add(novoTipo);
							novoTipo.setTipoDerivado(tipoPrincipal);
						}
						tipoPrincipal.setSubcategorias(subtiposReceita);
						receitas.add(tipoPrincipal);
						
					}
					
					else {
						receitas.add(new TipoReceita(line, TIPO_PRINCIPAL));
					}
				}
			} while(line != null);
			br.close();
		}
		
		catch (FileNotFoundException e) {
			System.err.println("Arquivo não encontrado");

		}
		
		catch (IOException e) { //pesquisar se isso se aplica a não existir nome de arquivo!!
			e.printStackTrace();
		}
		
		return receitas;
	}

	@Override
	public List<Lancamento> leLancamentos(String nomeArquivoLancamentos) {
		//List<Lancamento> listaLancamentos = new ArrayList<Lancamento>();
		lancamentos = new ArrayList<Lancamento>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(nomeArquivoLancamentos));
			String line = null;
			
			line = br.readLine();
			do {
				line = br.readLine();
				if (line != null) {
					//Lancamentos serão salvos como uma linha do tipo
					//dia, mes, ano, user, receitaOuDespesa, tipo, descricao, valor
					int identificador;
					int dia, mes, ano;
					String[] lineSplit = line.split(",");
					String[] dataSplit = lineSplit[1].split("/");
					System.out.println(lineSplit);
					identificador = Integer.parseInt(lineSplit[0]);

					dia = Integer.parseInt(dataSplit[0]);
					mes = Integer.parseInt(dataSplit[1]);
					ano = Integer.parseInt(dataSplit[2]);
					
					if(dia<=0) throw new ValorNegativoException(dia);
					if(mes<=0) throw new ValorNegativoException(mes);
					if(ano<=0) throw new ValorNegativoException(ano);
					
					Usuario user;
					user = getUsuarioFromList(lineSplit[4], usuarios);
					
					boolean rOuD;
					if(lineSplit[5].equals("TRUE")) {
						rOuD = DESPESA;
					}
					else if(lineSplit[5].equals("FALSE")) {
						rOuD = RECEITA;
					}
					
					else {
						throw new ValorInvalidoException(lineSplit[5]);
					}
					
					TipoOperacao tipo;
					try {
						tipo = getTipoReceitaFromList(lineSplit[6], receitas);
					}
					catch(TipoNaoRegistradoException e){
						tipo = getTipoDespesaFromList(lineSplit[6], despesas);
						System.out.println("IOException: " + e.getMessage());
					}
					if(tipo.getClass().equals(TipoReceita.class) && (rOuD==DESPESA) ||
							tipo.getClass().equals(TipoDespesa.class) && (rOuD==RECEITA)) {
						throw new ConfusaoReceitaDespesaException(lineSplit[5]);
					}
					
					String descricao = lineSplit[6];
					
					double valor;
					valor = Double.parseDouble(lineSplit[7]);
					if(valor<=0) throw new ValorNegativoException(1);
					
					
					
					if(identificador<=0) throw new ValorNegativoException(identificador);
					for(Lancamento i: lancamentos) {
						if(i.identificador==identificador) {
							throw new IdentificadorRepetidoEmLancamentosException(identificador);
						}
					}
					
					lancamentos.add(new Lancamento(dia, mes, ano, user, rOuD, tipo, descricao, valor, identificador));
					System.out.println(1234);
				}
			} while(line != null);
			br.close();
		}
		catch (IOException e) { //pesquisar se isso se aplica a não existir nome de arquivo!!
			e.printStackTrace();
			System.out.println("IOException1: " + e.getMessage());
		}
		/*catch (FileNotFoundException e) { Talvez n'ao precise dessa exception
			System.err.println("Arquivo não encontrado");
		}*/
		
		
		catch (Exception e) { //pesquisar se isso se aplica a não existir nome de arquivo!!
			e.printStackTrace();
			System.out.println("IOException2: " + e.getMessage());
		}
		
		return lancamentos;
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
	
	/*
	public Usuario getUsuarioFromList(String apelido, List<Usuario> l) throws UsuarioNaoExistenteException {
		if (l != null){
		for(Usuario i: l) {
			if(i.getApelido().equals(apelido)) {
				return i;
			}
		}}
		throw new UsuarioNaoExistenteException(apelido);
	}*/
	
	public Usuario getUsuarioFromList(String apelido, List<Usuario> l) throws UsuarioNaoExistenteException {
		if (l != null){
		for(Usuario i: l) {
			if(i.getApelido().equals(apelido)) {
				return i;
			}
		}}
		throw new UsuarioNaoExistenteException(apelido);
	}
	
}
