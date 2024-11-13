

public class StrassenMultiplication{


    public static void main(String[] args) {
        

        int[][] matrixA = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}
        };

        int[][] matrixB = {
            {16, 15, 14, 13},
            {12, 11, 10, 9},
            {8, 7, 6, 5},
            {4, 3, 2, 1}
        };

        int [][] result = Strassen.multiply(matrixA, matrixB);

        System.out.println("resultant Matrix:");
        for (int i = 0; i < result.length; i++){
            for(int j = 0; j < result[i].length; j++){
                System.out.println(result[i][j] + " ");
            }
            System.out.println();
        }
    } 
    
}