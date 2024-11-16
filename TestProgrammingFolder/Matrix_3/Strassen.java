package Matrix_3;

/**
 ** Java Program to Implement Strassen Algorithm
 **/
 
 import java.util.Scanner;

import Matrix_2.MatrixGenerator;
 
 /** Class Strassen **/
 public class Strassen
 {
     /** Function to multiply matrices **/
     public int[][] multiply(int[][] A, int[][] B)
     {        
         int n = A.length;
         int[][] R = new int[n][n];
         /** base case **/
         if (n == 1)
             R[0][0] = A[0][0] * B[0][0];
         else
         {
             int[][] A11 = new int[n/2][n/2];
             int[][] A12 = new int[n/2][n/2];
             int[][] A21 = new int[n/2][n/2];
             int[][] A22 = new int[n/2][n/2];
             int[][] B11 = new int[n/2][n/2];
             int[][] B12 = new int[n/2][n/2];
             int[][] B21 = new int[n/2][n/2];
             int[][] B22 = new int[n/2][n/2];
  
             /** Dividing matrix A into 4 halves **/
             split(A, A11, 0 , 0);
             split(A, A12, 0 , n/2);
             split(A, A21, n/2, 0);
             split(A, A22, n/2, n/2);
             /** Dividing matrix B into 4 halves **/
             split(B, B11, 0 , 0);
             split(B, B12, 0 , n/2);
             split(B, B21, n/2, 0);
             split(B, B22, n/2, n/2);
  
             int [][] M1 = multiply(add(A11, A22), add(B11, B22));
             int [][] M2 = multiply(add(A21, A22), B11);
             int [][] M3 = multiply(A11, sub(B12, B22));
             int [][] M4 = multiply(A22, sub(B21, B11));
             int [][] M5 = multiply(add(A11, A12), B22);
             int [][] M6 = multiply(sub(A21, A11), add(B11, B12));
             int [][] M7 = multiply(sub(A12, A22), add(B21, B22));
  
             /**
               C11 = M1 + M4 - M5 + M7
               C12 = M3 + M5
               C21 = M2 + M4
               C22 = M1 - M2 + M3 + M6
             **/
             int [][] C11 = add(sub(add(M1, M4), M5), M7);
             int [][] C12 = add(M3, M5);
             int [][] C21 = add(M2, M4);
             int [][] C22 = add(sub(add(M1, M3), M2), M6);
  
             /** join 4 halves into one result matrix **/
             join(C11, R, 0 , 0);
             join(C12, R, 0 , n/2);
             join(C21, R, n/2, 0);
             join(C22, R, n/2, n/2);
         }
         /** return result **/    
         return R;
     }
     /** Funtion to sub two matrices **/
     public int[][] sub(int[][] A, int[][] B)
     {
         int n = A.length;
         int[][] C = new int[n][n];
         for (int i = 0; i < n; i++)
             for (int j = 0; j < n; j++)
                 C[i][j] = A[i][j] - B[i][j];
         return C;
     }
     /** Funtion to add two matrices **/
     public int[][] add(int[][] A, int[][] B)
     {
         int n = A.length;
         int[][] C = new int[n][n];
         for (int i = 0; i < n; i++)
             for (int j = 0; j < n; j++)
                 C[i][j] = A[i][j] + B[i][j];
         return C;
     }
     /** Funtion to split parent matrix into child matrices **/
     public void split(int[][] P, int[][] C, int iB, int jB) 
     {
         for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
             for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                 C[i1][j1] = P[i2][j2];
     }
     /** Funtion to join child matrices intp parent matrix **/
     public void join(int[][] C, int[][] P, int iB, int jB) 
     {
         for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
             for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                 P[i2][j2] = C[i1][j1];
     }    
     /** Main function **/
     public static void main (String[] args) 
     {

        int[][] matrix_4x4 = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}
        };

        int [][]matrix_4x4_2 = {

            {84,  6 ,19, 84}, 
            {88, 97 ,91, 94},
            {72 ,59, 48, 25},
            {41, 52 ,90 ,54}
        };

        int [][] matrix_8x8 = 
        {
            {62, 81, 92, 86, 54, 56 ,38 ,65},
            {54, 49, 65, 39, 31, 87, 89,82},
            {24 ,47, 50, 59,  1 ,18, 61, 62},
            {94 ,59, 86, 89, 39, 74, 58, 22},
            {44, 70 ,16, 53,  1, 69,  5, 19},
            {78 , 5 , 7, 21, 43, 84, 37, 22},
            {84,  6 ,19, 84, 88, 97 ,91, 94},
            {72 ,59, 48, 25, 41, 52 ,90 ,54}
        };
        int [][] matrix_8X8_2 = 
        {
            {90, 48, 71, 50,  58, 66, 77, 30},
            {41, 70,  9, 19,  44, 17, 96, 76},
            {55, 26, 42, 75,  20, 13, 93, 94},
            {11, 71, 59,  4,  16,  8, 38, 42},
            {79, 20, 10, 25,  16,  1, 20, 87},
            {86, 12, 50, 31,  84, 44, 92, 92},
            {59, 38, 92, 59,  80, 15, 94, 71},
            {30,  7, 36, 39, 100,  5, 36, 56}
        };
        

        int [][] matrix16x16 = 
        {

            {50 , 86, 65, 67, 45, 13, 34, 78, 49, 41, 69, 60, 35,  5, 11, 73},
            { 2 , 71, 56, 22,  4, 18, 88, 68, 16, 55, 46, 74, 93, 92, 58, 96},
            {26 , 54, 16, 78, 84, 10, 44, 39, 55, 39, 89,  2, 88, 48, 57, 55},
            {75 , 45, 57,  1, 88, 20, 46,  0, 32, 24, 28, 21, 60, 89, 33, 41},
            {16 , 75, 50, 18, 66, 48, 17, 36, 30, 35, 12, 28, 29, 20, 32, 21},
            {85 , 71, 19, 41, 52, 94,  3, 47, 95, 29, 94, 79, 14, 53, 17, 26},
             {9 , 40, 89, 85, 57, 85, 67,  0, 97, 82,  7,  8, 80, 81, 37, 56},
            {54,  45, 37, 81, 69, 94, 22, 88, 21,  9, 39, 12, 75, 99, 89, 33},
            {67, 100, 28, 35, 17, 13, 72, 46, 10, 72, 59, 22, 88, 12, 59,  0},
             {7,  50, 64, 89, 48, 20,  1, 21,  4, 57, 41, 27, 54,  1, 18 ,95},
            {53 , 47, 24, 36, 76, 60 , 6, 35 ,17, 36 ,70, 24,  9, 32, 70, 62},
            {50 , 74, 57, 66, 92, 17, 41, 95, 20, 99, 11, 44, 16 , 4, 97, 11},
            {26 , 18, 13, 24, 85, 38, 68, 33, 17, 96, 67, 65, 66, 41, 97, 63},
            {48 , 83, 31,  4,  3, 11, 59, 59, 94, 99, 88, 17, 99, 61,  1, 48},
            {56,  41, 63, 60, 14, 80, 29, 75, 59, 25, 74, 67, 72, 38, 73, 64},
            {33,  44,  5, 69,  7, 93,  0, 52, 38, 29, 36, 13, 95, 62, 40, 12}
            
        };


        int [][] matrix16x16_2 = 
        {


            {67, 100, 28, 35, 17, 13, 72, 46, 10, 72, 59, 22, 88, 12, 59,  0},
             {7,  50, 64, 89, 48, 20,  1, 21,  4, 57, 41, 27, 54,  1, 18 ,95},
            {53 , 47, 24, 36, 76, 60 , 6, 35 ,17, 36 ,70, 24,  9, 32, 70, 62},
            {50 , 74, 57, 66, 92, 17, 41, 95, 20, 99, 11, 44, 16 , 4, 97, 11},
            {26 , 18, 13, 24, 85, 38, 68, 33, 17, 96, 67, 65, 66, 41, 97, 63},
            {48 , 83, 31,  4,  3, 11, 59, 59, 94, 99, 88, 17, 99, 61,  1, 48},
            {56,  41, 63, 60, 14, 80, 29, 75, 59, 25, 74, 67, 72, 38, 73, 64},
            {33,  44,  5, 69,  7, 93,  0, 52, 38, 29, 36, 13, 95, 62, 40, 12},
            {50 , 86, 65, 67, 45, 13, 34, 78, 49, 41, 69, 60, 35,  5, 11, 73},
            { 2 , 71, 56, 22,  4, 18, 88, 68, 16, 55, 46, 74, 93, 92, 58, 96},
            {26 , 54, 16, 78, 84, 10, 44, 39, 55, 39, 89,  2, 88, 48, 57, 55},
            {75 , 45, 57,  1, 88, 20, 46,  0, 32, 24, 28, 21, 60, 89, 33, 41},
            {16 , 75, 50, 18, 66, 48, 17, 36, 30, 35, 12, 28, 29, 20, 32, 21},
            {85 , 71, 19, 41, 52, 94,  3, 47, 95, 29, 94, 79, 14, 53, 17, 26},
            {9 , 40, 89, 85, 57, 85, 67,  0, 97, 82,  7,  8, 80, 81, 37, 56 },
            {54,  45, 37, 81, 69, 94, 22, 88, 21,  9, 39, 12, 75, 99, 89, 33}
            
        };
         
        Strassen newStrassen = new Strassen();
  
         int[][] C = newStrassen.multiply(matrix16x16, matrix16x16_2);
  
         System.out.println("\nProduct of matrices A and  B : ");
         for (int i = 0; i < 16; i++)
         {
             for (int j = 0; j < 16; j++)
                 System.out.print(C[i][j] +" ");
             System.out.println();
         }
  
     }
 }