package constran.pleitos.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import constran.pleitos.dao.CCDao;
import constran.pleitos.model.CentrosDeCusto;
import constran.pleitos.util.AbstractController;
import constran.pleitos.util.ServerResponse;


@Path("/restrito/cc")
public class CCServices extends AbstractController
{	
	@GET
	@Path("/listarDiretores")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarDiretores ()
	{
		Response resp = null;
		
		try 
		{
			abreConexaoBD();
			CCDao dao = new CCDao(conn);
			resp = Response.status(Response.Status.OK).entity(dao.listarDiretores()).build();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			resp = Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ServerResponse("Ocorreu um erro ao listar os Diretores: \n\n"+e.getMessage())).build();
		}
		finally 
		{
			fechaConexaoBD();
		}
		return resp;
	}
	
	
	@GET
	@Path("/listarCC")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarCC (){
		Response resp = null;
		
		if (noSession()) return redirectTo();
		
		try 
		{
			abreConexaoBD();
			CCDao dao = new CCDao(conn);
			List<CentrosDeCusto> listaCC = dao.listarCC(); 
			resp = Response.status(Response.Status.OK).entity(listaCC).build();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			resp = Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ServerResponse("Ocorreu um erro ao listar Centros de custos: \n\n"+e.getMessage())).build();
			
		}finally {
			fechaConexaoBD();
		}
		return resp;
	}
	
	
	@POST
	@Path("/salvar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response salvar (
			@DefaultValue("-1") @FormParam("_id_cc") String _id_cc,
			@DefaultValue("-1") @FormParam("id")String id,
			@DefaultValue("") @FormParam("descricao")String descricao,
			@DefaultValue("") @FormParam("porcentagemParticipa")String porcentagemParticipa,
			@DefaultValue("") @FormParam("_loginDiretor")String _loginDiretor)
	{
		Response resp = null;
		
		if (noSession()) return redirectTo();	
		
		if(id.length() == 0) erroMsg.add("Centro de custo é obrigatório");

		if(descricao.length() == 0) erroMsg.add("Descrição é um campo obrigatório.");
	
		if(porcentagemParticipa.length() == 0) erroMsg.add("Porcentagem é um campo obrigatório.");
		
		if(erroMsg.size() > 0) return Response.status(Response.Status.BAD_REQUEST).entity(new ServerResponse(getErroMsg())).build();
		
		try 
		{
			abreConexaoBD();
			CCDao dao = new CCDao(conn);
			String msg = "";	
			
			CentrosDeCusto cc = new CentrosDeCusto();
			cc.setId(id);
			cc.setDescricao(descricao);
			cc.setPorcentagemParticipa(porcentagemParticipa);
			cc.set_loginDiretor(_loginDiretor);
			
			 //if(Integer.valueOf(_id_cc) > 0 ){
			if(_id_cc.length() > 0 &&  !(_id_cc.equals("-1")))
			{
				 int reg = 0;
				 reg = dao.atualizar(cc);
				 msg = reg +" Registro atualizado com sucesso.";
			 }
			else
			 {
				 dao.salvar(cc);
				 msg = "Cadastrado com sucesso.";
			 }
			resp = Response.status(Status.OK).entity(new ServerResponse(msg)).build();
		} 
		catch (SQLException | ParseException  | NumberFormatException e) 
		{
			e.printStackTrace();
			resp = Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ServerResponse("Ocorreu um erro ao Salvar/ Atualizar o Centro de custo: \n\n"+e.getMessage())).build();
		}
		finally 
		{
			fechaConexaoBD();
		}
		return resp;
	}
}
