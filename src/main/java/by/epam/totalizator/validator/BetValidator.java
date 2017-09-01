package by.epam.totalizator.validator;

import java.math.BigDecimal;

public interface BetValidator extends Validator{
    /**
     * Check bet size.
     *
     * @param betSize
     * @return
     */
    boolean checkBetSize(BigDecimal betSize);
}
