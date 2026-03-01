package me.fponzi.semaphore;

import org.jetbrains.kotlinx.lincheck.LinChecker;
import org.jetbrains.kotlinx.lincheck.annotations.Operation;
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions;
import org.junit.Test;

/**
 * Lincheck model-checking test for CountingSemaphore.
 * Uses a semaphore with 1 resource (mutex behavior) to protect a counter,
 * then verifies linearizability of the counter operations.
 */
public class CountingSemaphoreLincheckTest {

    private final CountingSemaphore sem = new CountingSemaphore(1);
    private int counter = 0;

    @Operation
    public int incrementAndGet() {
        sem.acquire();
        try {
            return ++counter;
        } finally {
            sem.release();
        }
    }

    @Test
    public void modelCheckingTest() {
        ModelCheckingOptions options = new ModelCheckingOptions()
                .iterations(30)
                .invocationsPerIteration(100)
                .threads(2)
                .actorsPerThread(3);
        LinChecker.check(CountingSemaphoreLincheckTest.class, options);
    }
}
