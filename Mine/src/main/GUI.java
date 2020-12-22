package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static jdk.nashorn.tools.ShellFunctions.input;
import static main.Main.desktopPath;
import static main.Main.file;




public class GUI extends JFrame{
    
   String desktopPath2;   
   int reveal=0;
   boolean mode=false;
   boolean resetter = false; 
   boolean  happy=true;
   boolean victory=false;
   boolean defeat=false;
   Date startDate = new Date();
   int rrandom=80;
   int smileC=0;
   int modec=0;
   int first_click=0;
   
   
   public int orizontia=300*Main.level+8;
   public int katheta=400;
   public int space=2;
   public int sectime=0;
   public int secc=0;
   public int array=10*Main.level;
   
   
   public int bombc=0;
   public int flaggerX=135;
   public int flaggerY=5;
   public int flaggerCenterX = flaggerX+15;
   public int flaggerCenterY =flaggerY+15;
   

   public int mx =-100;
   public int my=-100;
   public  int smileX=135*Main.level;   
   public int smileY=20;
   
   public int smileCenterX=smileX+15;
   public int smileCenterY=smileY+15;
   
   
   int timeX=220*Main.level;
   int timeY=21;
   int sec=0;
   int neig;
   int [][] mines= new int [array][10];
   int [][] gitones = new int [array][10];
   boolean [][] revealed = new boolean [array][10];
   boolean [][] flagged = new boolean [array][10];
   boolean [][] flag = new boolean [array][10];
   public int mb=0;
   
   public int bombes=0;
   public int bombes2=0;
   
   
   public Random rand = new Random();
   
    public GUI() {
        
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(Main.desktopPath));
        this.setTitle("Minesweeper");
        this.setSize(orizontia,katheta);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setVisible(true);
        this.setResizable(false);
             
         JMenuBar menuBar = new JMenuBar();
         
        // Add the menubar to the frame
        setJMenuBar(menuBar);
        JMenu Menu = new JMenu("Menu");
        JMenu Menu2 = new JMenu("Help");
        menuBar.add(Menu);
        menuBar.add(Menu2);
        
        
        JMenuItem recors = new JMenuItem("Recors");
        JMenuItem easy = new JMenuItem("Easy");
        JMenuItem medium = new JMenuItem("Medium");
        JMenuItem hard = new JMenuItem("Hard");
        JMenuItem Manual = new JMenuItem("Manual");
        
        Menu.add(recors);
        Menu.add(easy);
        Menu.add(medium);
        Menu.add(hard);
        Menu2.add(Manual);
        
        
      Manual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try{
                    desktopPath2 = System.getProperty("user.home") + "/Desktop/Mine/Manual.txt";
                    System.out.print(desktopPath2.replace("\\", "/"));
                    }catch (Exception e){
                    System.out.println("Exception caught ="+e.getMessage());
                    }
                
                File file2 = new File(desktopPath2);
                Desktop desktop2 = Desktop.getDesktop();
                
                if(file2.exists())
                    try {
                        desktop2.open(file2);
                } catch (IOException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
             }
        });
        
        recors.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                File file = new File("C:\\MineSweeper\\Score.txt");
                Desktop desktop = Desktop.getDesktop();
                if(file.exists())
                    try {
                        desktop.open(file);
                } catch (IOException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
 
             }
        });
        
        easy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Main.level=1;
                dispose();
                new Thread(new Main()).start();
            }
        });
        medium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Main.level=2;
                dispose();
                 new Thread(new Main()).start();
            }
        });
        hard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Main.level=3;
                dispose();
                new Thread(new Main()).start();
            }
        });
        
        if(Main.level==1){
        bombc=20;
        bombes=20;
        timeX=timeX-10;
        bombes2=bombes;
        rrandom=80;
        smileC=35;
        }
        else if(Main.level==2){
            bombc=50;
            bombes=50;
            timeX=timeX+69;
            bombes2=bombes;
            rrandom=200;
            smileC=60;
        }
        else {
            bombc=80;
            bombes=80;
            timeX=timeX+149;
            bombes2=bombes;
            rrandom=250;
            smileC=100;  
        }
        
        for (int i=0; i<orizontia/30; i++){
                      for(int j=0; j<katheta/40; j++){
                           if(rand.nextInt(rrandom)<bombes){  
                               if (mb>=bombes){
                                   break;
                               }
                               mines[i][j]=1;
                               mb++;  
                           }
                           else {
                               if (mb>=bombes){
                                   break;
                               }
                               mines [i][j]=0; 
                           }
                           revealed[i][j]=false;
                  }
             }
        bombes2=mb;
        while(mb<bombes){
            resetAll();
        }
        
        for (int i=0; i<orizontia/30; i++){
                for(int j=0; j<katheta/40; j++){
                    neig=0;
                        for (int m=0; m<orizontia/30; m++){
                                 for(int n=0; n<katheta/40; n++){
                                     
                                     if(!(m==i && j==n)){
                                         if(isN(i,j,m,n) == true)
                                         neig++;
                                     }   
                                }
                        }     
                        gitones[i][j]=neig;
                }  
        }
        
        Board board = new Board();
        setContentPane(board);
        
        Move move = new Move();
        this.addMouseMotionListener(move);
        
        Click click = new Click();
        this.addMouseListener(click);
      
    }

    public class Board extends JPanel {
            
            @Override
            public void paintComponent(Graphics g){
                
                g.setColor(Color.DARK_GRAY);                                    //xroma background tou board
                g.fillRect(0,0,orizontia,katheta);                              //diastasis board
                g.setColor(Color.white);                                        //xroma metriti
                g.fillRect(15,timeY-13,50,30);                                     //sintetagmenes rologiou kai diastasis tou
                g.setFont(new Font("Tahoma",Font.BOLD,30));

                     g.setColor(Color.black);
                     g.drawString(Integer.toString(TotalMines()),20,50-13);        //print metriti bombon
                     
                     
                    for (int i=0; i<orizontia/30; i++){
                      for(int j=0; j<katheta/40; j++){
                          g.setColor(Color.gray);
                          
                          if(mines[i][j]==1){
                            //  g.setColor(Color.red);                            //xroma narkis
                          }          
                          if(flagged[i][j]==true){  
                              g.setColor(Color.blue);                           // xroma simaia
                                                                    
                          }
                                                     
                          if(flag[i][j]==true){                                 //prosthesi kai aferesi simeas
                                 g.setColor(Color.gray);
                                  flagged[i][j]=false;
                              }
                          if(revealed[i][j]==true){                             
                              g.setColor(Color.white);                          // xroma faneromenou tetragonou
                              if(mines[i][j]==1){                               // ama patiso narki to xroma ginete kokkino
                                  g.setColor(Color.red);
                                 if(victory==true && defeat==false)
                                     g.setColor(Color.green);
                              }
                          }         
                          
                          if (mx >= space + i *30 && mx <i*30+34-space && my>=space+j*30+30+60+5  && my <space+j*30+30+60+30+4)
                          {
                              g.setColor(Color.lightGray);
                          }
                          g.fillRect(space+i*30,space+j*30+70-23,30-space,30-space); // print board

                          
                          if(revealed[i][j]==true){                             
                              g.setColor(Color.black);                          //xroma narkis
                              if(mines[i][j] == 0 && gitones[i][j] != 0){       // xroma arithmon
                                  if(gitones[i][j]==1)
                                      g.setColor(Color.BLUE);
                                  if(gitones[i][j]==2)
                                      g.setColor(Color.GREEN);
                                  if(gitones[i][j]==3)
                                      g.setColor(Color.RED);
                                  if(gitones[i][j]==4)
                                      g.setColor(new Color(0,0,128)) ;   
                                  if(gitones[i][j]==5)
                                      g.setColor(new Color(178,34,34)) ;
                                  if(gitones[i][j]==6)
                                      g.setColor(new Color(72,209,204)) ;
                                  if(gitones[i][j]==7)
                                      g.setColor(Color.black) ;
                                  if(gitones[i][j]==8)
                                      g.setColor(Color.DARK_GRAY);
                                  
                              g.setFont(new Font("Tahoma",Font.BOLD,30));
                              g.drawString(Integer.toString(gitones[i][j]),i*30+6,j*30+70+20+7-23); // print to poses gitonikes bombes yparxoun
                              
                              }                                                 //telos xroma arithmon
                              
                              else if (mines[i][j] == 1){                       //sintetagmenes gia to pou tha gini print i narki
                                  g.fillRect(space+i*30+6,space+j*30+70+7-23,16,16);
                                  g.fillRect(space+i*30+3+1,space+j*30+70+10-23,20,10);
                                  g.fillRect(space+i*30+6+3,space+j*30+70+5-23,10,20);
                                  g.fillRect(space+i*30+12,space+j*30+70+3-23,4,24);
                                  g.fillRect(space+i*30+2,space+j*30+70+7+6-23,24,4);
                                  
                              }                                                 //telos sintetagmenes gia to pou tha gini print i narki
                              
                          }   
                          
                    }
                
                }
               g.setColor(Color.yellow);                                        // smile Paint
               g.fillOval(smileX,smileY-13,30,30);
               g.setColor(Color.black);
               g.fillOval(smileX+7,smileY+7-13,5,5);
               g.fillOval(smileX+18,smileY+7-13,5,5);
               
               
               
               if(happy==true ){                                                 //sintetagmenes xaroumenis fatsa
                   g.fillRect(smileX+8,smileY+20-13,15,4);
                   g.fillRect(smileX+7,smileY+19-13,4,4);
                   g.fillRect(smileX+20,smileY+19-13,4,4);
                   g.fillRect(smileX+6,smileY+18-13,4,4);
                   g.fillRect(smileX+21,smileY+18-13,4,4);
               }
               else if(happy==false){                                      //sintetagmenes lipimenis fatsas 
                   g.fillRect(smileX+8,smileY+20-13,15,4);
                   g.fillRect(smileX+7,smileY+21-13,4,4);
                   g.fillRect(smileX+21,smileY+21-13,4,4);
                   g.fillRect(smileX+6,smileY+22-13,4,4);
                   g.fillRect(smileX+22,smileY+22-13,4,4);
                   
               }
               else {
                   System.out.println("ERROR me to smile");
               }
               
               
               g.setColor(Color.white);                                                     //xroma rologiou
               g.fillRect(timeX,timeY-13,90,30);                                               // sintentagmenes rologiou

               if(defeat==false && victory==false && sectime>0){                                         //pause to roloi ama xaso/kerdiso
                     sec = (int)((new Date().getTime()-startDate.getTime())/1000)-secc;
               }
               if(defeat==true && victory==false){                                     // xroma sto roloio ama xaso/kerdiso
                   g.setColor(Color.red);
                   g.fillRect(timeX,timeY-13,90,30);
               }
                if(victory==true && defeat==false){
                   g.setColor(Color.green);
                   g.fillRect(timeX,timeY-13,90,30);
               }
                     g.setFont(new Font("Tahoma",Font.BOLD,30));
                     g.setColor(Color.black); 
                     if(sec<0)
                    sec=0;
                      if(sectime>=1)
                     g.drawString(Integer.toString(sec),timeX,timeY+29-13);
                      
                      if(sec>999)
                          resetAll();
                      
            }
            
            
        }
    


public class Move implements MouseMotionListener{

    @Override
    public void mouseDragged(MouseEvent e) {
      
    }

    @Override
    public void mouseMoved(MouseEvent e) {
      mx = e.getX();
      my = e.getY();
 
    }

}
    public class Click implements MouseListener{
            
        @Override
        public void mouseClicked(MouseEvent e) {
             
                                                           
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
            mx = e.getX();
            my = e.getY();
            int boxX;
            int boxY;
            
            
          if(e.getButton() == MouseEvent.BUTTON1) {                             // aristero klik
             
              mx = e.getX();
              my = e.getY();
            
             if(first_click==0 && inBoxX()!=-1 && inBoxY()!=-1 && defeat==false){
                 if(mines[inBoxX()][inBoxY()]==1 && inBoxX()!=-1 && inBoxY()!=-1) {
                     boxX=inBoxX();
                     boxY=inBoxY();
                     resetAll();
                     while(mines[boxX][boxY]==1)
                         resetAll();
                     revealed[boxX][boxY]=true;
                     Open(boxX,boxY);
                     
                 }    
             }
             
             first_click++;
             
          }  
            
            
          if(e.getButton() == MouseEvent.BUTTON1){
               mx = e.getX();
              my = e.getY();
            if(inBoxX() != -1 && inBoxY() != -1 && revealed[inBoxX()][inBoxY()]==false){
                revealed[inBoxX()][inBoxY()] = true;
                Open(inBoxX(),inBoxY());
            }
            
            if(inBoxX() != -1 && inBoxY() != -1){
               // System.out.println("The Mouse was Click "+ (inBoxX()+1) + ","+ (inBoxY()+1)+"\ngitonikes bombes "+ gitones[inBoxX()][inBoxY()]);
                if(sectime==0){
                secc = (int)((new Date().getTime()-startDate.getTime())/1000);   
                }
                sectime++;
            } 
          }
          
          
          if(e.getButton() == MouseEvent.BUTTON1){
              mx = e.getX();
              my = e.getY();
            if((mx*Main.level>=smileX*Main.level+2) && (mx*Main.level<=smileX*Main.level+smileC-2) && my>=smileY+25+13 && my<smileY+55+13){
                   
                  mx=e.getX();
                  my=e.getY();
                  first_click=0;
                  
                
                    while(true){
                        resetAllB();
                        resetAll();
                        if(mb<bombes){
                            resetAllB();
                            resetAll();
                          
                        }
                        else if(mb>=bombes ){
                            break;
                        }
                      
                   }  
                  

              }
          }
                                    //                                           //telos aristero klik
          
          if(e.getButton() == MouseEvent.BUTTON3) {                             //deksi klik
              mx = e.getX();
              my = e.getY();
          
            if(inBoxX() != -1 && inBoxY() != -1){  
                 
                if(flagged[inBoxX()][inBoxY()]==true){
                    flag[inBoxX()][inBoxY()]=true;   
                    
                } 
                else {
                    flag[inBoxX()][inBoxY()]=false;  
                   
                }
                flagged[inBoxX()][inBoxY()]=true;
                
            }
          }                                                                     //telos deksi klik                  
          
      
        }

       @Override
        public void mouseReleased(MouseEvent e) {
          
        }

        @Override
        public void mouseEntered(MouseEvent e) {
           
        } 

        @Override
        public void mouseExited(MouseEvent e) {
             
        }
        
    }
    
    public void CheckVictoryStatus(){
        
        if(TotalBoxesRevealed()>=orizontia/30*katheta/40-TotalMines() && defeat!=true){  // elenxos ama kerdises
           victory=true;
           defeat=false;
           for (int i=0; i<orizontia/30; i++){
               for (int j=0; j<katheta/40; j++){
                   revealed[i][j]=true;
                   resetter=true;
               }
           }

       }
        
        
        
            for (int i=0; i<orizontia/30; i++){                                 //loupa elenxou board
                if(victory==true)
                    break;
                     for(int j=0; j<katheta/40; j++){
                            if(revealed[i][j]==true && mines[i][j]==1){    // ama xtipisis bomba xanis 
                                if(sectime==0){
                                   sec=0;
                                }
                                  defeat=true;
                                  happy=false;
                                  victory=false;
                                for(int k=0; k<orizontia/30; k++){
                                   for(int m=0; m<katheta/40; m++){      //ama xasis faneroni olo to board
                                       revealed[k][m]=true;             
                                   }

                                 }  
                    }                 
                }  

            }
           
        
    }

    public int TotalMines(){                                                    //metraei poses bombes yparxoun sto board
        int total=0;
        for (int i=0; i<orizontia/30; i++){
                      for(int j=0; j<katheta/40; j++){
                       if(mines[i][j]==1) 
                           total++;                
                  }    
             }
        return total;
    }
    
    public int TotalBoxesRevealed(){                                            //metra posa koutia exoun anoiksi
        int total = 0;
        for (int i=0; i<orizontia/30; i++){
                      for(int j=0; j<katheta/40; j++){
                       if(revealed[i][j]==true) {
                           total++;
                       }                     
                  }    
             }
        return total;
    }
    
    public void resetAll(){
       
       mx=-1;
       my=-1;
       resetter=true;
       happy=true;
       victory=false;
       defeat=false;
       startDate = new Date();
       mb=0;
       sec=0;
       sectime=0;
       neig=0;
       first_click=0;
       reveal=0;

   /*    for (int i=0; i<orizontia/30; i++){
            for (int j=0; j<katheta/40; j++){
                        
                
                revealed[i][j]=false;
                flagged[i][j]=false;     
                mines[i][j]=0;
                flag[i][j]=false;
                gitones[i][j]=0;  
            }
        }  */
       
       
        if(Main.level==1){
            bombes=20;
            rrandom=80;
            bombes2=bombes;

        }
        else if(Main.level==2){
            bombes=50;
            rrandom=200;
            bombes2=bombes;

        }
        else {
            bombes=80;
            rrandom=250;
            bombes2=bombes;

        } 
        
        
        

            
            for (int i=0; i<orizontia/30; i++){
                for (int j=0; j<katheta/40; j++){

                    if(rand.nextInt(rrandom)<bombes){  
                        if (mb>=bombes){
                            break;
                        }
                        mines[i][j]=1;
                        mb++;

                    }
                    else {
                        if (mb>=bombes){
                            break;
                        }
                    mines [i][j]=0; 
                    }      
                }   
            }  
         
        
       
        for (int i=0; i<orizontia/30; i++){
                for(int j=0; j<katheta/40; j++){
                    neig=0;
                        for (int m=0; m<orizontia/30; m++){
                                 for(int n=0; n<katheta/40; n++){
                                     
                                     if(!(m==i && j==n)){
                                         if(isN(i,j,m,n) == true)
                                         neig++;
                                     }
                                     
                                }
                        }     
                        gitones[i][j]=neig;
                }  
        } 
        
        if(Main.level==1){
            bombes=20;
            bombes2=bombes; 
        }
        else if(Main.level==2){
            bombes=50;
            bombes2=bombes; 
        }
        else {
            bombes=80;
            bombes2=bombes;
        }
        
        for (int i=0; i<orizontia/30; i++){
            for (int j=0; j<katheta/40; j++){            
                revealed[i][j]=false;
            }
        }
        
        resetter=false;
        happy=true;
        victory=false;
        defeat=false;
    }
    
    public int inBoxX(){                        // sintetagmeni X mesa sta koutia
        for (int i=0; i<orizontia/30; i++){
                      for(int j=0; j<katheta/40; j++){
                        
                          if (mx >= space + i *30 && mx <i*30+34-space && my>=space+j*30+90-5  && my <space+j*30+120)
                          {
                              return i;
                          }
                          
                  }
                }
        return -1;
    }
    
    public int inBoxY(){                       // sintetagmeni Y mesa sta koutia
      for (int i=0; i<orizontia/30; i++){
                      for(int j=0; j<katheta/40; j++){
                        
                          if (mx >= space + i *30 && mx <i*30+34-space && my>=space+j*30+90  && my <space+j*30+120+5)
                          {
                              return j;
                          }
                          
                  }
                }
        return -1;
    }
    
    public boolean isN(int mx,int my,int cx, int cy){                             //metrai poses narkes yparxoun gyro sthn oxtada
        
        if(mx-cx < 2 && mx-cx >-2 && my-cy < 2 && my-cy > -2 && mines[cx][cy]==1 )
        {
            return true;
        }
        return false;
    }  
     
    void Open(int x,int y){
        
        if(gitones[x][y]==0 &&  x>=1 && x<=orizontia/30-2 && y==8){         //kato y=8          
                    for(int i=-1; i<2; i++){
                        for (int j=0; j<2; j++){
                            if(revealed[x+i][y+j]==false){
                               revealed[x+i][y+j]=true;
                               Open(x+i,y+j);
                                
                            }
                        }
                     }
                   
           }
        
        if(gitones[x][y]==0 && y>=1 && y<=katheta/40-2 && x==orizontia/30-2){         // x==orizontia/30-2          
                    for(int i=0; i<2; i++){
                        for (int j=-1; j<2; j++){
                            if(revealed[x+i][y+j]==false){
                               revealed[x+i][y+j]=true;
                               Open(x+i,y+j);
                                
                            }
                        }
                     }
                   
           }
        
        
           if(gitones[x][y]==0 && x>0 && y>0 && x<orizontia/30-1 && y<katheta/40-1){
               for(int i=-1; i<2; i++){
                   for (int  j=-1; j<2; j++){
                       if(revealed[x+i][y+j]==false && mines[x+i][y+j]==0 ){
                           
                           
                           if(x>=1 && y>=1 && x<=orizontia/30-1 && y<= katheta/40-1){
                               revealed[x+i][y+j]=true;
                           }
                           
                           //System.out.println("X="+x+" Y="+y);
                           //System.out.println("I="+i+" J="+j);
                           
                           
                           
                           if(x+i>=orizontia/30-1){
                              i=0;
                               
                           }
                           if(y+j>=katheta/40-1){
                              j=0;
                           }
                           
                           
                           Open(x+i,y+j);
                           
                   
                           
                       }
                   }
               }
           
       } 
           

           if(gitones[x][y]==0 && x==0 && y==0 ){                               //pano aristeri gonia
                    for(int i=0; i<2; i++){
                        for (int j=0; j<2; j++){
                            if(revealed[x+i][y+j]==false){
                               revealed[x+i][y+j]=true;
                              Open(x+i,y+j);
                                
                            }
                        }
                     }
                   
           }
           
           if(gitones[x][y]==0 && x==0 && y==katheta/40-1 ){                    //kato aristeri gonia
                    for(int i=0; i<2; i++){
                        for (int j=-1; j<1; j++){
                            if(revealed[x+i][y+j]==false){
                               revealed[x+i][y+j]=true;
                              Open(x+i,y+j);
                                
                            }
                        }
                     }
                   
           }
           
           if(gitones[x][y]==0 && x==orizontia/30-1 && y==0 ){                    // pano deksia gonia
                    for(int i=-1; i<1; i++){
                        for (int j=0; j<2; j++){
                            if(revealed[x+i][y+j]==false){
                               revealed[x+i][y+j]=true;
                               Open(x+i,y+j);
                                
                            }
                        }
                     }
                   
           }
           
           
           if(gitones[x][y]==0 && x==orizontia/30-1 && y==katheta/40-1 ){         //kato deksia gonia           
                    for(int i=-1; i<1; i++){
                        for (int j=-1; j<1; j++){
                            if(revealed[x+i][y+j]==false){
                               revealed[x+i][y+j]=true;
                               Open(x+i,y+j);
                                
                            }
                        }
                     }
                   
           }
           
           if(gitones[x][y]==0 && y>=1 && y<=katheta/40-2 && x==0){         //aristero tixoma           
                    for(int i=0; i<2; i++){
                        for (int j=-1; j<2; j++){
                            if(revealed[x+i][y+j]==false){
                               revealed[x+i][y+j]=true;
                               Open(x+i,y+j);
                                
                            }
                        }
                     }
                   
           }
           
           if(gitones[x][y]==0 && y>=1 && y<=katheta/40-2 && x==orizontia/30-1){         //deksio tixoma           
                    for(int i=-1; i<1; i++){
                        for (int j=-1; j<2; j++){
                            if(revealed[x+i][y+j]==false){
                               revealed[x+i][y+j]=true;
                               Open(x+i,y+j);
                                
                            }
                        }
                     }
                   
           }
       
           if(gitones[x][y]==0 && y>=1 && y<=katheta/40-2 && x==orizontia/30-1){         //deksio tixoma v2           
                    for(int i=-1; i<1; i++){
                        for (int j=0; j<2; j++){
                            if(revealed[x+i][y+j]==false){
                               revealed[x+i][y+j]=true;
                               Open(x+i,y+j);
                                
                            }
                        }
                     }
                   
           }
           
           if(gitones[x][y]==0 && x>=1 && y==0 && x<=orizontia/30-2){         //pano tixoma          
                    for(int i=-1; i<2; i++){
                        for (int j=0; j<2; j++){
                            if(revealed[x+i][y+j]==false){
                               revealed[x+i][y+j]=true;
                               Open(x+i,y+j);
                                
                            }
                        }
                     }
                   
           }
           
           if(gitones[x][y]==0 && y==9 && x>=1 && x<=orizontia/30-2){         //kato tixoma          
                    for(int i=-1; i<2; i++){
                        for (int j=-1; j<1; j++){
                            if(revealed[x+i][y+j]==false){
                               revealed[x+i][y+j]=true;
                               Open(x+i,y+j);
                                
                            }
                        }
                     }
                   
           }
           
           
    }
    
    void resetAllB(){
        for (int i=0; i<orizontia/30; i++){
            for (int j=0; j<katheta/40; j++){
               revealed[i][j]=false;
                flagged[i][j]=false;     
                mines[i][j]=0;
                flag[i][j]=false;
                gitones[i][j]=0;
            }
        }
    }
 
    class GI extends JFrame{
                    
               
                        //this.setLayout(null);
                    /*    
                        String fileName = "C:\\MineSweeper\\Score.txt";

                        textArea = new JTextArea(5, 20);
                        add(textArea);
                        textArea.setEditable(false);
                       createTextArea(200, 200, 390, 10);
                        // This will reference one line at a time
                        String line = null;

                        try {
                            // FileReader reads text files in the default encoding.
                            FileReader fileReader = 
                                new FileReader(fileName);

                            // Always wrap FileReader in BufferedReader.
                            BufferedReader bufferedReader = 
                                new BufferedReader(fileReader);

                            while((line = bufferedReader.readLine()) != null) {
                                System.out.println(line);
                            }   

                            // Always close files.
                            bufferedReader.close();         
                        }
                        catch(FileNotFoundException ex) {
                            System.out.println(
                                "Unable to open file '" + 
                                fileName + "'");                
                        }       catch (IOException ex) {
                                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                                }
                       */
                   }

        
                   
                    
                
    
}
    
