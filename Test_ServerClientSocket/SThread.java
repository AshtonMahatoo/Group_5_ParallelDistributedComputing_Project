import java.io.*;
import java.net.*;
import java.lang.Exception;

public class SThread extends Thread {
	
	Socket clientSocket;
	Socket serverSocket;
	BufferedReader clientIn;
	PrintWriter clientOut;
	BufferedReader serverIn;
	PrintWriter serverOut;
	
	String messageFromClient;
	String responseFromServer;
	

	// Constructor that takes in the client and server sockets from the router.
	SThread(Socket clientSocket, Socket serverSocket) throws IOException{
		clientSocket = this.clientSocket;
		serverSocket = this.serverSocket;
		
		// Get message from Client
		clientIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		clientOut = new PrintWriter(clientSocket.getOutputStream(), true);
		
		// Forward Client's message to Server
		serverIn = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
		serverOut = new PrintWriter(serverSocket.getOutputStream(), true);
	}
	
	public void run(){
		while(true){
			
			try {
				// Prints message from client
				messageFromClient = clientIn.readLine();
				System.out.println("Router received: " + messageFromClient + " From Client!!");
			}
			catch (IOException e) {
				System.out.println("Error printing message from client");
			}
			
			try {
				// Send message to Server
				serverOut.println(messageFromClient);
			}
			catch (Exception e) {
				System.out.println("Error sending messages to server.");
			}
			
			try {
				// Prints response from Server
				responseFromServer = serverIn.readLine();
				System.out.println("Router received : " + responseFromServer + " From server!!");
			}
			catch (Exception e) {
				System.out.println("Error receiving response from server.");
			}
			
			try {
				// Send response back to Client
				clientOut.println(responseFromServer);
			}
			catch (Exception e) {
				System.out.println("Error sending response back to server.");
			}
			
			try {
				// Close connection to client
				if(messageFromClient.equals("EXIT")) {
					clientSocket.close();
					break;
				}
			}
			catch (Exception e) {
				System.out.println("Error when attempting to close client socket.");
			}
				
		}
		return;
	}
}