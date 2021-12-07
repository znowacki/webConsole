package org.httpsystemout.view;

import com.sun.net.httpserver.HttpHandler;

public interface HttpView {

    /**
     * Return view handler
     * @return HttpHandler
     */
    HttpHandler getHttpHandler();

    /**
     * Stop handling, cleanup
     */
    void stop();
}
