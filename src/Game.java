
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Chandan Aulakh 18006095
 * 
 * This class has been updated to utilize a SQL database. The database
 * is used to save user information and retrieve it for use in user 
 * introductions. The variables used in connecting and utilizing the SQL database,
 * named "player" are connection, result set and prepared statement. There is a
 * new method getConnection and contestantIntro. The game start method has been modified to work with 
 * the SQL database. Simply, the file text system is replaced with the database 
 * framework in this modified class. Older redundant variables and methods have been 
 * deleted. The following java doc text is from the old version of this class if you 
 * are interested.
 * 
 * The main game class has a numerous amount of variables. These include,
 * File reader, writer and Buffered reader,writer. It also has a money array with
 * prize values, there is also a variable "value" to keep count of correct 
 * answers. String answer is used to collect answers and the classes Contestant
 * and Question are initialized.
 * 
 * There is a constructor for initialization of file reader. There are two methods
 * in this class, gameStart and and checkpointValue. GameStart handles the 
 * questions and checks to see if they are correct or not, returning a response
 * accordingly. The checkpointValue method holds conditions for milestones 
 * reached throughout the game, it @returns checkpointMoney.
 * 
 * A main method at the bottom starts the game.
 */

public class Game {
    //global variables used in this class 
    public static Connection myCon = null;//for connection to database
    public static ResultSet myRes=null;//to retrieve from database
    public static PreparedStatement preparedStatement;//to input to database
    boolean quit =false; 
    int moneyValue [] = new int []{100,200,300,500,1000,2000,4000,8000,16000,32000,64000,125000,250000,500000,1000000,1};
    int value=0;
    String answer;
    public static Scanner input = new Scanner(System.in);
    //end of global variables used
    //classes initilised 
    static Contestant newContestant = new Contestant();
    static Questions newQuestion = new Questions();
    static Game newGame = new Game();

    //basic method to get and establish a connection with the SQL database
    public static void getConnection()
    {    
        final String url = "jdbc:derby://localhost:1527/myDatabase";// url of the database
        final String user = "user123";// user name of the database
        final String password = "password";// password of the database

        // database connection forming with confirmation
        try {
            System.out.println("connecting to database...");
            myCon = DriverManager.getConnection(url, user, password);
            System.out.println("database connected!");
        } catch (final SQLException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // this is a method which introduces the constestant into the game
    public static ArrayList<String> contestantIntro() throws Exception {
        // variable for use in method
        String userName = null;
        String locationName = null;
        int ageInfo = 0;
        // end of variables
        try {
            // receive input from user for database storage
            System.out.println("*Please enter your details before beggining*");
            System.out.println("please enter your name:");
            userName = input.nextLine();
            System.out.println("where are you from?");
            locationName = input.nextLine();
            System.out.println("how old are you?");
            ageInfo = input.nextInt();
            // collection of input is complete

            // insertion of input into database table using prepared statement
            final String query = "INSERT INTO player (name, location, age)" + "VALUES (?, ?, ?)";
            preparedStatement = myCon.prepareStatement(query);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, locationName);
            preparedStatement.setInt(3, ageInfo);
            preparedStatement.execute();
            // insertion of input is completed

            // retrieval of data from player database for output
            preparedStatement = myCon.prepareStatement("SELECT name, location, age  FROM player");
            myRes = preparedStatement.executeQuery();
            // preparation of an array to convert data to a string and store
            final ArrayList<String> array = new ArrayList<>();
            // inserting the database data into an array list for use as a string
            while (myRes.next()) {
                array.add(myRes.getString("name"));
                array.add(myRes.getString("location"));
                array.add(myRes.getString("age"));
            }
            // retrieving the database informtion from the arraylist
            final String outName = array.get(0);
            final String outLocation = array.get(1);
            final String outAge = array.get(2);
            // retrieval of data from database completed

            // short player introduction using data from table
            System.out.println("\n\n\nWELCOME TO...\n\n****WHO WANTS TO BE A MILLIONAIRE: Special Edition****\n");
            System.out.println("\n" + "On tonight's show, we have with us " + outName + " who is from " + outLocation
                    + " and is " + outAge + " years old!");
            return array;// array is returned from the method
        } catch (final SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        return null;
    }

    public void gameStart() throws SQLException {
        // retrieval of contestant's name for use in message output
        preparedStatement = myCon.prepareStatement("SELECT name FROM player");
        myRes = preparedStatement.executeQuery();
        final ArrayList<String> array = new ArrayList<>();
        while (myRes.next()) {
            array.add(myRes.getString("name"));
        }
        final String outName = array.get(0);
        // retrieval of contestant name complete

        System.out.println("Lets begin with the first Question!...\n");
        final Scanner in = new Scanner(System.in);
        for (int j = 0; j < 15; j++) {// beggining of questions using for loop
            do {
                for (int i = 0; i < 5; i++)// loops for questions and answer request
                {
                    if (i == 0) {
                        System.out.println(newQuestion.getQuestion());// retrieves question
                    }
                    if (i == 1) {
                        System.out.println(newQuestion.getA());// retrieves option a
                    }
                    if (i == 2) {
                        System.out.println(newQuestion.getB());// retrieves option b
                    }
                    if (i == 3) {
                        System.out.println(newQuestion.getC());// retrieves option c
                    }
                    if (i == 4) {
                        System.out.println(newQuestion.getD());// retrieves option d
                    }
                }
                do {
                    System.out.println("\nEnter your answer: ");// input for answer
                    answer = in.nextLine();
                    answer = answer.toLowerCase();
                    if (answer.equals(newQuestion.answer()))// compares answer
                    {
                        System.out.println("\nThat is correct!");
                        System.out.println("You are now at $" + moneyValue[value] + "\n");// displays money won
                        value++;// adds one to correct count
                        if (moneyValue[value] == 1) {
                            System.out.println("CONGRATULATIONS YOU HAVE WON THE GAME\n"
                                    + "YOU GET TO TAKE HOME ONE MILLION DOLLARS!");
                        }
                    } else if (value == 0 && answer.equals("q"))// if player quits first round
                    {
                        System.out.println("\nYou have quit on the first question!\nYou go home with no money!");
                        System.exit(0);
                    } else if (answer.equals("q"))// if player quits on a round
                    {
                        System.out.println("\nYou have chosen to quit!");
                        System.out.println("Safe choice, you get to go home with $" + moneyValue[value - 1]);
                        System.exit(0);
                    } else if (answer.charAt(0) < 97 || answer.charAt(0) > 100)// response if incorrect input
                    {
                        System.out.println("\nincorrect input");
                        System.out.println("please enter an answer out a, b, c or d");
                    } else// if player gets wrong answer
                    {
                        System.out.println("\nThat is, unfortunately, the wrong answer!");
                        if (newGame.checkpointValue() != 0)// gives checkpoint result if any
                        {
                            System.out
                                    .println("However, you still get to walk away with $" + newGame.checkpointValue());
                        }
                        System.out.println("Thank you for playing " + outName);// reads exit line with contesant name
                        quit = true;
                        System.exit(0);// program ends

                    }
                } while (answer.charAt(0) < 97 || answer.charAt(0) > 102); // ensures answer is a,b,c or d
            } while (quit != false); // loops while quit is not true
        }
    }

    public int checkpointValue() {
        final int currentMoney = moneyValue[value];// retrieves current prize point
        int checkpointMoney = 0; // variable for milestone
        { // if conditions check to see if checkpoint money is valid
            if (currentMoney < 1000) {
                checkpointMoney = 0;
            } else if (currentMoney == 1000) {
                checkpointMoney = 1000;
            } else if (currentMoney >= 1000 && currentMoney < 32000) {
                checkpointMoney = 1000;
            } else if (currentMoney == 32000) {
                checkpointMoney = 32000;
            } else if (currentMoney >= 32000 && currentMoney < 1000000) {
                checkpointMoney = 32000;
            }
        }
        return checkpointMoney;// returns checkpointMoney
    }

    public static void main(final String[] args) throws Exception {
        getConnection();//connection to database is established
        contestantIntro();//contestant introduction method
        newContestant.gameIntro();//game introduction 
        newGame.gameStart();
    }
    
}
    
    

