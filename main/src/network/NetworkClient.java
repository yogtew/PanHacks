package network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Client will establish connection with server, then periodically receive gamestate updates,
 * and will send input updates to server
 */
public class NetworkClient {

    PrintWriter out;
    BufferedReader in;
    public NetworkClient(int id, String hostName, int portNumber) {

        Logger logger = Logger.getGlobal();

        try {
            // connect to host
            Socket socket = new Socket(hostName, portNumber);
            logger.log(Level.INFO, "Connected to server");

            // get I/O stuff
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // say hi (send id)
            out.println(id);
            logger.log(Level.INFO, "Sent id");

            // wait for gameState
            String fromServer;
            fromServer = in.readLine();
            System.out.println("Server: " + fromServer);
            logger.log(Level.INFO, "Received gameState");

            // setup runnable to keep receive stuff from server

            // also setup runnable to send updates to server
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}