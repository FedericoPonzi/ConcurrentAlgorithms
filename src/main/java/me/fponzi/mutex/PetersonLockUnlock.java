package me.fponzi.mutex;

import java.util.concurrent.atomic.AtomicInteger;

public class PetersonLockUnlock implements MutexInterface
{
    private AtomicInteger[] FLAG;
    private AtomicInteger[] LAST;
    private int N;
    
    /**
     * PetersonLockUnlock
     *
     * @param N number of processes.
     */
    public PetersonLockUnlock(int N)
    {
        
        this.N = N;
        
        this.FLAG = new AtomicInteger[N];
        this.LAST = new AtomicInteger[N];
        
        for (int i = 0; i < N; i++)
        {
            this.FLAG[i] = new AtomicInteger(-1);
            this.LAST[i] = new AtomicInteger(-1);
        }
    }
    
    public void lock(int i)
    {
        
        for (int l = 0; l < this.N - 1; l++)
        {
            this.FLAG[i].set(l);
            this.LAST[l].set(i);
            while (otherFlagsGt(l, i) && this.LAST[l].get() == i);
        }
    }
    
    /**
     * Other flags grather than. Utility method to improove readability.
     * @return
     */
    private boolean otherFlagsGt(int l, int i)
    {
        
        for (int k = 0; k < this.N; k++)
        {
            if (k != i && this.FLAG[k].get() >= l)
            {
                return true;
            }
        }
        return false;
    }
    
    public void unlock(int i)
    {
        
        this.FLAG[i].set(-1);
    }
}
