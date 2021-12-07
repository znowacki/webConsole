package org.httpsystemout.view;

import com.sun.net.httpserver.HttpHandler;
import org.httpsystemout.stream.SplitPrintStream;
import org.httpsystemout.stream.WindowedRingBufferStream;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

/**
 * System.out view to observe the stream.
 * Standard stream is replaced with another stream class instance to get a view.
 * Call stop() to return to previous state and stop observation.
 */
public class SystemOutView implements HttpView {

    public static final String ENCODING = "UTF-8";
    protected PrintStream formerOutStream = null;

    WindowedRingBufferStream buffer;
    int maxLines;

    /**
     * Starts SystemOut view. The view is limited by number of lines and buffer size
     * System.out stream is replaced with buffered stream having a window (storage) for data
     * @param maxLines maximum number of lines to handle
     * @param windowSize maximum number of characters to buffer
     */
    public SystemOutView(int maxLines, int windowSize) {
        this.maxLines = maxLines;
        initializeBuffer(windowSize);
    }

    protected synchronized void initializeBuffer(int windowSize) {
        buffer = new WindowedRingBufferStream(windowSize,2);
        formerOutStream = System.out;
        System.setOut( new SplitPrintStream( new PrintStream(buffer), System.out));
    }


    @Override
    public HttpHandler getHttpHandler() {
        return httpExchange -> {
            byte[] response = "Web console:\n".getBytes(ENCODING);

            httpExchange.getResponseHeaders().add("Content-Type", "text/plain; charset=UTF-8");
            httpExchange.sendResponseHeaders(200, 0);

            OutputStream out = httpExchange.getResponseBody();
            out.write(response);
            List<String> console = buffer.windowToLines(maxLines, ENCODING);
            for (String s : console) {
                out.write(s.getBytes(ENCODING));
                out.write('\n');
            }
            out.close();
        };
    }

    @Override
    public void stop() {
        if (formerOutStream != null) {
            System.setOut(formerOutStream);
            formerOutStream = null;
        }
    }

}
