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

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException, Exception {
        try (ServerSocket serverSocket = new ServerSocket(5000);
             Socket socket = serverSocket.accept();
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            int[][] matrix = (int[][]) in.readObject();
            int threadsCount1 = 1;
            int threadsCount3 = 3;
            int threadsCount5 = 5;
            int threadsCount7 = 7;

            int[][] resultThreadsCount1 = StrassenParallel.strassen(matrix, matrix,threadsCount1);
            int[][] resultThreadsCount3 = StrassenParallel.strassen(matrix, matrix,threadsCount3);
            int[][] resultThreadsCount5 = StrassenParallel.strassen(matrix, matrix,threadsCount5);
            int[][] resultThreadsCount7 = StrassenParallel.strassen(matrix, matrix,threadsCount7);

            
            // Process the matrix 
            //********************** Thread Count One **************************************************************************************//
            System.out.println("This is the Thread # : " + threadsCount1 );
            long startTime = System.nanoTime();
            for (int[] row : resultThreadsCount1) {
                for (int val : row) {
                    System.out.print(val + " ,");         
                }
                System.out.println();
            }
            long endTime = System.nanoTime();
            System.out.println("Total execution time: " + (endTime - startTime) + " nanoseconds");

            
            //****************************Thread Count Three ********************************************************************************//
            System.out.println("This is the Thread # : " + threadsCount3 );
            
            for (int[] row : resultThreadsCount3) {
                for (int val : row) {
                    System.out.print(val + " ,");         
                }
                System.out.println();
            }


            //****************************Thread Count 5 ********************************************************************************//
            System.out.println("This is the Thread # : " + threadsCount5 );
            
            for (int[] row : resultThreadsCount5) {
                for (int val : row) {
                    System.out.print(val + " ,");         
                }
                System.out.println();
            }


            //****************************Thread Count 7 ********************************************************************************//
            System.out.println("This is the Thread # : " + threadsCount7 );
            
            for (int[] row : resultThreadsCount7) {
                for (int val : row) {
                    System.out.print(val + " ,");         
                }
                System.out.println();
            }
            

            
        }
    }
}
