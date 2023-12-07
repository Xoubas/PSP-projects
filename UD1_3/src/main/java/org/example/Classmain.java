package org.example;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Classmain {
    public static void main(String[] args) throws IOException {
        // ProcessBuilder pb1 = new ProcessBuilder("dir");
        // System.out.println("Directorio: " + pb1.directory());

        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce path: ");
        String path = sc.nextLine();
        sc.close();
        File file = new File(path);
        ProcessBuilder pb2 = new ProcessBuilder();
        pb2.directory(file);
        pb2.command("cmd.exe", "/c", "dir");
        pb2.inheritIO(); // Translates de I/O from cmd to IDE
        pb2.start();
    }
}
