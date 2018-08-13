package ru.timestop.servlets;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ru.timestop.provider.ProviderFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;

/**
 * It's listener for logging events and another debug information
 *
 * @author NikolaevAS
 */
@WebListener()
public class ContextListener implements ServletContextListener {

    private final static Logger LOG = Logger.getLogger(ContextListener.class.getName());

    /**
     * Initialize log4j when the application is being started
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        // initialize log4j here
        ServletContext context = event.getServletContext();
        String log4jConfigFile = context.getInitParameter("log4j-config-location");
        String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;

        PropertyConfigurator.configure(fullPath);

        try {
            Class.forName(ProviderFactory.class.getName());
        } catch (ClassNotFoundException e) {
            LOG.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        ProviderFactory.close();
    }
}