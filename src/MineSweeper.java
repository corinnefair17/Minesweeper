/**
 *  This is the class that will play the mine sweeper game
 *
 * @author Corinne Fair
 * @version 0
 *
 */

// TODO - write this class as needed
public class MineSweeper 
{
   /** The minefield */ 
    private static MineField mf;
    
    /**
     * prints how to use MineSweeper
     */
    public static void printUsage()
    {
        String out = "-h prints a help message and quits\n";
        out += "-m creates a MineField with rows, cols, and mines\n";
        out += "-l loads a saved minefield from field and creates a "
                + "MineField instance\n";
        out += "-d creates a simple default game using default constructor\n";
        out += "-g loads an in-progress game";
        
        System.out.println(out);
    }

    /**
     * Generates a MineField
     * 
     * @param args Command line arguments
     * @return The generated MineFied
     */
    public static MineField createMineField(String[] args)
    {
        if (args[1] == null)
        {
            mf = new MineField(8, 8, 8);
            return mf;
        }
        
        int num1 = Integer.parseInt(args[1]);
        int num2 = Integer.parseInt(args[2]);
        int num3 = Integer.parseInt(args[3]);
        
        if (num1 > 2 && num2 > 2 && num3 > 2)
        {
            mf = new MineField(num1, num2, num3);
        }
        else
        {
            System.out.println("Invalid usage.");
            System.out.println(
                    "Correct usage: java MineSweeper rows cols mines");
            mf = null;
        }
        
        return mf;
    }
    // TODO -
    // boolean checkWin(MineField mf)

    /** 
     * The game play will be handled in the main method
     * 
     * @param args - command line arguments
     */
    public static void main(String[] args)
    {
        // TODO - fix this
        if (args[0].equals("-h"))
        {
            printUsage();
        }
    }
}
