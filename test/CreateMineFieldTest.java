import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * test for MineField
 *
 * @author dconner
 * @version 0
 */

/** Tester class */
public class CreateMineFieldTest {

    // 2 seconds max per test
    // @Rule
    // public Timeout globalTimeout = Timeout.seconds(2);

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

    private static final String SLASH = System.getProperty("file.separator");

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    /** reset the console output */
    @After
    public void redirectOutputToConsole() {
        // return output to its rightful owner
        System.setOut(console);
    }

    public int countMines(int[][] field) {
        int mines = 0;
        for (int[] row : field) {
            for (int cell : row) {
                if (cell < 0) {
                    ++mines;
                }
            }
        }
        return mines;
    }

    /** Just see if we can call the method with an empty array */
    @Test
    public void testCreateMineFieldNoArgs() {
        try {
            String[] args = {}; // empty
            MineField mf = MineSweeper.createMineField(args);
            MineField expected = new MineField(8, 8, 8);
            assertEquals("No args should have 8 rows", 8, mf.field.length);
            assertEquals("No args should have 8 cols", 8, mf.field[0].length);
            assertArrayEquals(" all unexposed ", mf.exposed, expected.exposed);

            int mines = countMines(mf.field);
            assertEquals("should create 8x8 w/ 8 mines", 8, mines);

        }
        catch (Exception e) {
            fail("Method createMineField threw exception with empty array:\n"
                    + e.getMessage());
        }

    }

    @Test
    public void testCreateMineFieldDefault() {
        try {
            String[] args = { "-d" }; // empty
            MineField mf = MineSweeper.createMineField(args);
            MineField expected = new MineField();
            assertEquals("No args should have 3 rows", 3, mf.field.length);
            assertEquals("No args should have 3 cols", 3, mf.field[0].length);
            assertEquals(" using no-arg constructor ", mf, expected);
        }
        catch (Exception e) {
            fail("Method createMineField threw exception with default -d option:\n"
                    + e.getMessage());
        }

    }

    @Test
    public void testCreateMineFieldLoadField() {

        // redirect output to a stream
        MineField expected = new MineField(10, 6, 12);
        String path = folder.getRoot().getPath();
        String filename = path + SLASH + "sampleMF.txt";
        MineFieldFileIO.saveMineFile(expected, filename);

        try {
            String[] args = { "-l", filename }; // empty
            MineField mf = MineSweeper.createMineField(args);
            assertEquals("Sample file has 10 rows", 10, mf.field.length);
            assertEquals("Sample file has 6 cols", 6, mf.field[0].length);
            assertEquals("Sample file has 12 mines", 12, countMines(mf.field));
            assertEquals(" must load from file ", mf, expected);
        }
        catch (Exception e) {
            fail("Method createMineField threw exception with valid -l option:\n"
                    + e.getMessage());
        }

    }

    @Test
    public void testCreateMineFieldLoadGame() {

        // redirect output to a stream
        MineField expected = new MineField(10, 6, 12);
        expected.exposed[0][3] = true;
        expected.exposed[4][0] = true;
        expected.exposed[5][2] = true;
        expected.exposed[8][5] = true;
        String path = folder.getRoot().getPath();
        String filename = path + SLASH + "sampleMF.txt";
        if (!MineFieldFileIO.saveMineSweeperGame(expected, filename)) {
            fail(" Failure to set up test - not your fault");
        }

        try {
            String[] args = { "-g", filename }; // empty
            MineField mf = MineSweeper.createMineField(args);
            assertEquals(" must load game from file ", mf, expected);
        }
        catch (Exception e) {
            fail("Method createMineField threw exception with valid -g option:\n"
                    + e.getMessage());
        }

    }

    @Test
    public void testCreateMineFieldHelp() {
        try {
            String[] args = { "-h" }; // empty
            MineField mf = MineSweeper.createMineField(args);
            assertNull(" -h should return null", mf);
        }
        catch (Exception e) {
            fail("Method createMineField threw exception with -h option (should return null):\n"
                    + e.getMessage());
        }

    }

    @Test
    public void testCreateMineFieldNoFileLoad() {
        try {
            String[] args = { "-l" }; // empty
            MineField mf = MineSweeper.createMineField(args);
            assertNull(" -l invalid if no filename given ", mf);
        }
        catch (Exception e) {
            fail("Method createMineField threw exception with -l option but not filename (should return null):\n"
                    + e.getMessage());
        }

    }

    @Test
    public void testCreateMineFieldNoGameLoad() {
        try {
            String[] args = { "-g" }; // empty
            MineField mf = MineSweeper.createMineField(args);
            assertNull(" -g invalid if no filename given ", mf);
        }
        catch (Exception e) {
            fail("Method createMineField threw exception with -g option but not filename (should return null):\n"
                    + e.getMessage());
        }

    }

    @Test
    public void testCreateMineFieldGameLoadBadFilename() {
        try {
            String[] args = { "-g squirrels.txt" }; // empty
            MineField mf = MineSweeper.createMineField(args);
            assertNull(" -g invalid if bad filename given ", mf);
        }
        catch (Exception e) {
            fail("Method createMineField threw exception with -g option but bad filename (should return null):\n"
                    + e.getMessage());
        }

    }

    @Test
    public void testCreateMineFieldLoadBadFilename() {
        try {
            String[] args = { "-l squirrels.txt" }; // empty
            MineField mf = MineSweeper.createMineField(args);
            assertNull(" -l invalid if bad filename given ", mf);
        }
        catch (Exception e) {
            fail("Method createMineField threw exception with -l option but bad filename (should return null):\n"
                    + e.getMessage());
        }

    }

    @Test
    public void testCreateMineFieldBadArg() {
        try {
            String[] args = { "squirrels" }; // empty
            MineField mf = MineSweeper.createMineField(args);
            assertNull(" invalid if bad argument given ", mf);
        }
        catch (Exception e) {
            fail("Method createMineField threw exception with invalid argument (should return null):\n"
                    + e.getMessage());
        }

    }

}
