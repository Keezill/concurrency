package controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FindAverageUsingForkJoin extends RecursiveTask<BigDecimal> {

    private final static int THRESHOLD = 100;
    private final List<BigInteger> ages;

    public FindAverageUsingForkJoin(List<BigInteger> ages) {
        this.ages = ages;
    }

    @Override
    protected BigDecimal compute() {
        var result = BigDecimal.ZERO;
        var size = ages.size();
        if (size < THRESHOLD) {
            return sequential(ages);
        } else {
            var x = new FindAverageUsingForkJoin(ages.subList(0, size / 2));
            var y = new FindAverageUsingForkJoin(ages.subList(size / 2, size));
            x.fork();
            y.fork();
            var xResult = x.join();
            var yResult = y.join();
            result = yResult.add(xResult);
        }
        return result.divide(BigDecimal.valueOf(2), 2, RoundingMode.CEILING);
    }

    public BigDecimal sequential(List<BigInteger> ages){
        var acc = BigDecimal.ZERO;
        for (var value : ages) {
            acc = acc.add(new BigDecimal(value));
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return acc.divide(BigDecimal.valueOf(ages.size()), 2, RoundingMode.CEILING);
    }
}
