package com.example.marketplace;

import com.example.marketplace.controller.cartAndSearchController;

import java.io.*;
import java.net.Socket;


public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public Client(Socket socket){
        try{
            this.socket=socket;
            this.bufferedWriter= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader= new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (IOException ex) {
            closeEverything(socket,bufferedReader, bufferedWriter);
        }
    }
    public void sendMessage(String messageToSend){
        try{
            if(socket.isConnected()){
                bufferedWriter.write(messageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException ex) {
            closeEverything(socket,bufferedReader, bufferedWriter);
        }
    }
    public void listenForMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromServer;
                while(socket.isConnected()){
                    try{
                        msgFromServer = bufferedReader.readLine();
                        if(msgFromServer == "updateView"){

                            //cartAndSearchController c = new cartAndSearchController();
                            //c.updateView();
                        }
                    }catch(IOException ex) {
                        closeEverything(socket,bufferedReader, bufferedWriter);
                    }
                }
            }
        }).start();
    }

    public void closeEverything(Socket socket , BufferedReader  bufferedReader , BufferedWriter bufferedWriter ) {

        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
