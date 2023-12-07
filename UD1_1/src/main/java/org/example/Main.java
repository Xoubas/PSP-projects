package org.example;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println(Runtime.getRuntime().availableProcessors());
            // Launch Google Chrome
            Process chromeProcess = Runtime.getRuntime().exec("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");

            // Sleep for a moment to let Chrome start (not the best practice)
            Thread.sleep(2000);

            // Launch YouTube on Google Chrome
            Runtime.getRuntime().exec("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe https://www.youtube.com");

            // Launch Notepad++
            Runtime.getRuntime().exec("C:\\Program Files\\Notepad++\\notepad++.exe");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
