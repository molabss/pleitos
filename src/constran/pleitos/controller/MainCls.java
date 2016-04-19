package constran.pleitos.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


@Path("/hello")
public class MainCls {

	
	  @GET
	  @Path("/say")
	  @Produces(MediaType.TEXT_HTML)
	  public String sayHtmlHello() {
	    return "<html> " + "<title>" + "Hello Jersey" + "</title>"
	        + "<body><h1>" + "Hello Jersey" + "</body></h1>" + "</html> ";
	  }
	  
	  
	  @GET
	  @Path("/say2")
	  @Produces(MediaType.APPLICATION_JSON)
	  public String sayHtmlHello2() {
	    return "<html> " + "<title>" + "Hello Jersey" + "</title>"
	        + "<body><h1>" + "Hello Jersey" + "</body></h1>" + "</html> ";
	  }
	  
	  
	  @GET
	  @Path("/say3")
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response sayHtmlHello3() {

		  List<String> lista = new ArrayList<String>();
		  
		  lista.add("oiaoia");
		  return Response.status(Status.OK).entity(lista).build();
	  }
}
