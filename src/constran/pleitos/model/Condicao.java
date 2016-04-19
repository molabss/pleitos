package constran.pleitos.model;

public class Condicao {
	
	private Integer id;
	private String descricao;
	
	public Condicao(){
		
	}
	
	
	public Condicao(Integer id){
		this.id = id;
	}
	
	
	public Condicao(String descricao){
		this.descricao = descricao;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}
