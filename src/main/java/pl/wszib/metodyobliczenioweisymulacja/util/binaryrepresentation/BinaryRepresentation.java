package pl.wszib.metodyobliczenioweisymulacja.util.binaryrepresentation;

public abstract class BinaryRepresentation<T extends Number> {
  protected final T value;
  protected final String sign;
  protected final String exponent;
  protected final String fraction;

  protected BinaryRepresentation(T value) {
    this.value = value;
    this.sign = extractSign(value);
    this.exponent = extractExponent(value);
    this.fraction = extractFraction(value);
  }

  // --- Abstract methods (implemented by subclasses) ---
  protected abstract String extractSign(T value);

  protected abstract String extractExponent(T value);

  protected abstract String extractFraction(T value);

  // --- Getters ---
  public T getValue() {
    return value;
  }

  public String getSign() {
    return sign;
  }

  public String getExponent() {
    return exponent;
  }

  public String getFraction() {
    return fraction;
  }

  @Override
  public String toString() {
    return sign + exponent + fraction;
  }
}
