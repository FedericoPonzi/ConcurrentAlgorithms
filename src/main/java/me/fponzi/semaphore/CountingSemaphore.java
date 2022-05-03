/*******************************************************************************
 * Federico Ponzi <federico.ponzi92@gmail.com>
 * @federico_ponzi
 * https://fponzi.me
 * Copyright (c) 2018
 ******************************************************************************/

package me.fponzi.semaphore;

import me.fponzi.semaphore.queue_policies.FifoStrategy;
import me.fponzi.semaphore.queue_policies.QueueStrategy;

public class CountingSemaphore implements SemaphoreInterface
{
    private final boolean privat;
    
    private int resources = 0;
    
    private QueueStrategy queue;
    
    /**
     *
     * @param resources available resources.
     * @param privat true, if this semaphore is private. A private semaphore can be downed only by creation process.
     */
    
    public CountingSemaphore(int resources, boolean privat)
    {
       this(resources, privat, new FifoStrategy());
    }
    
    /**
     * @param resources
     * @param privat
     * @param queueStrategy Choose the queue strategy. Use {@link me.fponzi.semaphore.queue_policies.FifoStrategy} for Strong semaphores.
     */
    public CountingSemaphore(int resources, boolean privat, QueueStrategy queueStrategy)
    {
        this.resources = resources;
        this.privat = privat;
        this.queue = queueStrategy;
    }
    
    public CountingSemaphore(int resources)
    {
        this(resources,false);
    }
    
    @Override
    synchronized public void acquire()
    {
        resources = resources -1;
        if(resources < 0)
        {
            queue.add(Thread.currentThread());
            try
            {
                System.out.println("Queue size: " + queue.size());
                wait();
            }catch (InterruptedException e)
            {
                e.printStackTrace();
                //ignored.
            }
        }
    }
    
    @Override
    synchronized public void release()
    {
        resources += 1;
        if(resources <= 0)
        {
            System.out.println("Queue size: " + queue.size());
            Thread t = queue.remove();
            
            t.notify();
        }
    }
}
