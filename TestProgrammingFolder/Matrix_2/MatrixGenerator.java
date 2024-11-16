package Matrix_2;

import java.util.Arrays;
import java.io.*;
import java.util.*;

public class MatrixGenerator {
    

    public int[][] matrix_4x4;
    public int[][] matrix_4x4_2;

    public static void printMatrix(int [][] matrix){
        for(int [] row : matrix){
            System.out.println(Arrays.toString(row));
        }
    }

    public static void main(String[] args) {

        
        
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



    }

    

    
}
