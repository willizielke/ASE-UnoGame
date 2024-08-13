package domain.service;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class AdditionTest {

    private Addition addition;

    @Before
    public void setUp() {
        addition = new Addition(3, 5);
    }

    @Test
    public void testGetLeft() {
        assertEquals(3, addition.getLeft());
    }

    @Test
    public void testGetRight() {
        assertEquals(5, addition.getRight());
    }

    @Test
    public void testSetResult() {
        addition.setResult(8);
        assertEquals(8, addition.getResult());
    }

    @Test
    public void testGetResultInitialValue() {
        assertEquals(0, addition.getResult());
    }
}