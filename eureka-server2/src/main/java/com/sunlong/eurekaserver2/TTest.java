package com.sunlong.eurekaserver2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : shipp
 * @description :
 * @data : 2019/3/15 11:29
 */
public class TTest {

    public static void main(String[] args) {
//        Random random = new Random();
//        List<Integer> tt = Stream.generate(() -> random.nextInt(10)).limit(10).distinct().collect(Collectors.toList());
//        tt.stream().filter(a -> a > 3).forEach(System.out::println);
//        tt.stream().map(p -> "aa" + p).forEach(System.out::println);

//        System.out.println(new BigDecimal((float) 1001 / 100).setScale(2, RoundingMode.FLOOR).toString());
//        System.out.println(new BigDecimal( 1001 / 100).setScale(2, RoundingMode.FLOOR).toString());
//        System.out.println(new BigDecimal(1234.51).setScale(1, RoundingMode.CEILING));
//        System.out.println(Math.pow(10,-2));

//        System.out.println(new BigDecimal(0.0001).setScale(1, RoundingMode.CEILING));
//        BigDecimal decimal = new BigDecimal(1234.5678);
//        getValue(decimal, 1);
//        getValue(decimal, 2);
//        getValue(decimal, 3);
//        getValue(decimal, 0);
//        getValue(decimal, -1);
//        getValue(decimal, -2);

//        String s = "123";
//        System.out.println(Integer.valueOf(123) == 123);

//        System.out.println(new Date());
        Double a = 1000d;
        Double b = 1111d;
        a = a / 100;
        b = b / 100;
        if (a % 1 == 0) {
            System.out.println((double)a.intValue());
        }
        System.out.println(b);
    }

    public static void getValue(BigDecimal d, int accuracy) {
        if (accuracy < 0 && accuracy <= -2) accuracy = -2;
        if (accuracy > 0 && accuracy >= 3) accuracy = 3;
        BigDecimal b;
        switch (accuracy) {
            case -2:
                b = new BigDecimal("0.01");
                break;
            case -1:
                b = new BigDecimal("0.1");
                break;
            default:
                b = new BigDecimal(Math.pow(10,accuracy));
        }
        BigDecimal dd = d.divide(b).setScale(0, RoundingMode.CEILING).multiply(b);

        System.out.println(dd);
    }
}
