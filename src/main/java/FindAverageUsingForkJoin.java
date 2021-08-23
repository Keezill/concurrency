import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FindAverageUsingForkJoin extends RecursiveTask<BigInteger> {

    private final static int THRESHOLD = 500;
    private volatile List<BigInteger> ages;

    public FindAverageUsingForkJoin(List<BigInteger> ages) {
        this.ages = ages;
    }

    @Override
    protected BigInteger compute() {
        var result = BigInteger.ZERO;
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
        return result.divide(BigInteger.valueOf(ages.size()));
    }

    public BigInteger sequential(List<BigInteger> ages){
        var acc = BigInteger.ZERO;
        for (var value : ages) {
            acc = acc.add(value);
        }
        return acc;
    }
}
