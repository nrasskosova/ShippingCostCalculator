package calculator.dto;

import lombok.Data;

@Data

public class Fragility {

    private Integer cost;

    public Fragility(boolean isFragile) {
        cost = isFragile ? cost = 300 : 0;
    }
}
