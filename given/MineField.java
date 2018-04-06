import java.awt.Point;
import java.util.Arrays;
import java.util.Random;

/**
 * Class to define MineField for eventual MineSweeper game
 *
 * @author <who are you>
 * @version 0
 */
public class MineField {

    /**
     * array of mine field data
     * 
     * mines are -1
     * 
     * other values represent the number (eight connected) of neighboring mines
     */
    protected int[][] field;

    /**
     * Has this cell been exposed
     */
    protected boolean[][] exposed;

    /** Private instance of random number generator */
    private static Random rand = new Random();

    /**
     * Helper method to create random point within array bounds
     * 
     * @return random location within array
     */
    public Point getRandomCell() {
        if (field == null) {
            return null;
        }

        Point cell = new Point();

        // nextInt returns number from [0, length)
        // that is, not including length
        cell.x = rand.nextInt(field.length);
        cell.y = rand.nextInt(field[0].length);
        return cell;
    }

    /**
     * Default No argument constructor Creates a defined 3x3 field with single
     * mine in upper left corner
     */
    public MineField() {
        field = new int[3][3]; // defaults to 0
        field[0][0] = -1;
        field[0][1] = 1; // hints
        field[1][0] = 1; // hints
        field[1][1] = 1; // hints
        exposed = new boolean[3][3]; // defaults to false

    }

    /**
     * 3 argument constructor Creates mine field with defined size and number of
     * mines
     * 
     * @param rows
     *            number of rows in the mine field
     * @param cols
     *            number of columns in the mine field
     * @param mines
     *            number of mines at random positions in the mine field
     */
    public MineField(int rows, int cols, int mines) {
        // @TODO - fix this
        // Hint: Use getRandomCell to return a cell location
        // within bounds of mineField array

        field = new int[rows][cols];
        exposed = new boolean[rows][cols];

        int set = -1;
        if (mines > 0.5 * rows * cols) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    field[i][j] = set;
                }
            }
            mines = rows * cols - mines; // invert
            set = 0;

        }

        int cnt = 0;
        while (cnt < mines) {
            Point cell = getRandomCell();
            if (field[cell.x][cell.y] == set) {
                continue;
            }

            field[cell.x][cell.y] = set;
            cnt++;

        }

        setHint();
    }

    /**
     * create a mine field from the given array Define mine field with hints,
     * and properly initialize the exposed array
     * 
     * @param array
     *            - array of mines only (-1 or 0)
     */
    public void setMineField(int[][] array) {
        // @TODO - fix this
        if (array == null) {
            return;
        }

        // Deep copy of array for mine field
        field = new int[array.length][array[0].length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                field[i][j] = array[i][j];
            }
        }
        exposed = new boolean[array.length][array[0].length];

        setHint();
    }

    /**
     * Construct from two arrays used for resetting game
     * 
     * @param newField
     *            - given mine field
     * @param newExposed
     *            - given exposed array
     */
    public MineField(int[][] newField, boolean[][] newExposed) {
        this.setMineField(newField);
        if (exposed.length != newExposed.length) {
            throw new MineFieldException(" newExposed is the wrong length");
        }
        if (exposed[0].length != newExposed[0].length) {
            throw new MineFieldException(" newExposed is the wrong width");
        }

        for (int i = 0; i < exposed.length; i++) {
            for (int j = 0; j < exposed[i].length; j++) {
                exposed[i][j] = newExposed[i][j];
            }
        }

    }

    /**
     * check for valid index into array
     * 
     * @param row
     * @param col
     * @return true if valid array index; false otherwise
     */
    public boolean isValidCell(Point cell) {

        if (cell.x < 0 || cell.x >= field.length) {
            return false;
        }

        if (cell.y < 0 || cell.y >= field[cell.x].length) {
            return false;
        }

        // Cell coordinates are valid array index
        return true;

    }

    /**
     * Given a minefield (-1 for mines) set all non negative cells to count the
     * number of adjacent cells that contain mines (8-connected including
     * diagonal corners)
     */
    public void setHint() {
        // @TODO - fix this
        if (field == null) {
            return;
        }

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (field[i][j] >= 0) {
                    // Need to calculate hint value for non mines
                    int hint = 0;
                    for (int ii = -1; ii < 2; ii++) {
                        for (int jj = -1; jj < 2; jj++) {
                            if (jj == 0 && ii == 0) {
                                continue; // same cell
                            }
                            Point cell = new Point(i + ii, j + jj);
                            if (isValidCell(cell))
                                // valid cell
                                if (field[cell.x][cell.y] < 0) {
                                ++hint;
                                }
                        } // end jj loop
                    } // end ii loop
                    field[i][j] = hint;
                } // if not mine
            } // end of j loop
        } // end of i loop
    } // end of setHint method

    /**
     * Expose cell exposes specified cell if a mine, returns false (i.e. we
     * dead)
     * 
     * if a hint, returns true
     * 
     * if zero, then exposes all neighbors until hints are exposed
     * 
     * @param row
     *            - cell row
     * @param col
     *            - cell column
     * @return false if we hit a mine, true otherwise
     * @throws InvalidArrayIndexException
     *             if row,col not valid
     */
    public boolean exposeCell(int row, int col) {
        // @TODO - fix this
        if (field == null) {
            return false;
        }

        exposed[row][col] = true;
        if (field[row][col] < 0) {
            return false;
        }

        if (field[row][col] > 0) {
            return true;
        }

        // Must be zero
        for (int ii = -1; ii < 2; ii++) {
            for (int jj = -1; jj < 2; jj++) {
                if (jj == 0 && ii == 0) {
                    continue; // same cell
                }
                Point cell = new Point(row + ii, col + jj);
                if (isValidCell(cell)) {
                    // valid cell
                    if (!exposed[cell.x][cell.y]) {
                        exposeCell(cell.x, cell.y);
                    }

                }
            } // end jj loop
        } // end ii loop

        return true;
    }

    /**
     * toString with each row in separate line, newline between Use 3 characters
     * per cell
     *
     * ' * ' if not exposed
     *
     * ' -1' if mine
     *
     * ' # ' where # is hint - the number of adjcent mines
     *
     * @return single string
     */
    @Override
    public String toString() {
        // @TODO - fix this
        // return null;

        String str = "";
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (exposed[i][j]) {
                    if (field[i][j] < 0) {
                        str += " -1";
                    }
                    else if (field[i][j] == 0) {
                        str += "   ";
                    }
                    else {
                        str += String.format(" %d ", field[i][j]);
                    }
                }
                else {
                    str += " * ";
                }
            }
            str += "\n";
        }
        return str;
    }

    /**
     * Check if two mine field instances are equal in current game state. Both
     * mines, hints, and exposed must be equal
     * 
     * @param other
     *            - object reference to compare
     * @return true if equal; false otherwise
     */
    public boolean equals(Object other) {

        if (!(other instanceof MineField)) {
            return false;
        }
        // NOTE: In this case, I want extended classes to pass this test
        // as well, so I am NOT using the
        // this.getClass() != other.getClass() test!

        MineField mf = (MineField) other;

        // For 2D arrays we need to use deepEquals to
        // check element by element
        // Arrays.equals returns false because it compares
        // references to each row, not elements in row
        if (!Arrays.deepEquals(this.field, mf.field)) {
            return false;
        }

        if (!Arrays.deepEquals(this.exposed, mf.exposed)) {
            return false;
        }

        return true; // all tests pass

    }
}
