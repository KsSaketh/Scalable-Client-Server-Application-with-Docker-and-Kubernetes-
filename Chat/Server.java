import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import java.util.Random;

public class Server {

    private static String serverName;
    private static final int PORT = 50000;
   
    private static BlockingQueue<Socket> clientQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args) throws IOException {

         serverName = generateName(); 

        ServerSocket serverSocket = new ServerSocket(PORT);

        Thread handlerThread = new Thread(new Runnable() {
            @Override 
            public void run() {
                handleClients();
            }
        });
        handlerThread.start();

        System.out.println("Server Running");
        
        while(true) {
            Socket client = serverSocket.accept();
            clientQueue.add(client);
        }
    }
    private static synchronized String getServerName() {
        return serverName;
    }
    // Generate random name
    private static String generateName() {
        // Implementation from before 
        int leftLimit = 97; 
        int rightLimit = 122;  
        int targetStringLength = 10;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
          .limit(targetStringLength)
          .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
          .toString();

        return generatedString;
    } 

    private static void handleClients() {
        while(true) {
            try {
                Socket client = clientQueue.take();
                handleClient(client);
            } catch(Exception e) {} 
        }
    }
    
    private static void handleClient(Socket client) throws IOException, InterruptedException {
        Thread.sleep(5000);
    
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        out.println("Hello from " + getServerName());
    
        client.close();
    }

}