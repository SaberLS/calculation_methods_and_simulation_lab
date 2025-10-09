package pl.wszib.metodyobliczenioweisymulacja.lab1.zadanie1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

public class Zadanie1 {
  public static void main(String[] args) {
    int maxIter = 100;
    compareFloatDouble(maxIter);
  }

  public static void compareFloatDouble(int maxIter) {
    // Starting point
    try (PrintWriter csvWriter = new PrintWriter(new FileWriter("results_1.csv"))) {

      csvWriter.println("n,float,f_hex,double,d_hex");

      double xDouble = 0.01d;
      float xFloat = 0.01f;

      // Table header
      System.out.printf("%5s\t%25s\t%10s\t%25s\t%18s\t%n", "n", "float", "f_hex", "double", "d_hex");
      System.out.println(
          "------------------------------------------------------------------------------------------------------");

      for (int n = 0; n < maxIter; n++) {
        BigDecimal xDoubleBD = new BigDecimal(xDouble);
        BigDecimal xFloatBD = new BigDecimal(xFloat);

        // Get hex representations
        String xFloatHex = Float.toHexString(xFloat);
        String xDoubleHex = Double.toHexString(xDouble);

        // use BigDecimals to display the values which are actually stored in memory
        System.out.printf("%-5s\t%.20E\t%10s\t%.20E\t%10s%n", n, xFloatBD, xFloatHex, xDoubleBD, xDoubleHex);

        csvWriter.println(
            n + "," + xFloatBD + "," + xFloatHex + "," + xDoubleBD + "," + xDoubleHex);

        // Kolejne iteracje
        xDouble = countNext(xDouble);
        xFloat = countNext(xFloat);
      }
      System.out.println("\n Results saved to results.csv");
    } catch (IOException e) {
      System.err.println("Error writing to CSV file: " + e.getMessage());
    }
  }

  public static double countNext(double previous) {
    return previous + 3.0d * previous * (1d - previous);
  }

  public static float countNext(float previous) {
    return previous + 3.0f * previous * (1f - previous);
  }

}
