import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class MineFieldFileIOTestField {

    private static final String SLASH = System.getProperty("file.separator");
    private static final String INPUT = "world.txt";

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

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

            MineField actual = MineFieldFileIO.readMineFile(fileNameWithPath);
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
            System.out.println(" Create file at " + fileNameWithPath);
            File file = folder.newFile(INPUT);
            PrintWriter fout = new PrintWriter(file);
            fout.close();
            // invoke program

            MineField actual = MineFieldFileIO.readMineFile(fileNameWithPath);
            fail("MineFieldException exception should be thrown for invalid data");
        }
        catch (MineFieldException e) {
            System.out.println(" Correctly threw exception for invalid data");
        }
        catch (IOException e) {
            e.printStackTrace();
            fail("Only MineFieldException exception should be thrown");
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

            MineField actual = MineFieldFileIO.readMineFile(fileNameWithPath);
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
    public void testSaveFileWithNullMF() {
        try {
            // create file
            String path = folder.getRoot().getPath();
            String fileNameWithPath = path + SLASH + INPUT;

            assertFalse("Return false from saveWorld if world array is null",
                    MineFieldFileIO.saveMineFile(null, fileNameWithPath));

            MineField mf = new MineField();
            assertTrue("Should be able to save this world",
                    MineFieldFileIO.saveMineFile(mf, fileNameWithPath));

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
                    MineFieldFileIO.saveMineFile(mf, fileNameWithPath));

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

            MineField mf = new MineField(4, 6, 4);
            assertTrue("Failed to write the file",
                    MineFieldFileIO.saveMineFile(mf, fileNameWithPath));

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

            MineField mfTest = new MineField(4, 6, 5);

            assertTrue("Failed to write the  4x6 field",
                    MineFieldFileIO.saveMineFile(mfTest, fileNameWithPath));

            MineField mfAct = MineFieldFileIO.readMineFile(fileNameWithPath);

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

            MineField mfTest = new MineField(6, 4, 5);

            assertTrue("Failed to write the 6x4 field",
                    MineFieldFileIO.saveMineFile(mfTest, fileNameWithPath));

            MineField mfAct = MineFieldFileIO.readMineFile(fileNameWithPath);

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

            MineField mfTest = new MineField(4, 4, 3);

            assertTrue("Failed to write the 4x4 field",
                    MineFieldFileIO.saveMineFile(mfTest, fileNameWithPath));

            MineField mfAct = MineFieldFileIO.readMineFile(fileNameWithPath);

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

            assertTrue("Failed to write the 20x30 field",
                    MineFieldFileIO.saveMineFile(mfTest, fileNameWithPath));

            MineField mfAct = MineFieldFileIO.readMineFile(fileNameWithPath);

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

            assertTrue("Failed to write the 30x10 field",
                    MineFieldFileIO.saveMineFile(mfTest, fileNameWithPath));

            MineField mfAct = MineFieldFileIO.readMineFile(fileNameWithPath);

            assertEquals("Failed to load 30x10 world", mfTest, mfAct);
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("No exception should be thrown");
        }
    }

    @Test
    public void testInvalidNoMines() {
        try {
            // create file
            String path = folder.getRoot().getPath();
            String fileNameWithPath = path + SLASH + INPUT;
            MineField mf = new MineField();
            mf.field[0][0] = 0;
            mf.field[0][1] = 0;
            mf.field[0][2] = 0;
            mf.field[1][0] = 0;
            mf.field[1][1] = 0;
            mf.field[1][2] = 0;
            mf.field[2][0] = 0;
            mf.field[2][1] = 0;
            mf.field[2][2] = 0;

            boolean saved = MineFieldFileIO.saveMineFile(mf, fileNameWithPath);
            if (!saved) {
                fail("Failed to save - even with no mines");
            }
            MineField actual = MineFieldFileIO.readMineFile(fileNameWithPath);
            fail("MineFieldException exception should be thrown for no mines");
        }
        catch (MineFieldException e) {
            System.out.println(" Correctly threw exception for no mines");
            System.out.println("     " + e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("Only MineFieldException exception should be thrown");
        }

    }

    @Test
    public void testInvalidNoEmpty() {
        try {
            // create file
            String path = folder.getRoot().getPath();
            String fileNameWithPath = path + SLASH + INPUT;
            MineField mf = new MineField();
            mf.field[0][0] = -1;
            mf.field[0][1] = -1;
            mf.field[0][2] = -1;
            mf.field[1][0] = -1;
            mf.field[1][1] = -1;
            mf.field[1][2] = -1;
            mf.field[2][0] = -1;
            mf.field[2][1] = -1;
            mf.field[2][2] = -1;

            boolean saved = MineFieldFileIO.saveMineFile(mf, fileNameWithPath);
            if (!saved) {
                fail("Failed to save - even with no free space");
            }
            MineField actual = MineFieldFileIO.readMineFile(fileNameWithPath);
            fail("MineFieldException exception should be thrown for no free space");
        }
        catch (MineFieldException e) {
            System.out.println(" Correctly threw exception for no free space");
            System.out.println("     " + e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("Only MineFieldException exception should be thrown");
        }

    }

}
