package com.log.chatlog;

import javafx.scene.layout.VBox;

import java.io.*;
import java.net.*;

public class Server {

    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;


    public Server(ServerSocket serverSocket) {
        try {
            //This socket listens for an incoming connection
            this.serverSocket = serverSocket;
            //Then creates an object for us to communicate (blocking object)
            this.socket = serverSocket.accept();
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            System.out.println("Error creating server");
            e.printStackTrace();
        }
    }

    public void sendMessageToClient(String messageToClient){
        try{
            bufferedWriter.write(messageToClient);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Error sending message to client!");
           closeEverything(socket, bufferedWriter,bufferedReader);
        }
    }

    public void receiveMessageFromClient(VBox vBox){
        // Creates a new thread to avoid busy waiting
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (socket.isConnected()){
                    try {
                        String messageFromClient = bufferedReader.readLine();
                        HelloController.addLabel(messageFromClient, vBox);
                    }catch (IOException e){
                        e.printStackTrace();
                        System.out.println("Error receiving client's message");
                        closeEverything(socket, bufferedWriter,bufferedReader);
                        break;
                    }

                }
            }
        }).start();
    }

    public void closeEverything(Socket socket, BufferedWriter bufferedWriter, BufferedReader bufferedReader){
        try {
            if(bufferedReader!= null){
                bufferedReader.close();
            }
            if(bufferedWriter!= null){
                bufferedWriter.close();
            }
            if(socket != null){
                socket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
