package constran.pleitos.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import constran.pleitos.model.Usuario;
import constran.pleitos.util.AbstractController;



@Path("/restrito/view")
public class ViewServices extends AbstractController{

	@GET
	@Path("/menuTopoInicio")
	@Produces(MediaType.TEXT_HTML)
	public Response getMenuTopoInicio(){
		
		if (noSession()) return redirectTo();
		
		Response resp = null;
		
		Usuario u = (Usuario)request.getSession().getAttribute("usuario");
		
		StringBuilder html = new StringBuilder("");
		
		//if(u.getGrupo().equals("ADMINISTRADORES_DEP") || u.getGrupo().equals("ADMINISTRADORES_SYS")){
		
		if(u.contemGrupo("ADMINISTRADORES_DEP") || u.contemGrupo("ADMINISTRADORES_SYS")){
			
			html.append("<ul>");
				html.append("<li>Pleitos");
				    html.append("<ul style='width: 220px;'>");
					html.append("<li><a href=\"#\" id=\"abreCadastroPleito\" onclick=\"$('#cadastro_pleito_window').jqxWindow('open');\">Cadastro</a></li>");
					html.append("<li>Exportar");
						html.append("<ul style='width: 220px;'>");
							html.append("<li><a href=\"#\" id=\"exportPdfLink\" onclick=\"$('#jqxgrid').jqxGrid('exportdata','xls','Pleito');\">XLS</a></li>");
							html.append("<li><a href=\"#\" id=\"exportCSVLink\" onclick=\"$('#jqxgrid').jqxGrid('exportdata','csv','Pleito');\">CSV</a></li>");
							html.append("<li><a href=\"#\" id=\"exportJsonLink\" onclick=\"$('#jqxgrid').jqxGrid('exportdata','json','Pleito');\">JSON</a></li>");
						html.append("</ul>");
					html.append("</li>");
					html.append("</ul>");
				html.append("</li>");           	
				html.append("<li><a href=\"cc.html\">Centro de Custos</a></li>");
				
				if(u.contemGrupo("ADMINISTRADORES_SYS")){
					html.append("<li><a href=\"usuarios.html\">Usuários</a></li>");
				}
				
				
				html.append("<li>Relatórios");
				html.append("<ul style='width: 220px;'>");
					html.append("<li><a href=\"relPeito.html\" id=\"\">Pleito</a></li>");
				html.append("</ul>");					
				
				
				html.append("<li><a href=\"#\" onclick=\"logoff();\">Sair</a></li>");
				html.append("</ul>");				
		html.append("</ul>");
			
		//}else if(u.getGrupo().equals("USUARIOS")){
		
		}else if(u.contemGrupo("USUARIOS")){
		
			html.append("<ul>");
			html.append("<li>Pleitos");
			html.append("<ul style='width: 220px;'>");
				html.append("<li><a href=\"#\" id=\"abreCadastroPleito\" onclick=\"$('#cadastro_pleito_window').jqxWindow('open');\">Cadastro</a></li>");
				html.append("<li>Exportar");
					html.append("<ul style='width: 220px;'>");
						html.append("<li><a href=\"#\" id=\"exportPdfLink\" onclick=\"$('#jqxgrid').jqxGrid('exportdata','xls','Pleito');\">XLS</a></li>");
						html.append("<li><a href=\"#\" id=\"exportCSVLink\" onclick=\"$('#jqxgrid').jqxGrid('exportdata','csv','Pleito');\">CSV</a></li>");
						html.append("<li><a href=\"#\" id=\"exportJsonLink\" onclick=\"$('#jqxgrid').jqxGrid('exportdata','json','Pleito');\">JSON</a></li>");                         
					html.append("</ul>");
				html.append("</li>");
				html.append("</ul>");
			html.append("</li>");

			
			html.append("<li>Relatórios");
			html.append("<ul style='width: 220px;'>");
				html.append("<li><a href=\"#\" id=\"\">Pleito</a></li>");
			html.append("</ul>");			
			
			
			html.append("<li><a href=\"#\" onclick=\"logoff();\">Sair</a></li>");
		html.append("</ul>");
		
		}
		
		resp = Response.status(Response.Status.OK).entity(html.toString()).build();
		
		return resp;
	}
	
	
	
	
}
