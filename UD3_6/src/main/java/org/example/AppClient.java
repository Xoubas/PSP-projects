package org.example;

import java.io.IOException;
import java.net.Socket;

public class AppClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 49154;

        try (Socket socket = new Socket(serverAddress, port)){
            Thread t1 = new Thread(new AppClientHandler(socket));
            Thread t2 = new Thread(new AppClientHandler(socket));
            Thread t3 = new Thread(new AppClientHandler(socket));

            t1.start();
//            t2.start();
//            t3.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
