import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * test for MineField
 *
 * @author dconner
 * @version 0
 */

/** Tester class */
public class BasicMineSweeperTest {

    // 2 seconds max per test
    @Rule
    public Timeout globalTimeout = Timeout.seconds(2);

    /** redirect console output */
    private PrintStream console;

    /** buffer for output data */
    private ByteArrayOutputStream buffer;

    /** Set up the console re-direct */
    @Before
    public void redirectOutputToStream() {
        // redirect output to a stream
        console = System.out;
        buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
    }

    /** reset the console output */
    @After
    public void redirectOutputToConsole() {
        // return output to its rightful owner
        System.setOut(console);
    }

    /**
     * test default constructor - this test should pass with given code
     */
    @Test
    public void testConstructor() {
        MineSweeper ms = new MineSweeper();
        assertNotNull("created valid instance", ms);

    }

    /** Just see if we can call the method */
    @Test
    public void testBasicPrintUsage() {
        try {

            MineSweeper.printUsage();

            // Get the re-directed output
            final String actual = buffer.toString();
            // System.err.println(">" + actual + "<");
            assertFalse(
                    "printUsage must print something to the screen (necessary but not sufficient)",
                    actual.isEmpty());
        }
        catch (Exception e) {
            fail("Method printUsage threw exception:\n" + e.getMessage());
        }

    }

    /** Just see if we can call the method with an empty array */
    @Test
    public void testBasicCreateMineField() {
        try {
            MineSweeper.createMineField(new String[] {});
        }
        catch (Exception e) {
            fail("Method createMineField threw exception with empty array:\n"
                    + e.getMessage());
        }

    }

}
