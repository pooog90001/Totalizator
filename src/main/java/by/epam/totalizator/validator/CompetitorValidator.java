package by.epam.totalizator.validator;

import by.epam.totalizator.entity.CompetitorEntity;

public interface CompetitorValidator extends Validator {
    /**
     * Is places valid.
     *
     * @param competitors
     * @return
     */
    boolean isPlacesValid(CompetitorEntity[] competitors);

    /**
     * Is score valid.
     *
     * @param competitors
     * @return
     */
    boolean isScoreValid (CompetitorEntity[] competitors);
}
