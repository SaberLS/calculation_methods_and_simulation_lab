package pl.wszib.metodyobliczenioweisymulacja.util.binaryrepresentation;

public class Main {
  public static void main(String[] args) {
    float smallestSubnormal = 1.4012984643e-45f;
    FloatBinaryRepresentation fr = new FloatBinaryRepresentation(smallestSubnormal);

    // Example single precision with the smallest smallest positive subnormal float
    // https://en.wikipedia.org/wiki/Single-precision_floating-point_format#Notable_single-precision_cases
    System.out.println(fr);
    System.out.println(fr.getSign());
    System.out.println(fr.getExponent());
    System.out.println(fr.getFraction());

    // Example double precision with the smallest double greater than one
    // https://en.wikipedia.org/wiki/Double-precision_floating-point_format#Double-precision_examples
    double smallestGreaterThanOne = 1.0000000000000002220;
    DoubleBinaryRepresentation dr = new DoubleBinaryRepresentation(smallestGreaterThanOne);

    System.out.println(dr);
    System.out.println(dr.getSign());
    System.out.println(dr.getExponent());
    System.out.println(dr.getFraction());
  }
}
