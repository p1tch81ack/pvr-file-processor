package org.shullfamily.pvrfileprocessor;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.net.URL;

//--ini=c:\Comskip\comskip.ini

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Server jettyServer = null;
        try {
            jettyServer = new Server(8081);
            ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
            servletContextHandler.setContextPath("/");

            ServletHolder jerseyServlet = servletContextHandler.addServlet(
                    org.glassfish.jersey.servlet.ServletContainer.class, "/*");
            jerseyServlet.setInitOrder(0);

            // Tells the Jersey Servlet which REST service/class to load.
            jerseyServlet.setInitParameter(
                    "jersey.config.server.provider.classnames",
                    TestingRESTService.class.getCanonicalName()
                            + ";" + Foo.class.getCanonicalName());

            ResourceHandler resourceHandler= new ResourceHandler();
            URL webappUrl = App.class.getClassLoader().getResource("webapp");
            if(webappUrl != null) {
                resourceHandler.setResourceBase(webappUrl.toExternalForm());
            }
            resourceHandler.setDirectoriesListed(true);
            resourceHandler.setHandler(servletContextHandler);
            resourceHandler.setWelcomeFiles(new String[]{"index.html"});

            jettyServer.setHandler(resourceHandler);

            jettyServer.start();
            jettyServer.join();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
        }
        finally {
            if(jettyServer != null) {
                jettyServer.destroy();
            }
        }
    }

}
