import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.io.*;
import java.util.Arrays;

public class Client {
    // Method to generate random matrices
    private static int[][] generateRandomMatrix(int size) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = (int) (Math.random() * 10); // Random integers [0-9]
            }
        }
        return matrix;
    }

    public static void main(String[] args) {

        try {
            Socket socket = new Socket("localhost", 3000);// Connect to Router on port 4000
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());


            // Generate matrices
            ArrayList<int[][]> matrixList = new ArrayList<>();

            int matrixCount = 2;
            int matrixSize = 2;

            for (int i = 0; i < matrixCount; i++) {
                matrixList.add(generateRandomMatrix(matrixSize));
            }

            for (int i = 0; i < matrixList.size(); i++) {
                System.out.println(Arrays.deepToString(matrixList.get(i)));
            }

            long startTime = System.nanoTime();

            // Send matrices
            out.writeObject(matrixList);
            out.flush();

            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            // Receive resultantMatrix
            int[][] resultantMatrix = (int[][]) in.readObject();

            long endTime = System.nanoTime();
            long duration = (endTime - startTime);

            // Print Array
            System.out.println(Arrays.deepToString(resultantMatrix));

            // Print the duration in nanoseconds
            System.out.println("Duration: " + duration + " nanoseconds");

            // Convert to milliseconds
            long durationInMillis = duration / 1000000;
            System.out.println("Duration: " + durationInMillis + " milliseconds");


            // Close connection
            socket.close();
        }
        catch(Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
}
