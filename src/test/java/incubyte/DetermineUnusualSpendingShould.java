package incubyte;

import org.incubyte.Category;
import org.incubyte.DetermineUnusualSpending;
import org.incubyte.HighSpending;
import org.incubyte.Payment;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DetermineUnusualSpendingShould {
  DetermineUnusualSpending determineUnusualSpending;



  @Test
  void zero_unusual_payments_by_user()
  {
    LocalDate currentDate = LocalDate.of(2020, 6, 5);
    LocalDate previousDate = LocalDate.of(2020, 5, 4);
    List<Payment> paymentList = Arrays.asList(
        new Payment(150, Category.TRAVEL, 1, currentDate),
        new Payment(200, Category.TRAVEL, 1, previousDate)
    );
    determineUnusualSpending = new DetermineUnusualSpending(currentDate);

    List<HighSpending> expectedList = new ArrayList<>();
    List<HighSpending> actualList = determineUnusualSpending.compute(paymentList);

    assertEquals(expectedList, actualList);
  }

  @Test
  void one_unusual_spending_by_user()
  {
    LocalDate currentDate = LocalDate.of(2020, 6, 5);
    LocalDate previousDate = LocalDate.of(2020, 5, 4);
    List<Payment> paymentList = Arrays.asList(
        new Payment(100, Category.TRAVEL, 1, currentDate),
        new Payment(50, Category.TRAVEL, 1, previousDate)
    );
    determineUnusualSpending = new DetermineUnusualSpending(currentDate);

    List<HighSpending> expectedList = List.of(new HighSpending(150, Category.TRAVEL));
    List<HighSpending> actualList = determineUnusualSpending.compute(paymentList);

    assertEquals(expectedList.size(),actualList.size());
    assertEquals(1, actualList.size());
    assertEquals(Category.TRAVEL, actualList.get(0).Category());
  }

  @Test
  void more_than_one_unusual_spending_by_user()
  {
    LocalDate currentDate = LocalDate.of(2020, 6, 5);
    LocalDate previousDate = LocalDate.of(2020, 5, 4);
    List<Payment> paymentList = Arrays.asList(
        new Payment(100, Category.TRAVEL, 1, currentDate),
        new Payment(50, Category.TRAVEL, 1, previousDate),
        new Payment(100, Category.GROCERIES, 1, currentDate),
        new Payment(70, Category.GROCERIES, 1, previousDate),
        new Payment(50, Category.ENTERNAINMENT, 1, currentDate)
    );
    determineUnusualSpending = new DetermineUnusualSpending(currentDate);

    List<HighSpending> expectedList = Arrays.asList(
        new HighSpending(150, Category.TRAVEL),
        new HighSpending(170, Category.GROCERIES)
    );
    List<HighSpending> actualList = determineUnusualSpending.compute(paymentList);

    assertEquals(actualList.size(),expectedList.size());
    assertEquals(2, actualList.size());
    assertEquals(Category.TRAVEL, actualList.get(0).Category());
  }

  @Test
  void one_unusual_spending_by_user_in_different_year()
  {
    LocalDate currentDate = LocalDate.of(2020, 1, 5);
    LocalDate previousDate = LocalDate.of(2019, 12, 4);


    List<Payment> paymentList = Arrays.asList(
        new Payment(100, Category.TRAVEL, 1, currentDate),
        new Payment(50, Category.TRAVEL, 1, previousDate)
    );
    determineUnusualSpending = new DetermineUnusualSpending(currentDate);

    List<HighSpending> expectedList = List.of(new HighSpending(150, Category.TRAVEL));
    List<HighSpending> actualList = determineUnusualSpending.compute(paymentList);

    assertEquals(expectedList.size(), actualList.size());
    assertEquals(1, actualList.size());
  }
}
