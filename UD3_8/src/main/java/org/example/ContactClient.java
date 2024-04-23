package org.example;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

//class to launch the client
public class ContactClient {

    //class to launch the client class
    public static void main(String[] args) {
        final int SERVER_PORT = 60000;

        byte[] buffer = new byte[1024];

        Scanner sc = new Scanner(System.in);

        try {
            //create the socket
            DatagramSocket socket = new DatagramSocket();

            //creamos el packet para el nombre

            DatagramPacket packetName = new DatagramPacket(buffer, buffer.length);
            //creamos el packet para el telefono
            DatagramPacket packetPhone = new DatagramPacket(buffer, buffer.length);

            //datagramas para las respuestas
            DatagramPacket packetNameResponse = new DatagramPacket(buffer, buffer.length);
            DatagramPacket packetPhoneResponse = new DatagramPacket(buffer, buffer.length);

            //Set the destination port
            packetName.setPort(SERVER_PORT);
            //setr the destination address
            packetPhone.setAddress(InetAddress.getByName("localhost"));
            //Sende the message1
            socket.send(packetName);
            socket.send(packetPhone);
            //recieve the message
            socket.receive(packetNameResponse);
            socket.receive(packetPhoneResponse);

            //print the message
            System.out.println(new String(packetNameResponse.getData(), 0, packetNameResponse.getLength(), "UTF-8"));
            System.out.println(new String(packetPhoneResponse.getData(), 0, packetPhoneResponse.getLength(), "UTF-8"));

            socket.close();

        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
