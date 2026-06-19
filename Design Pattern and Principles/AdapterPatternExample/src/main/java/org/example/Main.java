package org.example;

public class Main {

    public static void main(String[] args) {

        PaymentProcessor paypal =
                new PayPalAdapter(
                        new PayPalGateway());

        paypal.processPayment(1000);

        PaymentProcessor stripe =
                new StripeAdapter(
                        new StripeGateway());

        stripe.processPayment(2000);

        PaymentProcessor razorpay =
                new RazorpayAdapter(
                        new RazorpayGateway());

        razorpay.processPayment(3000);
    }
}