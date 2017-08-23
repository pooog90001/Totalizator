package by.epam.litvin.validator;

import by.epam.litvin.bean.CompetitionEntity;

import java.math.BigDecimal;

public interface CompetitionValidator extends Validator {

    boolean isNameValid(String name);
    boolean isCoeffValid(BigDecimal coeff);
    boolean isNumberValid(BigDecimal number);
    BigDecimal checkStringCoeff(String stringCoeff);
    BigDecimal checkStringNumber(String stringCoeff);
    boolean isValidForBet (CompetitionEntity competition);

}
