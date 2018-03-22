This project continues creation of the ```MineSweeper``` game found on some computers.  See the slides from class for pictures of the game.

I have given you code that defines the MineField and MineFieldFileIO (solution to P1 and P2) in the ``given`` folder.
Please review this code.

In this project, you will write code to play the MineSweeper game.

The MineSweeper will accept command line arguments:
* -h   print a help message and quit
 * create ``public static void printUsage()`` method that prints the command line options below
* -m   <rows cols mines>  creates a MineField instance with rows, cols, and mines.
* -l   <filename> load a saved minefield from file and create a MineField instance
* -d   create a simple default game using default constructor
* -g   <filename> loads an in progress game

No arguments will create a random 8 x 8 minefield with 8 mines.

From command line in the ``bin`` folder after compiling with Eclipse, here are some examples:
* ``java MineSweeper``
 * Creates a 8x8 mine field with 8 mines and plays the game
*  ``java MineSweeper -h``
 * prints the usage message
* ``java MineSweeper -m 10 10 25``
 * Creates a 10 x 10 minefield with 25 randomly placed mines
* ``java MineSweeper -m 10 10``
 * Prints an error message for invalid input (missing number of mines) and usage
* ``java MineSweeper -l my_file.txt``
 * Loads and validates the mine from the given file
 * If valid plays the game, if invalid then prints error message and quits.






The arguments are mutally exclusive; on one is valid at a time.
If multiple choices, or invalid arguements then print an error message, and then print the usage message.

Define a method:
*  ``public static MineField createGame(String[] args)`` that returns the appropriate MineField based on the arguments.

Presuming a valid MineField is created, in the main method, you will call the createGame method, and then enter a loop where you:
* Print the current game state (using the MineField.toString)
* Get a command string from the user (one line after enter).
 * There are three valid options:
 * ``quit``   - just quits the game and prints "You lose!"
 * ``save <filename>`` - saves the current game state and quits
 * `` row col``  - two numbers separated by either space, comma, or tab
  * expose the cell at (row, col) using method in MineField.
  * if a mine is encountered, quit the game and print "You lose!"
* If an invalid command is entered, print and error message and get another command
* If no mine is encountered, then check to see if you won.
 * Add a method ``public static boolean checkWin(MineField mf)`` to check all unexposed cells, if only mines are unexposed, quit and print "You won!"
 * If any non-mine cells are unexposed, then continue playing.

You are not allowed to modify the given code MineField.java class.

All of your code should be in the ``MineSweeper.java``

## Grading
50 points
* Due 3-April at 11:59PM
* the ``printUsage`` and ``createMineField`` methods work via WebCat

50 points
* Due 12-April at 11:59PM
* Style and JUnit tests on webcat for ``printUsage()``, ``createMineField()``, and ``checkWin()`` methods.

50 points
* Due 12-April at 11:59PM
* complete game functions
* Instructor discretion on game design, comments, and style

## Testing and scoring

You are responsible for testing and design.
The WebCat tests will not be uploaded until AFTER the due date.

Do NOT go by your initial score on WebCat.

YOU need to verify that your code works properly before I upload my tests.

## Setup
* Fork this project into your course group.

* Clone your fork of repo to your computer
* Create Eclipse project that points to this repo
 * Be sure to add JUnit library
* Update the login.txt with your web-cat information

Write the necessary ``MineSweeper.java`` class.


## Honor Policy

 Unlike weekly assignments, this project has strict Honor Guidelines

* You may work in collaboration with one partner (or individually at your discretion).
* If you want to work with a partner, choose one yourself
 * Email me and cc your partner prior to Thursday 25-January at 5PM
* You must both submit to your personal GitLab repos.  Either:
 * Work together in one shared repo, and then do a final fork to individual repos
 * Work collaboratively, but making individual code commits to separate repos
* You may freely discuss and share code with your partner
* You may NOT view anyone else's code other than your partner!

* Any discussions with others, must follow the *Empty Hands Rule*
 * You may discuss concepts in abstract, including on paper or white board
 * You must destroy notes/erase prior to coding
 * You must document all discussions with other people
  * This includes both giving and receiving help
* Code will be checked for plagarism and unauthorized sharing.

## Notes:

* Read the JavaDoc for specific directions for each methods
* Look at the unit tests carefully when debugging for details.
* You do NOT need to write JUnit tests for this.
* Web-cat is picky on style and logic.  You can have correct code that will still lose points in web-cat.  Look at the reports
 * Web-cat wants extra braces around single line statements.  Just do it.
