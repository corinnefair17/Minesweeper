import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

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
public class MineFieldTest {

    /**
     * This local class uses inheritance to get access to instance variables for
     * test
     */
    class MineFieldTester extends MineField {
        MineFieldTester() {
            super();
        }

        MineFieldTester(int rows, int cols, int mines) {
            super(rows, cols, mines);
        }

        int[][] getMineField() {
            return field;
        }

        boolean[][] getExposed() {
            return exposed;
        }

        void printMineField() {
            System.out.print(" expectedMF = {");
            for (int i = 0; i < field.length; i++) {
                System.out.print("{");
                for (int j = 0; j < field[0].length; j++) {
                    System.out.print(field[i][j]);
                    if (j < field[0].length - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.print("}");
                if (i < field.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("};");
        }

        void printExposed() {
            System.out.print(" expectedExposed = {");
            for (int i = 0; i < exposed.length; i++) {
                System.out.print("{");
                for (int j = 0; j < exposed[0].length; j++) {
                    System.out.print(exposed[i][j]);
                    if (j < exposed[0].length - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.print("}");
                if (i < exposed.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("};");
        }
    }

    // 2 seconds max per test
    @Rule
    public Timeout globalTimeout = Timeout.seconds(2);

    /**
     * test default constructor - this test should pass with given code
     */
    @Test
    public void testConstructor() {
        MineFieldTester mf = new MineFieldTester();
        assertNotNull("created valid instance", mf);
        int[][] field = mf.getMineField();
        assertNotNull(field);
        assertEquals(3, field.length);
        assertEquals(3, field[0].length);

    }

    /**
     * test - this will require you to create the field array properly
     */
    @Test
    public void test3ArgConstructor() {
        MineFieldTester mf = new MineFieldTester(4, 5, 6);
        assertNotNull("created valid instance", mf);
        int[][] field = mf.getMineField();
        assertNotNull(field);
        assertEquals(4, field.length);
        assertEquals(5, field[0].length);
    }

    /**
     * test - this test requires the setHint method
     */
    @Test
    public void testDefaultConstructorWithHints() {
        MineFieldTester mf = new MineFieldTester();
        assertNotNull("created valid instance", mf);
        int[][] expectedMF = { { -1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 0 } };
        boolean[][] expectedExposed = { { false, false, false },
                { false, false, false }, { false, false, false } };
        int[][] field = mf.getMineField();
        assertNotNull(field);
        assertEquals(3, field.length);
        assertEquals(3, field[0].length);
        assertArrayEquals(" check minefield", expectedMF, field);
        assertArrayEquals(" check exposed", expectedExposed, mf.getExposed());
    }

    /**
     * test - we can create a field with no mines
     */
    @Test
    public void test3ArgConstructorWithNoMines() {
        MineFieldTester mf = new MineFieldTester(4, 4, 0);
        assertNotNull("created valid instance", mf);
        int[][] expectedMF = { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 },
                { 0, 0, 0, 0 } };
        boolean[][] expectedExposed = { { false, false, false, false },
                { false, false, false, false }, { false, false, false, false },
                { false, false, false, false } };
        assertArrayEquals(" check minefield", expectedMF, mf.getMineField());
        assertArrayEquals(" check exposed", expectedExposed, mf.getExposed());
    }

    /**
     * test requesting too many mines just fills all cells with mine
     * 
     */
    @Test
    public void test3ArgConstructorWithTooManyMines() {
        MineFieldTester mf = new MineFieldTester(4, 4, 20);
        assertNotNull("created valid instance", mf);
        int[][] expectedMF = { { -1, -1, -1, -1 }, { -1, -1, -1, -1 },
                { -1, -1, -1, -1 }, { -1, -1, -1, -1 } };
        boolean[][] expectedExposed = { { false, false, false, false },
                { false, false, false, false }, { false, false, false, false },
                { false, false, false, false } };
        assertArrayEquals(" check minefield", expectedMF, mf.getMineField());
        assertArrayEquals(" check exposed", expectedExposed, mf.getExposed());
    }

    /**
     * Tests that we can setMineField to new array
     * 
     */
    @Test
    public void testSetMineField4x4A() {
        MineFieldTester mf = new MineFieldTester();
        assertNotNull("created valid instance", mf);
        int[][] givenMF = { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 },
                { 0, 0, 0, 0 } };
        boolean[][] expectedExposed = { { false, false, false, false },
                { false, false, false, false }, { false, false, false, false },
                { false, false, false, false } };

        // Set the minefield, which should then initialize exposed and hints
        mf.setMineField(givenMF);
        assertNotSame("should deep copy", givenMF, mf.getMineField());
        assertEquals("updated field array size", 4, mf.getMineField().length);
        assertEquals("updated exposed array size", 4, mf.getExposed().length);
        assertArrayEquals(" check minefield", givenMF, mf.getMineField());
        assertArrayEquals(" check exposed", expectedExposed, mf.getExposed());
    }

    /**
     * Tests that we called setHint after setting new data
     * 
     */
    @Test
    public void testSetMineField3x3B() {
        MineFieldTester mf = new MineFieldTester();
        assertNotNull("created valid instance", mf);
        int[][] givenMF = { { 0, 0, 0 }, { 0, -1, 0 }, { -1, 0, 0 } };
        int[][] expectedMF = { { 1, 1, 1 }, { 2, -1, 1 }, { -1, 2, 1 } };
        boolean[][] expectedExposed = { { false, false, false },
                { false, false, false }, { false, false, false } };

        // Set the minefield, which should then initialize exposed and hints
        int[][] original = mf.getMineField();
        mf.setMineField(givenMF);
        assertNotSame("should deep copy", original, mf.getMineField());
        assertNotSame("should deep copy", givenMF, mf.getMineField());
        assertEquals("updated field array size", 3, mf.getMineField().length);
        assertEquals("updated exposed array size", 3, mf.getExposed().length);
        assertArrayEquals(" check minefield", expectedMF, mf.getMineField());
        assertArrayEquals(" check exposed", expectedExposed, mf.getExposed());
    }

    /** test creation of larger set */
    @Test
    public void testSetMineField4x5() {
        MineFieldTester mf = new MineFieldTester();
        int[][] givenMF = { { 0, 0, 0, 0, 0 }, { -1, -1, 0, 0, 0 },
                { 0, 0, 0, 0, -1 }, { -1, 0, -1, 0, 0 } };
        boolean[][] expectedExposed = { { false, false, false, false, false },
                { false, false, false, false, false },
                { false, false, false, false, false },
                { false, false, false, false, false } };

        mf.setMineField(givenMF);
        assertNotSame("should deep copy", givenMF, mf.getMineField());
        assertEquals("updated field array size", 4, mf.getMineField().length);
        assertEquals("updated exposed array size", 4, mf.getExposed().length);
        assertEquals("updated field array size", 5,
                mf.getMineField()[0].length);
        assertEquals("updated exposed array size", 5,
                mf.getExposed()[0].length);
        assertArrayEquals(" check exposed", expectedExposed, mf.getExposed());

    }

    /** test creation of larger set with hints set */
    @Test
    public void testSetMineField5x5() {
        MineFieldTester mf = new MineFieldTester();
        int[][] givenMF = { { 0, 0, 0, 0, 0 }, { -1, -1, 0, 0, 0 },
                { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, -1 }, { -1, 0, -1, 0, 0 } };
        int[][] expectedMF = { { 2, 2, 1, 0, 0 }, { -1, -1, 1, 0, 0 },
                { 2, 2, 1, 1, 1 }, { 1, 2, 1, 2, -1 }, { -1, 2, -1, 2, 1 } };
        boolean[][] expectedExposed = { { false, false, false, false, false },
                { false, false, false, false, false },
                { false, false, false, false, false },
                { false, false, false, false, false },
                { false, false, false, false, false } };

        mf.setMineField(givenMF);
        assertNotSame("should deep copy", givenMF, mf.getMineField());
        assertEquals("updated field array size", 5, mf.getMineField().length);
        assertEquals("updated exposed array size", 5, mf.getExposed().length);
        assertEquals("updated field array size", 5,
                mf.getMineField()[0].length);
        assertEquals("updated exposed array size", 5,
                mf.getExposed()[0].length);
        assertArrayEquals(" check minefield with hints", expectedMF,
                mf.getMineField());
        assertArrayEquals(" check exposed", expectedExposed, mf.getExposed());

    }

    @Test
    public void testCreateMineField7x8NoMines() {
        MineFieldTester mf = new MineFieldTester(7, 8, 0);

        assertEquals(7, mf.getMineField().length);
        assertEquals(8, mf.getMineField()[0].length);

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 8; j++) {
                assertEquals("no mines", 0, mf.getMineField()[i][j]);
                assertEquals("not exposed", false, mf.getExposed()[i][j]);
            }
        }

    }

    @Test
    public void testCreateMineField7x8TooManyMines() {
        MineFieldTester mf = new MineFieldTester(7, 8, 100);

        assertEquals(7, mf.getMineField().length);
        assertEquals(8, mf.getMineField()[0].length);

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 8; j++) {
                assertEquals("all mines", -1, mf.getMineField()[i][j]);
                assertEquals("not exposed", false, mf.getExposed()[i][j]);
            }
        }

    }

    @Test
    public void testCreateMineField7x8_9Mines() {
        MineFieldTester mf = new MineFieldTester(7, 8, 9);

        assertEquals(7, mf.getMineField().length);
        assertEquals(8, mf.getMineField()[0].length);

        // Count that the correct number of mines were placed
        int mines = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 8; j++) {
                if (mf.getMineField()[i][j] < 0) {
                    ++mines;
                }
                assertEquals("not exposed", false, mf.getExposed()[i][j]);
            }
        }
        assertEquals("correct number of mines", 9, mines);

    }

    @Test
    public void testCreateMineField5x6_24Mines() {
        MineFieldTester mf = new MineFieldTester(5, 6, 24);

        assertEquals(5, mf.getMineField().length);
        assertEquals(6, mf.getMineField()[0].length);

        // Count that the correct number of mines were placed
        int mines = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                if (mf.getMineField()[i][j] < 0) {
                    ++mines;
                }
                assertEquals("not exposed", false, mf.getExposed()[i][j]);
            }
        }
        assertEquals("correct number of mines", 24, mines);

    }

    @Test
    public void testToString3x3UnExposed() {
        MineFieldTester mf = new MineFieldTester();

        String actual = mf.toString();
        String expected = " *  *  * \n *  *  * \n *  *  * \n";
        assertEquals("all * for unexposed", expected, actual);

    }

    @Test
    public void testToString3x3AllExposed() {
        MineFieldTester mf = new MineFieldTester();
        String actual = mf.toString();
        String expected = " *  *  * \n *  *  * \n *  *  * \n";
        assertEquals("all * for unexposed", expected, actual);

        boolean[][] exposed = mf.getExposed();
        for (int i = 0; i < exposed.length; i++) {
            for (int j = 0; j < exposed[0].length; j++) {
                exposed[i][j] = true;
            }
        }
        // updated after exposed
        actual = mf.toString();

        String expectedExposed = " -1 1    \n 1  1    \n         \n";
        assertEquals("all exposed", expectedExposed, actual);

    }

    @Test
    public void testToString3x3Exposed() {
        MineFieldTester mf = new MineFieldTester();
        String actual = mf.toString();
        String expected = " *  *  * \n *  *  * \n *  *  * \n";
        assertEquals("all * for unexposed", expected, actual);

        boolean expose22 = mf.exposeCell(2, 2);
        actual = mf.toString();

        // updated after exposed
        String expectedExposed = " *  1    \n 1  1    \n         \n";
        assertEquals(" (2,2) exposed", expectedExposed, actual);

    }

    @Test
    public void test5x5Exposed() {
        MineFieldTester mf = new MineFieldTester();
        int[][] givenMF = { { 0, 0, 0, 0, 0 }, { -1, -1, 0, 0, 0 },
                { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, -1 }, { -1, 0, -1, 0, 0 } };
        int[][] expectedMF = { { 2, 2, 1, 0, 0 }, { -1, -1, 1, 0, 0 },
                { 2, 2, 1, 1, 1 }, { 1, 2, 1, 2, -1 }, { -1, 2, -1, 2, 1 } };
        boolean[][] expectedExposed = { { false, false, false, false, false },
                { false, false, false, false, false },
                { false, false, false, false, false },
                { false, false, false, false, false },
                { false, false, false, false, false } };

        mf.setMineField(givenMF);
        assertArrayEquals(" check minefield", expectedMF, mf.getMineField());
        assertArrayEquals(" check exposed", expectedExposed, mf.getExposed());

        boolean expose22 = mf.exposeCell(2, 2);
        boolean[][] expectedExposed22 = {
                { false, false, false, false, false },
                { false, false, false, false, false },
                { false, false, true, false, false },
                { false, false, false, false, false },
                { false, false, false, false, false } };
        assertTrue(expose22);
        assertArrayEquals(" (2,2) exposed", expectedExposed22,
                mf.getExposed());

        boolean expose04 = mf.exposeCell(0, 4);
        boolean[][] expectedExposed04 = { { false, false, true, true, true },
                { false, false, true, true, true },
                { false, false, true, true, true },
                { false, false, false, false, false },
                { false, false, false, false, false } };
        assertTrue(expose04);
        assertArrayEquals(" (0,4) exposed", expectedExposed04,
                mf.getExposed());
    }

    @Test
    public void test5x5ExposeMine() {
        MineFieldTester mf = new MineFieldTester();
        int[][] givenMF = { { 0, 0, 0, 0, 0 }, { -1, -1, 0, 0, 0 },
                { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, -1 }, { -1, 0, -1, 0, 0 } };
        int[][] expectedMF = { { 2, 2, 1, 0, 0 }, { -1, -1, 1, 0, 0 },
                { 2, 2, 1, 1, 1 }, { 1, 2, 1, 2, -1 }, { -1, 2, -1, 2, 1 } };
        boolean[][] expectedExposed = { { false, false, false, false, false },
                { false, false, false, false, false },
                { false, false, false, false, false },
                { false, false, false, false, false },
                { false, false, false, false, false } };

        mf.setMineField(givenMF);
        assertArrayEquals(" check minefield", expectedMF, mf.getMineField());
        assertArrayEquals(" check exposed", expectedExposed, mf.getExposed());

        boolean expose11 = mf.exposeCell(1, 1);
        boolean[][] expectedExposed11 = {
                { false, false, false, false, false },
                { false, true, false, false, false },
                { false, false, false, false, false },
                { false, false, false, false, false },
                { false, false, false, false, false } };
        assertFalse(expose11);
        assertArrayEquals(" (1,1) exposed", expectedExposed11,
                mf.getExposed());
    }

}