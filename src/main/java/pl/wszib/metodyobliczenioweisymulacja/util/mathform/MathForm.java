package pl.wszib.metodyobliczenioweisymulacja.util.mathform;

public class MathForm {

  public static String of(FloatingPointRepresentation<?> fp) {
    int bias = fp.getBias();
    int expValue = fp.getExponentBits();
    long fractionBits = fp.getFractionBits();
    String signSymbol = fp.getSignBit() == 0 ? "+" : "-";

    // Determine whether normalized or subnormal
    boolean isSubnormal = (expValue == 0);
    int realExp = isSubnormal ? 1 - bias : expValue - bias;

    // Handle special cases: Infinity or NaN
    if (isSpecialCase(fp, expValue, fractionBits)) {
      if (isNaN(expValue, fractionBits, fp)) {
        return signSymbol + " NaN";
      } else {
        return signSymbol + " Infinity";
      }
    }

    // Build conceptual mantissa representation
    StringBuilder mantissaExpr = new StringBuilder();
    mantissaExpr.append(isSubnormal ? "0" : "1");

    // Represent fraction bits as powers of 2
    StringBuilder fractionExpr = new StringBuilder();
    boolean hasFraction = false;
    int precision = fp.getFractionSize();

    for (int i = 0; i < precision; i++) {
      if (((fractionBits >>> (precision - 1 - i)) & 1) == 1) {
        if (!hasFraction) {
          fractionExpr.append("2^-").append(i + 1);
          hasFraction = true;
        } else {
          fractionExpr.append(" + 2^-").append(i + 1);
        }
      }
    }

    if (fractionExpr.length() > 0) {
      mantissaExpr.append(" + ").append(fractionExpr);
    }

    return String.format("(%s1) * (%s) * 2^%d", signSymbol, mantissaExpr, realExp);
  }

  private static boolean isSpecialCase(FloatingPointRepresentation<?> fp, int expValue, long fractionBits) {
    int maxExp = (1 << fp.getExponentSize()) - 1;
    return expValue == maxExp;
  }

  private static boolean isNaN(int expValue, long fractionBits, FloatingPointRepresentation<?> fp) {
    int maxExp = (1 << fp.getExponentSize()) - 1;
    return expValue == maxExp && fractionBits != 0;
  }
}
