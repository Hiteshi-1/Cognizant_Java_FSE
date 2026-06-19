package org.example;

public class FinancialForecast {

    public static double futureValue(
            double currentValue,
            double growthRate,
            int years) {

        // Base Case
        if(years == 0) {
            return currentValue;
        }

        // Recursive Case
        return futureValue(
                currentValue,
                growthRate,
                years - 1)
                * (1 + growthRate);
    }
}
