package constran.pleitos.controller;

import java.sql.SQLException;
import java.util.Arrays;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import constran.pleitos.dao.CCDao;
import constran.pleitos.dao.UsuarioDAO;
import constran.pleitos.model.Usuario;
import constran.pleitos.util.AbstractController;
import constran.pleitos.util.ServerResponse;

@Path("/restrito/usuarios")
public class UsuarioServices extends AbstractController{
	
	
	//java.lang.OutOfMemoryError: PermGen space
	
	@GET
	@Path("/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarTodos()
	{
		if (noSession()) return redirectTo();
		
		Response resp = null;
		
		try 
		{
			abreConexaoBD();
			UsuarioDAO dao = new UsuarioDAO(conn);
			resp = Response.status(Status.OK).entity(dao.listarTodos()).build();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			resp = Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ServerResponse("Ocorreu um erro ao listar Usuários. \n\n"+e.getMessage())).build();
		}
		finally
		{
			fechaConexaoBD();
		}
		return resp;
	}
	
	
	
	@GET
	@Path("/listarCCcadastrados")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarCCcadastrados()
	{
		if (noSession()) return redirectTo();
		
		Response resp = null;
		
		try 
		{
			abreConexaoBD();
			CCDao dao = new CCDao(conn);
			resp = Response.status(Status.OK).entity(dao.listarTodos()).build();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			resp = Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ServerResponse("Ocorreu um erro ao listar os centros de custo. \n\n"+e.getMessage())).build();
		}
		finally
		{
			fechaConexaoBD();
		}
		return resp;
	}
	
	
	
	@GET
	@Path("/listarTipos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarTipos()
	{
		if (noSession()) return redirectTo();
		
		Response resp = null;
		
		try 
		{
			abreConexaoBD();
			
			UsuarioDAO dao = new UsuarioDAO(conn);
			resp = Response.status(Status.OK).entity(dao.listarTipos()).build();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			resp = Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ServerResponse("Ocorreu um erro ao listar os tipos de usuários. \n\n"+e.getMessage())).build();
		
		}finally
		{
			
			fechaConexaoBD();
		}
		return resp;
	}
	
	
	
	@GET
	@Path("/listarGrupos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarGrupos()
	{
		if (noSession()) return redirectTo();
		
		Response resp = null;
		
		try 
		{
			abreConexaoBD();
			UsuarioDAO dao = new UsuarioDAO(conn);
			resp = Response.status(Status.OK).entity(dao.listarGrupos()).build();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			resp = Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ServerResponse("Ocorreu um erro ao listar os grupos de usuários. \n\n"+e.getMessage())).build();
		}
		finally
		{
			
			fechaConexaoBD();
		}
		return resp;
	}
	
	
	
	@POST
	@Path("/salvar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response salvar(/*@DefaultValue("-1") @FormParam("_id_login")String _id_login,*/
						@DefaultValue("") @FormParam("login")String login,
						@DefaultValue("") @FormParam("nome")String nome,
						@DefaultValue("") @FormParam("senha")String senha,
						@DefaultValue("S") @FormParam("ativo")String ativo,
						@DefaultValue("") @FormParam("tipo")String tipo,
						@DefaultValue("") @FormParam("grupos")String grupos,
						//@DefaultValue("S") @FormParam("permitir")String permitir,
						@DefaultValue("") @FormParam("autorizarCCs")String autorizarCCs
						
			)
	{
		if (noSession()) return redirectTo();
		
		//Usuario uLogado = (Usuario)request.getSession().getAttribute("usuario");
		
		if(nome.length() == 0) erroMsg.add("Nome é obrigatório.");
		
		if(login.length() == 0) erroMsg.add("Login é obrigatório.");
		
		//if(!uLogado.getLogin().equals(login))erroMsg.add("Usuario informado é diferente do usuário logado.");
		
		if(tipo.length() == 0) erroMsg.add("Tipo é obrigatório.");
		
		if(grupos.length() == 0) erroMsg.add("Grupo é obrigatório.");
		
		//if(permitir.length() == 0) erroMsg.add("Permitir é um campo obrigatório.");
		
		if(autorizarCCs.length() == 0) erroMsg.add("Selecione pelo menos 1 centro de custo.");
		
		if(erroMsg.size() > 0) return Response.status(Response.Status.BAD_REQUEST).entity(new ServerResponse(getErroMsg())).build();
		
		Usuario u = new Usuario();
		u.setNome(nome);
		u.setLogin(login);
		u.setSenha(senha);
		u.setAtivo(ativo);
		u.setTipo(tipo);
		u.setGrupos(Arrays.asList(grupos.split(",")));
		u.setListaCC(Arrays.asList(autorizarCCs.split(",")));
		//u.setPermitirAcesso(permitir);

		Response resp = null;
		String msg = "";
		
		try 
		{
			abreConexaoBD();
			desabilitarBDautoCommit();
			UsuarioDAO usrDAO = new UsuarioDAO(conn);

			if(usrDAO.isUpdate(u.getLogin()))
			{
				//atualizar na tabela usuarios
				int regU = usrDAO.atualizarUsuario(u);
				
				//atualizar em usuario_permissoes
				int regP = usrDAO.atualizarPermissao(u);
				
				//atualizar em usuario_centro_de_custos
				int regCC = usrDAO.atualizarAutorizarCCs(u);
				
				if(regU > 0 && regP > 0 && regCC > 0)
				{
					
					dbCommit();
					msg = "Atualizado com sucesso!";
				}
				else
				{
					dbRollback();
					msg = "O Registro atual não pode ser salvo.";
				}
			}
			else
			{
				//cadastrar na tabela usuarios
				usrDAO.cadastrarUsuario(u);
				
				//cadastrar em usuario_permissoes
				usrDAO.cadastrarPermissao(u);
				
				//cadastrar em usuario_centro_de_custos
				usrDAO.autorizarCCs(u);
				
				dbCommit();
				
				msg = "Cadastrado com sucesso!";
			}
			resp = Response.status(Status.OK).entity(new ServerResponse(msg)).build();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			dbRollback();
			msg = "Ocorreu um erro ao salvar/ atualizar o registro.";
			resp = Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ServerResponse(msg+"\n"+e.getMessage())).build();
		}
		finally
		{
			fechaConexaoBD();
		}
		return resp;
	}
	
	
	
	@DELETE
	@Path("/excluir/{login}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response excluir(@PathParam("id")String login)
	{
		if (noSession()) return redirectTo();
		
		Response resp = null;
		
		return resp;
	}
}
