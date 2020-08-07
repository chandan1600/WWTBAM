
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Chandan Aulakh 18006095
 * 
 * This class has been updated to make use of an SQL database. The gameIntro
 * method has been modified to retrieve data from the SQL database for the
 * basic introduction.
 * 
 * This is the contestant class. It has some basic variables for name, age 
 * and location of the contestant. There are also File writers, readers and
 * Buffered reader and writer. There is also a variable for money which holds
 * the pize amounts. There is a contestant constructor which initializes
 * the file reader,writer and buffered reader, writer. There is a method
 * called contestantDetails which asks the contestant basic questions
 * The other method is ContestantIntro which introduces the contestant
 * with a bit of small talk included. 
 */
public class Contestant{
    //variables for contestant class
    static Scanner input = new Scanner(System.in);
    public static FileWriter fw;
    public static BufferedWriter bw;
    public static FileReader fr;
    public static BufferedReader br;
    public static PreparedStatement preparedStatement;
    public static ResultSet myRes;
    public static Connection myCon;
    public static int moneyValue [] = new int []{100,200,300,500,1000,2000,4000,8000,16000,32000,64000,125000,250000,500000,1000000};
    public String name;
    public String age;
    public String location;
    //end of variables for contestant class
    
    //initialisation of some variables
    public Contestant(){
        try {
            fw = new FileWriter("contestant.txt");//file writer for file
            bw = new BufferedWriter(fw);
            fr = new FileReader("contestant.txt");//file reader for file
            br = new BufferedReader(fr);
        } catch (final IOException ex) {
            Logger.getLogger(Contestant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // method for game introduciton outlining basic rules and information
    public void gameIntro() throws SQLException {
        System.out.println("In this special edition, there are no lifelines! wrong answer - you lose!\n");
        System.out.println("As you know, the prize amount will increment with each correct answer.\n"
                + "You will have two checkpoints at $1000 and $32000\nIf you happen to get the question wrong,"
                + " you will go home with the appropriate checkpoint cash amount!\n");
        System.out.println(
                "At any point, you may also quit the game by entering 'q' and go home with the amount of money you have won!\n");
        System.out.println("Now, if you happen to win one million today what are you going " + "to do with it?\n");
        final String goal = input.nextLine();
        System.out.println("\nExcellent, that's amazing! Let's not waste time then and begin!\n");
        if (br != null)
            try {
                br.close();// to close the contestant file
            } catch (final IOException ex) {
                Logger.getLogger(Contestant.class.getName()).log(Level.SEVERE, null, ex);
            }
    }    
}