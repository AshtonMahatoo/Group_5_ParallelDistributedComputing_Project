

public class MatrixUtils {

    public static int[][] add(int[][] A, int[][] B){
        int n = A.length;
        int [][] result = new int[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                result[i][j] = A[i][j] + B[i][j];
            }
        }
        return result;
    }

    public static int[][] subtract(int[][] A, int[][] B){
        int n = A.length;
        int [][] result = new int[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                result[i][j] = A[i][j] - B[i][j];
            }
        }
        return result;
    }

    public static int[][] split(int[][] matrix, int row, int col, int size) {
        int[][] result = new int[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(matrix[row + i], col, result[i], 0, size);
        }
        return result;
    }

    public static void join(int[][] C, int[][] P, int row, int col) {
        int n = C.length;
        for (int i = 0; i < n; i++) {
            System.arraycopy(C[i], 0, P[row + i], col, n);
        }
    }
    
}
