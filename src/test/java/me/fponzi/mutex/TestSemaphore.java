/*******************************************************************************
 * Federico Ponzi <federico.ponzi92@gmail.com>
 * @federico_ponzi
 * https://fponzi.me
 * Copyright (c) 2018
 ******************************************************************************/

package me.fponzi.mutex;

import me.fponzi.mutex.util.SumThread;
import me.fponzi.util.IntegerWrapper;
import org.junit.Assert;
import org.junit.Test;

public class TestSemaphore
{
    private final int[] numberOfProcesses = { 2, 5, 50, 100};
    
    private int testLock(int number_of_threads, MutexInterface p)
    {
        Thread[] threads = new Thread[number_of_threads];
        IntegerWrapper w = new IntegerWrapper();
    
        for (int i = 0; i < number_of_threads; i++)
        {
            threads[i] = new Thread(new SumThread(p, w), "" + i);
        }
    
        for (Thread t : threads)
        {
            t.start();
        }
        for (Thread t : threads)
        {
            try
            {
                t.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        return w.val;
    }
    
    @Test
    public void testBinarySemaphore()
    {
        for(int t : numberOfProcesses)
        {
            Assert.assertEquals(testLock(t, new PetersonLockUnlock(t)), t* SumThread.times);
        }
    }
    
}
