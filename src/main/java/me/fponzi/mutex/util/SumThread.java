/*******************************************************************************
 * Federico Ponzi <federico.ponzi92@gmail.com>
 * @federico_ponzi
 * https://fponzi.me
 * Copyright (c) 2018
 ******************************************************************************/

package me.fponzi.mutex.util;

import me.fponzi.mutex.MutexInterface;
import me.fponzi.util.IntegerWrapper;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * A simple thread which adds `times` times 1 to the IntegerWrapper value.
 */
public class SumThread implements Runnable{
    public static final int times = 5;
    private volatile AtomicInteger p;
    private MutexInterface mutex;
    private volatile IntegerWrapper wrapper;

    public SumThread(MutexInterface m, IntegerWrapper test_value)
    {
        this.wrapper = test_value;
        this.mutex = m;
    }
    @Override
    public void run()
    {
        String threadName = Thread.currentThread().getName();
        int threadId = Integer.parseInt(threadName);
        
        for (int i = 0; i < times; i++)
        {
            mutex.lock(threadId);
            this.wrapper.val +=1;
            mutex.unlock(threadId);
        }
    }
}