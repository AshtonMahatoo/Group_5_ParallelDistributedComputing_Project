import java.util.ArrayList;
import java.util.Arrays;

public class Strassen implements Runnable {
    int[][] matrixA;
    int[][] matrixB;
    volatile int[][] matrixC;

    public Strassen(int[][] matrixA, int[][] matrixB) {
        this.matrixA = matrixA;
        this.matrixB = matrixB;
    }

    public int[][] getMatrixC(){
        return this.matrixC;
    }

    @Override
    public void run() {
        matrixC = multiply(matrixA, matrixB);
    }

    public int[][] multiply(int[][] A, int[][] B) {

        try {
            int n = A.length;
            int[][] result = new int[n][n];

            if (n == 1) {
                result[0][0] = A[0][0] * B[0][0];
            } else {
                int newSize = n / 2;

                int[][] A11 = MatrixUtils.split(A, 0, 0, newSize);
                int[][] A12 = MatrixUtils.split(A, 0, newSize, newSize);
                int[][] A21 = MatrixUtils.split(A, newSize, 0, newSize);
                int[][] A22 = MatrixUtils.split(A, newSize, newSize, newSize);

                int[][] B11 = MatrixUtils.split(B, 0, 0, newSize);
                int[][] B12 = MatrixUtils.split(B, 0, newSize, newSize);
                int[][] B21 = MatrixUtils.split(B, newSize, 0, newSize);
                int[][] B22 = MatrixUtils.split(B, newSize, newSize, newSize);

                ArrayList<Strassen> runnables = new ArrayList<>();
                runnables.add(new Strassen(MatrixUtils.add(A11, A22), MatrixUtils.add(B11, B22)));
                runnables.add(new Strassen(MatrixUtils.add(A21, A22), B11));
                runnables.add(new Strassen(A11, MatrixUtils.subtract(B12, B22)));
                runnables.add(new Strassen(A22, MatrixUtils.subtract(B21, B11)));
                runnables.add(new Strassen(MatrixUtils.add(A11, A12), B22));
                runnables.add(new Strassen(MatrixUtils.subtract(A21, A11), MatrixUtils.add(B11, B12)));
                runnables.add(new Strassen(MatrixUtils.subtract(A12, A22), MatrixUtils.add(B21, B22)));

                ArrayList<Thread> t = new ArrayList<>();
                for (Strassen strassen : runnables) {
                    Thread thread = new Thread(strassen);
                    t.add(thread);
                    thread.start();
                }
                for (Thread thread : t) {
                    thread.join();
                }

                int[][] M1 = runnables.get(0).getMatrixC();
                int[][] M2 = runnables.get(1).getMatrixC();
                int[][] M3 = runnables.get(2).getMatrixC();
                int[][] M4 = runnables.get(3).getMatrixC();
                int[][] M5 = runnables.get(4).getMatrixC();
                int[][] M6 = runnables.get(5).getMatrixC();
                int[][] M7 = runnables.get(6).getMatrixC();

                int[][] C11 = MatrixUtils.add(MatrixUtils.subtract(MatrixUtils.add(M1, M4), M5), M7);
                int[][] C12 = MatrixUtils.add(M3, M5);
                int[][] C21 = MatrixUtils.add(M2, M4);
                int[][] C22 = MatrixUtils.add(MatrixUtils.subtract(MatrixUtils.add(M1, M3), M2), M6);

                MatrixUtils.join(C11, result, 0, 0);
                MatrixUtils.join(C12, result, 0, newSize);
                MatrixUtils.join(C21, result, newSize, 0);
                MatrixUtils.join(C22, result, newSize, newSize);

                return result;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
