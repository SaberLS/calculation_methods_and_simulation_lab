package pl.wszib.metodyobliczenioweisymulacja.lab1.zadanie2;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;

final class EPSILON {
    public static final float FLOAT;
    public static final double DOUBLE;

    static {
        DOUBLE = findMachineEpsilonDouble();
        FLOAT = findMachineEpsilonFloat();
    };

    private static float findMachineEpsilonFloat() {
        float epsilon = 1.0f;

        while (Float.compare((1.0f + epsilon / 2.0f), 1.0f) > 0) {
            epsilon = epsilon / 2.0f;
        }

        return epsilon;
    }

    private static double findMachineEpsilonDouble() {
        double epsilon = 1.0;

        while (Double.compare(1.0 + epsilon / 2.0, 1.0) > 0) {
            epsilon = epsilon / 2.0;
        }

        return epsilon;
    }

    // find epsilon for given math context
    public static BigDecimal findMathContextEpsilon(MathContext mc) {
        BigDecimal epsilon = BigDecimal.ONE;
        BigDecimal one = BigDecimal.ONE;
        BigDecimal two = new BigDecimal(2);

        BigDecimal prev = epsilon;

        while (one.add(epsilon, mc).compareTo(one) > 0) {
            prev = epsilon;
            epsilon = epsilon.divide(two, mc);
        }

        return prev;
    }

    public static float findMachineEpsilonFloat(String fileName) {
        float epsilon = 1.0f;
        int iteration = 0;

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("iteration,epsilon,hex,1+epsilon/2\n");

            while (Float.compare(1.0f + epsilon / 2.0f, 1.0f) > 0) {
                float testVal = 1.0f + epsilon / 2.0f;
                writer.write(String.format(
                        "%d,%.10e,%s,%.10e%n",
                        iteration,
                        epsilon,
                        Float.toHexString(epsilon),
                        testVal));

                epsilon /= 2.0f;
                iteration++;
            }

            // Zapisz ostatni epsilon (ten już zbyt mały, by zmienić 1.0)
            writer.write(String.format(
                    "%d,%.10e,%s,%.10e%n",
                    iteration,
                    epsilon,
                    Float.toHexString(epsilon),
                    1.0f + epsilon / 2.0f));

        } catch (IOException e) {
            System.err.println("Błąd zapisu CSV (float): " + e.getMessage());
        }

        return epsilon * 2.0f; // poprzedni epsilon to właściwy
    }

    // --- Algorytm dla double ---
    public static double findMachineEpsilonDouble(String fileName) {
        double epsilon = 1.0;
        int iteration = 0;

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("iteration,epsilon,hex,1+epsilon/2\n");

            while (Double.compare(1.0 + epsilon / 2.0, 1.0) > 0) {
                double testVal = 1.0 + epsilon / 2.0;
                writer.write(String.format(
                        "%d,%.20e,%s,%.20e%n",
                        iteration,
                        epsilon,
                        Double.toHexString(epsilon),
                        testVal));

                epsilon /= 2.0;
                iteration++;
            }

            writer.write(String.format(
                    "%d,%.20e,%s,%.20e%n",
                    iteration,
                    epsilon,
                    Double.toHexString(epsilon),
                    1.0 + epsilon / 2.0));

        } catch (IOException e) {
            System.err.println("Błąd zapisu CSV (double): " + e.getMessage());
        }

        return epsilon * 2.0; // poprzedni epsilon to właściwy
    }
}
