package org.example;

import java.net.Socket;

public class SquareServerHandler {
    private Socket socket;
    private int number;

    public SquareServerHandler(Socket socket) {
        this.socket = socket;
    }


}
