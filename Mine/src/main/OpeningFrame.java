
package main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import static main.Main.desktopPath;

final class OpeningFrame extends JFrame{
    
    
     
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

    
     
    public  OpeningFrame () throws InterruptedException, IOException{
        
        Path();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(desktopPath));
        this.setAlwaysOnTop(true);
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






