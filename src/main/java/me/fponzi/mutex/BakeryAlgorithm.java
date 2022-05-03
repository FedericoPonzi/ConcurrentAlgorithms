/*******************************************************************************
 * Federico Ponzi <federico.ponzi92@gmail.com>
 * @federico_ponzi
 * https://fponzi.me
 * Copyright (c) 2018
 ******************************************************************************/

package me.fponzi.mutex;

import java.util.Arrays;

/**
 * Algorithm by L.Lamport (1974)
 *
 *
 */
public class BakeryAlgorithm implements MutexInterface
{
    volatile private boolean[] flag;
    volatile private int[] my_turn;
    private int numberOfProcesses;

    public BakeryAlgorithm(int numberOfProcesses)
    {
        this.numberOfProcesses = numberOfProcesses;
        this.flag = new boolean[numberOfProcesses];
        this.my_turn = new int[numberOfProcesses];
        my_turn = Arrays.stream(my_turn).map((i) -> -1 ).toArray();
    }
    @Override
    public void lock(int i) {
        this.flag[i] = true;
        // The problem with bakery algorithm, is this instruction.
        my_turn[i] = Arrays.stream(this.my_turn).reduce(Integer::max).getAsInt() +1;
        // Since my_turn is not synchronized, we can read whatever value from array.
        // Aravind's solution fixes this by finding the max in a concurrency-free context (in the unlock).
        this.flag[i] = false;

        for (int j = 0; j < numberOfProcesses; j++){
            if(j == i ) continue;
            while(flag[j]);
            while((my_turn[j] != -1 ) && (my_turn[i] > my_turn[j] || (my_turn[i] == my_turn[j] && i > j)));
        }
    }

    @Override
    public void unlock(int i) {
        my_turn[i] = -1;
    }
}
