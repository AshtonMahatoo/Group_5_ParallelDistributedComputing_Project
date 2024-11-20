import java.io.*;
import java.net.*;
import java.util.ArrayList;

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

    public static long communicateClientMatrices(int matrixCount, int matrixSize, boolean useThreading) {
        try {
            Socket socket = new Socket("localhost", 4000);// Connect to Router on port 4000
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());


            // Generate matrices
            ArrayList<int[][]> matrixList = new ArrayList<>();

            for (int i = 0; i < matrixCount; i++) {
                matrixList.add(generateRandomMatrix(matrixSize));
            }

            long startTime = System.nanoTime();

            // Send matrices
            var wrapper = new MatrixListWrapper(matrixList, useThreading);
            out.writeObject(wrapper);
            out.flush();

            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            // Receive resultantMatrix
            @SuppressWarnings("unused")
            int[][] resultantMatrix = (int[][]) in.readObject();

            long endTime = System.nanoTime();
            long duration = (endTime - startTime);

            // Print Resultant Matrix (Currently Inactive)
//             System.out.println(Arrays.deepToString(resultantMatrix));

            // Print the duration in nanoseconds
            //System.out.println("Duration: " + duration + " nanoseconds");

            // Convert to milliseconds
            long durationInMillis = duration / 1000000;
            System.out.println("*  Duration: " + durationInMillis + " milliseconds");


            // Close connection
            socket.close();
            return durationInMillis;
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    private static void prettyPrintMatrixData(int matrixNum){
        int[] matrixSizes = new int[] {10, 20, 50, 100, 200};
        System.out.println("===================== Given " + matrixNum + " matrices: ======================");
        for (int i : matrixSizes) {
            System.out.println("************************************");
            System.out.println("*  Of size " + i + " (WITHOUT threading):");
            var d1 = communicateClientMatrices(matrixNum, i, false);
            System.out.println("*");

            System.out.println("*  Of size " + i + " (WITH threading):");
            var d2 = communicateClientMatrices(matrixNum, i, true);
            System.out.println("*");

            double speedup = (double)d2/d1;
            System.out.println("*  Speedup: " + speedup);
            System.out.println("*  Efficiency: " + speedup / (matrixNum - 1));
            System.out.println("************************************");
            System.out.println();
        }
        System.out.println();
    }


    public static void main(String[] args) {
        int[] matrixCounts = new int[] {2, 4, 8, 16, 32};

        for (int m : matrixCounts){
            prettyPrintMatrixData(m);
        }
    }
}
