package constran.pleitos.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


@WebListener
public class UserSessionListener implements HttpSessionListener, HttpSessionBindingListener {
	
    public UserSessionListener() {    }

    
    public void sessionCreated(HttpSessionEvent evt)  { 
    	//System.out.println("Sessao criada: "+evt.getSession().getId());
    }


    public void valueBound(HttpSessionBindingEvent evt)  { 
    	//System.out.println("valor adicionado");
    }


    public void sessionDestroyed(HttpSessionEvent evt)  { 
    	//System.out.println("Sessao destruida: "+evt.getSession().getId());
    }


    public void valueUnbound(HttpSessionBindingEvent evt)  { 
    	//System.out.println("valor removido");
    }
	
}
