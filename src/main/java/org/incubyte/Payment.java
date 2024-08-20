package org.incubyte;

import java.time.LocalDate;

public record Payment(Integer Price, Category Category, Integer Id, LocalDate Date) {
}
