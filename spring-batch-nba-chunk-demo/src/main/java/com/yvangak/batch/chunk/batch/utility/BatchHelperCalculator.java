package com.yvangak.batch.chunk.batch.utility;

import java.time.LocalDate;

public class BatchHelperCalculator {

    public static Integer computeYearsInActivity(Integer startYear, Integer endYear) {
        if (endYear == null) {
            return LocalDate.now().getYear() - startYear;
        } else {
            return endYear - startYear;
        }
    }

    public static Boolean isStillActive(Integer endYear) {
        if (endYear == null) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public static double computePercentage(Integer value1, Integer value2) {
        int total = value1 + value2;
        return (double) value1 * total / 100;
    }

    public static Integer computeTotal(Integer value1, Integer value2, Integer Value1) {
        return value1 + value2 + Value1;
    }
}
