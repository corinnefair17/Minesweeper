
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
        String out = "\n";
        out += "Usage: java MineSweeper <command>\n";
        out += " -- List of Commands -- \n";
        out += "-h prints this help message and quits\n";
        out += "-m <rows> <cols> <mines> creates a MineField with "
                + "a given number of rows, cols,\n   and mines ";
        out += "or a default field if called without any numbers\n";
        out += "-l <filename.txt> loads a saved minefield from field and "
                + "creates a MineField \n   instance\n";
        out += "-d creates a simple default game using default constructor\n";
        out += "-g <filename.txt> loads an in-progress game";
        
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
            return new MineField(8, 8, 8);
        }
        if (args[0].equals("-h"))
        {
            printUsage();
            return null;
        }
        if (args[0].equals("-d"))
        {
            return new MineField();
        }
        if (args[0].equals("-m"))
        {
            if (args.length != 4)
            {
                System.out.println("Incorrect Usage\nCorrect usage is "
                        + "java MineSweeper <rows> <cols> <mines>");
                return null;
            }
            else
            {
                try
                {
                    int rows = Integer.parseInt(args[1]);
                    int cols = Integer.parseInt(args[2]);
                    int mines = Integer.parseInt(args[3]);
                    
                    return new MineField(rows, cols, mines);
                }
                catch (Exception e)
                {
                    System.out.println("Incorrect Usage\nCorrect usage is "
                            + "java MineSweeper <rows> <cols> <mines>");
                    return null;
                }
            }
        }
        if ((args[0].equals("-l") || args[0].equals("-g")) && args.length == 2)
        {
            try
            {
                if (args[0].equals("-l"))
                {
                    mf = MineFieldFileIO.readMineFile(args[1]);
                }
                if (args[0].equals("-g"))
                {
                    mf = MineFieldFileIO.readMineSweeperGame(args[1]);
                }
            }
            catch (Exception e)
            {
                return null;
            }
        }
        else
        {
            System.out.println("Incorrect Usage. "
                    + "Type java MineSweeper -h for help.");
            return null;
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
        mf = createMineField(args);
        
        if (mf == null)
        {
            System.out.println("\nNow quitting...");
        }
        else
        {
            System.out.println(mf.toString());
        }
    }
}
