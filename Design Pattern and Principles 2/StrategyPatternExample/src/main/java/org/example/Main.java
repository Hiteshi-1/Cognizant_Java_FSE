package org.example;

public class Main {

    public static void main(String[] args) {

        // Credit Card Payment
        PaymentContext context =
                new PaymentContext(
                        new CreditCardPayment());

        context.executePayment(5000);

        // Change strategy at runtime
        context.setPaymentStrategy(
                new PayPalPayment());

        context.executePayment(3000);
    }
}
