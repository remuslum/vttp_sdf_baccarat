package src.baccarat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class BaccaratEngine {
    private final Socket socket;

    public BaccaratEngine(Socket socket){
        this.socket = socket;
    }

    public void parse() throws IOException{
        List<String> playerBuyIn = new ArrayList<>();
        int betAmount = 0;
        String username = "";
        
        //Read message to client
        InputStream is = socket.getInputStream();
        Reader reader = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(reader);

        //Write message to client
        OutputStream os = socket.getOutputStream();
        Writer writer = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(writer);

        // String messageFromClient = "";
        // while((messageFromClient = br.readLine()) != null){
        //     if(messageFromClient.contains("login")) {
        //         String[] inputs = messageFromClient.split("|");
        //         username = inputs[1];
        //         FileManager fileManager = new FileManager(username);
        //         playerBuyIn = fileManager.readFile(inputs[2]);
        //     } else if(messageFromClient.contains("bet")) {
        //         String[] inputs = messageFromClient.split("|");
        //         Player player = new Player(username, Integer.parseInt(playerBuyIn.get(0)), new ArrayList<>());
        //         betAmount = Integer.parseInt(inputs[1]);
        //     } else if (messageFromClient.contains("deal")){
        //         Baccarat baccarat = new Baccarat(null, null, null)
        //     }
        // }

        
    }
    // public static void main(String[] args) {
    //     Deck deck = new Deck(4);
    //     deck.makeDeck();
    //     deck.shuffleDeck();

    //     Console console = System.console();
    //     String userInput = console.readLine("What is your buy in? ");

    //     Player player = new Player("kenneth", Integer.parseInt(userInput), new ArrayList<>());
    //     Banker banker = new Banker(new ArrayList<>());
    //     Baccarat baccarat = new Baccarat(deck.getDeck(), player, banker);

    //     for (int i = 0; i < 5; i++) {
    //         String betAmount = console.readLine("What is your bet amount?");
    //         baccarat.runGame(Integer.parseInt(betAmount));
    //         System.out.println(player.getWinnings());
    //         System.out.println(player.getBuyIn());
    //     }
        
    }
