/**
Progress Report: The Design and Implementation of a Client-Server Paradigm
Date: 11/19/24
Project Name: CS 4504 PROJECT REPORT – PART2
Report Prepared by: Group 5 (Parallel Distributed Computing, Section W03, Fall 2024)
Courtney Faulkner, Nicholas Hodge, Ashton Mahatoo, Colson Sims, Joshua Smith, Mike Tokura, Carinne Tzurdecker, Giovanni Zavala

Report Submitted to: 
Professor Patrick O. Bobbie, PhD
Email: pbobbie@kennesaw.edu
Office Location: Atrium Bldg, J386
Office phone: 470.578.3810
CS 4504 PROJECT REPORT – PART1
Fall 2024
 */

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
            result = StrassenParallel.strassen(A, B, false); // Recursive call with 1 thread
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int[][] getResult() {
        return result;
    }
}