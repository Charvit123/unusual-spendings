package org.incubyte;

import java.util.List;

public class TriggerUnusualSpendingsEmail {
  private final IPayments payments;
  private final IDetermineUnusualSpending determineUnusualSpending;
  private final IEmail email;

  public TriggerUnusualSpendingsEmail(IPayments payments, IDetermineUnusualSpending determineUnusualSpending, IEmail iEmail) {
    this.payments = payments;
    this.determineUnusualSpending = determineUnusualSpending;
    this.email = iEmail;
  }

  public void triggerEmail(String id) {
    List<Payment> userPayments = payments.getPayments(id);
    List<HighSpending> listOfHighSpendings = determineUnusualSpending.compute(userPayments);
    email.sendEmail(listOfHighSpendings);
  }
}
