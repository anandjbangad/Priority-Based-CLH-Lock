package edu.vt.ece.bench;
import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.Random;
//import edu.vt.ece.locks.CLH.QNode;
import edu.vt.ece.locks.PriorityCLH;
import edu.vt.ece.bench.Counter;
import edu.vt.ece.locks.PriorityCLH_Try;
import java.util.concurrent.ThreadLocalRandom;
public class Blocking_Priority_Queue_TryLock extends Thread {
	private static int ID_GEN = 0;
	private int id;
	int random_number;
	private Counter counter;
	Random rand = new Random(); 
	int MAX_COUNT =2000;
	//PriorityCLH_Try p;
	public Blocking_Priority_Queue_TryLock(Counter counter){
		id = ID_GEN++;
		this.counter = counter;
	}
	
	public void run(){
//		Priority_Compare p = new Priority_Compare(getThreadid());
//		QNode each_thread = new QNode(getThreadid());
//		queue.add(each_thread);
		Thread.currentThread().setPriority(ThreadLocalRandom.current().nextInt(1, 6));
		for(int i=0; i<MAX_COUNT;i++){
			counter.getAndIncrement();
			}
	//	System.out.println("Thread "+id+" "+ "with priority" +" "+ currentThread().getPriority() + " DONE.. <Counter:" + counter + ">");
		System.out.println("Threads failed "+PriorityCLH_Try.count+ " before entering CS");
}
	public int getThreadid(){
		return id;
	}
}
