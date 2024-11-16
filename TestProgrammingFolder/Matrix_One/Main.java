package Matrix_One;


public class Main {
    public static void main(String[] args) {
        // Initialize two 4x4 matrices
        int[][] matrixValues1 = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}
        };

        int[][] matrixValues2 = {
            {1, 0, 0, 1},
            {0, 1, 1, 0},
            {1, 1, 0, 0},
            {0, 0, 1, 1}
        };

        Matrix matrix1 = new Matrix(matrixValues1);
        Matrix matrix2 = new Matrix(matrixValues2);

        // Create and start the thread for matrix multiplication
        MatrixMultiplicationThread multiplicationThread = new MatrixMultiplicationThread(matrix1, matrix2);
        multiplicationThread.start();

        // Wait for the thread to complete
        try {
            multiplicationThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Get and print the result (already printed in the thread)
    }
}

