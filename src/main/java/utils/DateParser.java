package utils;

import entity.Laureates;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

public class DateParser {
    private static final DateTimeFormatter format = new DateTimeFormatterBuilder()
            .appendPattern("yyyy")
            .parseDefaulting(ChronoField.MONTH_OF_YEAR, 12)
            .parseDefaulting(ChronoField.DAY_OF_MONTH, 10)
            .toFormatter();

    public static List<BigInteger> fromStringToDateParser(List<Laureates> laureates) {
        List<BigInteger> ages = new ArrayList<>();
        int diff;
        for (Laureates laureate : laureates) {
            if (laureate.getBorn() != null && laureate.getPrizes().get(0).getYear() != null) {
                if (!laureate.getBorn().equals("0000-00-00") &&
                        !laureate.getBorn().contains("-00-")) {
                    LocalDate d1 = LocalDate.parse(laureate.getPrizes().get(0).getYear(), format);
                    LocalDate d2 = LocalDate.parse(laureate.getBorn(), DateTimeFormatter.ISO_LOCAL_DATE);
                    Period period = Period.between(d1, d2);
                    diff = Math.abs(period.getYears());
                    ages.add(BigInteger.valueOf(diff));
                } else if (laureate.getBorn().contains("-00-00")) {
                    LocalDate d1 = LocalDate.parse(laureate.getPrizes().get(0).getYear(), format);
                    LocalDate d2 = LocalDate.parse(laureate.getBorn().substring(0, 4), format);
                    Period period = Period.between(d1, d2);
                    diff = Math.abs(period.getYears());
                    ages.add(BigInteger.valueOf(diff));
                }
            }
        }
        return ages;
    }
}
