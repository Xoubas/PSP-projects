package org.example.client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class PhonebookClient {

    private static final int PORT = 6090;

    public static void main(String[] args) {

        try {
            DatagramSocket socket = new DatagramSocket();

            DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
            DatagramPacket packet2 = new DatagramPacket(new byte[1024], 1024);

            packet.setPort(PORT);
            packet.setAddress(java.net.InetAddress.getByName("localhost"));

            System.out.println("Enter a message: ");
            packet.setData(new java.util.Scanner(System.in).nextLine().toUpperCase().getBytes());

            socket.send(packet);

            socket.receive(packet2);

            System.out.println(new String(packet2.getData(), 0, packet2.getLength(), "UTF-8"));

            socket.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
