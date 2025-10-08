package pl.wszib.metodyobliczenioweisymulacja.lab1.zadanie1;

import java.math.BigDecimal;

public class Zadanie1 {
  public static void main(String[] args) {
    int maxIter = 100;
    compareFloatDouble(maxIter);
  }

  public static void compareFloatDouble(int maxIter) {
    double xDouble = 0.01d;
    float xFloat = 0.01f;

    // Table header
    System.out.printf("%5s%25s%25s%n", "n", "float", "double");
    System.out.println("---------------------------------------------------------------");

    for (int n = 0; n < maxIter; n++) {

      System.out.printf("%-5s\t%.20E\t%.20E%n", n, new BigDecimal(xFloat), new BigDecimal(xDouble));

      // Kolejne iteracje
      xDouble = countNext(xDouble);
      xFloat = countNext(xFloat);
    }
  }

  public static double countNext(double previous) {
    return previous + 3.0d * previous * (1d - previous);
  }

  public static float countNext(float previous) {
    return previous + 3.0f * previous * (1f - previous);
  }

}
