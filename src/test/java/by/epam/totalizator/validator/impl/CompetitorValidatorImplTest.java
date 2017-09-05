package by.epam.totalizator.validator.impl;

import by.epam.totalizator.entity.CompetitorEntity;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CompetitorValidatorImplTest {
    private static CompetitorValidatorImpl competitorValidator;

    @BeforeClass
    public static void setUp() throws Exception {
        competitorValidator = new CompetitorValidatorImpl();
    }

    @Test
    public void isPlacesValid1() {
        CompetitorEntity[] competitors = {
                new CompetitorEntity() {{
                    setResult(3);
                }},
                new CompetitorEntity() {{
                    setResult(1);
                }},
                new CompetitorEntity() {{
                    setResult(6);
                }},
                new CompetitorEntity() {{
                    setResult(2);
                }}
        };
        assertFalse(competitorValidator.isPlacesValid(competitors));
    }

    @Test
    public void isPlacesValid2() {
        CompetitorEntity[] competitors = {
                new CompetitorEntity() {{
                    setResult(3);
                }},
                new CompetitorEntity() {{
                    setResult(1);
                }},
                new CompetitorEntity() {{
                    setResult(4);
                }},
                new CompetitorEntity() {{
                    setResult(2);
                }}
        };
        assertTrue(competitorValidator.isPlacesValid(competitors));
    }

    @Test
    public void isPlacesValid3() {
        CompetitorEntity[] competitors = {
                null,
                new CompetitorEntity() {{
                    setResult(1);
                }},
                new CompetitorEntity() {{
                    setResult(6);
                }},
                new CompetitorEntity() {{
                    setResult(2);
                }}
        };
        assertFalse(competitorValidator.isPlacesValid(competitors));
    }

    @Test
    public void isPlacesValid4() {
        CompetitorEntity[] competitors = {
                new CompetitorEntity() {{
                    setResult(3);
                }},
                new CompetitorEntity() {{
                    setResult(2);
                }},
                new CompetitorEntity() {{
                    setResult(6);
                }},
                new CompetitorEntity() {{
                    setResult(2);
                }}
        };
        assertFalse(competitorValidator.isPlacesValid(competitors));
    }

    @Test
    public void isScoreValid1() {
        CompetitorEntity[] competitors = {
                null,
                new CompetitorEntity() {{
                    setResult(2);
                }},
                new CompetitorEntity() {{
                    setResult(6);
                }},
                new CompetitorEntity() {{
                    setResult(-2);
                }}
        };
        assertFalse(competitorValidator.isScoreValid(competitors));
    }

    @Test
    public void isScoreValid2() {
        CompetitorEntity[] competitors = {
                new CompetitorEntity() {{
                    setResult(3);
                }},
                new CompetitorEntity() {{
                    setResult(-2);
                }},
                new CompetitorEntity() {{
                    setResult(623);
                }},
                new CompetitorEntity() {{
                    setResult(2);
                }}
        };
        assertFalse(competitorValidator.isScoreValid(competitors));
    }

    @Test
    public void isScoreValid3() {
        CompetitorEntity[] competitors = {
                new CompetitorEntity() {{
                    setResult(3);
                }},
                new CompetitorEntity() {{
                    setResult(23);
                }},
                new CompetitorEntity() {{
                    setResult(2);
                }}
        };
        assertTrue(competitorValidator.isScoreValid(competitors));
    }

}