package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class NumberGameClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 50000);
             DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
             Scanner scanner = new Scanner(System.in)
        ) {
            String response;
            while ((response = dataInputStream.readUTF()) != null) {
                System.out.println(response);

                if (response.startsWith("11 BYE")) {
                    break;
                }

                System.out.print("Enter command: ");
                String command = scanner.nextLine();
                dataOutputStream.writeUTF(command);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

