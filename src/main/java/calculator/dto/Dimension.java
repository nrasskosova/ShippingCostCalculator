package calculator.dto;

public enum Dimension {
    SMALL(100),
    BIG(200);

    private final Integer cost;

    Dimension(Integer cost) {
        this.cost = cost;
    }

    public Integer getCost() {
        return cost;
    }
}
