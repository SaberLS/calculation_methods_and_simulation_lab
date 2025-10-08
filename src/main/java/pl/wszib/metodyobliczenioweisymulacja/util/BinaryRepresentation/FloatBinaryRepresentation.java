package pl.wszib.metodyobliczenioweisymulacja.util.binaryrepresentation;

public class FloatBinaryRepresentation extends BinaryRepresentation<Float> {

  public FloatBinaryRepresentation(float value) {
    super(value);
  }

  @Override
  protected String extractSign(Float value) {
    int bits = Float.floatToIntBits(value);
    return Integer.toBinaryString((bits >>> 31) & 0x1);
  }

  @Override
  protected String extractExponent(Float value) {
    int bits = Float.floatToIntBits(value);
    return String.format("%8s", Integer.toBinaryString((bits >>> 23) & 0xFF)).replace(' ', '0');
  }

  @Override
  protected String extractFraction(Float value) {
    int bits = Float.floatToIntBits(value);
    return String.format("%23s", Integer.toBinaryString(bits & 0x7FFFFF)).replace(' ', '0');
  }
}
