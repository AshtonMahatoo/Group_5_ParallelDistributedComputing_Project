package Matrix_One;
public class MatrixMultiplier {
    // Method to multiply two 4x4 matrices
    public static Matrix multiply(Matrix m1, Matrix m2) {
        int[][] result = new int[4][4];
        int[][] values1 = m1.getValues();
        int[][] values2 = m2.getValues();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result[i][j] = 0;
                for (int k = 0; k < 4; k++) {
                    result[i][j] += values1[i][k] * values2[k][j];
                }
            }
        }

        return new Matrix(result);
    }
}

