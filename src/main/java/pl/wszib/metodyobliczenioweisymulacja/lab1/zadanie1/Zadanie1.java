package pl.wszib.metodyobliczenioweisymulacja.lab1.zadanie1;

import java.math.BigDecimal;
import java.math.MathContext;

public class Zadanie1 extends FloatingPointExperiment {
  public Zadanie1(int maxIter) {
    super(maxIter);
  }

  @Override
  public double getNext(double previous) {
    return previous + 3.0d * previous * (1d - previous);
  }

  @Override
  public float getNext(float previous) {
    return previous + 3.0f * previous * (1f - previous);
  }

  @Override
  public BigDecimal getNext(BigDecimal x_n) {
    final MathContext MC = MathContext.DECIMAL128;
    // 1 - x_n
    BigDecimal t1 = BigDecimal.ONE.subtract(x_n, MC);
    // 3 * x_n
    BigDecimal t2 = BigDecimal.valueOf(3).multiply(x_n, MC);

    // 3 * x_n * (1 - x_n)
    BigDecimal t3 = t2.multiply(t1, MC);

    // x_n + 3 * x_n * (1 - x_n)
    return t3.add(x_n, MC);
  }

}
