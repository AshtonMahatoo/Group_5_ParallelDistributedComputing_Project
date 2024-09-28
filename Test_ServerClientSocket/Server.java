import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {

        /**
         * The ServerSocket class is used by the server to connect to a specific port and listen for incoming client connections.
            When a client connects, the ServerSocket accepts the connection and returns a Socket object for communication.
         */
        
        ServerSocket serverSocket = new ServerSocket(5000); // Server listens on port 5000
        System.out.println("Server started and waiting for client connection...");

        while (true) {
            Socket clientSocket = serverSocket.accept(); // Wait for client connection via Router
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String message = in.readLine(); // Read message from Client via Router
            System.out.println("Received message from Client via Router: " + message);

            /** This will return the Client message to the Client via the Router in Upper Case */
            out.println("Server Response: Message received Confirmation!! - ("+message.toUpperCase()+")");


            /** Close socket connections */
            clientSocket.close();
            
        }
    }
}
