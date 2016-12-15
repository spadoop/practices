package org.apache.http;

import java.util.ArrayList;
import java.util.List;

import java.util.Arrays;

public class Solution {
	public static List<List<Integer>> threeSum(Integer[] nums) {
    	long start = System.nanoTime();
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(nums.length==0) return result;
        quickSort(nums,0,nums.length-1); 
        int i=0;
    	int j=0;
    	int zeroGroupZ=1; //the smallest num in the triplets
    	int zeroGroupJ=-1; //the biggest num in the triplets
    	for(int z=0;z<nums.length-2;z++){ 
    	    if(nums[z]>0) break;
    	    if(z>0 && nums[z-1]==nums[z])continue;

//        	System.out.println(System.nanoTime()-start);
    	    int target=0-nums[z];
top:        for(i=z+1;i<nums.length-1;i++){
    	        if(i>z+1 && nums[i-1]==nums[i])continue;
                if(nums[i]>target) break;
                 
                for(j=i+1;j<nums.length;j++){ 
    	            if(j>i+1 && nums[j-1]==nums[j])continue;
                    if(nums[i]+nums[j]>target) break;
                    if(nums[i]+nums[j]==target) {
                        // if(nums[i]==0||nums[j]==0){
                            if(zeroGroupZ==nums[z]&&zeroGroupJ==nums[j]){
                                break  ;
                            } else {
                                List<Integer> temp = new ArrayList<Integer>(3);
                                temp.add(nums[z]);
                                temp.add(nums[i]);
                                temp.add(nums[j]);
                                zeroGroupZ=nums[z];
                                zeroGroupJ=nums[j];
                                result.add(temp);
                            }
                        // } else result.add(temp);
                    }
                }
            }
    	}
    	System.out.println(System.nanoTime()-start);
    	Arrays.asList(nums).forEach(x->System.out.print(x+", "));
    	System.out.println("");
    	return result;
    }
    
    public static void quickSort(Integer arr[],int low,int high)
	{
		int l=low;
		int h=high;
		int povit=arr[low];

		while(l<h)
		{  
			while(l<h&&arr[h]>=povit)				h--;
			if(l<h){
				int temp=arr[h];
				arr[h]=arr[l];
				arr[l]=temp;
				l++;
			}
			while(l<h&&arr[l]<=povit)				l++;
			if(l<h){
				int temp=arr[h];
				arr[h]=arr[l];
				arr[l]=temp;
				h--;
			}
		}
		if(l>low)quickSort(arr,low,l-1);
		if(h<high)quickSort(arr,l+1,high);
	} 
    public static void main(String ...s){
    	Integer[] nums=
//    	new int[]{1,-1,-1,0};
    	new Integer[]{7,-1,14,-12,-8,7,2,-15,8,8,-8,-14,-4,-5,7,9,11,-4,-15,-6,1,-14,4,3,10,-5,2,1,6,11,2,-2,-5,-7,-6,2,-15,11,-6,8,-4,2,1,-1,4,-6,-15,1,5,-15,10,14,9,-8,-6,4,-6,11,12,-15,7,-1,-9,9,-1,0,-4,-1,-12,-2,14,-9,7,0,-3,-4,1,-2,12,14,-10,0,5,14,-1,14,3,8,10,-8,8,-5,-2,6,-11,12,13,-7,-12,8,6,-13,14,-2,-5,-11,1,3,-6};
    	System.out.println(threeSum(nums));
    	System.out.println((int)'a');
    }
}
