
/**

Progress Report: The Design and Implementation of a Client-Server Paradigm 

Date: 11/19/24

Project Name: CS 4504 PROJECT REPORT – PART2

Report Prepared by: Group 5 (Parallel Distributed Computing, Section W03, Fall 2024)

Courtney Faulkner, Nicholas Hodge, Ashton Mahatoo, Colson Sims, Joshua Smith, Mike Tokura, Carinne Tzurdecker, Giovanni Zavala



Report Submitted to: 

Professor Patrick O. Bobbie, PhD

Email: pbobbie@kennesaw.edu

Office Location: Atrium Bldg, J386

Office phone: 470.578.3810

CS 4504 PROJECT REPORT – PART1

Fall 2024

 */





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
        System.out.println("Client sent: " + first_messageToSend);

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
        System.out.println("Client received: " + response + " via the Router");

        // Close connection
        socket.close();
    }
}
