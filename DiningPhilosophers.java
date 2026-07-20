import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

class DiningPhilosophers {
    
    // Locks representing the 5 forks
    private final ReentrantLock[] forks = new ReentrantLock[5];
    // Semaphore to limit maximum concurrent diners to 4 (prevents deadlock)
    private final Semaphore dinerLimit = new Semaphore(4);

    public DiningPhilosophers() {
        for (int i = 0; i < 5; i++) {
            forks[i] = new ReentrantLock();
        }
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        
        // Find fork numbers based on philosopher ID
        int leftFork = philosopher;
        int rightFork = (philosopher + 1) % 5;
        
        // Only allow up to 4 philosophers to compete for forks at the same time
        dinerLimit.acquire();
        
        // Acquire both forks
        forks[leftFork].lock();
        forks[rightFork].lock();
        
        try {
            // Execute operations
            pickLeftFork.run();
            pickRightFork.run();
            eat.run();
            putLeftFork.run();
            putRightFork.run();
        } finally {
            // Always release locks and semaphore permits in the finally block
            forks[rightFork].unlock();
            forks[leftFork].unlock();
            dinerLimit.release();
        }
    }
}