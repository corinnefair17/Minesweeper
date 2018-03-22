import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.PrintWriter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class MineFieldFileIOTestGame {
    // 2 seconds max per test
    // @Rule
    // public Timeout globalTimeout = Timeout.seconds(2);

    private static final String SLASH = System.getProperty("file.separator");
    private static final String INPUT = "world.txt";

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

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

    @Test
    public void testInvalidInputFile() {
        try {
            // create file
            String path = folder.getRoot().getPath();
            String fileNameWithPath = path + SLASH + INPUT;
            File file = folder.newFile(INPUT);
            PrintWriter fout = new PrintWriter(file);
            fout.println("This is an invalid world file");
            fout.close();

            MineField actual = MineFieldFileIO
                    .readMineSweeperGame(fileNameWithPath);
            assertNull(
                    "Should have returned null for invalid file (simple text)",
                    actual);
            fail("MineFieldException exception should be thrown for invalid data");
        }
        catch (MineFieldException e) {
            System.out.println(" Correctly threw exception for invalid data");
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("Only MineFieldException exception should be thrown");
        }
    }

    @Test
    public void testInvalidInputFileEmpty() {
        try {
            String path = folder.getRoot().getPath();
            String fileNameWithPath = path + SLASH + INPUT;
            File file = folder.newFile(INPUT);
            PrintWriter fout = new PrintWriter(file);
            fout.close();
            // invoke program
            MineField actual = MineFieldFileIO
                    .readMineSweeperGame(fileNameWithPath);
            fail("MineFieldException exception should be thrown for invalid data");
        }
        catch (MineFieldException e) {
            System.out.println(" Correctly threw exception for invalid data");
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("Only MineFieldException exception should be thrown");
        }
    }

    @Test
    public void testInvalidInputFile2() {
        try {
            // create file
            String path = folder.getRoot().getPath();
            String fileNameWithPath = path + SLASH + INPUT;
            File file = folder.newFile(INPUT);
            PrintWriter fout = new PrintWriter(file);
            fout.println("5\t6"); // this might be a valid file
            fout.println("valid?");
            fout.println("But this is definitely not valid world data");
            fout.close();

            MineField actual = MineFieldFileIO
                    .readMineSweeperGame(fileNameWithPath);
            fail("Should throw MineFieldException with invalid data");
        }
        catch (MineFieldException e) {
            System.out.println(
                    "Correctly threw a MineFieldException with invalid data");
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("No exception should be thrown\n  " + e.getMessage());
        }

    }

    @Test
    public void testSaveFileWithNullMF() {
        try {
            // create file
            String path = folder.getRoot().getPath();
            String fileNameWithPath = path + SLASH + INPUT;

            assertFalse("Return false from saveWorld if world array is null",
                    MineFieldFileIO.saveMineSweeperGame(null,
                            fileNameWithPath));

            MineFieldTester mf = new MineFieldTester();

            assertTrue("Should be able to save this world", MineFieldFileIO
                    .saveMineSweeperGame((MineField) mf, fileNameWithPath));

        }
        catch (Exception e) {
            e.printStackTrace();
            fail("No exception should be thrown");
        }
    }

    @Test
    public void testSaveValidMineField() {
        try {
            // create file
            String path = folder.getRoot().getPath();
            String fileNameWithPath = path + SLASH + INPUT;

            MineField mf = new MineField();
            assertTrue("Should be able to save this world",
                    MineFieldFileIO.saveMineSweeperGame(mf, fileNameWithPath));

        }
        catch (Exception e) {
            e.printStackTrace();
            fail("No exception should be thrown");
        }
    }

    @Test
    public void testValid4x6JustSave() {
        try {
            // create file
            String path = folder.getRoot().getPath();
            String fileNameWithPath = path + SLASH + INPUT;

            MineFieldTester mf = new MineFieldTester(4, 6, 4);
            assertTrue("Failed to write the file", MineFieldFileIO
                    .saveMineSweeperGame((MineField) mf, fileNameWithPath));

        }
        catch (Exception e) {
            e.printStackTrace();
            fail("No exception should be thrown");
        }
    }

    @Test
    public void testValid4x6() {
        try {
            // create file
            String path = folder.getRoot().getPath();
            String fileNameWithPath = path + SLASH + INPUT;

            MineFieldTester mfTest = new MineFieldTester(4, 6, 5);
            mfTest.getExposed()[3][4] = true;
            mfTest.getExposed()[2][1] = true;
            mfTest.getExposed()[0][2] = true;

            assertTrue("Failed to write the  4x6 field", MineFieldFileIO
                    .saveMineSweeperGame(mfTest, fileNameWithPath));

            MineField mfAct = MineFieldFileIO
                    .readMineSweeperGame(fileNameWithPath);

            assertEquals("Failed to load 4x6 world", mfTest, mfAct);
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("No exception should be thrown");
        }
    }

    @Test
    public void testValid6x4() {
        try {
            // create file
            String path = folder.getRoot().getPath();
            String fileNameWithPath = path + SLASH + INPUT;

            MineFieldTester mfTest = new MineFieldTester(6, 4, 5);
            mfTest.getExposed()[3][3] = true;
            mfTest.getExposed()[5][1] = true;
            mfTest.getExposed()[0][2] = true;

            assertTrue("Failed to write the 6x4 field", MineFieldFileIO
                    .saveMineSweeperGame(mfTest, fileNameWithPath));

            MineField mfAct = MineFieldFileIO
                    .readMineSweeperGame(fileNameWithPath);

            assertEquals("Failed to load default 6x4 world", mfTest, mfAct);
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("No exception should be thrown");
        }
    }

    @Test
    public void testValid4x4() {
        try {
            // create file
            String path = folder.getRoot().getPath();
            String fileNameWithPath = path + SLASH + INPUT;

            MineFieldTester mfTest = new MineFieldTester(4, 4, 3);
            mfTest.getExposed()[3][0] = true;
            mfTest.getExposed()[2][1] = true;
            mfTest.getExposed()[0][2] = true;

            assertTrue("Failed to write the 4x4 field", MineFieldFileIO
                    .saveMineSweeperGame(mfTest, fileNameWithPath));

            MineField mfAct = MineFieldFileIO
                    .readMineSweeperGame(fileNameWithPath);

            assertEquals("Failed to load 4x4 world", mfTest, mfAct);
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("No exception should be thrown");
        }
    }

    @Test
    public void testValid20x30() {
        try {
            // create file
            String path = folder.getRoot().getPath();
            String fileNameWithPath = path + SLASH + INPUT;

            MineField mfTest = new MineField(20, 30, 10);

            assertTrue("Failed to write the 20x30 field", MineFieldFileIO
                    .saveMineSweeperGame(mfTest, fileNameWithPath));

            MineField mfAct = MineFieldFileIO
                    .readMineSweeperGame(fileNameWithPath);

            assertEquals("Failed to load 20x30 world", mfTest, mfAct);
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("No exception should be thrown");
        }
    }

    @Test
    public void testValid30x10() {
        try {
            // create file
            String path = folder.getRoot().getPath();
            String fileNameWithPath = path + SLASH + INPUT;

            MineField mfTest = new MineField(30, 10, 10);

            assertTrue("Failed to write the 30x10 field", MineFieldFileIO
                    .saveMineSweeperGame(mfTest, fileNameWithPath));

            MineField mfAct = MineFieldFileIO
                    .readMineSweeperGame(fileNameWithPath);

            assertEquals("Failed to load 30x10 world", mfTest, mfAct);
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("No exception should be thrown");
        }
    }

}
