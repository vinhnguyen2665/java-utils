package com.zero9vn.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberUtils {
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    /**
     *
     * @param input
     * @return
     */
    public static String formatDouble(Double input) {
        return NumberUtils.formatDouble(input, "#,###.##");
    }

    /**
     *
     * @param input
     * @param pattern
     * @return
     */
    public static String formatDouble(Double input, String pattern) {
        if (null == input) {
            return null;
        }
        DecimalFormat df = new DecimalFormat(pattern);
        BigDecimal inputBigDecimal = new BigDecimal(input.toString());
        String formatted = df.format(inputBigDecimal);
        return formatted;
    }
}
