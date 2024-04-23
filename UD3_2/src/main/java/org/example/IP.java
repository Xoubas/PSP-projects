package org.example;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;

public class IP {
    public static void main(String[] args) throws IOException {
        InetAddress localHost = InetAddress.getLocalHost();
        String ipAddress = localHost.toString();
        System.out.println("LocalHost IPAdress: " + ipAddress);
        ipAddress = ipAddress.substring(8);

        NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localHost);
        System.out.println("Network interface name: " + networkInterface);
        System.out.println(networkInterface.getInterfaceAddresses());
        short prefix = networkInterface.getInterfaceAddresses().get(0).getNetworkPrefixLength();
        System.out.println(ipAddress + "/" + prefix);

        byte[] networkAddress = getNetworkAddress(localHost, prefix);
        for (int i = 0; i < Math.pow(2, (32 - prefix) - 2); i++) {
            networkAddress[3]++;
            if (InetAddress.getByAddress(networkAddress).isReachable(1000)) {
                System.out.println("Bacanov: " + InetAddress.getByAddress(networkAddress).getHostAddress());
            } else {
                System.out.println("NoVA: " + InetAddress.getByAddress(networkAddress).getHostAddress());
            }

            if (networkAddress[3] == 0) {
                networkAddress[3] = 0;
                networkAddress[2]++;
            }
        }
    }

    public static byte[] getNetworkAddress(InetAddress address, short prefixLength) {
        byte[] ipBytes = address.getAddress();
        int subnetMask = 0xffffffff << (32 - prefixLength);

        // Apply bitwise AND to get the network address
        byte[] networkBytes = new byte[4];
        for (int i = 0; i < 4; i++) {
            networkBytes[i] = (byte) (ipBytes[i] & (subnetMask >> (24 - 8 * i)));
        }
        return networkBytes;
    }
}
