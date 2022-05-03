/*******************************************************************************
 * Federico Ponzi <federico.ponzi92@gmail.com>
 * @federico_ponzi
 * https://fponzi.me
 * Copyright (c) 2018
 ******************************************************************************/

package me.fponzi.semaphore.queue_policies;

public interface QueueStrategy
{
    void add(Thread t);
    Thread remove();
    int size();
}
