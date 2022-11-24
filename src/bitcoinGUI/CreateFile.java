package bitcoinGUI;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
public class CreateFile {
    public static void createScoreFile(){
        try{
            File scoreFile = new File("scoreFile.txt");
            if(scoreFile.createNewFile()){
                JOptionPane.showMessageDialog(null, "File created: " + scoreFile.getName(), "File Status", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "File already exist.", "File Status", JOptionPane.INFORMATION_MESSAGE);
            }
        }catch (IOException e){
            JOptionPane.showMessageDialog(null, "An error occured.", "File Status", JOptionPane.INFORMATION_MESSAGE);
            e.printStackTrace();
        }
    }
}
