package org.apache.http;

public class Newton {
	public static void main(String[] args) {

        float num = 0.2f;

        float e = 0.01F; 
        sqrtNewton(num, e);  

        e = 0.0001F; 
        sqrtNewton(num, e); 

        e = 0.00001F; 
        sqrtNewton(num, e);

 }




private static float sqrtNewton(float num, float e) {

        

        float guess = 1;

        float e0;
        System.out.printf("Try %f, y:  %f\n", guess, guess*guess - num   );
        int count = 0; 
        do { 
               guess = (guess + num / guess) / 2; 
               e0 = guess*guess - num; 
               count++; 
               System.out.printf("Try %f, e: %f\n", guess, e0);

        } while (e0 > e); 
        System.out.printf("Try %d times, result: %f\n", count, guess); 
        return guess;

 }
}
