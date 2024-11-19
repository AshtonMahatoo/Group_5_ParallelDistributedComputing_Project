import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Server {

    // Outer wrapper that just pairs off matrices and feeds them to Strassen.multiply
    public static int[][] strassenHelper(ArrayList<int[][]> matrixList) {
        if (matrixList.size() == 1) {
            return matrixList.get(0);
        }
        try {

            int matrixCount = (matrixList.size());
            ArrayList<StrassenThread> threads = new ArrayList<>();

            for (int i = 0; i < matrixCount; i = i + 2) {
                StrassenThread strassenThread = new StrassenThread(matrixList.get(i), matrixList.get(i + 1));
                threads.add(strassenThread);
            }
            for (Thread thread : threads) {
                thread.start();
            }
            for (Thread thread : threads) {
                thread.join();
            }

            ArrayList<int[][]> results = new ArrayList<>();
            for (StrassenThread thread : threads) {
                results.add(thread.matrixC);
            }
            return strassenHelper(results);
        } catch (Exception e) {

        }
        return null;
    }

    public static class StrassenThread extends Thread {
        int[][] matrixA;
        int[][] matrixB;
        volatile int[][] matrixC;

        public StrassenThread(int[][] matrixA, int[][] matrixB){
            this.matrixA = matrixA;
            this.matrixB = matrixB;
        }

        @Override
        public void run() {
            //this.SerialMultiply();
            this.ParallelMultiply();
        }

        private void ParallelMultiply(){
            var r = new ParallelStrassen(matrixA, matrixB);
            var t = new Thread(r);
            t.start();
            try {
                t.join();
            }
            catch (Exception e){
                
            }
            matrixC = r.matrixC;
        }

        private void SerialMultiply(){
            matrixC = Strassen.multiply(matrixA, matrixB);
        }
    }

    public static void main(String[] args) {
        try {

            ServerSocket serverSocket = new ServerSocket(5000); // Server listens on port 5000
            System.out.println("Server started and waiting for client connection...");

            while (true) {
                Socket routerSocket = serverSocket.accept(); // Wait for client connection via Router
                ObjectInputStream in = new ObjectInputStream(routerSocket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(routerSocket.getOutputStream());

                // Take in Matrix List -- this can be any number of matrices (10, 20, 50, etc)
                ArrayList<int[][]> matrixList = (ArrayList<int[][]>) in.readObject();

                // StrassenHelper splits N matrices into N/2 pairs (recursively) and multiplies them together using Strassens
                int[][] resultantMatrix = strassenHelper(matrixList);

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
