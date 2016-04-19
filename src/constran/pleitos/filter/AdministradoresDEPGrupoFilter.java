package constran.pleitos.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constran.pleitos.model.Usuario;



public class AdministradoresDEPGrupoFilter implements Filter {


    public AdministradoresDEPGrupoFilter() {

    }


	public void destroy() {

	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		
		Usuario u = (Usuario)session.getAttribute("usuario");

		//if (u == null || !u.getGrupo().equals("ADMINISTRADORES_DEP")){
		
		//NAO TERAO ACESSOS USUARIOS QUE NAO POSSUIREM OS GRUPOS "ADMINISTRADORES_DEP" E "ADMINISTRADORES_SYS"
		
		if (u == null || !u.contemGrupo("ADMINISTRADORES_DEP") && !u.contemGrupo("ADMINISTRADORES_SYS")){
			
			res.sendRedirect(req.getContextPath()+"/index.html");
		}
		else {
			
			chain.doFilter(request, response);
		}
	}


	public void init(FilterConfig fConfig) throws ServletException {

	}

}
