package org.apache.http;

public class Newton {
	public static void main(String[] args) {

        float num = 12f;

        float e = 0.01F; 
        sqrtNewton(num, e);  

        e = 0.0001F; 
        sqrtNewton(num, e); 

        e = 0.00001F; 
        sqrtNewton(num, e);

 }




private static float sqrtNewton(float num, float e) {

        

        float x = 1;

        float e0;
        System.out.printf("Try %f, y:  %f\n", x, x*x*x - num   );
        int count = 0; 
        do { 
//               guess = (guess + num / guess) / 2; //Æ½·½¸ù
        	   x =  x *2/3+num/3/x/x;
               e0 = x*x*x - num; 
               count++; 
               System.out.printf("Try %f, e: %f\n", x, e0);

        } while (e0 > e); 
        System.out.printf("Try %d times, result: %f\n", count, x); 
        return x;

 }
}
