package controller;

import entity.Laureates;
import utils.DateParser;
import utils.JSONReader;
import utils.SimpleBenchmark;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Controller {
    public static void run() throws InterruptedException {
        JSONReader jsonReader = new JSONReader();
        File file = jsonReader.fileReader("laureate.json");
        List<Laureates> laureates = jsonReader.JSONParser(file);
        List<BigInteger> ages = DateParser.fromStringToDateParser(laureates);
        FindAverageUsingForkJoin findAverageUsingForkJoin = new FindAverageUsingForkJoin(ages);


        Runnable parallel = () -> {
            ForkJoinPool commonPool = ForkJoinPool.commonPool();
            BigDecimal result = commonPool.invoke(new FindAverageUsingForkJoin(ages));

            System.out.println("Parallel Result is: " + result);
        };

        Runnable sequential = () -> {
            BigDecimal acc = findAverageUsingForkJoin.sequential(ages);

            System.out.println("Sequential Result is: " + acc);
        };

        parallel.run();
        sequential.run();

        Thread.sleep(2000);

        System.out.println("#### After some JIT \n\n");

        SimpleBenchmark.dummyBenchmark(sequential);
        SimpleBenchmark.dummyBenchmark(parallel);

        Thread.sleep(2000);

        System.out.println("#### After more JIT \n\n");

        SimpleBenchmark.dummyBenchmark(sequential);
        SimpleBenchmark.dummyBenchmark(parallel);
    }
}
