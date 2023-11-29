package org.example;

import java.io.*;

public class PSCountVowel {
    public static void countVowels(String vw, File df, File dr) throws IOException {
        int counter = 0;
        String line;
        FileReader fr = new FileReader(df);
        BufferedReader br = new BufferedReader(fr);
        while ((line = br.readLine()) != null) {
            for (int i = 0; i < line.length(); i++) {
                if (Character.toUpperCase(line.charAt(i)) == Character.toUpperCase(vw.charAt(0))) {
                    counter++;
                }
            }
        }

        BufferedWriter bw1 = new BufferedWriter(new FileWriter(dr));
        bw1.write(String.valueOf(counter));
        bw1.close();
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Hello world");
        File dataFile = new File(args[0]);
        String vowel = args[1];
        File dataRes = new File(args[2]);
        countVowels(vowel, dataFile, dataRes);
    }
}