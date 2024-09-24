import java.io.*;
import java.net.*;

public class Router {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Router started. Waiting for clients and servers...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new RouterThread(clientSocket).start();
            }
        } catch (IOException e) {
            System.err.println("Error in router: " + e.getMessage());
        }
    }
}

class RouterThread extends Thread {
    private Socket clientSocket;

    public RouterThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try (Socket serverSocket = new Socket("localhost", 6000);
             DataInputStream clientIn = new DataInputStream(clientSocket.getInputStream());
             DataOutputStream serverOut = new DataOutputStream(serverSocket.getOutputStream());
             DataInputStream serverIn = new DataInputStream(serverSocket.getInputStream());
             DataOutputStream clientOut = new DataOutputStream(clientSocket.getOutputStream())) {

            //////////////////// Txt.files ////////////////
            // Receive file from client
            int fileSize = clientIn.readInt();
            byte[] fileBytes = new byte[fileSize];
            clientIn.readFully(fileBytes);

            // Forward file to server
            serverOut.writeInt(fileSize);
            serverOut.write(fileBytes);

            // Receive response from server
            String serverResponse = serverIn.readUTF();
            System.out.println("Router received response from Server: " + serverResponse);

            // Send response back to client
            clientOut.writeUTF(serverResponse);

            /////////////////////////////////////// Sound Files ////////////////////
            // Receive sound file from server
            int soundfileSize = serverIn.readInt();
            byte[] soundData = new byte[soundfileSize];
            serverIn.readFully(soundData);

            // Forward sound file to client
            clientOut.writeInt(soundfileSize);
            clientOut.write(soundData);
            System.out.println("Router sent sound file to client.");
            ////////////////////////////////////////////////////////

        } catch (IOException e) {
            System.err.println("Error in router thread: " + e.getMessage());
        }
    }
}