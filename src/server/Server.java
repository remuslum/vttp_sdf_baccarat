package src.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException{
        ServerSocket server = new ServerSocket(Integer.parseInt(args[0]));
        System.out.println(">>> Server created");

        while (true) { 
            System.out.println("Waiting for connection");

            Socket connection = server.accept();

            System.out.println("Got a client connection");
        }
    }
}
