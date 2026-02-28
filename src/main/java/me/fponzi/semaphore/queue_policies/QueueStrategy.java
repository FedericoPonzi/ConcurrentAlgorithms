/*******************************************************************************
 * Federico Ponzi <me@fponzi.me>
 * https://fponzi.me
 * Copyright (c) 2018
 ******************************************************************************/

package me.fponzi.semaphore.queue_policies;

public interface QueueStrategy {
    void add(Thread t);

    Thread remove();

    int size();
}
