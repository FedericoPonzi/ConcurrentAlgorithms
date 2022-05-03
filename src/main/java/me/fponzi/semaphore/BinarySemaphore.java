/*******************************************************************************
 * Federico Ponzi <federico.ponzi92@gmail.com>
 * @federico_ponzi
 * https://fponzi.me
 * Copyright (c) 2018
 ******************************************************************************/

package me.fponzi.semaphore;
public class BinarySemaphore extends CountingSemaphore
{
    public BinarySemaphore(){
        super(2);
    }
}
