package pl.wszib.metodyobliczenioweisymulacja.util.mathform;

interface FloatFormat {
  int BIAS = 127;
  int FRACTION = 23;
  int EXPONENT = 8;
}

public class FloatRepresentation extends FloatingPointRepresentation<Float> implements FloatFormat {
  private final long bits;

  public FloatRepresentation(float value) {
    super(value);
    this.bits = Integer.toUnsignedLong(Float.floatToIntBits(value));
  }

  @Override
  public int getExponentSize() {
    return EXPONENT;
  }

  @Override
  public int getSignBit() {
    return (int) (this.bits >>> 31) & 0x1;
  }

  @Override
  public int getExponentBits() {
    return (int) (this.bits >>> 23) & 0xFF;
  }

  @Override
  public long getFractionBits() {
    return this.bits & 0x7FFFFF;
  }

  @Override
  public int getFractionSize() {
    return FRACTION;
  }

  @Override
  public int getBias() {
    return BIAS;
  }

  @Override
  public long getBits() {
    return this.bits;
  }

  @Override
  public double getUlp() {
    return Math.ulp(this.value);
  }

  @Override
  public String getHex() {
    return Float.toHexString(this.value);
  }
}
