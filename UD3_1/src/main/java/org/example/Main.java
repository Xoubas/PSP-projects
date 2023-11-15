package org.example;

import java.net.*;
import java.util.Enumeration;

public class Main {
    public static void main(String[] args) {
        try {
            Enumeration<NetworkInterface> localHost = NetworkInterface.getNetworkInterfaces();

            while (localHost.hasMoreElements()) {
                NetworkInterface networkInterface = localHost.nextElement();
                System.out.println("Interface: " + networkInterface.getName());

                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();

                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                    System.out.println(inetAddress.getHostAddress());
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
