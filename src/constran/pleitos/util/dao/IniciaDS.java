package constran.pleitos.util.dao;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class IniciaDS implements ServletContextListener {


	
    public IniciaDS() {

    }
    
    
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		ConnectionFactory.init();
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}
}
