/*******************************************************************************
 * Federico Ponzi <federico.ponzi92@gmail.com>
 * @federico_ponzi
 * https://fponzi.me
 * Copyright (c) 2018
 ******************************************************************************/

package me.fponzi.mutex;

import java.util.Arrays;

public class AravindAlgorithm implements MutexInterface
{
    private final int numberOfProcesses;
    volatile private boolean[] stage;
    volatile private int[] date;
    volatile private boolean[] flag;
    
    public AravindAlgorithm(int numberOfProcesses)
    {
        this.numberOfProcesses = numberOfProcesses;
        this.flag = new boolean[numberOfProcesses];
        this.stage = new boolean[numberOfProcesses];
        this.date = new int[numberOfProcesses];
        resetDate();
    }
    
    
    public void lock(int i)
    {
        this.flag[i] = true;
        do{
            this.stage[i] = false;
            while(everyFlagDown(i));
            this.stage[i] = true;
        }while(everyStageDown(i));
    }
    private boolean everyFlagDown(int i)
    {
        for(int j = 0; j < numberOfProcesses; j++)
        {
            if(j == i) continue;
            if(!this.flag[j] || date[i] < date[j]) continue;
            else{
                return true;
            }
        }
        return false;
    }
    private boolean everyStageDown(int i )
    {
        for(int j = 0; j < numberOfProcesses; j++)
        {
            if (j == i) continue;
            
            if(this.stage[j]){
                return true;
            }
        }
        return false;
    }
    
    
    public void unlock(int i)
    {
        this.date[i] = Arrays.stream(this.date).reduce(Integer::max).getAsInt() + 1;
        if(this.date[i] > 2*numberOfProcesses){
            resetDate();
        }
        this.stage[i] = false;
        this.flag[i] = false;
    }
    
    
    private void resetDate()
    {
        for(int i = 0; i < numberOfProcesses; i++)
        {
            this.date[i] = i;
        }
    }
}
