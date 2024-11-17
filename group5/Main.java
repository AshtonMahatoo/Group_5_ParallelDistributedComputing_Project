public class Main {
    public static void main(String[] args) throws InterruptedException {
        int[][] A = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}
        };

        int[][] B = {
            {17, 18, 19, 20},
            {21, 22, 23, 24},
            {25, 26, 27, 28},
            {29, 30, 31, 32}
        };

        int threads = 3; // Test with 1, 3, 5, or 7

        System.out.println("Using " + threads + " threads:");
        int[][] result = StrassenParallel.strassen(A, B, threads);

        for (int[] row : result) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
