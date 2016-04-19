package constran.pleitos.model;

public class Tipo {
	
	private Integer id;
	private String descricao;
	
	public Tipo(Integer id){
		this.id = id;
	}
	
	
	public Tipo(String descricao){
		this.descricao = descricao;
	}
	
	public Tipo(){

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
