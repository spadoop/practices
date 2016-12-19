package org.apache.http;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;


public class SingleDemo {  
    private static SingleDemo s = null;    
    private SingleDemo(){}  
    public  static  SingleDemo getInstance() throws   InterruptedException, IOException{  
        if(s==null){    

            Files.lines(Paths.get("D:\\crowd.csv"), Charset.forName("gbk") ).forEach(System.out::println);          
            s=new SingleDemo();
        }
        return s;  
    }  
    public static void main(String[] args) throws InterruptedException, IOException {  
         ThreadTest t = new ThreadTest(); 
        Thread[] th = new Thread[50];
        for(int i=0;i<2;i++) {
        	th[i] = new Thread(t);
        	th[i].start();
        }
        for(int i=0;i<2;i++) {th[i].join();}
        System.out.println(t.singles.size()); 
//    	System.out.println(getTextFromText());
    }   
    public static String getTextFromText( ){  
        
        try {  
            InputStreamReader isr = new InputStreamReader(new FileInputStream("d:\\crowd.csv"),"GB2312");  
            BufferedReader br = new BufferedReader(isr);  
              
            StringBuffer sb = new StringBuffer();     
            String temp = null;     
            while((temp = br.readLine()) != null){     
                sb.append(temp);     
            }     
            br.close();          
            return sb.toString();     
        } catch (FileNotFoundException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
              
        }catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }     
        return null;  
}  
} 
class ThreadTest implements Runnable {  
    //存放单例对象，使用Set是为了不存放重复元素  
    public Set<SingleDemo> singles = new HashSet<SingleDemo>();  
    @Override  
    public void run() {  
        //获取单例  
        SingleDemo s=null;
		try {
	        System.out.println(Thread.currentThread().getId()+" start...");
			s = SingleDemo.getInstance(); 
		} catch (InterruptedException | IOException e) { 
			e.printStackTrace();
		}  
        //添加单例  
        singles.add(s);  
    }  
}  