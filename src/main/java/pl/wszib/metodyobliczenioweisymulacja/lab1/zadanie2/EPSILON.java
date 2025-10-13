package pl.wszib.metodyobliczenioweisymulacja.lab1.zadanie2;

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
}
