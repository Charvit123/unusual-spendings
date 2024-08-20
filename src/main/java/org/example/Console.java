package org.example;

import org.incubyte.Category;
import org.incubyte.Payment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Console {
  private List<Payment> consolePayments;
  public Scanner scanner = new Scanner(System.in);
  Category selectedCategory;
  Integer expenditure;
  LocalDate localDate;

  public List<Payment> getPayments()
  {
    return consolePayments;
  }

  public void start()
  {
    List<Payment> payments = new ArrayList<>();
    int numberOfPayments = 1;
    for (int i = 0; i < numberOfPayments; i++)
    {
      getCategoryFromConsole();
      getExpenditureFromConsole();
      getDateFromConsole();
      System.out.println("Add another payment? Type Y or N");
      Scanner scannerForMorePayments = new Scanner(System.in);
      String answer = scannerForMorePayments.nextLine();
      switch (answer)
      {
        case "Y":
          numberOfPayments++;
          break;
        case "N":
          numberOfPayments = 0;
          break;
        default:
          System.out.println("Unknown answer");
          numberOfPayments = 0;
          scannerForMorePayments.close();
          break;
      }
      payments.add(new Payment(expenditure, selectedCategory, 1, localDate));
    }
    consolePayments = payments;
    for (Payment payment : consolePayments)
    {
      System.out.println("You have spent "+payment.Price()+ " on "+payment.Category());
    }
  }

  private void getCategoryFromConsole()
  {
    System.out.println("Select payment category");
    int i = 1;
    for (Category category : Category.values())
    {
      System.out.println(i + " "+category);
      i++;
    }

    int category = scanner.nextInt();
    switch (category)
    {
      case 1:
        selectedCategory = Category.TRAVEL;
        break;
      case 2:
        selectedCategory = Category.GROCERIES;
        break;
      case 3:
        selectedCategory = Category.ENTERNAINMENT;
        break;
      default:
        System.out.println("unknown category!");
        break;
    }
  }

  private void getExpenditureFromConsole()
  {
    System.out.println("Enter category expenditure");
    expenditure = scanner.nextInt();
  }

  private void getDateFromConsole()
  {
    System.out.println("Enter date in YY/MM/DD format");
    int year = scanner.nextInt();
    int month = scanner.nextInt();
    int day = scanner.nextInt();

    localDate = LocalDate.of(year, month, day);
  }
}
