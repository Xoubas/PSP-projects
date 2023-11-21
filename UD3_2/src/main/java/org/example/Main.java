package org.example;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class Main {
    public static void main(String[] args) throws UnknownHostException, SocketException {
        InetAddress localHost = InetAddress.getLocalHost();
        String ipAddress = localHost.toString();
        ipAddress = ipAddress.substring(8, ipAddress.length());

        NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localHost);
        short prefix = networkInterface.getInterfaceAddresses().get(0).getNetworkPrefixLength();
        System.out.println(ipAddress + "/" + prefix);

        System.out.println(getNetworkAddress(localHost, prefix));
    }

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