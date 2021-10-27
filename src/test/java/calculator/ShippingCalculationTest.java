package calculator;

import calculator.dto.CalculationError;
import calculator.dto.Dimension;
import calculator.dto.Workload;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static calculator.ShippingCalculator.calculateShippingCost;
import static org.assertj.core.api.Assertions.assertThat;

public class ShippingCalculationTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/combinations.csv", numLinesToSkip = 1, delimiterString = ";")
    public void shippingCalculationPositiveTest(String distance,
                                      String dimension,
                                      String isFragile,
                                      String workload,
                                      String resultCost) {

        var errorOrCost = calculateShippingCost(Double.parseDouble(distance),
                Dimension.valueOf(dimension),
                Boolean.parseBoolean(isFragile),
                Workload.valueOf(workload));


        assertThat(errorOrCost.get())
                .usingComparator(BigDecimal::compareTo)
                .isEqualTo(new BigDecimal(resultCost));
    }

    @ParameterizedTest
    @CsvSource({"2,770", "10,840", "30,980"})
    public void shippingCalculationCornerCasesTest(String distance, String resultCost) {

        var errorOrCost = calculateShippingCost(Double.parseDouble(distance),
                Dimension.BIG,
                true,
                Workload.HIGH);

        assertThat(errorOrCost.get())
                .usingComparator(BigDecimal::compareTo)
                .isEqualTo(new BigDecimal(resultCost));
    }

    @Test
    public void shippingCalculationForFragilityAndDistance() {

        var errorOrCost = calculateShippingCost(60,
                Dimension.BIG,
                true,
                Workload.HIGH);

        var expectedCalculationError = CalculationError.of("2", "Fragile goods cannot be delivered further than 30 km!");

        assertThat(errorOrCost.getLeft())
                .isEqualTo(expectedCalculationError);
    }

    @Test
    public void shippingCalculationForNegativeDistance() {

        var errorOrCost = calculateShippingCost(-10,
                Dimension.BIG,
                true,
                Workload.HIGH);

        var expectedCalculationError = CalculationError.of("1", "Invalid distance!");

        assertThat(errorOrCost.getLeft())
                .isEqualTo(expectedCalculationError);
    }
}
