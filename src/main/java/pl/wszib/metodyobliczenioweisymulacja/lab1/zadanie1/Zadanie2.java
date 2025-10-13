package pl.wszib.metodyobliczenioweisymulacja.lab1.zadanie1;

import java.math.BigDecimal;
import java.math.MathContext;

public class Zadanie2 extends FloatingPointExperiment {
  public Zadanie2(int maxIter) {
    super(maxIter);
  }

  @Override
  public double getNext(double previous) {
    return 4.0 * previous - (3 * (previous * previous));
  }

  @Override
  public float getNext(float previous) {
    return 4.0f * previous - (3f * (previous * previous));
  }

  @Override
  public BigDecimal getNext(BigDecimal x_n) {
    final MathContext MC = MathContext.DECIMAL128;
    // 4 * x_n
    BigDecimal t1 = BigDecimal.valueOf(4).multiply(x_n, MC);

    // x_n^2
    BigDecimal t2 = x_n.multiply(x_n, MC);

    // 3 * x_n^2
    BigDecimal t3 = BigDecimal.valueOf(3).multiply(t2, MC);

    // 4 * x_n - 3 * x_n^2
    return t1.subtract(t3, MC);
  }

}
