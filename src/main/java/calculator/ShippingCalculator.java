package calculator;

import calculator.dto.CalculationError;
import calculator.dto.Dimension;
import calculator.dto.Distance;
import calculator.dto.Fragility;
import calculator.dto.Workload;
import io.vavr.control.Either;
import lombok.NonNull;

import java.math.BigDecimal;


public class ShippingCalculator {

    private static final BigDecimal MIN_COST = new BigDecimal(400);

    public static Either<CalculationError, BigDecimal> calculateShippingCost(@NonNull double distance,
                                                                             @NonNull Dimension dimension,
                                                                             @NonNull boolean isFragile,
                                                                             @NonNull Workload workload) {


        var distanceCost = new Distance(distance);
        var fragilityCost = new Fragility(isFragile);

        if (distanceCost.getCost() == -1)
            return Either.left(CalculationError.of("1", "Invalid distance!"));

        if (isFragile && distance > 30)
            return Either.left(CalculationError.of("2", "Fragile goods cannot be delivered further than 30 km!"));

        var cost = BigDecimal.valueOf(workload.getCoefficient()).multiply(
                new BigDecimal(distanceCost.getCost() + dimension.getCost() + fragilityCost.getCost()));

        var finalCost = cost.compareTo(MIN_COST) < 0 ? MIN_COST : cost;

        return Either.right(finalCost);

    }

}
