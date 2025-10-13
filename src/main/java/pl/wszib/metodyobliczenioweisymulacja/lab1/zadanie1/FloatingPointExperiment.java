package pl.wszib.metodyobliczenioweisymulacja.lab1.zadanie1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import pl.wszib.metodyobliczenioweisymulacja.util.mathform.DoubleRepresentation;
import pl.wszib.metodyobliczenioweisymulacja.util.mathform.FloatRepresentation;
import pl.wszib.metodyobliczenioweisymulacja.util.mathform.FloatingPointRepresentation;
import pl.wszib.metodyobliczenioweisymulacja.util.mathform.MathForm;

public abstract class FloatingPointExperiment {
  protected int maxIter = 100;
  protected float startFloat = 0.01f;
  protected double startDouble = 0.01;
  protected String startBigDecimal = "0.01";

  public FloatingPointExperiment(int maxIter) {
    this.maxIter = maxIter;
  }

  public void runExperiment(String csvFileName) {
    try (PrintWriter csvWriter = new PrintWriter(new FileWriter(csvFileName))) {

      double xDouble = startDouble;
      float xFloat = startFloat;
      BigDecimal xBigDecimal = new BigDecimal(startBigDecimal);

      csvWriter.println("n,float,f_ulp,f_hex,double,d_ulp,d_hex,big_dec,f_abs_err,d_abs_err");

      for (int n = 0; n < maxIter; n++) {
        DoubleRepresentation _double = new DoubleRepresentation(xDouble);
        FloatRepresentation _float = new FloatRepresentation(xFloat);

        display(n, _double, "DOUBLE");
        display(n, _float, "FLOAT");
        System.out.println("BigDecimal: " + xBigDecimal);

        write(csvWriter, _float, _double, xBigDecimal, n);

        // Compute next iteration
        xDouble = getNext(xDouble);
        xFloat = getNext(xFloat);
        xBigDecimal = getNext(xBigDecimal);
      }

      System.out.println("\nResults saved to " + csvFileName);
    } catch (IOException e) {
      System.err.println("Error writing to CSV file: " + e.getMessage());
    }
  }

  protected void write(PrintWriter csvWriter, FloatRepresentation _float, DoubleRepresentation _double,
      BigDecimal xBigDecimal, int n) {
    BigDecimal errFloat = xBigDecimal.subtract(_float.getRealValue()).abs();
    BigDecimal errDouble = xBigDecimal.subtract(_double.getRealValue()).abs();

    csvWriter.println(n + "," +
        _float.getRealValue() + "," +
        _float.getUlp() + ',' +
        _float.getHex() + "," +
        _double.getRealValue() + "," +
        _double.getUlp() + ',' +
        _double.getHex() + ',' +
        xBigDecimal + "," +
        errFloat + "," +
        errDouble);
  }

  protected void display(int n, FloatingPointRepresentation<?> fp, String header) {
    String mathForm = MathForm.of(fp);
    System.out.println(n + " -------------------------------" + header + "-------------------------------");
    System.out.println(fp.toString());
    System.out.println("Math: " + mathForm);
  }

  // --- Abstract methods ---
  public abstract double getNext(double previous);

  public abstract float getNext(float previous);

  public abstract BigDecimal getNext(BigDecimal previous);

}
