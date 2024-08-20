package incubyte;

import org.incubyte.Category;
import org.incubyte.Email;
import org.incubyte.HighSpending;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmailShould {
  Email subject;

  @Test
  void zeroUnusualSpendingByUser()
  {
    subject = new Email();

    String expectedEmail = "* You have no unusual spendings ";
    subject.sendEmail(new ArrayList<>());
    String actualEmail = String.valueOf(subject.message);

    assertEquals(expectedEmail, actualEmail);
  }

  @Test
  void oneUnusualSpendingByUser()
  {
    List<HighSpending> highSpendingsList = new ArrayList<>();
    highSpendingsList.add(new HighSpending(150, Category.TRAVEL));
    subject = new Email();

    String expectedEmail = "* You have spent 150 on TRAVEL"+"\n";
    subject.sendEmail(highSpendingsList);
    String actualEmail = String.valueOf(subject.message);

    assertEquals(expectedEmail, actualEmail);
  }

  @Test
  void moreThanOneUnusualSpendingByUser()
  {
    List<HighSpending> unsusalSpendingList = Arrays.asList(
        new HighSpending(150, Category.TRAVEL),
        new HighSpending(150, Category.GROCERIES)
    );
    subject = new Email();


    String expectedEmail = "* You have spent 150 on TRAVEL"+"\n"+ "* You have spent 150 on GROCERIES"+"\n";
    subject.sendEmail(unsusalSpendingList);
    String actualEmail = String.valueOf(subject.message);

    assertEquals(expectedEmail, actualEmail);
  }
}
