package constran.pleitos.model;

import java.util.List;

public class Usuario {
	
	private String nome;
	private String login;
	private String senha;
	//private String grupo;
	
	private List<String> grupos;
	
	@SuppressWarnings("unused")
	private String grupos_delimitado;
	
	
	private String tipo;
	private String ativo;
	
	@SuppressWarnings("unused")
	private String listaCC_delimitado;
	
	
	private List<String> listaCC;
	
	public Usuario (){
		
	}
	
	
	
	/*
	public Usuario(String nome, String login, String senha,String grupo, List<String> listaCC) {
		super();
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.grupo = grupo;
		this.setListaCC(listaCC);
	}*/
	
	public Usuario(String nome, String login, String senha, List<String> grupos, List<String> listaCC) {
		super();
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.grupos = grupos;
		this.setListaCC(listaCC);
	}


	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	/*
	public String getGrupo() {
		return grupo;
	}
	
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	
	 */


	public List<String> getListaCC() {
		return listaCC;
	}




	public void setListaCC(List<String> listaCC) {
		this.listaCC = listaCC;
	}




	public String getTipo() {
		return tipo;
	}




	public void setTipo(String tipo) {
		this.tipo = tipo;
	}





	public String getAtivo() {
		return ativo;
	}




	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	
	public boolean contemCC(String cc){
		
		boolean contem = false;
		
		for(String c : listaCC){
			
			if(c.equals(cc)){
				contem = true;
				break;
			}
		}
		return contem;		
	}


	public String getListaCC_delimitado() {
		
		StringBuilder strCC = new StringBuilder();
		
		if(listaCC == null) return null;
		
		for(String s : listaCC){
			strCC.append(s);
			
			if(listaCC.indexOf(s) < listaCC.size()-1){
				strCC.append(",");	
			}
		}
		return strCC.toString();
	}

	
	public String getGrupos_delimitado() {
		
		StringBuilder strGrupos = new StringBuilder();
		
		if(grupos == null) return null;
		
		for(String s : grupos){
			strGrupos.append(s);
			
			if(grupos.indexOf(s) < grupos.size()-1){
				strGrupos.append(",");	
			}
		}
		return strGrupos.toString();
	}

	
	public boolean contemGrupo(String grupo){

		boolean contem  = false;
		
		for(String gr : grupos){
			
			if(grupo.equals(gr)){
				contem = true;
				break;
			}
		}
		return contem;
	}
	
	
	public void setGrupos_delimitado(String grupos_delimitado) {
		this.grupos_delimitado = grupos_delimitado;
	}


	public void setListaCC_delimitado(String listaCC_delimitado) {
		this.listaCC_delimitado = listaCC_delimitado;
	}




	public List<String> getGrupos() {
		return grupos;
	}




	public void setGrupos(List<String> grupos) {
		this.grupos = grupos;
	}









}
