package org.incubyte;

import java.util.List;

public interface IDetermineUnusualSpending {
  List<HighSpending> compute(List<Payment> payments);
}
