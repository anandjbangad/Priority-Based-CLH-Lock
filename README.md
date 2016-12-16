# Priority-Based-CLH-Lock
The CLH lock provides FCFS fairness, which is a useful property for many applications. But some applications may attach priorities to
threads (e.g., some thread may have a higher priority than others).
Here I have designed and implemented a priority CLH lock that ensures that the highest
priority thread that is waiting for the lock is always granted access to
the critical section than all other waiting threads.
Whenever the lock is released, the
highest priority waiting thread is granted access first.
A trylock() is added method that attempts to acquire the lock (respecting
thread priorities), and if it can’t acquire it within a predefined amount
of time (constructor argument in ms), it fails/aborts. trylock() will
return a boolean value indicating whether the lock was successfully
acquired
