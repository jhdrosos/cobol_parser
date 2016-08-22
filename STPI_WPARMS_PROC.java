/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eclipsefinalproject;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.*;

public class STPI_WPARMS_PROC {

    public String stpi = "C:\\Users\\c22701\\Documents\\NetBeansProjects\\EclipseFinalProject\\src\\Outputfiles\\SeperatedFiles\\STPI_WPARMS_PROC.txt";
    String fout = "C:\\Users\\c22701\\Documents\\NetBeansProjects\\EclipseFinalProject\\src\\Outputfiles\\SeperatedFiles\\TotalConvertedFiles.txt";
    ArrayList<String> l = new ArrayList<String>();
    File fread = new File(stpi);

    public void readFile() {
        try 
        {
            FileWriter fwrite = new FileWriter(fout, true);
            Scanner scanner = new Scanner(fread).useDelimiter("\\n");
            while (scanner.hasNext())
            {
                l.add(scanner.next());
            }
            scanner.close();
        
            for(int i = 0; i < l.size(); i++)
            { 
                if(l.get(i).contains("      ***          WORKING STORAGE VARIABLES              ***   "))
                {
                    l.add(i+1, "           EXEC SQL BEGIN DECLARE SECTION END-EXEC.   ");
                }
                if(l.get(i).contains("           02  W-STPI-OBS-END-DT         PIC  9(08)."))
                {
                    l.add(i+1, "           EXEC SQL END DECLARE SECTION END-EXEC.   ");
                }
                fwrite.write(l.get(i));
                fwrite.write(System.lineSeparator());
            }
            fwrite.close();
            
        } 
        catch (FileNotFoundException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        catch (IOException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
