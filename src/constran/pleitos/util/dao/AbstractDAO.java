package constran.pleitos.util.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractDAO {
	
	public Connection conn = null;
	public PreparedStatement prepStmt = null;
	public Statement stmt = null;
	public ResultSet rs = null;
	public ResultSetMetaData rsmd = null;
	public CallableStatement cStmt = null;
	
	
	public AbstractDAO(Connection conn) 
	{
		this.conn = conn;
	}
	
	public void liberarRecursosBD() throws SQLException 
	{
		
		if(rs != null)
		{
			rs.close();
			rs = null;
		}
		
		if(rsmd != null)
		{
			rsmd = null;
		}
		
		if(stmt != null)
		{
			stmt.close();
			stmt = null;
		}
		
		if(prepStmt != null)
		{
			prepStmt.clearParameters();
			prepStmt.close();
			prepStmt = null;
		}
		
		if(cStmt != null)
		{
			cStmt.close();
			cStmt = null;
		}
	}
}
