package org.example;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SquareClientHandler {
    private int number;
    private Socket serverSocket;

    public SquareClientHandler(Socket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void changeNumber() {
        try (PrintWriter pw = new PrintWriter(serverSocket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()))) {
            System.out.println("Write number: ");
            Scanner sc = new Scanner(System.in);
            number = sc.nextInt();
            pw.println(number);
            pw.flush();
            number = Integer.parseInt(reader.readLine());
            System.out.println("Squared number: " + number);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
