package calculator.dto;

public enum Workload {

    SUPER_HIGH(1.6),
    HIGH(1.4),
    INCREASED(1.2),
    NORMAL(1);


    private final double coefficient;

    Workload(double coefficient) {
        this.coefficient = coefficient;
    }

    public double getCoefficient() {
        return coefficient;
    }
}
