package org.incubyte;

import java.util.List;

public interface IPayments {
  List<Payment> getPayments(String id);
}
