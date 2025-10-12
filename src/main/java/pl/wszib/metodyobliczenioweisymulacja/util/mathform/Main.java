package pl.wszib.metodyobliczenioweisymulacja.util.mathform;

public class Main {
  public static void main(String[] args) {
    // Example single precision with the smallest smallest positive subnormal float
    // https://en.wikipedia.org/wiki/Single-precision_floating-point_format#Notable_single-precision_cases
    // 0 00000000 000000000000000000000012 = 0000 000116 = 2^−126 × 2^−23 = 2^−149 ≈
    // 1.4012984643 × 10^−45
    float smallestSubnormal = 1.4012984643e-45f;
    FloatRepresentation fr = new FloatRepresentation(smallestSubnormal);
    System.out.println(MathForm.of(fr));

    // Example smallest positive subnormal double precision number
    // https://en.wikipedia.org/wiki/Double-precision_floating-point_format#Double-precision_examples
    // 0 00000000000 00000000000000000000000000000000000000000000000000012
    // ≙ 0000 0000 0000 000116
    // ≙ +2^−1022 × 2^−52
    // = 2^−1074
    double smallestpositiveSubnormal = 4.9406564584124654e-324;
    DoubleRepresentation dr = new DoubleRepresentation(smallestpositiveSubnormal);
    System.out.println(MathForm.of(dr));
  }
}
