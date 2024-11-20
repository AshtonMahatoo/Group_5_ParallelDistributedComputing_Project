import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Server {

    private static ExecutorService Executor;

    // Outer wrapper that just pairs off matrices and feeds them to Strassen.multiply
    private static int[][] strassenHelper(ArrayList<int[][]> matrixList, boolean useThreading) {
        if (matrixList.size() == 1) {
            return matrixList.get(0);
        }
        try {

            List<Future<?>> futures = new ArrayList<>();

            int matrixCount = (matrixList.size());
            ArrayList<MatrixPair> matrixPairs = new ArrayList<>();

            // Iterates through however many matrices are in matrixList and splits them into pairs
            for (int i = 0; i < matrixCount; i = i + 2) {
                MatrixPair matrixPair = new MatrixPair(matrixList.get(i), matrixList.get(i + 1), useThreading);
                matrixPairs.add(matrixPair);
            }

            // Adds each matrixPair to the task pool and starts it running
            for (Runnable pair : matrixPairs) {
                Future<?> f = Executor.submit(pair);
                futures.add(f);
            }
            
            // Waits for all tasks to finish
            for (Future<?> f : futures) {
                f.get();
            }

            // We now have half as many matrices as we started with, repeat
            ArrayList<int[][]> results = new ArrayList<>();
            for (MatrixPair pair : matrixPairs) {
                results.add(pair.matrixC);
            }
            return strassenHelper(results, useThreading);
        } catch (Exception e) {

        }
        return null;
    }


    private static class MatrixPair implements Runnable {
        int[][] matrixA;
        int[][] matrixB;
        boolean UseThreading;
        volatile int[][] matrixC;

        public MatrixPair(int[][] matrixA, int[][] matrixB, boolean useThreading){
            this.matrixA = matrixA;
            this.matrixB = matrixB;
            this.UseThreading = useThreading;
        }

        @Override
        public void run() {
            try{
                matrixC = StrassenParallel.strassen(matrixA, matrixB, UseThreading);
            }
            catch (Exception e){

            }
        }
    }

    public static void main(String[] args) {
        

        try {

            @SuppressWarnings("resource")
            ServerSocket serverSocket = new ServerSocket(5000); // Server listens on port 5000
            System.out.println("Server started and waiting for client connection...");

            while (true) {
                Socket routerSocket = serverSocket.accept(); // Wait for client connection via Router
                ObjectInputStream in = new ObjectInputStream(routerSocket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(routerSocket.getOutputStream());

                // Take in Matrix List -- this can be any number of matrices (10, 20, 50, etc)
                MatrixListWrapper wrapper = (MatrixListWrapper) in.readObject();
                int cores = wrapper.MatrixList.size() - 1;
                Executor = Executors.newFixedThreadPool(cores);

                // StrassenHelper splits N matrices into N/2 pairs (recursively) and multiplies them together using Strassens
                int[][] resultantMatrix = strassenHelper(wrapper.MatrixList, wrapper.UseThreading);

                // Give resultantMatrix to router
                out.writeObject(resultantMatrix);
                out.flush();
                System.out.println(Arrays.deepToString(resultantMatrix));


                /** Close socket connections */
                routerSocket.close();

            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
