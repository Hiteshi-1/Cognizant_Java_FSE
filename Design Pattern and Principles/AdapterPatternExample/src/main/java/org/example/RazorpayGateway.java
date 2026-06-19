package org.example;

public class RazorpayGateway {
    public void sendPayment(double amount){
        System.out.println(
                "Payment of ₹" + amount +
                        " processed using Razorpay");
    }
}
