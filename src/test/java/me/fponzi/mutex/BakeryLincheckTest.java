package me.fponzi.mutex;

import org.jetbrains.kotlinx.lincheck.LinChecker;
import org.jetbrains.kotlinx.lincheck.annotations.Operation;
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Lincheck model-checking test for BakeryAlgorithm.
 * Note: BakeryAlgorithm has a documented race condition in the non-atomic max
 * computation in lock(). Lincheck may detect this as a linearizability violation.
 */
public class BakeryLincheckTest {

    private static final int NUM_THREADS = 2;
    private final BakeryAlgorithm lock = new BakeryAlgorithm(NUM_THREADS);
    private final AtomicInteger idCounter = new AtomicInteger(0);
    private final ThreadLocal<Integer> threadId = ThreadLocal.withInitial(() -> idCounter.getAndIncrement());
    private int counter = 0;

    @Operation
    public int incrementAndGet() {
        int id = threadId.get();
        lock.lock(id);
        try {
            return ++counter;
        } finally {
            lock.unlock(id);
        }
    }

    @Test
    public void modelCheckingTest() {
        ModelCheckingOptions options = new ModelCheckingOptions()
                .iterations(30)
                .invocationsPerIteration(100)
                .threads(NUM_THREADS)
                .actorsPerThread(3);
        LinChecker.check(BakeryLincheckTest.class, options);
    }
}
