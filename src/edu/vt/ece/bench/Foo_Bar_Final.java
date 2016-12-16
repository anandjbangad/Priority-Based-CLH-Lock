package edu.vt.ece.bench;

import edu.vt.ece.locks.Lock;
import edu.vt.ece.locks.TTAS;

public class Foo_Bar_Final extends Thread {
	private static final int THREAD_COUNT =32;
	//final String lockclass = "TTAS";
	private static int ID_GEN = 0;
	private int id;
	//private String threadName;
	private Thread t;
	volatile int counter;
	static int i;
	static long time;
	TTAS ttas_lock = new TTAS();
	static int []b = new int[THREAD_COUNT];
	public void run(){
		long startTime = System.nanoTime();
		foo();
		//time = (System.nanoTime() - startTime);
		
			ttas_lock.lock();
			try{
				counter = counter + 1;
				
			}
			finally{
				
				ttas_lock.unlock();
			}
			while(counter<THREAD_COUNT);
			
			/*
			//
			//System.out.println(t.getName() + "Entered foo");
			while(b[THREAD_COUNT - 1]!=2){
					//System.out.println(i);
					if(Integer.parseInt(t.getName()) == i){
						b[i]=1;
						i++;
					}
					if(Integer.parseInt(t.getName()) ==THREAD_COUNT - 1){
						if(b[THREAD_COUNT- 1]==1){
							b[THREAD_COUNT - 1] = 2;
						}	
					}
					
				}*/
		bar();
		//System.out.println("Total Time is" + time);
	}
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		for(int t=0; t<THREAD_COUNT; t++){            
			new Foo_Bar_Final().start();
			}
		
	}
	
	public void foo(){
		System.out.println(t.getName() + "Entered foo");
		
	}
	public void bar(){
		System.out.println(t.getName() + "Entered bar");
		return;
	}
	public void start(){
		if(t==null){
			id = ID_GEN++;
			t =new Thread (this,Integer.toString(id));
			t.start();
		}
	}
	public int getThreadId(){
		return id;
	}
	}

