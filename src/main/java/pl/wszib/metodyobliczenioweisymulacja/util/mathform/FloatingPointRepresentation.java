package pl.wszib.metodyobliczenioweisymulacja.util.mathform;

import java.math.BigDecimal;

/**
 * Abstract base class representing a general IEEE-754 floating-point number.
 *
 * @param <T> the numeric type (Float or Double)
 */
public abstract class FloatingPointRepresentation<T extends Number> {
  protected final T value;

  protected FloatingPointRepresentation(T value) {
    this.value = value;
  }

  public double getMantissa() {
    // Compute mantissa (1.fraction for normalized, fraction / 2^p for subnormals)
    if (getExponentBits() == 0) {
      return this.getFractionBits() / Math.pow(2, this.getFractionSize());
    }
    // else
    return 1.0 + this.getFractionBits() / Math.pow(2, this.getFractionSize());
  }

  // ---- Abstract methods to implement for each numeric type ----
  public abstract long getBits();

  public abstract int getExponentBits();

  public abstract int getExponentSize();

  public abstract int getFractionSize();

  public abstract int getSignBit();

  public abstract long getFractionBits();

  public abstract double getUlp();

  public abstract int getBias();

  // ---- Getters ----
  public T getValue() {
    return value;
  }

  public int getUnbiassedExponent() {
    return this.getExponentBits() - this.getBias();
  }

  public BigDecimal getRealValue() {
    return new BigDecimal(value.toString());
  }

  public abstract String getHex();

  public String formatExponentBits() {
    String format = "%" + this.getExponentSize() + "s";

    return String.format(format, Integer.toBinaryString(this.getExponentBits())).replace(' ', '0');
  }

  public String formatFractionBits() {
    String format = "%" + this.getFractionSize() + "s";

    return String.format(format, Long.toBinaryString(this.getFractionBits())).replace(' ', '0');
  }

  @Override
  public String toString() {
    return String.format(
        "Value: %s%nRealValue: %.20e%nSign: %d%nExponent bits: %s (unbiased: %d)%nFraction bits: %s%nMantissa: %.15f%nULP: %.20e",
        value,
        this.getRealValue(),
        this.getSignBit(),
        formatExponentBits(),
        this.getUnbiassedExponent(),
        formatFractionBits(),
        this.getMantissa(),
        this.getUlp());
  }
}
