package org.example;

public class Main {

    public static void main(String[] args) {

        System.out.println(
                "Email Notification:");

        Notifier emailNotifier =
                new EmailNotifier();

        emailNotifier.send("Hello User");

        System.out.println();

        System.out.println(
                "Email + SMS Notification:");

        Notifier smsNotifier =
                new SMSNotifierDecorator(
                        new EmailNotifier());

        smsNotifier.send("Hello User");

        System.out.println();

        System.out.println(
                "Email + SMS + Slack Notification:");

        Notifier fullNotifier =
                new SlackNotifierDecorator(
                        new SMSNotifierDecorator(
                                new EmailNotifier()));

        fullNotifier.send("Hello User");
    }

}
