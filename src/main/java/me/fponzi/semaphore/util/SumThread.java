/*******************************************************************************
 * Federico Ponzi <federico.ponzi92@gmail.com>
 * @federico_ponzi
 * https://fponzi.me
 * Copyright (c) 2018
 ******************************************************************************/

package me.fponzi.semaphore.util;

import me.fponzi.semaphore.SemaphoreInterface;
import me.fponzi.util.IntegerWrapper;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * A simple thread which adds `times` times 1 to the IntegerWrapper value.
 */
public class SumThread implements Runnable{
    public static final int times = 5;
    private final SemaphoreInterface semaphore;
    private volatile AtomicInteger p;
    private volatile IntegerWrapper wrapper;

    public SumThread(SemaphoreInterface semaphore, IntegerWrapper test_value)
    {
        this.wrapper = test_value;
        this.semaphore = semaphore;
    }
    @Override
    public void run()
    {
        this.semaphore.acquire();
        for(int i = 0; i < 10; i++)
        {
            System.out.println(Thread.currentThread().getName());
        }
        
        this.semaphore.release();
    }
}