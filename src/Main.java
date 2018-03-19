import me.fponzi.mutex.AravindAlgorithm;
import me.fponzi.mutex.MutexInterface;

public class Main {
    volatile static int test_value = 0;
    public static final int NTHREADS = 100;

    public static class SumThread implements Runnable{
        private MutexInterface mutex;

        SumThread(MutexInterface m)
        {
            this.mutex = m;
        }
        @Override
        public void run()  {
            String threadName = Thread.currentThread().getName();
            int threadId = Integer.parseInt(threadName);

            for (int i = 0; i < 5; i++)
            {
                mutex.lock(threadId);
                test_value +=1;
                mutex.unlock(threadId);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread[] threads = new Thread[NTHREADS];
        
        while (true) {
            MutexInterface p = new AravindAlgorithm(NTHREADS);
            test_value = 0;

            for (int i = 0; i < NTHREADS; i++) {
                threads[i] = new Thread(new SumThread(p), "" + i);
            }

            for (Thread t : threads) {
                t.start();
            }
            for (Thread t : threads) {
                t.join();
            }
            System.out.println("Result:" + test_value + ", should be: " + NTHREADS*5);
        }
    }

}
