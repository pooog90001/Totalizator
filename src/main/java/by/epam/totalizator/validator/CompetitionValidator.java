package by.epam.totalizator.validator;

import by.epam.totalizator.entity.CompetitionEntity;

import java.math.BigDecimal;

public interface CompetitionValidator extends Validator {

    /**
     * Is name valid.
     *
     * @param name
     * @return
     */
    boolean isNameValid(String name);

    /**
     * Is coefficient valid.
     *
     * @param coeff
     * @return
     */
    boolean isCoeffValid(BigDecimal coeff);

    /**
     * Is number valid.
     *
     * @param number
     * @return
     */
    boolean isNumberValid(BigDecimal number);

    /**
     * Check string coefficient.
     *
     * @param stringCoeff
     * @return
     */
    BigDecimal checkStringCoeff(String stringCoeff);

    /**
     * Check string number.
     *
     * @param stringCoeff
     * @return
     */
    BigDecimal checkStringNumber(String stringCoeff);

    /**
     * Is valid for bet.
     *
     * @param competition
     * @return
     */
    boolean isValidForBet (CompetitionEntity competition);

}
