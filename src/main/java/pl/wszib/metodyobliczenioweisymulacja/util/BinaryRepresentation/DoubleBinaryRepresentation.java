package pl.wszib.metodyobliczenioweisymulacja.util.binaryrepresentation;

public class DoubleBinaryRepresentation extends BinaryRepresentation<Double> {

  public DoubleBinaryRepresentation(double value) {
    super(value);
  }

  @Override
  protected String extractSign(Double value) {
    long bits = Double.doubleToLongBits(value);
    return Long.toBinaryString((bits >>> 63) & 0x1);
  }

  @Override
  protected String extractExponent(Double value) {
    long bits = Double.doubleToLongBits(value);
    return String.format("%11s", Long.toBinaryString((bits >>> 52) & 0x7FF)).replace(' ', '0');
  }

  @Override
  protected String extractFraction(Double value) {
    long bits = Double.doubleToLongBits(value);
    return String.format("%52s", Long.toBinaryString(bits & 0xFFFFFFFFFFFFFL)).replace(' ', '0');
  }
}
