import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Class to handle loading and saving of data
 * 
 * You must write 4 methods
 * 
 * loadMineField - loads mine field definition
 * 
 * saveMineField - saves minefield
 * 
 * Here the only concern is loading/saving a mine field definition file where -1
 * denotes a mine, and any non-negative number is safe.
 * 
 * saveGame - saves the entire game state
 * 
 * loadGame - loads the game state
 * 
 * For a game we need both the mines and the exposed array data. Unfortunately
 * the given MineField class (which you cannot change) does not provide a way to
 * set that.
 * 
 * Take a look at documentation for MineFieldIO and see if you can think of a
 * way to use that shell class to help you.
 * 
 * 
 * @author <student>
 * @version 0
 * 
 */
public class MineFieldFileIO {

    /**
     * Saves a mine field definition to a file (You decide the format)
     * 
     * @param mf
     *            - reference to a mine field
     * @param fileName
     *            - string to path to minefield
     * @return true if successfully saved, false otherwise
     * 
     */
    public static boolean saveMineFile(MineField mf, String fileName) {
        // @todo - fix this
        // return false;
        try {
            File file = new File(fileName);
            PrintWriter fout = new PrintWriter(file);

            // write size
            fout.println(String.format("%d\t%d", mf.field.length,
                    mf.field[0].length));
            // write each row
            for (int[] row : mf.field) {
                // - write each column
                for (int col = 0; col < row.length; col++) {
                    fout.print(row[col] + "\t");
                }
                fout.println();
            }
            fout.close();
            return true;

        }
        catch (IOException e) {
            System.out.println(" IOException in " + fileName);
            return false;
        }
        catch (Exception e) {
            System.out.println(" Unknown exception : " + e.getMessage());
            return false;
        }

    }

    /**
     * Reads the minefield definition from a file format of your choosing.
     * 
     * A complete minefield with initialized hints and exposed array should be
     * returned.
     * 
     * Throws a MineFieldException if the file is opened, but the data is
     * invalid.
     * 
     * Valid games should have at least 1 mine, and at least one empty cell.
     * 
     * @param fileName
     *            - string to path to minefield
     * @return valid instance to minefield instance or null if file has
     *         IOException
     * @throw invalid MineFieldException if data in file is invalid for any
     *        reason other than IOException
     */
    public static MineField readMineFile(String fileName) {
        // @todo - fix this
        try {
            File file = new File(fileName);
            Scanner fin = new Scanner(file);

            if (!fin.hasNextLine()) {
                throw new MineFieldException("Empty file");
            }
            String line = fin.nextLine();
            Scanner scan = new Scanner(line);
            scan.useDelimiter("\t");
            int rows = scan.nextInt();
            int cols = scan.nextInt();

            System.out.println("  rows=" + rows + " cols=" + cols);
            if (rows < 0 || cols < 0) {
                throw new MineFieldException("Invalid dimensions");
            }
            int[][] array = new int[rows][cols];

            int row = 0;
            int column = 0;
            boolean hasEmpty = false;
            boolean hasMine = false;

            while (fin.hasNextLine()) {

                line = fin.nextLine();
                scan = new Scanner(line);
                scan.useDelimiter("\t");
                column = 0;
                while (scan.hasNext()) {
                    array[row][column] = scan.nextInt();
                    if (array[row][column] >= 0) {
                        hasEmpty = true;
                    }
                    else {
                        hasMine = true;
                    }
                    column++;
                }
                if (column != cols) {
                    System.out.println(" Invalid row =" + row + " columns="
                            + column + " != " + cols);
                }
                ++row;
            } // end of reading row loop
            fin.close();

            if (!hasEmpty || !hasMine) {
                throw new MineFieldException("Invalid mine field hasMine="
                        + hasMine + " hasEmpty=" + hasEmpty);

            }
            MineField mf = new MineField();
            mf.setMineField(array);
            return mf;

        }
        catch (MineFieldException e) {
            System.out.println(" MineFieldException " + e.getMessage());
            throw e;
        }
        catch (IOException e) {
            System.out.println(" IOException " + e.getMessage());
            return null;
        }
        catch (Exception e) {
            System.out.println(" Unknown exception " + e.getMessage());
            throw new MineFieldException(e.getMessage());
        }
    }

    /**
     * 
     * Saves the current game state to file including both minefield and current
     * exposed array information.
     * 
     * The format is of your choosing.
     * 
     * @param mineField
     *            - reference to a mine field
     * @param fileName
     *            - string to path to minefield
     * @return true if successfully saved, false otherwise
     * 
     */
    public static boolean saveMineSweeperGame(MineField mf, String fileName) {
        // @todo - fix this
        // return false;
        try {
            File file = new File(fileName);
            PrintWriter fout = new PrintWriter(file);

            // write size
            fout.println(String.format("%d\t%d", mf.field.length,
                    mf.field[0].length));
            // write each row
            for (int[] row : mf.field) {
                // - write each column
                for (int col = 0; col < row.length; col++) {
                    fout.print(row[col] + "\t");
                }
                fout.println();
            }

            // write each row
            for (boolean[] row : mf.exposed) {
                // - write each column
                for (int col = 0; col < row.length; col++) {
                    fout.print(row[col] + "\t");
                }
                fout.println();
            }
            fout.close();
            return true;

        }
        catch (IOException e) {
            System.out.println(" IOException in " + fileName);
            return false;
        }
        catch (Exception e) {
            System.out.println(" Unknown exception : " + e.getMessage());
            return false;
        }
    }

    /**
     * Reads the saved game state, both field and exposed status, from a file
     * format of your choosing.
     * 
     * A complete minefield with initialized hints and exposed array should be
     * returned.
     * 
     * Throws a MineFieldException if the file is read, but the data is invalid.
     * 
     * Valid games should have at least 1 mine, at least one empty cell, and at
     * least 1 unexposed cell that is either a 0 or hint.
     * 
     * @param fileName
     *            - string to path to minefield
     * @return valid instance to minefield instance or null if file has
     *         IOException
     * @throw invalid MineFieldException if data is invalid for any reason other
     *        than a file IOException
     */
    public static MineField readMineSweeperGame(String fileName) {
        // @todo - fix this
        // return null;
        try {
            File file = new File(fileName);
            Scanner fin = new Scanner(file);

            if (!fin.hasNextLine()) {
                throw new MineFieldException("Empty file");
            }
            String line = fin.nextLine();
            Scanner scan = new Scanner(line);
            scan.useDelimiter("\t");
            int rows = scan.nextInt();
            int cols = scan.nextInt();

            System.out.println("  rows=" + rows + " cols=" + cols);
            if (rows < 0 || cols < 0) {
                throw new MineFieldException("Invalid size");
            }
            int[][] field = new int[rows][cols];
            boolean[][] exposed = new boolean[rows][cols];

            int row = 0;
            int column = 0;
            while (fin.hasNextLine() && row < rows) {

                line = fin.nextLine();
                scan = new Scanner(line);
                scan.useDelimiter("\t");
                column = 0;
                while (scan.hasNext()) {
                    field[row][column] = scan.nextInt();
                    column++;
                }
                if (column != cols) {
                    System.out.println(" Invalid row =" + row + " columns="
                            + column + " != " + cols);
                }
                ++row;
            } // end of reading row loop
            row = 0;
            while (fin.hasNextLine() && row < rows) {

                line = fin.nextLine();
                scan = new Scanner(line);
                scan.useDelimiter("\t");
                column = 0;
                while (scan.hasNext()) {
                    exposed[row][column] = scan.nextBoolean();
                    column++;
                }
                if (column != cols) {
                    System.out.println(" Invalid row =" + row + " columns="
                            + column + " != " + cols);
                }
                ++row;
            } // end of reading row loop

            fin.close();

            MineField mf = new MineField(field, exposed);
            return mf;

        }
        catch (MineFieldException e) {
            System.out.println(" MineFieldException " + e.getMessage());
            throw e;
        }
        catch (IOException e) {
            System.out.println(" IOException " + e.getMessage());
            return null;
        }
        catch (Exception e) {
            System.out.println(" Unknown exception " + e.getMessage());
            throw new MineFieldException(e.getMessage());
        }
    }

}
