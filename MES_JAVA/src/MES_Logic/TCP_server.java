package MES_Logic;

import java.io.*;
import java.net.*;

public class TCP_server extends Thread {
    @Override
    public void run() {
        int port = 8080; // Port to listen on

        try {
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started. Waiting for clients...");

            // Run the server indefinitely
            while (true) {
                // Wait for a client to connect
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                // Handle client communication in a separate thread
                ClientHandler handler = new ClientHandler(clientSocket);
                new Thread(handler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;
    private Logic logic;
    private DB_Interaction db;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        logic = new Logic();
        db = new DB_Interaction();
    }

    @Override
    public void run() {
        try {
            InetAddress clientAddress = clientSocket.getInetAddress();
            /*if(!clientAddress.getHostAddress().equals("127.0.0.1")) {
                clientSocket.close();
                return;
            }*/
            // Open input and output streams for communication with the client
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Receive a message from the client
            String message = in.readLine();
            System.out.println("ERP: " + message);
            readMessage(message);

            // Close the connection
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void readMessage(String message) throws Exception {
        char supplier = message.charAt(0);
        int colonIndex = message.indexOf(':');
        int pieceID = Integer.parseInt(message.substring(1, colonIndex));
        int quantity = Integer.parseInt(message.substring(colonIndex + 1));

        switch (supplier) {
            case 'A':
                if(pieceID == 1) {
                    logic.AddP1(quantity, 30);
                } else if (pieceID == 2) {
                    logic.AddP2(quantity, 10);
                }
                break;

            case 'B':
                if(pieceID == 1) {
                    logic.AddP1(quantity, 45);
                } else if (pieceID == 2) {
                    logic.AddP2(quantity, 15);
                }
                break;

            case 'C':
                if(pieceID == 1) {
                    logic.AddP1(quantity, 55);
                } else if (pieceID == 2) {
                    logic.AddP2(quantity, 18);
                }
                break;
        }
    }
}
