package pl.wszib.metodyobliczenioweisymulacja.util.mathform;

import pl.wszib.metodyobliczenioweisymulacja.util.binaryrepresentation.DoubleBinaryRepresentation;
import pl.wszib.metodyobliczenioweisymulacja.util.binaryrepresentation.FloatBinaryRepresentation;

public class MathForm {
  public static String of(DoubleBinaryRepresentation binaryRepresentation) {
    int bias = 1023;
    int expValue = Integer.parseInt(binaryRepresentation.getExponent(), 2);
    String signBit = binaryRepresentation.getSign();
    String fraction = binaryRepresentation.getFraction();

    String signSymbol = signBit.equals("0") ? "+" : "-";

    // Handle special cases
    if (expValue == 2047) {
      if (fraction.contains("1"))
        return signSymbol + " NaN";
      else
        return signSymbol + " Infinity";
    }

    // Determine whether normalized or subnormal
    boolean isSubnormal = (expValue == 0);
    int realExp = isSubnormal ? 1 - bias : expValue - bias;

    // Build conceptual mantissa representation
    StringBuilder mantissaExpr = new StringBuilder();

    if (isSubnormal) {
      // Subnormal no implicit leading 1
      // Value = (+1) * (0 + sum of fraction bits * 2^(-bit_index)) * 2^(1 - bias)
      mantissaExpr.append("0");
    } else {
      mantissaExpr.append("1");
    }

    // Find all bits that are 1 and represent them as powers of 2
    boolean hasFraction = false;
    StringBuilder fractionExpr = new StringBuilder();
    for (int i = 0; i < fraction.length(); i++) {
      if (fraction.charAt(i) == '1') {
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

  public static String of(FloatBinaryRepresentation binaryRepresentation) {
    int bias = 127; // bias for single-precision float
    int expValue = Integer.parseInt(binaryRepresentation.getExponent(), 2);
    String signBit = binaryRepresentation.getSign();
    String fraction = binaryRepresentation.getFraction();

    String signSymbol = signBit.equals("0") ? "+" : "-";

    // Handle special cases
    if (expValue == 255) {
      if (fraction.contains("1"))
        return signSymbol + " NaN";
      else
        return signSymbol + " Infinity";
    }

    // Determine whether normalized or subnormal
    boolean isSubnormal = (expValue == 0);
    int realExp = isSubnormal ? 1 - bias : expValue - bias;

    // Build conceptual mantissa representation
    StringBuilder mantissaExpr = new StringBuilder();

    if (isSubnormal) {
      // Subnormal no implicit leading 1
      mantissaExpr.append("0");
    } else {
      mantissaExpr.append("1");
    }

    // Find all bits that are 1 and represent them as powers of 2
    boolean hasFraction = false;
    StringBuilder fractionExpr = new StringBuilder();
    for (int i = 0; i < fraction.length(); i++) {
      if (fraction.charAt(i) == '1') {
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
}
