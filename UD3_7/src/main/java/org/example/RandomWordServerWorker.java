package org.example;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

import static org.example.RandomWordServer.getRandomWord;

public class RandomWordServerWorker implements Runnable {
    final int SERVER_PORT = 60000;
    DatagramSocket socket;
    DatagramPacket packet;
    byte[] buffer = new byte[1024];


    public RandomWordServerWorker(DatagramSocket socket, DatagramPacket packet) {
        this.socket = socket;
        this.packet = packet;
    }


    @Override
    public void run() {

        try{
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength(), "UTF-8");
                if (message.startsWith("WORD")) {
                    String[] parts = message.split(" ");
                    int numberLetters = 0;
                    if (parts.length == 2) {
                        numberLetters = Integer.parseInt(parts[1]);
                    }
                    message = getRandomWord(numberLetters);
                    System.out.println(message);
                    packet.setData(message.getBytes());
                    socket.send(packet);


                } else {
                    message = "Hello from the server";
                    packet.setData(message.getBytes());
                    socket.send(packet);
                }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
