package by.epam.totalizator.validator.impl;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CommentValidatorImplTest {
    private static CommentValidatorImpl commentValidator;

    @BeforeClass
    public static void setUp() throws Exception {
        commentValidator = new CommentValidatorImpl();
    }

    @Test
    public void isCommentTextValid1() {
        String commentText = "Hello";
        assertTrue(commentValidator.isCommentTextValid(commentText));
    }

    @Test
    public void isCommentTextValid2() {
        String commentText = "   ";
        assertFalse(commentValidator.isCommentTextValid(commentText));
    }

    @Test
    public void isCommentTextValid3() {
        assertFalse(commentValidator.isCommentTextValid(null));
    }

    @Test
    public void isCommentTextValid4() {
        String commentText = "Hello dddddddddddddddddddddd " +
                "dddddddddddddddddddddddddddd " +
                "dddddddddddddddddddddddddddd " +
                "dddddddddddddddddddddddddddd " +
                "dddddddddddddddddddddddddddd " +
                "dddddddddddddddddddddddddddd " +
                "dddddddddddddddddddddddddddd " +
                "dddddddddddddddddddddddddddd " +
                "dddddddddddddddddddddddddddd " +
                "dddddddddddddddddddddddddddd " +
                "ddddddddddddddddddddddddddddddd ";
        assertFalse(commentValidator.isCommentTextValid(commentText));
    }


}