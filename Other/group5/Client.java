
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


import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {

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

        try (Socket socket = new Socket("localhost", 5000);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

            out.writeObject(matrix16x16_2);
        }
    }
}