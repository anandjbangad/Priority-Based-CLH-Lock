//import edu.vt.ece.bench.Blocking_Priority_Queue;
import edu.vt.ece.bench.Counter;
import edu.vt.ece.bench.SharedCounter;
import edu.vt.ece.bench.TestThread;
import edu.vt.ece.locks.*;
import edu.vt.ece.bench.Blocking_Priority_Queue_TryLock;

public class Try_Lock {

	private static final int THREAD_COUNT =8;	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		String lockClass = "PriorityCLH_Try";
		PriorityCLH.THREAD_COUNT = THREAD_COUNT;
		final Counter counter = new SharedCounter(0, (Lock)Class.forName("edu.vt.ece.locks." + lockClass).newInstance());
		for(int t=0; t<THREAD_COUNT; t++){            
			new Blocking_Priority_Queue_TryLock(counter).start();
			}

		System.out.println("Threads failed "+PriorityCLH_Try.count+ " before entering CS");
	}
}


