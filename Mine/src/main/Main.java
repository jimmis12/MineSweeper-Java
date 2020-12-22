package main;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static main.Main.desktopPath;



public class Main implements Runnable{
 public static int level=1;
 public static int  press=0;
 public static String sname;
 public static String desktopPath;
 public static String desktopPath2;
    GUI gui = new GUI();
    
    
 @Override
     public void run(){ 
         
        while(true)
        {   
            gui.repaint();
            if(gui.resetter == false){
            gui.CheckVictoryStatus();
            
                if(gui.victory==true ){
                    write();
                }
                
            }
        
        }
    }  
    
    
    
    public static void main(String[] args)throws Exception {
        
        file();
        
        test();   
  
    }
    
    
    public static void test() throws InterruptedException, IOException  {
            
            GUI1 gui1 = new GUI1();
            
            
    }
    static void file() throws IOException{
        File file = new File("C:\\MineSweeper");
                if(!file.exists())
                file.mkdirs();
                FileWriter writer = new FileWriter("C:\\MineSweeper\\Score.txt", true);
                
    }
    void write(){
        String lvl;
        if(Main.level==1)
            lvl="Easy";
        else if(Main.level==2)
            lvl="Medium";
        else if (Main.level==3)
                lvl="Hard";
        else 
            lvl="Easy";

        try {
            
                FileWriter writer = new FileWriter("C:\\MineSweeper\\Score.txt", true);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                
                bufferedWriter.write(sname+" / "+gui.sec+" / "+lvl+"\n");
                bufferedWriter.close();
            } catch (FileNotFoundException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }    


}

 final class GUI1 extends JFrame{
    
    
     
    JRadioButton jRadioButton1; 
    JRadioButton jRadioButton2; 
    JRadioButton jRadioButton3; 
  
    JButton jButton; 
    JLabel Onoma;
    JTextField Field;
    ButtonGroup G1; 
  
  void Path() {
    try{
    desktopPath = System.getProperty("user.home") + "/Desktop/Mine/MS.png";
    System.out.print(desktopPath.replace("\\", "/"));
    }catch (Exception e){
        System.out.println("Exception caught ="+e.getMessage());
        }
    }

    
     
    public  GUI1 () throws InterruptedException, IOException{
        
        Path();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(desktopPath));
        this.setTitle("Minesweeper");
        this.setSize(308,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setVisible(true);
        this.setResizable(false);
        this.setLayout(null);         

        jRadioButton1 = new JRadioButton();
        jRadioButton2 = new JRadioButton(); 
        jRadioButton3 = new JRadioButton();

        Onoma= new JLabel();
        Field = new JTextField(20);
        
        jButton = new JButton("OK"); 

        G1 = new ButtonGroup(); 
   
        jRadioButton1.setText("Easy"); 
        jRadioButton2.setText("Medium"); 
        jRadioButton3.setText("Hard");
        Onoma.setText("NickName");
        
        jRadioButton1.setBounds(15, 30, 80, 15);  
        jRadioButton2.setBounds(15, 50,80,15); 
        jRadioButton3.setBounds(15, 70,80,15);

        Onoma.setSize(60, 12);
        Onoma.setLocation(200, 32);
        
        Field.setBounds(190,50,80,25);
        
        jButton.setBounds(100,170,100,30);
        this.add(jRadioButton1); 
        this.add(jRadioButton2); 
        this.add(jRadioButton3);
        
        this.add(Onoma);
        this.add(Field);
        this.add(jButton); 
        jRadioButton1.setSelected(true);
 
        G1.add(jRadioButton1); 
        G1.add(jRadioButton2);
        G1.add(jRadioButton3);
        
        
        this.repaint();
        
        jButton.addActionListener(new ActionListener() { 
  
            @Override
            public void actionPerformed(ActionEvent e) 
            {  
            
                
                if (jRadioButton1.isSelected()) { 
                    Main.level=1; 
                    Main.press=1;
                    Main.sname=(Field.getText());
                    if(Field.getText().equals("")){
                        Main.sname="Guest";
                    }
                    dispose();
                    new Thread(new Main()).start();
                    
                } 
  
                else if (jRadioButton2.isSelected()) { 
                    Main.level=2; 
                    Main.press=1;
                    Main.sname=(Field.getText());
                    if(Field.getText().equals("")){
                        Main.sname="Guest";
                    }
                    dispose();
                    new Thread(new Main()).start();
                } 
                else if (jRadioButton3.isSelected()) { 
                    Main.level=3;
                    Main.press=1;
                    Main.sname=(Field.getText());
                    if(Field.getText().equals("")){
                        Main.sname="Guest";
                    }
                    dispose();
                    new Thread(new Main()).start();
                }
                else {
                    Main.level=1;
                    Main.press=1;
                    Main.sname=(Field.getText());
                    if(Field.getText().equals("")){
                        Main.sname="Guest";
                    }
                    dispose();
                    new Thread(new Main()).start();
                }
  
                // MessageDialog to show information selected radion buttons. 
              //  JOptionPane.showMessageDialog(GUI1.this, Main.level); 
                
            } 
            
        });  
                         

}

    
   
}






