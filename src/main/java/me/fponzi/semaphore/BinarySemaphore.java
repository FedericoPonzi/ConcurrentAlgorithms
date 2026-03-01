/*******************************************************************************
 * Federico Ponzi <me@fponzi.me>
 * https://fponzi.me
 * Copyright (c) 2018
 ******************************************************************************/

package me.fponzi.semaphore;

public class BinarySemaphore extends CountingSemaphore {
    public BinarySemaphore() {
        super(1);
    }
}
