package constran.pleitos.model;

public class Status {
	
	private Integer id;
	private String descricao;
	
	public Status(Integer id){
		this.id = id;
	}
	
	
	public Status(String descricao){
		this.descricao = descricao;
	}
	
	public Status(){
		
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
