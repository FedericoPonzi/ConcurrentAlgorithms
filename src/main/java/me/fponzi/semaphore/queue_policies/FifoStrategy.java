/*******************************************************************************
 * Federico Ponzi <federico.ponzi92@gmail.com>
 * @federico_ponzi
 * https://fponzi.me
 * Copyright (c) 2018
 ******************************************************************************/

package me.fponzi.semaphore.queue_policies;

import java.util.LinkedList;
import java.util.Queue;

public class FifoStrategy implements QueueStrategy
{
    Queue<Thread> queue;
    
    public FifoStrategy()
    {
        queue = new LinkedList<>();;
    }
    
    @Override
    public void add(Thread t)
    {
        queue.add(t);
    }
    
    @Override
    public Thread remove()
    {
        return queue.remove();
    }
    
    @Override
    public int size()
    {
        
        return queue.size();
    }
}
