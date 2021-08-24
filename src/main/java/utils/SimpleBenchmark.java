package utils;

public class SimpleBenchmark {

    public static void dummyBenchmark(Runnable runnable) throws InterruptedException {
        var before = System.currentTimeMillis();
        runnable.run();
        var after = System.currentTimeMillis();
        System.out.println("Executed in: " + (after - before));
        System.out.println("######\n");
    }
}
