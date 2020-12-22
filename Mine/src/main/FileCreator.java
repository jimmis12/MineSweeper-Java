package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class FileCreator {
    
    FileCreator(){     
}
    void file() throws IOException{
        File file = new File("C:\\MineSweeper");
                if(!file.exists())
                file.mkdirs();
                FileWriter writer = new FileWriter("C:\\MineSweeper\\Score.txt", true);            
    }
    
}
