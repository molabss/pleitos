package constran.pleitos.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import constran.pleitos.model.Usuario;
import constran.pleitos.util.AbstractController;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Path("/restrito/relatorios")
public class RelatorioServices extends AbstractController{
	
	@GET
	@Path("/relPleitos")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public Response gerarRelPleitos(@QueryParam("cc")String cc)
	{

		Usuario u = (Usuario)request.getSession().getAttribute("usuario");
		
		
		if(u == null || !u.contemCC(cc))
		{
			return Response.status(Response.Status.UNAUTHORIZED).entity("<h1>Voc&ecirc; n&atilde;o tem autoriza&ccedil;&atilde;o para gerar relat&oacute;rio deste Centro de Custo.<h1>").build();
		}
		
		
		try 
		{
			abreConexaoBD();
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("ccObra", cc);
			parametros.put("IMAGE_DIR", request.getServletContext().getRealPath("/restrito")+"/img/constran.png");
			parametros.put("SUBREPORT_DIR", request.getServletContext().getRealPath("/restrito")+"/report/");
			
			JasperReport report = JasperCompileManager .compileReport(request.getServletContext().getRealPath("/restrito")+"/report/relPleitos.jrxml");
			JasperPrint print = JasperFillManager.fillReport(report, parametros, conn);
			
			response.setContentType("application/pdf");
			
			OutputStream out = response.getOutputStream();
				
			JasperExportManager.exportReportToPdfStream(print,out);
			
			out.close();
		} 
		catch (JRException | SQLException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			fechaConexaoBD();
		}
		return null;
	}
}
