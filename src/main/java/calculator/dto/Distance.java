package calculator.dto;

import lombok.Data;

@Data
public class Distance {

    private Integer cost;

    public Distance(double distance) {

        if (distance <= 0)
            cost = -1;

        else if (distance <= 2)
            cost = 50;

        else if (distance <= 10)
            cost = 100;

        else if (distance <= 30)
            cost = 200;

        else if (distance > 30)
            cost = 300;

    }
}
