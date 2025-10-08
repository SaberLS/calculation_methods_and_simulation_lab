package pl.wszib.metodyobliczenioweisymulacja.util.mathform;

import pl.wszib.metodyobliczenioweisymulacja.util.binaryrepresentation.DoubleBinaryRepresentation;
import pl.wszib.metodyobliczenioweisymulacja.util.binaryrepresentation.FloatBinaryRepresentation;

public class Main {
  public static void main(String[] args) {
    float smallestSubnormal = 1.4012984643e-45f;
    FloatBinaryRepresentation fr = new FloatBinaryRepresentation(smallestSubnormal);
    System.out.println(MathForm.of(fr));

    double smallestpositiveSubnormal = 4.9406564584124654e-324;
    DoubleBinaryRepresentation dr = new DoubleBinaryRepresentation(smallestpositiveSubnormal);
    System.out.println(MathForm.of(dr));
  }
}
