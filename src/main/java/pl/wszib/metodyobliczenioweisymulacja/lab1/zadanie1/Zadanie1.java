package pl.wszib.metodyobliczenioweisymulacja.lab1.zadanie1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import pl.wszib.metodyobliczenioweisymulacja.util.mathform.DoubleRepresentation;
import pl.wszib.metodyobliczenioweisymulacja.util.mathform.FloatRepresentation;
import pl.wszib.metodyobliczenioweisymulacja.util.mathform.FloatingPointRepresentation;
import pl.wszib.metodyobliczenioweisymulacja.util.mathform.MathForm;

public class Zadanie1 {
  public static void main(String[] args) {
    int maxIter = 100;
    double startDouble = 0.01d;
    float startFloat = 0.01f;

    compareFloatDouble(maxIter, startDouble, startFloat);
  }

  public static void compareFloatDouble(int maxIter, double startDouble, float startFloat) {
    try (PrintWriter csvWriter = new PrintWriter(new FileWriter("results_1.csv"))) {
      // Starting point
      double xDouble = startDouble;
      float xFloat = startFloat;

      csvWriter.println("n,float,f_ulp,f_hex,double,d_ulp,d_hex");

      for (int n = 0; n < maxIter; n++) {
        DoubleRepresentation _double = new DoubleRepresentation(xDouble);
        FloatRepresentation _float = new FloatRepresentation(xFloat);

        display(n, _double, "DOUBLE");
        display(n, _float, "FLOAT");

        csvWriter.println(n + "," +
            _float.getRealValue() + "," +
            _float.getUlp() + ',' +
            _float.getHex() + "," +
            _double.getRealValue() + "," +
            _double.getUlp() + ',' +
            _double.getHex());

        // Kolejne iteracje
        xDouble = countNext(xDouble);
        xFloat = countNext(xFloat);
      }
      System.out.println("\n Results saved to results.csv");
    } catch (IOException e) {
      System.err.println("Error writing to CSV file: " + e.getMessage());
    }
  }

  public static void display(int n, FloatingPointRepresentation<?> fp, String header) {
    String mathForm = MathForm.of(fp);

    System.out.println(n + " -------------------------------" + header + "-------------------------------");
    System.out.println(fp.toString());
    System.out.println("Math: " + mathForm);
  }

  public static double countNext(double previous) {
    return previous + 3.0d * previous * (1d - previous);
  }

  public static float countNext(float previous) {
    return previous + 3.0f * previous * (1f - previous);
  }

}
