public class Strassen {
    public static int[][] multiply(int[][] A, int[][] B) {
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

            int[][] M1 = multiply(MatrixUtils.add(A11, A22), MatrixUtils.add(B11, B22));
            int[][] M2 = multiply(MatrixUtils.add(A21, A22), B11);
            int[][] M3 = multiply(A11, MatrixUtils.subtract(B12, B22));
            int[][] M4 = multiply(A22, MatrixUtils.subtract(B21, B11));
            int[][] M5 = multiply(MatrixUtils.add(A11, A12), B22);
            int[][] M6 = multiply(MatrixUtils.subtract(A21, A11), MatrixUtils.add(B11, B12));
            int[][] M7 = multiply(MatrixUtils.subtract(A12, A22), MatrixUtils.add(B21, B22));

            int[][] C11 = MatrixUtils.add(MatrixUtils.subtract(MatrixUtils.add(M1, M4), M5), M7);
            int[][] C12 = MatrixUtils.add(M3, M5);
            int[][] C21 = MatrixUtils.add(M2, M4);
            int[][] C22 = MatrixUtils.add(MatrixUtils.subtract(MatrixUtils.add(M1, M3), M2), M6);

            MatrixUtils.join(C11, result, 0, 0);
            MatrixUtils.join(C12, result, 0, newSize);
            MatrixUtils.join(C21, result, newSize, 0);
            MatrixUtils.join(C22, result, newSize, newSize);
        }

        return result;
    }
}
