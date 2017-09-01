package by.epam.totalizator.validator.impl;

import by.epam.totalizator.bean.CompetitorEntity;
import by.epam.totalizator.validator.CompetitorValidator;

public class CompetitorValidatorImpl implements CompetitorValidator {
    @Override
    public boolean isPlacesValid(CompetitorEntity[] competitors) {
        boolean isValid = true;

        for (CompetitorEntity competitor1 : competitors) {
            for (CompetitorEntity competitor2 : competitors) {
                if (competitor1 != competitor2) {
                     if (competitor1.getResult() == competitor2.getResult() ||
                             competitor2.getResult() < 1 ||
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
            if (competitor.getResult() < 0 || competitor.getResult() > 9999) {
                isValid = false;
                break;
            }
        }

        return isValid;
    }


}
