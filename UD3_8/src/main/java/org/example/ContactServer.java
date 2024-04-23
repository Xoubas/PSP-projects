package org.example;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ContactServer {
    public static void main(String[] args) {
        final int PORT = 60000;

        try(DatagramSocket socket = new DatagramSocket(PORT)) {

            while (true) {
                DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
                Thread threadServer = new Thread(new ContactServerWorker(socket, packet));
                threadServer.start();
            }



        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

}
