package constran.pleitos.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import constran.pleitos.dao.UsuarioDAO;
import constran.pleitos.model.Usuario;
import constran.pleitos.util.AbstractController;
import constran.pleitos.util.ServerResponse;


@Path("/restrito/login")
public class LoginServices extends AbstractController{

	Usuario u;
	
	@GET
	@Path("/logoff")
	@Produces(MediaType.APPLICATION_JSON)
	public Response sair() throws IOException 
	{
		request.getSession().invalidate();
		return Response.status(Status.OK).entity(new ServerResponse(request.getContextPath()+"/index.html")).build();
	}

	
	@POST
	@Path("/autenticar")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response autenticarUsuario(@FormParam("login")String login, @FormParam("senha") String senha) 
	{
		Response response = null;
		request.getSession().invalidate();
		
		if(login == null || login.length() == 0) erroMsg.add("Usuário não foi informado.");
		
			if(senha == null || senha.length() == 0) erroMsg.add("Senha não foi informado.");
		
				if(erroMsg.size() > 0) return Response.status(Response.Status.BAD_REQUEST).entity(new ServerResponse(getErroMsg())).build();		
		try 
		{
			abreConexaoBD();
			UsuarioDAO dao = new UsuarioDAO(conn);
			u = dao.usuarioAutentico(login, senha);
			
			if(u == null) 
			{
				fechaConexaoBD(); 
				return Response.status(Response.Status.UNAUTHORIZED).entity(new ServerResponse("Login ou senha inválidos.")).build();
			}
			request.getSession().setAttribute("usuario", u);
			request.getSession().setMaxInactiveInterval(60*20); 
			response = Response.status(Status.OK).entity(new ServerResponse("restrito/inicio.html",true)).build();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			response = Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(new ServerResponse("Ocorreu um erro ao processar o login: \n\n"+ e.getClass().getName()+"\n"+e.getMessage())).build();			
		}
		finally
		{
			fechaConexaoBD();
		}	
		return response;
	}	
}
