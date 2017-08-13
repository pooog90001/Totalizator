package by.epam.litvin.validator;

import java.math.BigDecimal;

public interface CompetitionValidator extends Validator {

    boolean isNameValid(String name);
    boolean isCoeffValid(BigDecimal coeff);
    boolean isNumberValid(BigDecimal number);
    BigDecimal checkStringCoeff(String stringCoeff);
    BigDecimal checkStringNumber(String stringCoeff);

}
