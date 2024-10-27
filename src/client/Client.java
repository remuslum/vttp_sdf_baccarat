package src.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException{
        Console console = System.console();
        String[] inputs = args[0].split(":");
        Socket socket = new Socket(inputs[0],Integer.parseInt(inputs[1]));
        System.out.println("Server Connection Established");

        //Read message to server
        InputStream is = socket.getInputStream();
        Reader reader = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(reader);

        //Write message to server
        OutputStream os = socket.getOutputStream();
        Writer writer = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(writer);

        while(true) {
            String messageToServer = console.readLine("> ").toLowerCase();
            String finalMessageToServer = "";
            if(messageToServer.contains("login")) {
                String[] message = messageToServer.toLowerCase().split(" ");
                finalMessageToServer = message[0] + "|" + message[1] + "|" + message[2];
            } else if (messageToServer.contains("bet")) {
                String[] message = messageToServer.toLowerCase().split(" ");
                finalMessageToServer = message[0] + "|" + message[1];
            } else if (messageToServer.contains("deal")){
                String[] message = messageToServer.toLowerCase().split(" ");
                finalMessageToServer = message[0] + "|" + message[1];
            } else if (messageToServer.contains("exit")){
                bw.write("""
                 exit server
                 """);
                bw.flush();
                break;
            } else {
                finalMessageToServer = "Unknown command";
            }
            bw.write(finalMessageToServer + "\n");
            bw.flush();

            String messageFromServer = br.readLine();
            System.out.println(messageFromServer);
        }
        System.out.println(br.readLine());
        br.close();
        bw.close();
        socket.close();
    }
}
