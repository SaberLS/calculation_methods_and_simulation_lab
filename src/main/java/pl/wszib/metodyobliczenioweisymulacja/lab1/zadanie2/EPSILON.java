package pl.wszib.metodyobliczenioweisymulacja.lab1.zadanie2;

final class EPSILON {
    public static final float FLOAT;

    static {
        FLOAT = findMachineEpsilonFloat();
    };

    private static float findMachineEpsilonFloat() {
        float epsilon = 1.0f;

        while (Float.compare((1.0f + epsilon / 2.0f), 1.0f) > 0) {
            epsilon = epsilon / 2.0f;
        }

        return epsilon;
    }
}
