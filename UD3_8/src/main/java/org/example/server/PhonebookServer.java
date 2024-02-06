package org.example.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class PhonebookServer {
    private static final int PORT = 6090;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            while (true) {
                DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
                new Thread(new PhonebookServerHandler(packet, socket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
