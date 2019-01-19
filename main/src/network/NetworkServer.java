package network;

import input.Input;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.GameState;

/**
 * Server that maintains a synchronized gamestate and periodically updates clients with information about
 * latest gamestate.
 *
 * Also accepts inputs updates and updates internal inputmanager
 */
public class NetworkServer extends Thread {
    int portNumber = 3000;
    ServerSocket serverSocket;
    Socket clientSocket;
    PrintWriter out;
    BufferedReader in;
    private ArrayList<NetworkServerThread> threads;

    public NetworkServer() {
        threads = new ArrayList<>();
        try {
            serverSocket = new ServerSocket(portNumber);
            while (true) {
                clientSocket = serverSocket.accept();
                NetworkServerThread thread = new NetworkServerThread(clientSocket);
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
        for (NetworkServerThread t: threads) {
            t.push(gameState);
        }
    }
}

class NetworkServerThread extends Thread {
    Socket clientSocket;
    PrintWriter out;
    BufferedReader in;
    public int id;

    NetworkServerThread(Socket clientSocket) {
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
