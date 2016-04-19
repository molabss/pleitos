package constran.pleitos.controller;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import constran.pleitos.dao.PleitoDAO;
import constran.pleitos.model.CentrosDeCusto;
import constran.pleitos.model.Condicao;
import constran.pleitos.model.Pleito;
import constran.pleitos.model.Tipo;
import constran.pleitos.model.Usuario;
import constran.pleitos.util.AbstractController;
import constran.pleitos.util.ServerResponse;


@Path("/restrito/pleito")
public class PleitoServices extends AbstractController
{
	
	@GET
	@Path("/listarCC")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarCC()
	{
		if (noSession()) return redirectTo();
		
		Usuario u = (Usuario)request.getSession().getAttribute("usuario");
		return Response.status(Response.Status.OK).entity(u.getListaCC()).build();
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
			PleitoDAO dao = new PleitoDAO(conn);
			resp = Response.status(Response.Status.OK).entity(dao.listarTipos()).build();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			resp = Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ServerResponse("Ocorreu um erro ao listar os tipos: \n\n"+e.getMessage())).build();
		}
		finally
		{
			fechaConexaoBD();
		}
		return resp;
	}

	
	
	@GET
	@Path("/listarStatus")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarStatus()
	{
		if (noSession()) return redirectTo();

		Response resp = null;
		
		try 
		{
			abreConexaoBD();
			PleitoDAO dao = new PleitoDAO(conn);
			resp = Response.status(Response.Status.OK).entity(dao.listarStatus()).build();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			resp = Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ServerResponse("Ocorreu um erro ao listar os status: \n\n"+e.getMessage())).build();
		}
		finally
		{
			fechaConexaoBD();
		}
		return resp;
	}
	
	
	
	@GET
	@Path("/listarCondicoes")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarCondicoes()
	{
		if (noSession()) return redirectTo();

		Response resp = null;
		
		try 
		{
			abreConexaoBD();
			PleitoDAO dao = new PleitoDAO(conn);
			resp = Response.status(Response.Status.OK).entity(dao.listarCondicao()).build();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			resp = Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ServerResponse("Ocorreu um erro ao listar as Condições: \n\n"+e.getMessage())).build();
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
	public Response salvar(
			@DefaultValue("-1") @FormParam("id")String id,
			@DefaultValue("") @FormParam("cc")String cc,
			@DefaultValue("") @FormParam("tipo")String tipo,
			@DefaultValue("") @FormParam("status")String status,
			@DefaultValue("") @FormParam("condicao")String condicao,
			@FormParam("numDocumento")String numDocumento,
			@DefaultValue("") @FormParam("descricao")String descricao,
			@FormParam("dataEntrega")String dataEntrega,
			@DefaultValue("") @FormParam("colocadoEmP0")String colocadoEmP0,
			@FormParam("reajustadoAteAdata")String reajustadoAteAdata,
			@FormParam("esperadoEmP0_A")String esperadoEmP0_A,
			@FormParam("esperadoEmP0_B")String esperadoEmP0_B,
			@FormParam("custoAgregadoNoMinimoEsperadoP0")String custoAgregadoNoMinimoEsperadoP0,
			@FormParam("livreDeCustosNoMinimoEsperadoP0")String livreDeCustosNoMinimoEsperadoP0,
			@FormParam("minimoEsperadoReajustado")String minimoEsperadoReajustado,
			@FormParam("custoAgregadoNoMinimoEsperadoReajustado")String custoAgregadoNoMinimoEsperadoReajustado,
			@FormParam("livreDeCustosNoMinimoEsperadoReajustado")String livreDeCustosNoMinimoEsperadoReajustado,
			
			@FormParam("dtPrevFinalizaAprovacaoCancelada")String dtPrevFinalizaAprovacaoCancelada,
			@FormParam("jaRecebidoAteDataP0")String jaRecebidoAteDataP0,
			@FormParam("saldoAreceberP0")String saldoAreceberP0,
			@FormParam("jaRecebidoAteDTreajus")String jaRecebidoAteDTreajus,
			@FormParam("saldoAreceberReajustado")String saldoAreceberReajustado,
			@FormParam("dtPrevRecebimento")String dtPrevRecebimento,
			@FormParam("inclusoAPS")String inclusoAPS,
			@FormParam("obs")String obs,
			@FormParam("nuAdidito_parteDaVerba") String nuAdidito_parteDaVerba)
		{
		
		if (noSession()) return redirectTo();
		
		Response resp = null;
		
		if(cc.length() == 0) erroMsg.add("Centro de custo é obrigatório");

			if(tipo.length() == 0) erroMsg.add("Tipo é um campo obrigatório.");
		
				if(condicao.length() == 0) erroMsg.add("Condição é um campo obrigatório.");
		
					if(descricao.length() == 0) erroMsg.add("Descrição é um campo obrigatório.");
		
						if(dataEntrega.length() == 0) erroMsg.add("Data de entrega é um campo obrigatório.");
		
							if(colocadoEmP0.length() == 0) erroMsg.add("Colocado P0 é um campo obrigatório.");
				
		if(erroMsg.size() > 0) return Response.status(Response.Status.BAD_REQUEST).entity(new ServerResponse(getErroMsg())).build();
	

		try
		{
			
			abreConexaoBD();
			
			PleitoDAO dao = new PleitoDAO(conn);
			Pleito p = new Pleito();
			
			p.setCc(new CentrosDeCusto(cc.trim()));
			p.setTipo(new Tipo(Integer.parseInt(tipo)));
			p.setStatus(new constran.pleitos.model.Status(Integer.parseInt(status)));
			p.setCondicao(new Condicao(Integer.parseInt(condicao)));
			p.setNumDocumento(numDocumento);
			p.setDescricao(descricao);

			//-------------------------------------------------------------------------------------------- 
			Date initDate = new SimpleDateFormat("dd/MM/yyyy").parse(dataEntrega);
			SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd");
			String parsedDate = formatterDate.format(initDate);
			  
			p.setDtEntrega(parsedDate);
			//--------------------------------------------------------------------------------------------
			
			
			p.setColocadoEmP0(new BigDecimal(colocadoEmP0));
			 
			p.setReajustadoAteAData(new BigDecimal(reajustadoAteAdata));
			 
			p.setEsperadoEmP0_A(new BigDecimal(esperadoEmP0_A));
			 
			p.setEsperadoEmP0_B(new BigDecimal(esperadoEmP0_B));
			 
			p.setCustoAgregadoNoMinimoEsperadoP0(new BigDecimal(custoAgregadoNoMinimoEsperadoP0));
			 
			p.setLivreDeCustosNoMinimoEsperadoP0(new BigDecimal(livreDeCustosNoMinimoEsperadoP0));
			 
			p.setMinimoEsperadoReajustado(new BigDecimal(minimoEsperadoReajustado));
			 
			p.setCustoAgregadoNoMinimoEsperadoReajustado(new BigDecimal(custoAgregadoNoMinimoEsperadoReajustado));
			 
			p.setLivreDeCustosNoMinimoEsperadoReajustado(new BigDecimal(livreDeCustosNoMinimoEsperadoReajustado));
			 
			//--------------------------------------------------------------------------------------------
			initDate = new SimpleDateFormat("dd/MM/yyyy").parse(dtPrevFinalizaAprovacaoCancelada);
			formatterDate = new SimpleDateFormat("yyyy-MM-dd");
			parsedDate = formatterDate.format(initDate);

			p.setDtPrevFinalizaAprovacaoCancelada(parsedDate);
			//--------------------------------------------------------------------------------------------
			 
			 p.setJaRecebidoAteDataP0(new BigDecimal(jaRecebidoAteDataP0));
			 
			 p.setSaldoAreceberP0(new BigDecimal(saldoAreceberP0));
			 
			 p.setJaRecebidoAteDTreajus(new BigDecimal(jaRecebidoAteDTreajus));

			 p.setSaldoAreceberReajustado(new BigDecimal(saldoAreceberReajustado));

			 //--------------------------------------------------------------------------------------------
			 initDate = new SimpleDateFormat("dd/MM/yyyy").parse(dtPrevRecebimento);
			 formatterDate = new SimpleDateFormat("yyyy-MM-dd");
			 parsedDate = formatterDate.format(initDate);
			 
			 p.setDtPrevRecebimento(parsedDate);
			 //--------------------------------------------------------------------------------------------			
			
			 
			 p.setInclusoAPS(inclusoAPS);
			 
			 p.setObs(obs);
			 
			 p.setNuAdidito_parteDaVerba(nuAdidito_parteDaVerba);
			 
			 
			 p.setId(Integer.parseInt(id));

			 String msg = "";
			 
			 if(p.getId() > 0 )
			 {
				 int reg = 0;
				 reg = dao.atualizarPleito(p);
				 msg = reg +" Registro atualizado com sucesso.";
			 }
			 else
			 {
				 dao.salvarPleito(p);
				 msg = "Cadastrado com sucesso.";
			 }
			resp = Response.status(Status.OK).entity(new ServerResponse(msg)).build();
		} 
		catch (SQLException | ParseException  | NumberFormatException e) 
		{
			e.printStackTrace();
			resp = Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ServerResponse("Ocorreu um erro ao cadastrar/ atualizar o pleito. \n\n"+e.getMessage())).build();
		}
		finally
		{
			fechaConexaoBD();
		}
		return resp;
	}
	
	
	@GET
	@Path("/listarPleitos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarPleitos()
	{
		if (noSession()) return redirectTo();
		
		Response resp = null;
		
		Usuario u = (Usuario)request.getSession().getAttribute("usuario");
		
		try 
		{
			abreConexaoBD();
			PleitoDAO dao = new PleitoDAO(conn);
			
			if(u.getListaCC().size() == 0)
			{
				resp = Response.status(Status.NOT_FOUND).entity(new ServerResponse("Nenhum Centro de Custo está associado ao login do usuário.")).build();
			}
			else
			{
				resp = Response.status(Status.OK).entity(dao.listarPleitos(u.getListaCC())).build();
			}
		} 
		catch (SQLException | ParseException e) 
		{
			e.printStackTrace();
			resp = Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ServerResponse("Ocorreu um erro ao listar os pleitos: \n\n"+e.getMessage())).build();
		}
		finally
		{
			fechaConexaoBD();
		}
		return resp;
	}
	
	@DELETE
	@Path("/excluir/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response excluir(@PathParam("id")String id)
	{
		if (noSession()) return redirectTo();
		
		Response resp = null;
		
		try 
		{
			abreConexaoBD();
			int reg = 0;
			PleitoDAO dao = new PleitoDAO(conn);
			reg = dao.deleteFalse(id);
			resp = Response.status(Status.OK).entity(new ServerResponse(reg+" "+"Registro foi excluído com sucesso.")).build();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			resp = Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ServerResponse("Ocorreu um erro ao excluir o pleito: \n\n"+e.getMessage())).build();
		}
		finally
		{
			fechaConexaoBD();
		}
		return resp;
	}
}