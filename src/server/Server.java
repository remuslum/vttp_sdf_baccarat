package src.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import src.baccarat.BaccaratEngine;
import src.card.Deck;

public class Server {
    public static void main(String[] args) throws IOException{
        ServerSocket server = new ServerSocket(Integer.parseInt(args[0]));
        int numOfDecks = Integer.parseInt(args[1]);
        System.out.println(">>> Server created");
        Deck deck = new Deck(numOfDecks);
        deck.makeDeck();
        deck.shuffleDeck();

        while (true) { 
            System.out.println("Waiting for connection");

            Socket connection = server.accept();

            System.out.println("Got a client connection");

            BaccaratEngine baccaratEngine = new BaccaratEngine(connection, deck);
            baccaratEngine.runLogic();

            
            connection.close();
            System.out.println("Client disconnected. Closing connection...");
            break;
        }
        server.close();
        
    }
}
