/*******************************************************************************
 * Federico Ponzi <federico.ponzi92@gmail.com>
 * @federico_ponzi
 * https://fponzi.me
 * Copyright (c) 2018
 ******************************************************************************/

package me.fponzi.util;

/**
 * Used to share the same object to multiple threads.
 */
public class IntegerWrapper{
    public int val = 0;
    public IntegerWrapper(){}
    public IntegerWrapper(int test_value){
        this.val = 0;
    }
    
    @Override
    public String toString()
    {
        
        return val + "";
    }
}

