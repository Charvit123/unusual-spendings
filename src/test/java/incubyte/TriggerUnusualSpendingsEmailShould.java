package incubyte;
import org.incubyte.HighSpending;
import org.incubyte.IDetermineUnusualSpending;
import org.incubyte.IEmail;
import org.incubyte.IPayments;
import org.incubyte.Payment;
import org.incubyte.TriggerUnusualSpendingsEmail;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class TriggerUnusualSpendingsEmailShould {
  @InjectMocks
  TriggerUnusualSpendingsEmail subject;

  @Test
  void send_the_mail_if_spending_is_unusual(){
    IPayments payments = mock(IPayments.class);
    IDetermineUnusualSpending determineUnusualSpending = mock(IDetermineUnusualSpending.class);
    IEmail iEmail = mock(IEmail.class);
    subject = new TriggerUnusualSpendingsEmail(payments, determineUnusualSpending, iEmail);

    subject.triggerEmail("1");

    verify(payments).getPayments("1");
    verify(determineUnusualSpending).compute(new ArrayList<Payment>());
    verify(iEmail).sendEmail(new ArrayList<>());
  }
}
