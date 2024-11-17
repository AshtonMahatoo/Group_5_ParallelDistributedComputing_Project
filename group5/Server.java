import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException, Exception {
        try (ServerSocket serverSocket = new ServerSocket(5000);
             Socket socket = serverSocket.accept();
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            int[][] matrix = (int[][]) in.readObject();
            int threads = 7;

            System.out.println("Using " + threads + " threads:");
            int[][] result = StrassenParallel.strassen(matrix, matrix,threads);

            long startTime = System.nanoTime();
            // Process the matrix
            for (int[] row : result) {
                for (int val : row) {
                    System.out.print(val + " ");

                    
                }
                System.out.println();
            }
            long endTime = System.nanoTime();
            System.out.println("Total execution time: " + (endTime - startTime) + " nanoseconds");
            


            
        }
    }
}
