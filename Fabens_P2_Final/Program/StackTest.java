

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class StackTest.
 *
 * @author  Peter Fabens
 * @version 4/5/2022
 */
public class StackTest
{
    Stack<Integer> s;
    /**
     * Default constructor for test class StackTest
     */
    public StackTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        s = new Stack<Integer>();
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
        s = null;
    }
    
    @Test
    public void stack_test()
    {
        assertThrows(IndexOutOfBoundsException.class, () -> { s.pull(); });
        s.push(4);
        s.push(3);
        s.push(8);
        assertEquals(8, s.pull());
        assertEquals(3, s.pull());
        assertEquals(4, s.pull());
        assertThrows(IndexOutOfBoundsException.class, () -> { s.pull(); });
    }
}
