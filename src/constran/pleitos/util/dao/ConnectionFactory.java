package constran.pleitos.util.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.sql.DataSource;

public class ConnectionFactory {

    private static DataSource ds;
	
	protected static void init(){
	
		String jndiPath = "java:comp/env/jdbc/pleitos_db";
		Context ctx;
		
		System.out.println("Inicializando datasource: "+jndiPath);
		
		try {
			ctx = new InitialContext();
			ds = (DataSource) PortableRemoteObject.narrow(ctx.lookup(jndiPath), DataSource.class);
		} 
		catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	public static Connection getConnection() throws SQLException{
		return ds.getConnection();
	}
	
	
	public static void closeConnection(Connection conn){
		
		if(conn!= null){
		
			try {
				
				if(!conn.isClosed())
					conn.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
