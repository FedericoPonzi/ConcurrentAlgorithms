/*******************************************************************************
 * Federico Ponzi <me@fponzi.me>
 * https://fponzi.me
 * Copyright (c) 2018
 ******************************************************************************/

package me.fponzi.mutex.util;

import me.fponzi.mutex.MutexInterface;
import me.fponzi.util.IntegerWrapper;

/**
 * A simple thread which adds `times` times 1 to the IntegerWrapper value.
 */
public class SumThread implements Runnable {
    public static final int times = 5;
    private final MutexInterface mutex;
    private volatile IntegerWrapper wrapper;

    public SumThread(MutexInterface m, IntegerWrapper testValue) {
        this.wrapper = testValue;
        this.mutex = m;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        int threadId = Integer.parseInt(threadName);

        for (int i = 0; i < times; i++) {
            mutex.lock(threadId);
            this.wrapper.val += 1;
            mutex.unlock(threadId);
        }
    }
}