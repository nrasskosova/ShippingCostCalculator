package calculator.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName = "of")
public final class CalculationError {
    private final String code;
    private final String message;
}
