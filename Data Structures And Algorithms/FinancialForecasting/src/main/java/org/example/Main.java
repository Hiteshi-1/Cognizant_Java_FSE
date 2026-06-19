package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        double presentValue = 10000;

        double growthRate = 0.10;

        int years = 5;

        double result =
                FinancialForecast.futureValue(
                        presentValue,
                        growthRate,
                        years);

        System.out.println(
                "Future Value after "
                        + years
                        + " years = ₹"
                        + result);
    }
}