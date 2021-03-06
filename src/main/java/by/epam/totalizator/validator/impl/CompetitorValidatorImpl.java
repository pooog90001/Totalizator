package by.epam.totalizator.validator.impl;

import by.epam.totalizator.entity.CompetitorEntity;
import by.epam.totalizator.validator.CompetitorValidator;

public class CompetitorValidatorImpl implements CompetitorValidator {
    private static final int MIN_SCORE = 0;
    private static final int MIN_PLACE = 1;
    private static final int MAX_SCORE = 9999;


    @Override
    public boolean isPlacesValid(CompetitorEntity[] competitors) {

        if (competitors == null) {
            return false;
        }

        boolean isValid = true;

        for (CompetitorEntity competitor1 : competitors) {
            for (CompetitorEntity competitor2 : competitors) {
                if (competitor1 != competitor2) {
                    if (competitor2 == null || competitor1 == null ||
                            competitor1.getResult() == competitor2.getResult() ||
                            competitor2.getResult() < MIN_PLACE ||
                             competitor2.getResult() > competitors.length) {
                         isValid = false;
                         break;
                     }
                }
            }
        }

        return isValid;
    }

    @Override
    public boolean isScoreValid(CompetitorEntity[] competitors) {
        boolean isValid = true;

        for (CompetitorEntity competitor: competitors) {
            if (competitor == null ||
                    competitor.getResult() < MIN_SCORE ||
                    competitor.getResult() > MAX_SCORE) {
                isValid = false;
                break;
            }
        }

        return isValid;
    }


}
