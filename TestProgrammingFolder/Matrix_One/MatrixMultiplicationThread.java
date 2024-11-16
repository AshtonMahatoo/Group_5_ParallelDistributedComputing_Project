package Matrix_One;



public class MatrixMultiplicationThread extends Thread {
    private Matrix matrix1;
    private Matrix matrix2;
    private Matrix result;

    // Constructor to initialize the matrices
    public MatrixMultiplicationThread(Matrix matrix1, Matrix matrix2) {
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
    }

    // Override the run method to perform multiplication
    @Override
    public void run() {
        System.out.println("Starting matrix multiplication in thread...");
        result = MatrixMultiplier.multiply(matrix1, matrix2);
        System.out.println("Matrix multiplication completed. Result:");
        result.printMatrix();
    }

    // Getter for the result
    public Matrix getResult() {
        return result;
    }
}
