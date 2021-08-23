public class SimpleBenchmark {

    static void dummyBenchmark(Runnable runnable) {
        var before = System.currentTimeMillis();
        runnable.run();
        var after = System.currentTimeMillis();
        System.out.println("Executed in: " + (after - before));
        System.out.println("######\n");
    }
}
