package org.example;


/*
We want to create a UDP client-server application to obtain random words.

The client must send to the server a command entered by the user.
The only correct command is:

WORD <number_letters>

If the user does not specify the number of letters (letter_numbers),
the default value must be 5.

The server must query a Random Word API web service to obtain a word
with the length specified by the user and must send the word to the client.

If the user enters a command that does not exist, the server sends
a greeting to the client.

Create a RandomWordClient class to launch the Client.

Create a RandomWordServer class to launch the Server.

Create a RandomWordServerWorker class for each thread on the Server.
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class RandomWordServer {
    public static void main(String[] args) {
        final int PORT = 60000;

        try (DatagramSocket socket = new DatagramSocket(PORT);) {

            while (true) {
                DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
                Thread threadServer = new Thread(new RandomWordServerWorker(socket, packet));
                threadServer.start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //create a method to get a random word from the API https://random-word-api.herokuapp.com/word?length=

    public static String getRandomWord(int length) {
        try {
            // Establecer la URL de la API con la longitud deseada
            String apiUrl = "https://random-word-api.herokuapp.com/word?length=" + length;

            // Crear la URL y la conexión HTTP
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configurar la solicitud HTTP
            connection.setRequestMethod("GET");

            // Obtener la respuesta
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Leer la respuesta
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Cerrar la conexión
                connection.disconnect();

                return response.toString().substring(2, response.toString().length() - 2);
            } else {
                System.out.println("Error al realizar la solicitud. Código de respuesta: " + responseCode);
            }

            // Cerrar la conexión
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}