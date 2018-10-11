package Connection;

import java.io.IOException;

/**
 * Test class.
 * Created by cordu on 10/11/2018.
 */
public class Test {
    public static void main(String arg[]) throws IOException {
        Server server = new Server();
        Client x = new Client();
        Thread a = new Thread(server);
        a.start();
        Thread b = new Thread(x);
        b.start();

    }
}
