package edu.vt.ece.locks;
import edu.vt.ece.bench.Blocking_Priority_Queue;
import edu.vt.ece.bench.Counter;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.PriorityBlockingQueue;
import edu.vt.ece.bench.QNode;
public class PriorityCLH implements Lock {
	//final ThreadLocal<QNode> myPred;  
	public static int THREAD_COUNT;
	final ThreadLocal<QNode> myNode; 
	AtomicBoolean state = new AtomicBoolean(false);
	//final ThreadLocal<Integer> mycount;
    //final AtomicReference<QNode> tail;
    final AtomicReference<QNode> tail = new AtomicReference<QNode>(new QNode());
   PriorityBlockingQueue<QNode> queue = new PriorityBlockingQueue<QNode>();   
   long time=0;
    //public static QNode QNode_object;
    public PriorityCLH() {
    	//this.tail = new AtomicReference<QNode>();
    	
    	this.myNode = new ThreadLocal<QNode>() {  
            protected QNode initialValue() {  
                return new QNode();  
            }  
        };
        
  
          
    }  
  
    public void lock() {  
    	
          final QNode qnode = this.myNode.get(); 
          qnode.priority = Thread.currentThread().getPriority();
          //qnode.my_count = qnode.my_count + 1;
          queue.add(qnode);
          //AtomicInteger a =  queue.size();
          //while(queue.size()<THREAD_COUNT);
          while(!(queue.peek() == qnode));
          while(state.getAndSet(true));
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
//    public boolean trylock(){
//    	final QNode qnode = this.myNode.get(); 
//        qnode.priority = Thread.currentThread().getPriority();
//        //qnode.my_count = qnode.my_count + 1;
//        queue.add(qnode);
//        //AtomicInteger a =  queue.size();
//        //while(queue.size()<THREAD_COUNT);
//        while((!(queue.peek() == qnode))&& state.getAndSet(true)){
//        	while(System.currentTimeMillis() - time >= 20){
//        		return false;
//        	}
//        	}
//        
//        	return true;
//    }

    }

