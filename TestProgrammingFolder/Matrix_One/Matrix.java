package Matrix_One;



public class Matrix {
    private int[][] values;

    // Constructor to initialize the 4x4 matrix
    public Matrix(int[][] values) {
        if (values.length == 4 && values[0].length == 4) {
            this.values = values;
        } else {
            throw new IllegalArgumentException("Matrix must be 4x4");
        }
    }

    // Getter for matrix values
    public int[][] getValues() {
        return values;
    }

    // Method to print the matrix
    public void printMatrix() {
        for (int[] row : values) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}

