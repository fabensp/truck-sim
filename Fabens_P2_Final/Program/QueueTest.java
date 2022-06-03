import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class QueueTest.
 *
 * @author  Peter Fabens
 * @version 4/5/2022
 */
public class QueueTest
{
    Queue<Integer> q;
    /**
     * Default constructor for test class QueueTest
     */
    public QueueTest()
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
        q = new Queue<Integer>();
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
        q = null;
    }
    
    /**
     * Test the stack methods
     */
    @Test
    public void queue_test()
    {
        assertThrows(IndexOutOfBoundsException.class, () -> { q.next(); });
        q.join(4);
        q.join(3);
        q.join(8);
        assertEquals(4, q.next());
        assertEquals(3, q.next());
        assertEquals(8, q.next());
        assertThrows(IndexOutOfBoundsException.class, () -> { q.next(); });
    }
}