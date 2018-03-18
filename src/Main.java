import me.fponzi.mutex.BakeryAlgorithm;
import me.fponzi.mutex.MutexInterface;

public class Main {
    volatile static int test_value = 0;

    public static class PrintThread implements Runnable{
        private MutexInterface mutex;

        PrintThread(MutexInterface m)
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
        final int NTHREADS = 100;

        MutexInterface p = new BakeryAlgorithm(NTHREADS);

        Thread[] threads = new Thread[NTHREADS];
        while (true) {
            test_value = 0;
            for (int i = 0; i < NTHREADS; i++) {
                threads[i] = new Thread(new PrintThread(p), "" + i);
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
