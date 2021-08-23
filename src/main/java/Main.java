import java.io.*;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        /*Gson gson = new Gson();


        try (Reader reader = new FileReader(".\\src\\main\\resources\\laureate.json")) {

            List<Laureates> laureates = gson.fromJson(reader, new TypeToken<List<Laureates>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        }*/

        JSONReader jsonReader = new JSONReader();
        File file = jsonReader.fileReader();
        List<Laureates> laureates = jsonReader.JSONParser(file);
        List<BigInteger> ages = DateParser.fromStringToDateParser(laureates);
        FindAverageUsingForkJoin findAverageUsingForkJoin = new FindAverageUsingForkJoin(ages);


        Runnable parallel = () -> {
            var commonPool = ForkJoinPool.commonPool();
            var result = commonPool.invoke(new FindAverageUsingForkJoin(ages));

            System.out.println("Parallel Result is: " + result);
        };

        Runnable sequential = () -> {
            var acc = findAverageUsingForkJoin.sequential(ages);

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
