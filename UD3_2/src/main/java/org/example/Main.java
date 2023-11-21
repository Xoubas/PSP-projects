package org.example;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class Main {
    public static void main(String[] args) throws UnknownHostException, SocketException {
        InetAddress localHost = InetAddress.getLocalHost();
        String iaString = localHost.toString();
        iaString = iaString.substring(8, iaString.length());
        System.out.println(iaString);

        NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localHost);
        System.out.println(networkInterface.getInterfaceAddresses().get(0).getNetworkPrefixLength());

        private static String getNetworkAddress(InetAddress address, short prefixLength) {
            byte[] ipBytes = address.getAddress();
            int subnetMask = 0xffffffff << (32 - prefixLength);

            // Apply bitwise AND to get the network address
            byte[] networkBytes = new byte[4];
            for (int i = 0; i < 4; i++) {
                networkBytes[i] = (byte) (ipBytes[i] & (subnetMask >> (24 - 8 * i)));
            }

            try {
                // Create InetAddress from the network address bytes
                return InetAddress.getByAddress(networkBytes).getHostAddress();
            } catch (UnknownHostException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}