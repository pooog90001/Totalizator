package by.epam.totalizator.validator.impl;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NewsValidatorImplTest {
    private static NewsValidatorImpl newsValidator;

    @BeforeClass
    public static void setUp() throws Exception {
        newsValidator = new NewsValidatorImpl();
    }

    @Test
    public void isTitleValid1() {
        String title = " ";
        assertFalse(newsValidator.isTitleValid(title));
    }

    @Test
    public void isTitleValid2() {
        String title = "sdfsdf";
        assertTrue(newsValidator.isTitleValid(title));
    }

    @Test
    public void isTitleValid3() {
        String title = "sdfsdfsdfsdfsd sdfdsfsdfsdfs " +
                "sdfsdfsdfsdfsd sdfsdfsdsfsdf sdfsdfsdfsd";
        assertFalse(newsValidator.isTitleValid(title));
    }

    @Test
    public void isTitleValid4() {
        assertFalse(newsValidator.isTitleValid(null));
    }

    @Test
    public void isTextValid1() {
        String title = "sdfsdfsdf";
        assertTrue(newsValidator.isTextValid(title));
    }

    @Test
    public void isTextValid3() {
        assertFalse(newsValidator.isTextValid(null));
    }

    @Test
    public void isTextValid4() {
        String title = "  ";
        assertFalse(newsValidator.isTextValid(title));
    }

}