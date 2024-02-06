package org.example.server;

import org.example.Contact;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class PhonebookServerHandler implements Runnable {
    private final DatagramPacket packet;
    private final DatagramSocket socket;
    public PhonebookServerHandler(DatagramPacket packet, DatagramSocket socket) {
        this.packet = packet;
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            socket.receive(packet);
            String message = new String(packet.getData(), 0, packet.getLength(), "UTF-8");
            if (message.startsWith("ADD")) {
                String[] parts = message.split(" ");
                if (parts.length == 3) {
                    String name = parts[1];
                    String number = parts[2];
                    if (PhoneBook.addContact(new Contact(name, number))) {
                        message = "ACCEPTED";
                    } else {
                        message = "REJECTED";
                    }
                } else {
                    message = "ERROR";
                }
            } else if (message.startsWith("FIND")) {
                String[] parts = message.split(" ");
                if (parts.length == 2) {
                    String name = parts[1];
                    String number = PhoneBook.findContact(name);
                    if (number != null) {
                        message = "ACCEPTED " + number;
                    } else {
                        message = "REJECTED";
                    }
                } else {
                    message = "ERROR";
                }
            } else if (message.startsWith("EXIT")) {
                message = "BYE";
            } else {
                message = "INVALID";
            }
            packet.setData(message.getBytes());
            socket.send(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
