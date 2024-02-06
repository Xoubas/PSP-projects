package org.example;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class RandomWordClient {
    public static void main(String[] args) {

        final int SERVER_PORT = 60000;
        //buffer to store the messages
        byte[] buffer = new byte[1024];

        Scanner scanner = new Scanner(System.in);

        try {
            //create the socket
            DatagramSocket socket = new DatagramSocket();
            //create the datagram packet
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            DatagramPacket packet2 = new DatagramPacket(buffer, buffer.length);
            //set the destination port
            packet.setPort(SERVER_PORT);
            //set the destination address
            packet.setAddress(InetAddress.getByName("localhost"));
            //set the message
            System.out.println("Enter a message: ");
            packet.setData(scanner.nextLine().toString().toUpperCase().getBytes());
            //send the message
            socket.send(packet);
            //receive the message
            socket.receive(packet2);
            //print the message
            System.out.println(new String(packet2.getData(),0,packet2.getLength(),"UTF-8"));
            //close the socket
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
