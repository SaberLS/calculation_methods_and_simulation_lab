package pl.wszib.metodyobliczenioweisymulacja.lab1.zadanie1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

public class BigDecimalExample {
  public static BigDecimal countNext(BigDecimal previous) {
    // 3 * previous
    BigDecimal threeTimesPrev = BigDecimal.valueOf(3).multiply(previous);
    // 1 - previous
    BigDecimal oneMinusPrev = BigDecimal.ONE.subtract(previous);
    // 3 * previous * (1 - previous)
    BigDecimal delta = threeTimesPrev.multiply(oneMinusPrev);
    // Add previous: x_{n+1} = previous + delta
    return delta.add(previous);
  }

  public static void main(String[] args) {
    System.out.println(Runtime.getRuntime().maxMemory() / (1024 * 1024) + " MB");
    BigDecimal x = new BigDecimal("0.01");

    try (PrintWriter csvWriter = new PrintWriter(new FileWriter("results_1_bigdec.csv"))) {
      csvWriter.println("n,big_dec");

      int n = 0;

      do {
        System.out.println("n " + n);
        csvWriter.println(Integer.toString(n) + ',' + x.toString());

        x = countNext(x);
        n++;
      } while (n < 30);

    } catch (IOException e) {
      System.err.println("Error writing to CSV file: " + e.getMessage());
    }
  }
}
