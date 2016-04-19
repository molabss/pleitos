package constran.pleitos.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import constran.pleitos.model.CentrosDeCusto;
import constran.pleitos.model.Usuario;
import constran.pleitos.util.dao.AbstractDAO;

public class CCDao  extends AbstractDAO {

	
	
	
	public CCDao(Connection conn) 
	{
		super(conn);
	}
	
	
	public void salvar(CentrosDeCusto cc) throws SQLException
	{
	
		StringBuilder insert = new StringBuilder();
		insert.append("insert into [CENTROS_DE_CUSTOS] values (?,?,?,?)");
		prepStmt = conn.prepareStatement(insert.toString());
		
		prepStmt.setString(1, String.valueOf(cc.getId()));
		prepStmt.setString(2, cc.getDescricao());
		prepStmt.setBigDecimal(3, new BigDecimal(cc.getPorcentagemParticipa()));
		prepStmt.setString(4, cc.get_loginDiretor());
		
		prepStmt.executeUpdate();
		
		liberarRecursosBD();
	}
	
	
	public int atualizar(CentrosDeCusto cc) throws SQLException, ParseException
	{
		
		StringBuilder update = new StringBuilder();
		update.append("update [CENTROS_DE_CUSTOS] set id_cc =?, descricao=?, porcentagemParticipa=?, fk_id_login_diretor = ? where id_cc = ?");
		prepStmt = conn.prepareStatement(update.toString());
	
		prepStmt.setString(1, cc.getId());
		prepStmt.setString(2, cc.getDescricao());
		prepStmt.setBigDecimal(3, new BigDecimal(cc.getPorcentagemParticipa()));
		prepStmt.setString(4, cc.get_loginDiretor());

		prepStmt.setString(5, cc.getId());

		int reg = prepStmt.executeUpdate();
		
		liberarRecursosBD();
		
		return reg;
	}
	
	public List<CentrosDeCusto> listarTodos() throws SQLException
	{
    	List<CentrosDeCusto> listaCC = new ArrayList<CentrosDeCusto>();
     	CentrosDeCusto cc = null;
    	prepStmt = conn.prepareStatement("select * from centros_de_custos order by id_cc");
    	rs = prepStmt.executeQuery();
    	
    	while(rs.next())
    	{
    		cc = new CentrosDeCusto();
    		
    		cc.setId(rs.getString("id_cc"));
    		cc.setDescricao(rs.getString("descricao"));
    		cc.setPorcentagemParticipa(rs.getString("porcentagemParticipa"));
    		//cc.setDiretor(rs.getString("nome"));
    		
    		listaCC.add(cc);
    	}
    	liberarRecursosBD();
    	return listaCC;
	}
	
	
    public List<CentrosDeCusto> listarCC() throws SQLException
    {
       	List<CentrosDeCusto> listaCC = new ArrayList<CentrosDeCusto>();
    	CentrosDeCusto cc = null;
     	prepStmt = conn.prepareStatement("select * from centros_de_custos cc left join usuarios us on cc.fk_id_login_diretor = us.login");
    	rs = prepStmt.executeQuery();
    	
    	while(rs.next())
    	{
     		cc = new CentrosDeCusto();
     		cc.setId(rs.getString("id_cc"));
    		cc.setDescricao(rs.getString("descricao"));
    		cc.setPorcentagemParticipa(rs.getString("porcentagemParticipa"));
    		cc.set_loginDiretor(rs.getString("fk_id_login_diretor"));
    		cc.setDiretor(rs.getString("nome"));
     		listaCC.add(cc);
    	}
    	liberarRecursosBD();
    	return listaCC;
    }
    
    
    public List<Usuario> listarDiretores() throws SQLException
    {
    	List<Usuario> listaDiretores = new ArrayList<Usuario>();
    	Usuario u = null;
    	prepStmt = conn.prepareStatement("select login, nome from USUARIOS where fk_id_tipo = ?");
    	prepStmt.setString(1, "DIRETOR DE OBRA");
     	rs = prepStmt.executeQuery();
    	
    	while(rs.next())
    	{
    		u = new Usuario();
    		u.setLogin(rs.getString("login"));
    		u.setNome(rs.getString("nome"));
    		listaDiretores.add(u);
    	}
    	
    	liberarRecursosBD();
    	return listaDiretores;
    }
}
