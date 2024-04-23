package org.example;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ContactServerWorker implements Runnable {

    final int SERVER_PORT = 60000;
    DatagramSocket socket;
    DatagramPacket packet;

    byte[] buffer = new byte[1024];

    public ContactServerWorker(DatagramSocket socket, DatagramPacket packet) {
        this.socket = socket;
        this.packet = packet;
    }

    @Override
    public void run() {
        try {
            socket.receive(packet);
            String message = new String(packet.getData(), 0, packet.getLength(), "UTF-8");

            if (message.startsWith("ADD")) {
                String[] parts = message.split(" ");
                String command = parts[0];
                String name = parts[1];
                int telephone = Integer.parseInt(parts[2]);

                System.out.println("Los datos que se ha leido son -> Name: " + name + " Telephone: " + telephone);

            } else if (message.startsWith("FIND")) {
                String[] parts = message.split(" ");
                String command = parts[0];
                String name = parts[1];
                System.out.println("El nombre que se ha leido es -> Name: " + name);

            } else if (message.startsWith("EXIT")) {
                System.out.println("El cliente ha cerrado la conexion");


            }else {
                message = "Hello from the server";
                packet.setData(message.getBytes());
                socket.send(packet);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
