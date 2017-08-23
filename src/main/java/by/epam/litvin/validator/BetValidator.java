package by.epam.litvin.validator;

import java.math.BigDecimal;

public interface BetValidator extends Validator{
    boolean checkBetSize(BigDecimal betSize);
}
