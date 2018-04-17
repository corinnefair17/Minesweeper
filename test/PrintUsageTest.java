import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
public class PrintUsageTest {

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

    @Test
    public void testPrintUsageHasJava() {
        try {

            MineSweeper.printUsage();

            // Get the re-directed output
            final String actual = buffer.toString();
            // System.err.println(">" + actual + "<");
            assertFalse(
                    "printUsage must print something to the screen (necessary but not sufficient)",
                    actual.isEmpty());
            assertTrue("must say \"java\" somewhere", actual.contains("java"));
        }
        catch (Exception e) {
            fail("Method printUsage threw exception:\n" + e.getMessage());
        }

    }

    @Test
    public void testPrintUsageHasMineSweeper() {
        try {

            MineSweeper.printUsage();

            // Get the re-directed output
            final String actual = buffer.toString();
            // System.err.println(">" + actual + "<");
            assertFalse(
                    "printUsage must print something to the screen (necessary but not sufficient)",
                    actual.isEmpty());
            assertTrue("must say \"MineSweeper\" somewhere",
                    actual.contains("MineSweeper"));
        }
        catch (Exception e) {
            fail("Method printUsage threw exception:\n" + e.getMessage());
        }

    }

    @Test
    public void testPrintUsageHasJavaMineSweeper() {
        try {

            MineSweeper.printUsage();

            // Get the re-directed output
            final String actual = buffer.toString();
            // System.err.println(">" + actual + "<");
            assertFalse(
                    "printUsage must print something to the screen (necessary but not sufficient)",
                    actual.isEmpty());
            assertTrue("must say \"java MineSweeper\" somewhere",
                    actual.contains("java MineSweeper"));
        }
        catch (Exception e) {
            fail("Method printUsage threw exception:\n" + e.getMessage());
        }

    }

    @Test
    public void testPrintUsageHasHelp() {
        try {

            MineSweeper.printUsage();

            // Get the re-directed output
            final String actual = buffer.toString();
            // System.err.println(">" + actual + "<");
            assertFalse(
                    "printUsage must print something to the screen (necessary but not sufficient)",
                    actual.isEmpty());
            assertTrue("must say \"-h\" somewhere", actual.contains("-h"));
        }
        catch (Exception e) {
            fail("Method printUsage threw exception:\n" + e.getMessage());
        }

    }

    @Test
    public void testPrintUsageHasMines() {
        try {

            MineSweeper.printUsage();

            // Get the re-directed output
            final String actual = buffer.toString();
            // System.err.println(">" + actual + "<");
            assertFalse(
                    "printUsage must print something to the screen (necessary but not sufficient)",
                    actual.isEmpty());
            assertTrue("must say \"-m\" somewhere", actual.contains("-m"));
        }
        catch (Exception e) {
            fail("Method printUsage threw exception:\n" + e.getMessage());
        }

    }

    @Test
    public void testPrintUsageHasGameLoad() {
        try {

            MineSweeper.printUsage();

            // Get the re-directed output
            final String actual = buffer.toString();
            // System.err.println(">" + actual + "<");
            assertFalse(
                    "printUsage must print something to the screen (necessary but not sufficient)",
                    actual.isEmpty());
            assertTrue("must say \"-g\" somewhere", actual.contains("-g"));
        }
        catch (Exception e) {
            fail("Method printUsage threw exception:\n" + e.getMessage());
        }

    }

    @Test
    public void testPrintUsageHasFileLoad() {
        try {

            MineSweeper.printUsage();

            // Get the re-directed output
            final String actual = buffer.toString();
            // System.err.println(">" + actual + "<");
            assertFalse(
                    "printUsage must print something to the screen (necessary but not sufficient)",
                    actual.isEmpty());
            assertTrue("must say \"-l\" somewhere", actual.contains("-l"));
        }
        catch (Exception e) {
            fail("Method printUsage threw exception:\n" + e.getMessage());
        }

    }

    @Test
    public void testPrintUsageHasDefault() {
        try {

            MineSweeper.printUsage();

            // Get the re-directed output
            final String actual = buffer.toString();
            // System.err.println(">" + actual + "<");
            assertFalse(
                    "printUsage must print something to the screen (necessary but not sufficient)",
                    actual.isEmpty());
            assertTrue("must say \"-d\" somewhere", actual.contains("-d"));
        }
        catch (Exception e) {
            fail("Method printUsage threw exception:\n" + e.getMessage());
        }

    }

}
