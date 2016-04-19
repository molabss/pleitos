package constran.pleitos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import constran.pleitos.model.Usuario;
import constran.pleitos.util.dao.AbstractDAO;



public class UsuarioDAO extends AbstractDAO{

	
	public UsuarioDAO(Connection conn) 
	{
		super(conn);
	}
	
    
    public String compararLoginCadastrado(String login) throws SQLException
    {
    	String select = "select login from usuarios where login = ?";
    	prepStmt = conn.prepareStatement(select);
    	prepStmt.setString(1, login);
    	rs = prepStmt.executeQuery();
    	
    	if(rs.next())
    		return rs.getString("login");
    	else
    		return "NAO_CADASTRADO";
    }
    
    
    public String obterEmailLogin(String login) throws SQLException
    {
    	String select = "select email from usuarios where login = ?";
    	prepStmt = conn.prepareStatement(select);
    	prepStmt.setString(1, login);
    	rs = prepStmt.executeQuery();
    	
    	if(rs.next())
    		
    		return rs.getString("email");
    	else
    		return "NENHUM_EMAIL_ENCONTRADO";
    }
    
    public String obterIDusuario(String login) throws SQLException
    {
    	String select = "select idUsuario from usuarios where login = ?";
    	prepStmt = conn.prepareStatement(select);
    	prepStmt.setString(1, login);
    	rs = prepStmt.executeQuery();
    	
    	if(rs.next())
    		return rs.getString("idUsuario");
    	else
    		return "-1";
    }
    
    public Usuario usuarioAutentico(String login, String senha) throws SQLException
    {
    	StringBuilder select = new StringBuilder();
    	select.append("select u.nome, u.login, u.senha, up.fk_grupo, u.fk_id_tipo from usuarios u ");
    	select.append("inner join usuario_permissoes up ");
    	select.append("on up.fk_login = u.login ");
    	select.append("where u.login = ? and (PWDCOMPARE(?, senha) = 1) and u.ativo = ?");
    	
    	prepStmt = conn.prepareStatement(select.toString());
    	prepStmt.setString(1,login);
    	prepStmt.setString(2,senha);
    	prepStmt.setString(3,"S");
    	
    	rs = prepStmt.executeQuery();
    	
    	Usuario u = null;
    	
    	if(rs.next())
    	{
    		u = new Usuario();
    		u.setNome(rs.getString("nome"));
    		u.setLogin(rs.getString("login"));
    		u.setSenha("*******");
    		u.setGrupos(listarGruposPorUSR(u.getLogin()));
    		u.setTipo(rs.getString("fk_id_tipo"));
    		u.setListaCC(obterCCusuario(login));
    	}
		liberarRecursosBD();
		return u;
    }
    
    
    private List<String> obterCCusuario(String login) throws SQLException
    {
    	List<String> listaCC = new ArrayList<String>();
    	prepStmt = conn.prepareStatement("select fk_id_cc from usuarios_centro_de_custos where fk_login = ?");
    	prepStmt.setString(1, login);
    	rs = prepStmt.executeQuery();
    	
    	while(rs.next())
    	{
    		listaCC.add(rs.getString("fk_id_cc"));
    	}
    	liberarRecursosBD();
    	return listaCC;
    }
    
    public List<String> listarTipos() throws SQLException
    {
    	List<String> listaTipos = new ArrayList<String>();
    	prepStmt = conn.prepareStatement("select * from usuarios_tipo");
    	rs = prepStmt.executeQuery();
    	
    	while(rs.next()){
    		listaTipos.add(rs.getString("id_tipo"));
    	}
    	liberarRecursosBD();
    	return listaTipos;
    }
    
    
    public List<String> listarGruposPorUSR(String login) throws SQLException 
    {
    	List<String> listaGrupos = new ArrayList<String>();
    	PreparedStatement pre = conn.prepareStatement("select fk_grupo from USUARIO_PERMISSOES where fk_login = ?");
    	pre.setString(1, login);
    	ResultSet res = pre.executeQuery();
    	
    	while(res.next())
    	{
    		listaGrupos.add(res.getString("fk_grupo"));
    	}
    	return listaGrupos;
    }
    
    public List<String> listarGrupos() throws SQLException
    {
    	List<String> listaGrupos = new ArrayList<String>();
    	prepStmt = conn.prepareStatement("select * from usuario_grupos");
    	rs = prepStmt.executeQuery();
    	
    	while(rs.next())
    	{
    		listaGrupos.add(rs.getString("idGrupo"));
    	}
    	liberarRecursosBD();
    	return listaGrupos;
    }
    
    
    public void cadastrarUsuario(Usuario u) throws SQLException
    {
    	StringBuilder insert = new StringBuilder();
    	insert.append("insert into usuarios values (?,PWDENCRYPT(?),?,?,?)");
    	prepStmt = conn.prepareStatement(insert.toString());
    	
    	prepStmt.setString(1, u.getLogin());
    	prepStmt.setString(2, u.getSenha());
    	prepStmt.setString(3, u.getAtivo());
    	prepStmt.setString(4, u.getNome());
    	prepStmt.setString(5, u.getTipo());	
    	
    	prepStmt.executeUpdate();
    	liberarRecursosBD();
    }
    
    
    public void cadastrarPermissao(Usuario u) throws SQLException
    {
    	StringBuilder insert = new StringBuilder();
    	insert.append("insert into usuario_permissoes values (?,?)");
    	prepStmt = conn.prepareStatement(insert.toString());
    	
    	for(String gr : u.getGrupos())
    	{
        	prepStmt.setString(1, u.getLogin());
        	prepStmt.setString(2, gr);
        	prepStmt.addBatch();
    	}
    	prepStmt.executeUpdate();
    	liberarRecursosBD();
    }
    
    
    public void autorizarCCs(Usuario u) throws SQLException
    {
    	StringBuilder insert = new StringBuilder();
    	insert.append("insert into USUARIOS_CENTRO_DE_CUSTOS values (?,?)");
    	prepStmt = conn.prepareStatement(insert.toString());
    	
    	for(String cc : u.getListaCC())
    	{
        	prepStmt.setString(1, u.getLogin());
        	prepStmt.setString(2, cc);
        	prepStmt.addBatch();
    	}
    	prepStmt.executeBatch();
    	liberarRecursosBD();
    }
  
  
    
    public List<Usuario> listarTodos() throws SQLException
    {
    	List<Usuario> listaUSR = new ArrayList<Usuario>();
    	Usuario usr = null;
    	
    	StringBuilder select = new StringBuilder();
    	select.append("select u.nome, u.login, u.ativo, ut.id_tipo from usuarios u ");
    	select.append("left join usuarios_tipo ut ");
    	select.append("on u.fk_id_tipo = ut.id_tipo ");
    	//select.append("left join usuario_permissoes up ");
    	//select.append("on up.fk_login = u.login");
    	
    	prepStmt = conn.prepareStatement(select.toString());
    	rs = prepStmt.executeQuery();
    	
    	while(rs.next())
    	{
    		usr = new Usuario();
    		usr.setLogin(rs.getString("login"));
    		usr.setNome(rs.getString("nome"));
    		usr.setAtivo(rs.getString("ativo"));
    		usr.setTipo(rs.getString("id_tipo"));
    		//usr.setGrupo(rs.getString("fk_grupo"));
    		usr.setGrupos(listarGruposPorUSR(usr.getLogin()));
    		//usr.setPermitirAcesso(rs.getString("permitir"));
    		usr.setListaCC(listarCCautorizados(usr.getLogin()));
    		listaUSR.add(usr);
    	}
    	liberarRecursosBD();
    	return listaUSR;
    }
    
    
    public boolean isUpdate(String login) throws SQLException
    {
    	boolean update = false;
    	prepStmt = conn.prepareStatement("select login from usuarios where login = ?");
    	prepStmt.setString(1, login);
    	rs = prepStmt.executeQuery();
    	
    	if(rs.next()) update = true;
    	
    	liberarRecursosBD();
    	return update;
    }
    
    private List<String> listarCCautorizados(String login) throws SQLException
    {
    	List<String> listaCC = new ArrayList<String>();
    	PreparedStatement prep = conn.prepareStatement("select fk_id_cc from USUARIOS_CENTRO_DE_CUSTOS where fk_login = ?");
    	prep.setString(1, login);
    	ResultSet res = prep.executeQuery();

    	while(res.next())
    	{
    		listaCC.add(res.getString("fk_id_cc"));
    	}
   	   	return listaCC;
    }
    
    
    public int atualizarUsuario(Usuario u) throws SQLException 
    {
    	if(u.getSenha().length() == 0)
    	{
        	prepStmt = conn.prepareStatement("update USUARIOS set login = ?, ativo = ?, nome = ? where login = ?");
        	prepStmt.setString(1, u.getLogin());
        	prepStmt.setString(2, u.getAtivo());
        	prepStmt.setString(3, u.getNome());
        	prepStmt.setString(4, u.getLogin());
    	
    	}
    	else
    	{
        	prepStmt = conn.prepareStatement("update USUARIOS set login = ?, senha = PWDENCRYPT(?), ativo = ?, nome = ? where login = ?");
        	prepStmt.setString(1, u.getLogin());
        	prepStmt.setString(2, u.getSenha());
        	prepStmt.setString(3, u.getAtivo());
        	prepStmt.setString(4, u.getNome());
        	prepStmt.setString(5, u.getLogin());
    	}
    	int reg = prepStmt.executeUpdate();
    	liberarRecursosBD();
    	return reg;
    }
    
    
    public int atualizarPermissao(Usuario u) throws SQLException 
    {
    	prepStmt = conn.prepareStatement("delete from USUARIO_PERMISSOES where fk_login = ?");
    	prepStmt.setString(1, u.getLogin());
    	prepStmt.executeUpdate();
    	
    	prepStmt = conn.prepareStatement("insert into USUARIO_PERMISSOES values(?,?)");
    	
    	for(String g : u.getGrupos())
    	{
        	prepStmt.setString(1, u.getLogin());
        	prepStmt.setString(2, g);
        	//prepStmt.setString(3, u.getPermitirAcesso());
    		prepStmt.addBatch();
    	}
    	
    	int[] reg = prepStmt.executeBatch();
    	liberarRecursosBD();
    	return reg.length;
    }
    
    
    
    public int atualizarAutorizarCCs(Usuario u) throws SQLException
    {
    	prepStmt = conn.prepareStatement("delete from USUARIOS_CENTRO_DE_CUSTOS where fk_login = ?");
    	prepStmt.setString(1, u.getLogin());
    	prepStmt.executeUpdate();
    	prepStmt = conn.prepareStatement("insert into USUARIOS_CENTRO_DE_CUSTOS (fk_login,fk_id_cc) values(?,?)");
    	
    	for(String cc : u.getListaCC())
    	{
        	prepStmt.setString(1, u.getLogin());
        	prepStmt.setString(2, cc);
    		prepStmt.addBatch();
    	}
    	int[] reg = prepStmt.executeBatch();
    	liberarRecursosBD();
    	return reg.length;
    }
    
    
    public int removerUsuario(Usuario u) throws SQLException
    {
    	prepStmt = conn.prepareStatement("delete from USUARIOS where login = ?");
    	prepStmt.setString(1, u.getLogin());
    	int reg = prepStmt.executeUpdate();
    	liberarRecursosBD();
    	return reg;
    }
    
    
    public void removerPermissao(Usuario u) {
    	
    }
    
    
    public void desautorizarCCs(Usuario u) {
    	
    }
    
    

}
