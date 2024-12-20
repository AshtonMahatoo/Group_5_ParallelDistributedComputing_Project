
/**
Progress Report: The Design and Implementation of a Client-Server Paradigm
Date: 11/19/2
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
public class MatrixFunction {

    public static int[][] add(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = A[i][j] + B[i][j];
            }
        }
        return result;
    }

    public static int[][] subtract(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = A[i][j] - B[i][j];
            }
        }
        return result;
        
    }

    public static int[][] combine(int[][] C11, int[][] C12, int[][] C21, int[][] C22) {
        int n = C11.length;
        int[][] result = new int[2 * n][2 * n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = C11[i][j];
                result[i][j + n] = C12[i][j];
                result[i + n][j] = C21[i][j];
                result[i + n][j + n] = C22[i][j];
            }
        }
        return result;
    }
}


