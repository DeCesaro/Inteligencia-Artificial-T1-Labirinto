package pucrs.br.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class Utils {

    public static double rand100NumbersBetween0and1() {
        double min = 1;
        double max = 100;

        Random generator = new Random();

        double result = ((generator.nextInt(100)+1) - min) / (max - min);
        return round(result, 2);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException("Places invalid!");

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
