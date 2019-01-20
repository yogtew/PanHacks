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
import logic.GameState;

/**
 * Server that maintains a synchronized gamestate and periodically updates clients with information about
 * latest gamestate.
 *
 * Also accepts inputs updates and updates internal inputmanager
 */
public class NetworkServer implements Runnable {
    int portNumber = 3000;
    ServerSocket serverSocket;
    Socket clientSocket;
    PrintWriter out;
    BufferedReader in;
    private ArrayList<NetworkServerThread> threads;
    private GameState gameState;

    public NetworkServer(GameState gameState) {
        threads = new ArrayList<>();
        this.gameState = gameState;
    }

    @Override
    public void run() {
        Logger logger = Logger.getGlobal();
        try {
            serverSocket = new ServerSocket(portNumber);
            while (true) {
                clientSocket = serverSocket.accept();
                logger.log(Level.INFO, "Accepted a client");
                NetworkServerThread thread = new NetworkServerThread(clientSocket, gameState);
                threads.add(thread);
                thread.start();
                logger.log(Level.INFO, "Waiting for next client");
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
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
    private GameState gameState;

    NetworkServerThread(Socket clientSocket, GameState gameState) {
        String fromClient;
        Logger logger = Logger.getGlobal();
        this.clientSocket = clientSocket;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            logger.log(Level.INFO, "Waiting for client id...");
            // first thing client does when connecting is send id
            fromClient = in.readLine();
            id = Integer.parseInt(fromClient);
            logger.log(Level.INFO, "Received id from client: " + id);

            // send gamestate
            out.println(gameState.serialize());

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
