package org.httpsystemout.server;


import com.sun.net.httpserver.HttpServer;
import org.httpsystemout.view.HttpView;

import java.net.InetSocketAddress;

/**
 * A simple Sun-based servlet class with a HttpView to return handler producing response
 */
public class SimpleHttpServlet {

    private HttpServer server = null;
    HttpView httpView;

    /**
     * Stert the servlet
     * @param port at the port
     * @param path context path
     * @param httpView view with HttpHandler
     */
    public synchronized void start(int port, String path, HttpView httpView) {
        this.httpView = httpView;
        try
        {
            server = HttpServer.create(new InetSocketAddress(port), 0);

            server.createContext(path, httpView.getHttpHandler());

            server.start();
        }
        catch (Throwable tr)
        {
            tr.printStackTrace();
        }
    }

    /**
     * Stop server, cleanup view
     */
    public void stop() {
        if (server != null) {
            server.stop(0);
            server = null;
        }
        httpView.stop();
    }

}
