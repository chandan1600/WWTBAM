/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Chandan Aulakh 18006095
 * 
 * This class has not been modified to implement SQL as it is based of the old
 * framework of utilizing the text files. 
 * 
 * This is a simple Question class. It holds few variables for reading files,
 * string variables for each of the question options and answer and question
 * itself. The constructor initializes file reader and buffered reader. It opens
 * the text file preparing to read from it. 
 * There are several methods. GetQuestion to retrieve the question line from 
 * the text file, Get a,b,c and d to respectively read the options a,b,c and d
 * from the question file. There is also an answers method which reads the 
 * answer and stores it to be compared later. All classes have @return to return
 * their string for each question, answer and options. 
 */
public class Questions {
    
    public static FileReader fr;
    public static BufferedReader br;
    public String a, b, c, d;
    public String answer, question;
    
    public Questions(){
        try 
        {
            fr = new FileReader("questions.txt");//reads from file
            br = new BufferedReader(fr);
        } catch (FileNotFoundException ex) 
        {
            Logger.getLogger(Questions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getQuestion(){
        try {
            question = br.readLine();//reads the question from file
        } catch (IOException ex) {
            Logger.getLogger(Questions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return question;
        
    }
    
     public String getA(){
        try {
            a = br.readLine();//reads option a from file
        } catch (IOException ex) {
            Logger.getLogger(Questions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
     
     public String getB(){
        try {
            b=br.readLine();//reads option b from file
        } catch (IOException ex) {
            Logger.getLogger(Questions.class.getName()).log(Level.SEVERE, null, ex);
        }
         return b;
     }
     
     public String getC(){
        try {
            c=br.readLine();//reads option c from file
        } catch (IOException ex) {
            Logger.getLogger(Questions.class.getName()).log(Level.SEVERE, null, ex);
        }
         return c;
     }
     
     public String getD(){
        try {
            d=br.readLine();//reads option d from file
        } catch (IOException ex) {
            Logger.getLogger(Questions.class.getName()).log(Level.SEVERE, null, ex);
        }
         return d;
     }
     
     public String answer(){
        try {
            answer=br.readLine();//reads answer from file
        } catch (IOException ex) {
            Logger.getLogger(Questions.class.getName()).log(Level.SEVERE, null, ex);
        }
         return answer;
     }
    
}
