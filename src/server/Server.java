
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mikołaj
 */
public class Server {

    public static final int PORT_NUMBER = 9877;
    
    public static void threadDownload(ServerSocket sock) throws IOException{
        while (true) {
                Socket socket = sock.accept();
                ThreadHandling thread = new ThreadHandling(socket);
                new Thread(thread).start();
        }
    }
    
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
            
            threadDownload(serverSocket);
        
        } catch (Exception e) {
            System.err.println("Server error: " + e);
        }
        
    }
    
}
