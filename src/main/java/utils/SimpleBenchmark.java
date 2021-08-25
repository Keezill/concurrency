package utils;

public class SimpleBenchmark {

    public static void dummyBenchmark(Runnable runnable) {
        long before = System.currentTimeMillis();
        runnable.run();
        long after = System.currentTimeMillis();
        System.out.println("Executed in: " + (after - before));
        System.out.println("######\n");
    }
}
