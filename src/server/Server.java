package src.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import src.baccarat.Baccarat;
import src.banker.Banker;
import src.card.Deck;
import src.player.Player;

public class Server {
    public static void main(String[] args) throws IOException{
        ServerSocket server = new ServerSocket(Integer.parseInt(args[0]));
        int numOfDecks = Integer.parseInt(args[1]);
        System.out.println(">>> Server created");
        List<Baccarat> currentSession = new ArrayList<>();
        List<Player> currentPlayer = new ArrayList<>();
        List<Banker> currentBanker = new ArrayList<>();
        int betAmount = 0;
        Deck deck = new Deck(numOfDecks);
        deck.makeDeck();
        deck.shuffleDeck();

        while (true) { 
            System.out.println("Waiting for connection");

            Socket connection = server.accept();

            System.out.println("Got a client connection");

            //Read message to client
            InputStream is = connection.getInputStream();
            Reader reader = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(reader);

            //Write message to client
            OutputStream os = connection.getOutputStream();
            Writer writer = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(writer);

            String messageFromClient = "";
            while((messageFromClient = br.readLine()) != null){
                System.out.println(messageFromClient);
                String messageToClient = "";

                if(messageFromClient.contains("login")) {
                    String[] message = messageFromClient.split("\\|");
                    Player player = new Player(message[1], Integer.parseInt(message[2]), new ArrayList<>());
                    Banker banker = new Banker(new ArrayList<>());
                    currentPlayer.add(player);
                    currentBanker.add(banker);
                    currentSession.add(new Baccarat(deck, player, banker));
                    messageToClient = player.toString() + "\n";
                } else if (messageFromClient.contains("bet")) {
                    String[] message = messageFromClient.split("\\|");
                    betAmount = Integer.parseInt(message[1]);
                    messageToClient = "Bet has been set. \n";
                } else if (messageFromClient.contains("deal")){
                    currentSession.get(0).runGame(betAmount);
                }

                bw.write(messageToClient);
                bw.flush();
            }
        }
    }
}
