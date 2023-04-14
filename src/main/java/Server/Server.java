package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket;

        serverSocket = new ServerSocket(5500);

        while(true){
            System.out.println("Aguardando Cliente ...");
            Socket socketClient = serverSocket.accept();
            Worker worker = new Worker(socketClient);
            worker.start();
        }

    }

}
