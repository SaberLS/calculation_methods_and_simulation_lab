package pl.wszib.metodyobliczenioweisymulacja.util;

public class BinaryRepresentation {
  private String sign;
  private String exponent;
  private String mantissa;

  // --- Constructor for float (32-bit IEEE 754) ---
  public BinaryRepresentation(float value) {
    int bits = Float.floatToIntBits(value);
    String binary = String.format("%32s", Integer.toBinaryString(bits)).replace(' ', '0');

    this.sign = binary.substring(0, 1);
    this.exponent = binary.substring(1, 9);
    this.mantissa = binary.substring(9);
  }

  // --- Constructor for double (64-bit IEEE 754) ---
  public BinaryRepresentation(double value) {
    long bits = Double.doubleToLongBits(value);
    String binary = String.format("%64s", Long.toBinaryString(bits)).replace(' ', '0');

    this.sign = binary.substring(0, 1);
    this.exponent = binary.substring(1, 12);
    this.mantissa = binary.substring(12);
  }

  public String getSign() {
    return this.sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }

  public String getExponent() {
    return this.exponent;
  }

  public void setExponent(String exponent) {
    this.exponent = exponent;
  }

  public String getMantissa() {
    return this.mantissa;
  }

  public void setMantissa(String mantissa) {
    this.mantissa = mantissa;
  }

  @Override
  public String toString() {
    return sign + exponent + mantissa;
  }
}
