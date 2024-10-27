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
import src.banker.Banker;
import src.card.Deck;
import src.file.FileManager;
import src.player.Player;

public class BaccaratEngine {
    private final Socket socket;
    private final Deck deck;

    public BaccaratEngine(Socket socket, Deck deck){
        this.socket = socket;
        this.deck = deck;
    }

    public void runLogic() throws IOException{
        List<Baccarat> currentSession = new ArrayList<>();
        List<Player> currentPlayer = new ArrayList<>();
        List<Banker> currentBanker = new ArrayList<>();
        String username = "";
        
        //Read message to client
        InputStream is = socket.getInputStream();
        Reader reader = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(reader);

        //Write message to client
        OutputStream os = socket.getOutputStream();
        Writer writer = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(writer);

        String messageFromClient = "";
        while((messageFromClient = br.readLine()) != null){
            System.out.println(messageFromClient);
            String messageToClient = "";

            if(messageFromClient.contains("login")) {
                String[] message = messageFromClient.split("\\|");
                username = message[1];
                FileManager fileManager = new FileManager(username);
                List<String> buyIn = fileManager.readFile(message[2]);
                int totalBuyIn = Integer.parseInt(buyIn.get(0));

                Player player = new Player(message[1], totalBuyIn, new ArrayList<>());
                Banker banker = new Banker(new ArrayList<>());
                currentPlayer.add(player);
                currentBanker.add(banker);
                messageToClient = player.toString() + "\n";
            } else if (messageFromClient.contains("bet")) {
                String[] message = messageFromClient.split("\\|");
                int betAmount = Integer.parseInt(message[1]);
                Player player = currentPlayer.get(0);
                if(betAmount > player.getTotalFunds()) {
                    messageToClient = "Not enough funds \n";
                } else {
                    currentSession.add(new Baccarat(deck, currentPlayer.get(0), currentBanker.get(0), betAmount));
                    messageToClient = "Bet has been set. \n";
                }
                
            } else if (messageFromClient.contains("deal")){
                if(currentSession.isEmpty()) {
                    messageToClient = "Bet has not been set, please place bet \n";
                } else {
                    Baccarat currentBaccarat = currentSession.get(0);
                    String determineGame = currentBaccarat.runGame();
                    messageToClient = determineGame + "\n";
                    currentSession.remove(0);
                }
            } else if (messageFromClient.contains("exit")){
                FileManager fileManager = new FileManager(username);
                List<String> fileContent = new ArrayList<>();
                fileContent.add(String.valueOf(currentPlayer.get(0).getTotalFunds()));
                fileManager.writeToFile(fileContent);
                bw.write("Godbye! Better luck next time \n");
                bw.flush();
                break;
            } else {
                messageToClient = "I do not understand this command.\n";
            }
            bw.write(messageToClient);
            bw.flush();
        }
        bw.close();
        br.close();
    }
}

