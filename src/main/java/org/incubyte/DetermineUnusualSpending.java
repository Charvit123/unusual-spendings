package org.incubyte;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DetermineUnusualSpending {

  private List<Payment> userPayments;
  private final LocalDate currentDate;

  public DetermineUnusualSpending(LocalDate currentDate) {
    this.currentDate = currentDate;
  }

  public List<HighSpending> compute(List<Payment> payments)
  {
    userPayments = payments;
    List<HighSpending> unusualSpendings = new ArrayList<>();

    Integer currentMonth = currentDate.getMonthValue();
    Integer previousMonth = currentMonth == 1 ? 12 : currentMonth - 1;

    for (Category category : Category.values())
    {
      Integer previousTotalExpenditures = getMonthlyExpenditures(category, previousMonth);
      Integer currentTotalExpenditures = getMonthlyExpenditures(category, currentMonth);

      if (currentTotalExpenditures > ((1.5) * previousTotalExpenditures))
      {
        unusualSpendings.add(new HighSpending(previousTotalExpenditures + currentTotalExpenditures,
            category));
      }
    }
    return unusualSpendings;
  }

  private Integer getMonthlyExpenditures(Category category, Integer month)
  {
    List<Payment> categoryPayments = getSpecificCategoryPayments(category);
    Integer totalExpenditure = 0;

    for (Payment payments : categoryPayments)
    {
      if(Objects.equals(payments.Date().getMonthValue(), month))
      {
        totalExpenditure += payments.Price();
      }
    }
    return totalExpenditure;
  }

  private List<Payment> getSpecificCategoryPayments(Category category)
  {
    List<Payment> categoryList = new ArrayList<>();

    for (Payment payments : userPayments)
    {
      if(category == payments.Category())
      {
        categoryList.add(payments);
      }
    }
    return categoryList;
  }
}
