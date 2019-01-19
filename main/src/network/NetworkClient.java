package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import input.Input;
import logic.GameState;

/**
 * Client will establish connection with server, then periodically receive gamestate updates,
 * and will send input updates to server
 */
public class NetworkClient {
    int portNumber = 3000;
    ServerSocket serverSocket;
    Socket clientSocket;
    PrintWriter out;
    BufferedReader in;
    private ArrayList<NetworkThread> threads;

    public NetworkClient() {
        threads = new ArrayList<>();
        try {
            serverSocket = new ServerSocket(portNumber);
            while (true) {
                clientSocket = serverSocket.accept();
                NetworkThread thread = new NetworkThread(clientSocket);
                threads.add(thread);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public Input getInputs() {
        return new Input();
    }

    public void push(GameState gameState) {
        for (NetworkThread t: threads) {
            t.push(gameState);
        }
    }
}

class NetworkThread extends Thread {
    Socket clientSocket;
    PrintWriter out;
    BufferedReader in;
    public int id;

    NetworkThread(Socket clientSocket) {
        String fromClient;
        this.clientSocket = clientSocket;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            // first thing client does when connecting is send id
            fromClient = in.readLine();
            id = Integer.parseInt(fromClient);

            while (true) {
                // accepts inputs in the format key:value
                fromClient = in.readLine();
                Logger.getGlobal().log(Level.INFO,
                        String.format("Received packet from client %d: %s \n", id, fromClient));
                String[] split = fromClient.split(":");
                String key = split[0];
                boolean status = Boolean.parseBoolean(split[1]);
                // TODO: update inputmanager
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void push(GameState gameState) {
        out.print(gameState.serialize());
    }
}