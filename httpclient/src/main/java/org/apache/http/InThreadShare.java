package org.apache.http;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class InThreadShare {
	private static int data;
	private static Map<Long, Integer> threadMap = new ConcurrentHashMap<Long, Integer>();

	public static void main(String[] args) throws InterruptedException {  
		for (int i = 1; i <= 3  ; i++) {			  
			Thread t=new MyThread(i);
			t.start(); 
		}
	}
	static class MyThread extends Thread{
		int id;
		MyThread(int id){
			this.id=id;
		}
		@Override
		public void run() {
			data = new Random().nextInt();
			System.out.println("runner " + id+" "+data);
//			while(threadMap.get(Thread.currentThread().getName())==null)
				 threadMap.put((long)id, data); 
			new A( id).getData();
			new B( id).getData();
		}
	}
	static class A {
		int id;
		A(int id){
			this.id=id;
		}
		public void getData() {   
			System.out.println(threadMap);
			Integer data =  threadMap.get( (long)id );
			System.out.println(threadMap +" A " + id+ "	data" + data);
		}
	}

	static class B {
		int id;
		B(int id){
			this.id=id;
		}
		public void getData() {
			System.out.println(threadMap);
			Integer data= threadMap.get ((long)id );
			System.out.println(threadMap +" B " + id+ "	data" + data);
		}
	}
}