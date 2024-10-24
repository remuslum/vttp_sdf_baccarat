package src.baccarat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;

public class BaccaratEngine {
    private final Socket socket;

    public BaccaratEngine(Socket socket){
        this.socket = socket;
    }

    public void parse() throws IOException{
        //Read message to client
        InputStream is = socket.getInputStream();
        Reader reader = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(reader);

        //Write message to client
        OutputStream os = socket.getOutputStream();
        Writer writer = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(writer);

        String dataDirectory = "/Users/remuslum/Downloads/vttp_sdf/vttp_sdf_bacarrat/data";

        String messageFromClient = "";
        while((messageFromClient = br.readLine()) != null){
            if(messageFromClient.contains("login")){
                String[] inputs = messageFromClient.split("|");
                String filePath = dataDirectory + File.separator + inputs[1] + ".db";
                File file = new File(filePath);
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter FileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
            }
        }
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
