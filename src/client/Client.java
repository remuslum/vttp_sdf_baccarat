package src.client;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException{
        String[] inputs = args[0].split(":");
        Socket socket = new Socket(inputs[0],Integer.parseInt(inputs[1]));
        System.out.println("Server Connection Established");
    }
}
