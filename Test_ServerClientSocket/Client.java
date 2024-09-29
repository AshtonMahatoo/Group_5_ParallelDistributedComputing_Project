import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {

        /** The Socket class is used by both the client and server to read and write data.
            A client uses the Socket class to initiate a connection to a server.*/
        
        Socket socket = new Socket("localhost", 4000);// Connect to Router on port 4000
		Scanner scan = new Scanner(System.in);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		while(true) {
			//Asks the user to input the message they wish to send then sends it to the Server
			System.out.println("Please enter a message to send to the server. (Type EXIT to exit the Client Socket)");
			String messageToSend = scan.nextLine();
			out.println(messageToSend);
			System.out.println("Client sent: " + messageToSend);

			/**  Receive response from Server via Router**/
			String response = in.readLine();
			System.out.println("Client received: " + response + " via the Router");
			
			// If the message is EXIT then the socket will close
			if (messageToSend.equals("EXIT")){
				break;
			}
		}
        // Close connection
        socket.close();
    }
}
