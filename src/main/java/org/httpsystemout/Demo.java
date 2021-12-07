package org.httpsystemout;

import org.httpsystemout.server.SimpleHttpServlet;
import org.httpsystemout.view.SystemOutView;

import java.util.concurrent.TimeUnit;

/**
 * Run and take a look in the browser at localhost:8080
 */
public class Demo {

    public static final int HTTP_PORT = 8080;

    public static void main(String[] args) {
        SimpleHttpServlet console = new SimpleHttpServlet();
        console.start(HTTP_PORT, "/", new SystemOutView(40, 2000));

        int i = 1;
        while (true) {
            System.out.print( "Some generated line no: ");
            System.out.println(i);
            i++;
            if( i % 50 == 0) {
                System.out.println("Some veeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeery looooooooooooooooooooooooooooooooooooooooong additional line " +
                        "veeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeery looooooooooooooooooooooooooooooooooooooooong"+
                        " veeery veeery auch how long it is look here hahahahaha come on let read it  pleaseeeeeee, truly long read ;-)");
            }

            if( i>999) {
                console.stop();
                return;
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
