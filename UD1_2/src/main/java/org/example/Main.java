package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            // Execute a process without errors (ping)
            executeCommand("ping www.google.es");

            // Execute a non-existing process (pin)
//            executeCommand("pin");

            // Execute a process that finishes with an error code (ping -n)
            executeCommand("ping -n");  // Adjust the command as needed
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void executeCommand(String command) throws IOException {
        Process process = Runtime.getRuntime().exec(command);

        // Wait for the process to complete
        try {
            int exitCode = process.waitFor();
            System.out.println("Process exited with code: " + exitCode);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
