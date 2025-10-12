package pl.wszib.metodyobliczenioweisymulacja.util.mathform;

interface DoubleFormat {
  int BIAS = 1023;
  int FRACTION = 52;
  int EXPONENT = 11;
}

public class DoubleRepresentation extends FloatingPointRepresentation<Double> implements DoubleFormat {
  private final long bits;

  public DoubleRepresentation(double value) {
    super(value);
    this.bits = Double.doubleToLongBits(value);
  }

  @Override
  public long getBits() {
    return this.bits;
  }

  @Override
  public int getBias() {
    return BIAS;
  }

  @Override
  public int getSignBit() {
    return (int) ((this.getBits() >>> 63) & 0x1);
  }

  @Override
  public int getExponentBits() {
    return (int) ((this.getBits() >>> 52) & 0x7FF);
  }

  @Override
  public int getExponentSize() {
    return EXPONENT;
  }

  @Override
  public long getFractionBits() {
    return this.getBits() & 0xFFFFFFFFFFFFFL;
  }

  @Override
  public int getFractionSize() {
    return FRACTION;
  }

  @Override
  public double getUlp() {
    return Math.ulp(this.value);
  }

  @Override
  public String getHex() {
    return Double.toHexString(this.value);
  }
}
