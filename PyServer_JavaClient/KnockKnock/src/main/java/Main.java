import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


import java.io.*;
import java.net.*;
public class Main {
    public static void main(String[] args) {
        try{
            Socket socIn=new Socket("localhost",2869);
            Socket socOut=new Socket("localhost",8080);
            DataOutputStream dout=new DataOutputStream(socOut.getOutputStream());
            DataInputStream in = new DataInputStream(socIn.getInputStream());

            dout.writeUTF("My name is Joel");
            dout.flush();
            dout.close();


            String msg=(String)in.readUTF();
            System.out.println("Server: "+msg);
            socIn.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}

