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
        String out = "Usage: java MineSweeper <command>\n";
        out += " -- List of Commands -- \n";
        out += "-h prints this help message and quits\n";
        out += "-m <rows> <cols> <mines> creates a MineField with "
                + "a given number of rows, cols, and mines\n";
        out += "    or a default field if called without any numbers\n";
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
        if (args == null || args.length == 0)
        {
            return null;
        }
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
    /**
     * checks to see if the game has been won
     * 
     * @param field The field in question
     * @return True if the game is won, false otherwise
     */
    public static boolean checkWin(MineField field)
    {
        return false;
    }

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
        else if (args[0].equals("-m"))
        {
            mf = createMineField(args);
            System.out.println(mf.toString());
        }
    }
}
