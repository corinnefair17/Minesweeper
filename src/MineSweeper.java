import java.util.Scanner;

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

    /**
     * checks to see if the game has been won
     * 
     * @param field The field in question
     * @return True if the game is won, false otherwise
     */
    public static boolean checkWin(MineField field)
    {
        for (int row = 0; row < mf.exposed.length; row++)
        {
            for (int col = 0; col < mf.exposed[row].length; col++)
            {
                if (!mf.exposed[row][col] && mf.field[row][col] != -1)
                {
                    return false;
                } // end if
            } // end inner for
        } // end outer for
        
        return true;
    }

    /** 
     * The game play will be handled in the main method
     * 
     * @param args - command line arguments
     */
    public static void main(String[] args)
    {
        mf = createMineField(args);
        Scanner in = new Scanner(System.in);
        
        if (mf == null) // create minefield returns null - invalid usage
        {
            System.out.println("\nNow quitting...");
        }
        else
        {
            while (true)
            {
                // prints the minefield + a command prompt & waits for input
                System.out.println(mf.toString());
                System.out.println();
                System.out.print(" >>> ");
                String command = in.nextLine();
                
                String[] coms = command.split(" ");
                
                if (coms[0].equals("quit")) // quit command
                {
                    System.out.println("You lose!");
                    break;
                }
                // save command + file name
                else if (coms[0].equals("save") && coms.length == 2)
                {
                    boolean saved = MineFieldFileIO.saveMineSweeperGame(
                            mf, coms[1]);
                    
                    if (saved) // game successfully saved
                    {
                        System.out.println("Game saved to " + coms[1]);
                        System.out.println("\nNow quitting...");
                        break;
                    }
                }
                else if (coms.length == 2)
                {
                    try // if both commands are ints
                    {
                        int row = Integer.parseInt(coms[0]);
                        int col = Integer.parseInt(coms[1]);
                        
                        mf.exposeCell(row, col);
                        
                        if (checkWin(mf))
                        {
                            System.out.println(mf.toString());
                            System.out.println();
                            System.out.println("You won!");
                            break;
                        }
                        if (mf.field[row][col] == -1)
                        {
                            System.out.println(mf.toString());
                            System.out.println();
                            System.out.println("You lose!");
                            break;
                        }
                    }
                    catch (Exception e) // both commands are not ints
                    {
                        System.out.println("\nInvalid command. Commands are:\n"
                                + "quit: quits the game\n"
                                + "save <filename>: saves the game to a given "
                                + "file name and quits\n"
                                + "<row> <col>: exposes the cell at the given "
                                + "location\n");
                    }
                }
                else // command is not valid
                {
                    System.out.println("\nInvalid command. Commands are:\n"
                            + "quit: quits the game\n"
                            + "save <filename>: saves the game to a given "
                            + "file name and quits\n"
                            + "<row> <col>: exposes the cell at the given "
                            + "location\n");
                } // end else
            } // end while
        } // end else
        
        in.close();
    } // end main
} // end class
