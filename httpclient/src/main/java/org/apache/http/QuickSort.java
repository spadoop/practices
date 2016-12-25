package org.apache.http;

import java.util.Arrays;

public class QuickSort {
	public void quickSort(int arr[],int low,int high)
	{
		int l=low;
		int h=high;
		int povit=arr[low];

		while(l<h)
		{
//			Arrays.asList(arr).forEach(x->System.out.print(x+",") ) ;
//			System.out.println(" ");
			while(l<h&&arr[h]>=povit)
				h--;
			if(l<h){
				arr[h]^=arr[l];
				arr[l]^=arr[h];
				arr[h]^=arr[l];
				l++;
			}

			while(l<h&&arr[l]<=povit)
				l++;

//			Arrays.asList(arr).forEach(x->System.out.print(x+",") ) ;
			if(l<h){
				arr[h]^=arr[l];
				arr[l]^=arr[h];
				arr[h]^=arr[l];
				h--;
			}
		}

		System.out.print("l="+(l+1)+"h="+(h+1)+"povit="+povit+"\n");
		if(l>low)quickSort(arr,low,l-1);
		if(h<high)quickSort(arr,l+1,high);
	}  
	/*//////////////方式三：减少交换次数，提高效率/////////////////////*/
	private <T extends Comparable<? super T>>	void quickSort3(T[] targetArr,int start,int end)
	{
		int i=start,j=end;
		T key=targetArr[start];

		while(i<j)
		{
			/*按j--方向遍历目标数组，直到比key小的值为止*/
			while(j>i&&targetArr[j].compareTo(key)>=0)
			{
				j--;
			}
			if(i<j)
			{
				/*targetArr[i]已经保存在key中，可将后面的数填入*/
				Arrays.asList(targetArr).forEach(x->System.out.print(x+",") ) ;
				targetArr[i]=targetArr[j];
				i++;
			}
			System.out.println(" ");
			/*按i++方向遍历目标数组，直到比key大的值为止*/
			while(i<j&&targetArr[i].compareTo(key)<=0)
				/*此处一定要小于等于零，假设数组之内有一亿个1，0交替出现的话，而key的值又恰巧是1的话，那么这个小于等于的作用就会使下面的if语句少执行一亿次。*/
			{
				i++;
			}
			if(i<j)
			{
				Arrays.asList(targetArr).forEach(x->System.out.print(x+",") ) ;
				/*targetArr[j]已保存在targetArr[i]中，可将前面的值填入*/
				targetArr[j]=targetArr[i];
				j--;
			}
		}
		/*此时i==j*/
		targetArr[i]=key;

		Arrays.asList(targetArr).forEach(System.out::print ) ;
		System.out.println("-----");
		if(i==start && j==end) return;
		/*递归调用，把key前面的完成排序*/
		this.quickSort3(targetArr,start,i-1);

		/*递归调用，把key后面的完成排序*/
		this.quickSort3(targetArr,j+1,end);

	}
	
	public static void main(String ...arg){
		QuickSort a = new QuickSort();
		int[] targetArr = {5,1,4,8,3,7,6,2,9};
		a.quickSort (targetArr, 0, 8);
		Arrays.asList(targetArr).forEach(System.out::print ) ;
	}
}
