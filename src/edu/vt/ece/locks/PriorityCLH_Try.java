package edu.vt.ece.locks;
import edu.vt.ece.bench.Blocking_Priority_Queue;
import edu.vt.ece.bench.Counter;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.PriorityBlockingQueue;
import edu.vt.ece.bench.QNode;
public class PriorityCLH_Try implements Lock {
	//final ThreadLocal<QNode> myPred;  
	public static int count;
	public static int THREAD_COUNT;
	final ThreadLocal<QNode> myNode; 
	AtomicBoolean state = new AtomicBoolean(false);
	//final ThreadLocal<Integer> mycount;
    //final AtomicReference<QNode> tail;
    final AtomicReference<QNode> tail = new AtomicReference<QNode>(new QNode());
   PriorityBlockingQueue<QNode> queue = new PriorityBlockingQueue<QNode>();   
   
    //public static QNode QNode_object;
    public PriorityCLH_Try() {
    	//this.tail = new AtomicReference<QNode>();
    	
    	this.myNode = new ThreadLocal<QNode>() {  
            protected QNode initialValue() {  
                return new QNode();  
            }  
        };
        
  
          
    }  
  
    public void lock() {  
    	
        
          //AtomicInteger a =  queue.size();
          //while(queue.size()<THREAD_COUNT);
         //System.out.println("kmdkdkdkdkdkdk");
          while(!(trylock()));
          
          //( queue.peek().getPriority() == qnode.getPriority())); // && Integer.toHexString(System.identityHashCode(queue.peek())).equals(Integer.toHexString(System.identityHashCode(qnode)))  );
          
          //System.out.println("Thread entered" + qnode.getPriority());
          }  
  
    public void unlock() { 
    	final QNode qnode1 = this.myNode.get(); 
    	//while(qnode1.getmy_count()<100);
    	state.set(false);
    	queue.remove(qnode1);
    	
          //System.out.println(THREAD_COUNT);
    }  
  
//    public static class QNode {  
//        private volatile int priority;
//        public int getPriority(){
//        	return priority;
//        }
//    }  
    public boolean trylock(){
    	long time=System.nanoTime();
    		//System.out.println(time);
    		//System.out.println(System.currentTimeMillis());
    	  final QNode qnode = this.myNode.get(); 
          qnode.priority = Thread.currentThread().getPriority();
          //qnode.my_count = qnode.my_count + 1;
          queue.add(qnode);
        	while (System.nanoTime() - time >= 2000){
        		 if(!(queue.peek() == qnode)){
        			 if(state.getAndSet(true)){
        				 continue;
        			 }
        			 
        		 }
        		 else{
        			 return true;
        		 }
                }
        	queue.remove(qnode);
        	count = count + 1;
        	//System.out.println(count);
        	return false;

    }

    }

