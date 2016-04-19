package constran.pleitos.model;

public class CentrosDeCusto {

	
	private String id;
	private String descricao;
	private  String porcentagemParticipa;

	//Necessário por conta do componente GRID da tela que nao acessa subobjetos em json
	private String diretor;
	private String _loginDiretor;
	//---------------------------------------------------------------------------------
	
	
	public CentrosDeCusto(){

	}
	
	public CentrosDeCusto(String id){
		this.id = id;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPorcentagemParticipa() {
		return porcentagemParticipa;
	}

	public void setPorcentagemParticipa(String porcentagemParticipa) {
		this.porcentagemParticipa = porcentagemParticipa;
	}

	public String getDiretor() {
		return diretor;
	}

	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}

	public String get_loginDiretor() {
		return _loginDiretor;
	}

	public void set_loginDiretor(String _loginDiretor) {
		this._loginDiretor = _loginDiretor;
	}
	
	
	
	
}
