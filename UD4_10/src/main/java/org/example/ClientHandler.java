package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler {
    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void sendCommand() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce a command: ");
        String command = scanner.nextLine();
        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(command);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        getInfo();
    }

    public void getInfo(){
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            System.out.println(in.readUTF());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
