package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class AppClientHandler implements Runnable{
    private final Socket serverSocket;

    public AppClientHandler(Socket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        sendCommand();
    }

    private void getCommand() {
        try {
            DataInputStream input = new DataInputStream(serverSocket.getInputStream());
            String response = input.readUTF();
            while (!response.equals("QUIT")) {
                System.out.println("Server response: " + response);
                if (response.equals("QUIT")) {
                    System.exit(5);
                }
                sendCommand();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void sendCommand() {
        try {
            DataOutputStream output = new DataOutputStream(serverSocket.getOutputStream());
            System.out.println("Write command: ");
            Scanner sc = new Scanner(System.in);
            String command = sc.nextLine();
            output.writeUTF(command);
            getCommand();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
