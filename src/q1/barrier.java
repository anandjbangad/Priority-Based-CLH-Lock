package q1;
import edu.vt.ece.locks.*;
public class barrier {
private Lock lock;
public static volatile int count;
public static int THREAD_COUNT= Q1_Test.THREAD_COUNT;
public volatile int[] array_barrier2 = new int[THREAD_COUNT];
public barrier(int count ,Lock lock){
	this.lock = lock;
	this.count = count;
}
public void barrier(){
	lock.lock();
	count = count +1;
	lock.unlock();
}
}
