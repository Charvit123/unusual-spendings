package org.incubyte;

import java.util.List;

public class Email implements IEmail{
  public final StringBuilder message= new StringBuilder();

  @Override
  public void sendEmail(List<HighSpending> highSpendings)
  {
    if(highSpendings.isEmpty())
    {
      message.append("* You have no unusual spendings ");
    }
    else
    {
      for (HighSpending highSpending : highSpendings) {
        message.append("* You have spent ").append(highSpending.Price()).append(" on ").append(highSpending.Category()).append("\n");
      }
    }
  }
}
