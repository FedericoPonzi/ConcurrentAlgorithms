/*******************************************************************************
 * Federico Ponzi <federico.ponzi92@gmail.com>
 * @federico_ponzi
 * https://fponzi.me
 * Copyright (c) 2018
 ******************************************************************************/

package me.fponzi;

import me.fponzi.semaphore.BinarySemaphore;
import me.fponzi.semaphore.SemaphoreInterface;
import me.fponzi.semaphore.util.SumThread;
import me.fponzi.util.IntegerWrapper;

public class Main
{
    
    public static final int NTHREADS = 10;
    
    public static void main(String[] args) throws InterruptedException
    {
        
        Thread[] threads = new Thread[NTHREADS];
        
        while (true)
        {
            SemaphoreInterface sem = new BinarySemaphore();
            
            IntegerWrapper w = new IntegerWrapper();
            
            for (int i = 0; i < NTHREADS; i++)
            {
                threads[i] = new Thread(new SumThread(sem, w), "" + i);
            }
            
            for (Thread t : threads)
            {
                t.start();
            }
            
            for (Thread t : threads)
            {
                t.join();
            }
            System.out.println("Result:" + w + ", should be: " + NTHREADS * 5);
        }
    }
    
}
