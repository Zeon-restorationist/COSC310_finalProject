import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Reply {
    Socket clientSocket;
    PrintWriter out;
    BufferedReader in;
    String rep;

    public void Reply() throws IOException {

        clientSocket = new Socket("127.0.0.1", 8080);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        Client client = new Client();
        client.startConnection("127.0.0.1", 8080);


        this.rep = in.readLine();


    }

}
