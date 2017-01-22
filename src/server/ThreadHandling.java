
package server;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mikołaj
 */
public class ThreadHandling implements Runnable {

    Socket socket;

    ThreadHandling(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        Object object = null;
        try {
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            object = objectInputStream.readObject();
            FileOutputStream fileOutputStream = new FileOutputStream(object.toString());

            byte[] buffer = new byte[500];
            int dataSize = 500;
            while ((dataSize = objectInputStream.read(buffer)) > -1)
            {
                fileOutputStream.write(buffer, 0, dataSize);
            }

            objectInputStream.close();
            fileOutputStream.close();
            System.out.println("Transfer completed");

        } catch (Exception e) {
            System.err.println("Thread error: " + e);
        }
    }
}
