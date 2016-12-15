package org.apache.http;

import java.util.Arrays;

public class LevenshteinDistanceMetric  {
	/**
	 * * Calculates the distance between Strings x and y using the * <b>Dynamic
	 * Programming</b> algorithm.
	 */
	public final int distance(String x, String y) {
    	long start = System.nanoTime();
		int m = x.length();
		int n = y.length();
		int[][] T = new int[2][20];
		T[0][0] = 0;
		for (int j = 0; j < m; j++) {
			T[0][j + 1] = T[0][j] + 1;
		}
		System.out.println(Arrays.toString(T[0] )); 
		for (int i = 0; i < n; i++) {
			T[ 1][0] = i+1; //Î²²¿append£¬¾àÀëÏÔÈ»ÊÇ1
			for (int j =  0; j <= ( m-1 ); j++) {
			//for (int j = i-1>0?i-1:0; j <= (i+1>n-1?n-1:i+1); j++) {
				T[  1][j + 1] = min(T[0][j] + sub(x, j, y, i), T[0][j + 1]
						+ 1, T[ 1][j] + 1);
			}
//			System.out.println(Arrays.toString(T[0] ));
			System.out.println(Arrays.toString(T[1] )); 
			System.arraycopy(T[1], 0, T[0], 0, T[1].length);
//			System.out.println(" ");
		}
    	System.out.println(System.nanoTime()-start);
		return T[0][m];
	}

	private int sub(String x, int xi, String y, int yi) {
		return x.charAt(xi) == y.charAt(yi) ? 0 : 1;
	}

	private int ins(String x, int xi) {
		return 1;
	}

	private int del(String x, int xi) {
		return 1;
	}

	private int min(int a, int b, int c) {
		return Math.min(Math.min(a, b), c);
	}
	
	public static void main(String...s){
		LevenshteinDistanceMetric ldm = new LevenshteinDistanceMetric();
		System.out.println(ldm.distance("azzelity163134pzs", "asfsdfa"));
	}
}
