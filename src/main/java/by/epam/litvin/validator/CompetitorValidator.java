package by.epam.litvin.validator;

import by.epam.litvin.bean.CompetitorEntity;

public interface CompetitorValidator extends Validator {
    boolean isPlacesValid(CompetitorEntity[] competitors);
    boolean isScoreValid (CompetitorEntity[] competitors);
}
