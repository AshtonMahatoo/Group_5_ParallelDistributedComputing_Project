class StrassenThread extends Thread {
    private final int[][] A;
    private final int[][] B;
    private int[][] result;

    public StrassenThread(int[][] A, int[][] B) {
        this.A = A;
        this.B = B;
    }

    @Override
    public void run() {
        try {
            result = StrassenParallel.strassen(A, B, 1); // Recursive call with 1 thread
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int[][] getResult() {
        return result;
    }
}
