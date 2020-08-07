
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.WindowConstants;

/**
 * @author Chandan Aulakh 18006095
 * 
 * This is a GUI class for the who wants to be a millionaire game
 * It is not fully functional and only displays minimal information
 * It holds a simple test area for the display of the questions
 * It also has four JButtons for choosing the answer
 * The GUI has been customized with colors, background and a logo.
 * The GUI also opens minimized, it is requested that you manually 
 * change the screen size of the GUI by dragging it
 */

public class gameGUI extends javax.swing.JFrame {
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    // Variables declaration begins
    JButton A, B, C, D;
    JTextArea textArea;
    JLabel moneyTab;
    JScrollPane questionBox;
    JLabel labelBackground;
    JLabel logoLabel;
    JToggleButton onOff;
    FileReader fr;
    BufferedReader br;
    String line;
    // End of variables declaration  
  
    //constructor for gui class
    public gameGUI() throws IOException {
            initComponents();   
    }    
    
    //components within the JFrame
    private void initComponents() throws FileNotFoundException, IOException {
        //intilisation of variables 
        questionBox = new JScrollPane();
        textArea = new JTextArea();
        A = new JButton();
        B = new JButton();
        C = new JButton();
        D = new JButton();
        moneyTab = new JLabel();
        onOff = new JToggleButton();      
        logoLabel = new JLabel();
        labelBackground = new JLabel();
        br = new BufferedReader(new FileReader("questions.txt"));
        //end of initilisation 
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        
        //text area properties are set
        textArea.setColumns(20);
        textArea.setRows(5);
        questionBox.setViewportView(textArea);
       
        getContentPane().add(questionBox);
        questionBox.setBounds(184, 260, 470, 84);

        //money tab properties with beggining cash amount
        moneyTab.setForeground(new Color(255, 255, 255));
        moneyTab.setText("$100");
        getContentPane().add(moneyTab);
        moneyTab.setBounds(320, 370, 220, 50);

        onOff.setText("QUIT");//on off toggle to begin and exit the game
        onOff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                if(onOff.isSelected())//exits system if toggled
                {
                    System.exit(0); 
                }
                else
                {
                    System.out.println("game will begin");
                }
            }
        });
        getContentPane().add(onOff);
        onOff.setBounds(360, 600, 123, 29);
        
        //Question and possible choices are read from text file
        String Q1 = br.readLine();
        String Q1a = br.readLine();
        String Q1b = br.readLine();
        String Q1c = br.readLine();
        String Q1d = br.readLine();
        textArea.setText(Q1+"\n"+Q1a+"\n"+Q1b+"\n"+Q1c+"\n"+Q1d);//displayed in text area
        questionBox.setViewportView(textArea);//textArea is displayed in the question box
        
        String q1Ans = br.readLine();//reads the answer from text file
       
        //properties set for A JButton
        A.setBackground(new java.awt.Color(0, 0, 153));
        A.setText("A");
        A.addActionListener(new ActionListener() //action listener for A Button
        {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if(!q1Ans.equalsIgnoreCase("A"))//if answer matches button will turn green
                {
                    A.setBackground(Color.red);
                    moneyTab.setText("incorrect");
                }
                else if(q1Ans.equalsIgnoreCase("A"))//if answer does not match button will turn red
                {
                    A.setBackground(Color.green);
                    moneyTab.setText("correct");
                }   
            }
        });
        getContentPane().add(A);
        A.setBounds(160, 440, 110, 40);

        //properties for B JButton
        B.setBackground(new java.awt.Color(0, 0, 153));
        B.setText("B");
        B.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {//action listener for B button
                if(!q1Ans.equalsIgnoreCase("B"))//if answer matches button will turn green
                {
                    A.setBackground(Color.red);
                    moneyTab.setText("incorrect");
                }
                else if(q1Ans.equalsIgnoreCase("B"))//if answer does not match button will turn red
                {
                    A.setBackground(Color.green);
                    moneyTab.setText("correct");
                }  
            }
        });
        getContentPane().add(B);
        B.setBounds(570, 440, 100, 40);
        
        //properties for C Jbutton
        C.setBackground(new java.awt.Color(0, 0, 153));
        C.setText("C");
        C.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) //action listener for C Button
            {
                if(!q1Ans.equalsIgnoreCase("C"))//if answer matches button will turn green
                {
                    A.setBackground(Color.red);
                    moneyTab.setText("incorrect");
                }
                else if(q1Ans.equalsIgnoreCase("C"))//if answer does not match button will turn red
                {
                    A.setBackground(Color.green);
                    moneyTab.setText("correct");
                }  
            }
        });
        getContentPane().add(C);
        C.setBounds(160, 510, 110, 40);
        
        //properties for D JButton
        D.setBackground(new java.awt.Color(0, 0, 153));
        D.setText("D");
        D.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt)//action listener for D Button
            {
                if(!q1Ans.equalsIgnoreCase("D"))//if answer matches button will turn green
                {
                    A.setBackground(Color.red);
                    moneyTab.setText("incorrect");
                }
                else if(q1Ans.equalsIgnoreCase("D"))//if answer does not match button will turn red
                {
                    A.setBackground(Color.green);
                    moneyTab.setText("correct");
                }  
            }
        });
        getContentPane().add(D);
        D.setBounds(570, 510, 100, 40);

        //properties set for generating who wants to be a millionaire logo
        logoLabel.setIcon(new javax.swing.ImageIcon("/Users/Chandan/Desktop/logo.png")); 
        logoLabel.setText("jLabel4");
        getContentPane().add(logoLabel);
        logoLabel.setBounds(280, 0, 310, 260);

        //properties set for having a black background image
        labelBackground.setIcon(new javax.swing.ImageIcon("/Users/Chandan/Desktop/Black-Background-DX58.jpg"));
        labelBackground.setText("jLabel2");
        getContentPane().add(labelBackground);
        labelBackground.setBounds(0, 0, 960, 730);
        pack();
    }                       
 
    //main class for GUI, partially auto-generated
    public static void main(String args[]) { 
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(gameGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(gameGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(gameGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(gameGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new gameGUI().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(gameGUI.class.getName()).log(Level.SEVERE, null, ex);
                }       
            }
        });
    }                   
}