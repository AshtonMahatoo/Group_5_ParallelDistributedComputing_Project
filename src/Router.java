import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Router {
    public static void main(String[] args) {
        try {
            ServerSocket routerSocket = new ServerSocket(3000); // Router listens on port 4000
            System.out.println("Router started and waiting for client connection...");

            while (true) {
                Socket clientSocket = routerSocket.accept(); // Wait for Client connection
                ObjectInputStream clientIn = new ObjectInputStream(clientSocket.getInputStream());
                ObjectOutputStream clientOut = new ObjectOutputStream(clientSocket.getOutputStream());

                // Forward Client's message to Server
                Socket serverSocket = new Socket("localhost", 5000); // Connect to Server on port 5000

                // Receive matrices from Client
                Object matrixList = clientIn.readObject();

                ObjectOutputStream serverOut = new ObjectOutputStream(serverSocket.getOutputStream());
                // Send message to Server
                serverOut.writeObject(matrixList);
                serverOut.flush();

                ObjectInputStream serverIn = new ObjectInputStream(serverSocket.getInputStream());
                // Get response from Server
                Object resultantMatrix = serverIn.readObject();

                // Send response back to Client
                clientOut.writeObject(resultantMatrix);
                clientOut.flush();

                // Close connections
                serverSocket.close();
                clientSocket.close();
            }
        }
        catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
}

