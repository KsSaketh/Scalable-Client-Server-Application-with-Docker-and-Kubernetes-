import java.io.*;
import java.net.*;

public class Client {

    private static String HOST = "localhost";
    
    public static void main(String[] args) throws IOException {

      if(args.length < 1) {
        System.out.println("Usage: Client <port>");
        return;
      }
      
      int PORT = Integer.parseInt(args[0]);

      Socket socket = new Socket(HOST, PORT);

      // rest of client code
      BufferedReader input = 
      new BufferedReader(new InputStreamReader(socket.getInputStream()));

  String message = input.readLine();
  System.out.println("Received: " + message);

  input.close();
  socket.close();

    }

}