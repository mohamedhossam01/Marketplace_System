package com.example.marketplace.ServerLogic;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    public void startServer(){
        try{
            while(!serverSocket.isClosed()){
                Socket socket=serverSocket.accept();
                ClientHandler clienthandler = new ClientHandler(socket);
                Thread thread = new Thread(clienthandler);
                thread.start();
            }
        } catch(IOException e){
            this.closeServerSocket();
        }
    }

    public void closeServerSocket(){
        try{
            if(serverSocket != null){
                serverSocket.close();
            }

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args)  {
        try{
            ServerSocket serverSocket = new ServerSocket(1234);
            Server server = new Server(serverSocket);
            server.startServer();
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
