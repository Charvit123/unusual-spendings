package org.example;

import org.incubyte.DetermineUnusualSpending;
import org.incubyte.Email;
import org.incubyte.HighSpending;

import java.time.LocalDate;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    Console console = new Console();
    console.start();

    DetermineUnusualSpending determineUnusualSpending = new DetermineUnusualSpending(LocalDate.now());
    List<HighSpending> highSpendings = determineUnusualSpending.compute(console.getPayments());

    Email email = new Email();
    email.sendEmail(highSpendings);

    System.out.println(email.message);
  }
}