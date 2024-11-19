import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {

        /** The Socket class is used by both the client and server to read and write data.
            A client uses the Socket class to initiate a connection to a server.*/
        
        Socket socket = new Socket("localhost", 4000);// Connect to Router on port 4000
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        /**Send message to Server via Router */
        String first_messageToSend = "From Client, Amdahl's law is an intuitive the observation that the performance improvement that can be gained through parallel processing is limited by the part of a system that's inherently sequential -- that is, the set of operations that must be run in series.";
       
        
        out.println(first_messageToSend);
        System.out.println("*** CLIENT SENT ****: " + first_messageToSend);

        long startTime = System.nanoTime();

        // Code to be timed

        long endTime = System.nanoTime();
        long duration = (endTime - startTime); 

        // Print the duration in nanoseconds
        System.out.println("Duration: " + duration + " nanoseconds"); 

        // Convert to milliseconds
        long durationInMillis = duration / 1000000;
        System.out.println("Duration: " + durationInMillis + " milliseconds"); 

        
        /**  Receive response from Server via Router**/
        String response = in.readLine();
        System.out.println("**** CLIENT RECEIVED ****: " + response + " **** VIA THE ROUTER!! ****");

        // Close connection
        socket.close();
    }
}
