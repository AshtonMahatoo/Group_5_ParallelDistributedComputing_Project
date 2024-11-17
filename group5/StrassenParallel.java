public class StrassenParallel {

    public static int[][] strassen(int[][] A, int[][] B, int threads) throws InterruptedException {
        int n = A.length;
        if (n == 1) {
            return new int[][]{{A[0][0] * B[0][0]}};
        }

        int newSize = n / 2;

        // Partition matrices into submatrices
        int[][] A11 = new int[newSize][newSize];
        int[][] A12 = new int[newSize][newSize];
        int[][] A21 = new int[newSize][newSize];
        int[][] A22 = new int[newSize][newSize];
        int[][] B11 = new int[newSize][newSize];
        int[][] B12 = new int[newSize][newSize];
        int[][] B21 = new int[newSize][newSize];
        int[][] B22 = new int[newSize][newSize];

        for (int i = 0; i < newSize; i++) {
            for (int j = 0; j < newSize; j++) {
                A11[i][j] = A[i][j];
                A12[i][j] = A[i][j + newSize];
                A21[i][j] = A[i + newSize][j];
                A22[i][j] = A[i + newSize][j + newSize];

                B11[i][j] = B[i][j];
                B12[i][j] = B[i][j + newSize];
                B21[i][j] = B[i + newSize][j];
                B22[i][j] = B[i + newSize][j + newSize];
            }
        }

        int[][] M1, M2, M3, M4, M5, M6, M7;

        if (threads >= 7) {
            // Create threads for all 7 multiplications
            StrassenThread t1 = new StrassenThread(MatrixUtils.add(A11, A22), MatrixUtils.add(B11, B22));
            StrassenThread t2 = new StrassenThread(MatrixUtils.add(A21, A22), B11);
            StrassenThread t3 = new StrassenThread(A11, MatrixUtils.subtract(B12, B22));
            StrassenThread t4 = new StrassenThread(A22, MatrixUtils.subtract(B21, B11));
            StrassenThread t5 = new StrassenThread(MatrixUtils.add(A11, A12), B22);
            StrassenThread t6 = new StrassenThread(MatrixUtils.subtract(A21, A11), MatrixUtils.add(B11, B12));
            StrassenThread t7 = new StrassenThread(MatrixUtils.subtract(A12, A22), MatrixUtils.add(B21, B22));

            t1.start();
            t2.start();
            t3.start();
            t4.start();
            t5.start();
            t6.start();
            t7.start();

            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
            t7.join();

            M1 = t1.getResult();
            M2 = t2.getResult();
            M3 = t3.getResult();
            M4 = t4.getResult();
            M5 = t5.getResult();
            M6 = t6.getResult();
            M7 = t7.getResult();
        } else if (threads == 5) {
            // Create threads for 5 calculations; execute remaining sequentially
            StrassenThread t1 = new StrassenThread(MatrixUtils.add(A11, A22), MatrixUtils.add(B11, B22));
            StrassenThread t2 = new StrassenThread(MatrixUtils.add(A21, A22), B11);
            StrassenThread t3 = new StrassenThread(A11, MatrixUtils.subtract(B12, B22));
            StrassenThread t4 = new StrassenThread(A22, MatrixUtils.subtract(B21, B11));
            StrassenThread t5 = new StrassenThread(MatrixUtils.add(A11, A12), B22);

            t1.start();
            t2.start();
            t3.start();
            t4.start();
            t5.start();

            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();

            M1 = t1.getResult();
            M2 = t2.getResult();
            M3 = t3.getResult();
            M4 = t4.getResult();
            M5 = t5.getResult();
            M6 = strassen(MatrixUtils.subtract(A21, A11), MatrixUtils.add(B11, B12), 1);
            M7 = strassen(MatrixUtils.subtract(A12, A22), MatrixUtils.add(B21, B22), 1);
        } else if (threads == 3) {
            // Create threads for 3 calculations; execute remaining sequentially
            StrassenThread t1 = new StrassenThread(MatrixUtils.add(A11, A22), MatrixUtils.add(B11, B22));
            StrassenThread t2 = new StrassenThread(MatrixUtils.add(A21, A22), B11);
            StrassenThread t3 = new StrassenThread(A11, MatrixUtils.subtract(B12, B22));

            t1.start();
            t2.start();
            t3.start();

            t1.join();
            t2.join();
            t3.join();

            M1 = t1.getResult();
            M2 = t2.getResult();
            M3 = t3.getResult();
            M4 = strassen(A22, MatrixUtils.subtract(B21, B11), 1);
            M5 = strassen(MatrixUtils.add(A11, A12), B22, 1);
            M6 = strassen(MatrixUtils.subtract(A21, A11), MatrixUtils.add(B11, B12), 1);
            M7 = strassen(MatrixUtils.subtract(A12, A22), MatrixUtils.add(B21, B22), 1);
        } else {
            // No multithreading; sequential execution
            M1 = strassen(MatrixUtils.add(A11, A22), MatrixUtils.add(B11, B22), 1);
            M2 = strassen(MatrixUtils.add(A21, A22), B11, 1);
            M3 = strassen(A11, MatrixUtils.subtract(B12, B22), 1);
            M4 = strassen(A22, MatrixUtils.subtract(B21, B11), 1);
            M5 = strassen(MatrixUtils.add(A11, A12), B22, 1);
            M6 = strassen(MatrixUtils.subtract(A21, A11), MatrixUtils.add(B11, B12), 1);
            M7 = strassen(MatrixUtils.subtract(A12, A22), MatrixUtils.add(B21, B22), 1);
        }

        // Combine results into final submatrices
        int[][] C11 = MatrixUtils.add(MatrixUtils.subtract(MatrixUtils.add(M1, M4), M5), M7);
        int[][] C12 = MatrixUtils.add(M3, M5);
        int[][] C21 = MatrixUtils.add(M2, M4);
        int[][] C22 = MatrixUtils.add(MatrixUtils.subtract(MatrixUtils.add(M1, M3), M2), M6);

        return MatrixUtils.combine(C11, C12, C21, C22);
    }
}
