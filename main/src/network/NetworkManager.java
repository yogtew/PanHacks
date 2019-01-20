package network;

import input.GameKeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.common.eventbus.Subscribe;
import events.KeyPressedEvent;
import input.GameKeyListener;
import logic.GameState;

public class NetworkManager {
    int portNumber = 3000;
    ServerSocket serverSocket;
    Socket clientSocket;
    PrintWriter out;
    BufferedReader in;

    public NetworkManager() {
        try {
            serverSocket = new ServerSocket(portNumber);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public GameKeyListener getInputs() {
        return GameKeyListener.getSingleton();
    }

    public void push(GameState gameState) {

    }

    @Subscribe
    public void KeyPressHandler(KeyPressedEvent event) {
        // key press => send to server

    }
}
