package bitcoinGUI;
import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
public class WriteToFile {
    public static void WriteScoreFile( ){
        try{
            FileWriter write = new FileWriter("scoreFile.txt");
            write.write("Player Name ");
            write.close();
            JOptionPane.showMessageDialog(null, "Successfully wrote to the File.", "Status of writing", JOptionPane.INFORMATION_MESSAGE);
        }catch (IOException e){
            JOptionPane.showMessageDialog(null, "An error occured.");
            e.printStackTrace();

        }

    }

}
