import java.io.*;
import java.net.*;

public class Router {
    public static void main(String[] args) throws IOException {
        ServerSocket routerSocket = new ServerSocket(4000); // Router listens on port 4000
        System.out.println("*** Router started and waiting for client connection.... ***");

        while (true) {
            Socket clientSocket = routerSocket.accept(); // Wait for Client connection
            BufferedReader clientIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter clientOut = new PrintWriter(clientSocket.getOutputStream(), true);

            // Forward Client's message to Server
            Socket serverSocket = new Socket("localhost", 5000); // Connect to Server on port 5000
            BufferedReader serverIn = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            PrintWriter serverOut = new PrintWriter(serverSocket.getOutputStream(), true);

            // Get message from Client
            String messageFromClient = clientIn.readLine();
            System.out.println("**** ROUTER RECEIVED DATA MESSAGE!! **** " + messageFromClient + "**** FROM CLIENT!! **** ");

            // Send message to Server
            serverOut.println(messageFromClient);

            // Get response from Server
            String responseFromServer = serverIn.readLine();
            System.out.println("**** ROUTER RECEIVED DATA MESSAGE!! ****" + responseFromServer + "*** FROM SERVER!! ***");

            // Send response back to Client
            clientOut.println(responseFromServer);

            // Close connections
            serverSocket.close();
            clientSocket.close();
        }
    }
}

