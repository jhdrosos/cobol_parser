/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

public class DEL_STPI_PROC {

    public String stpi = "C:\\Users\\c22701\\Documents\\NetBeansProjects\\EclipseFinalProject\\src\\Outputfiles\\SeperatedFiles\\DEL_STPI_PROC.txt";
    String fout = "C:\\Users\\c22701\\Documents\\NetBeansProjects\\EclipseFinalProject\\src\\Outputfiles\\SeperatedFiles\\TotalConvertedFiles.txt";
    ArrayList<String> al = new ArrayList<String>();
    File fread = new File(stpi);
    int startingpoint = 0;
    int endingpoint = 0;
    
    public void readFile() 
    {
        try 
        {
            //FileWriter fwrite = new FileWriter(fout, true);
            Scanner scanner = new Scanner(fread).useDelimiter("\\n");
            while (scanner.hasNext())
            {
                al.add(scanner.next().trim());
            }
            scanner.close();
        
            for(int i = 0; i <al.size(); i++)
            { 
                System.out.println(al.get(i));
                //fwrite.write(l.get(i));
                //fwrite.write(System.lineSeparator());
            }
            //fwrite.close();
            
            for(int i = 0; i < al.size(); i++)
            {
                if(al.get(i).contains("DELIMITED BY SIZE INTO COMAND."))
                {
                    System.out.println("to vrika");
                    al.set(i, "END-EXEC.");
                    endingpoint = i;
                }
                
                if(al.get(i).contains("STRING"))
                {
                    al.set(i,"EXEC SQL");
                    startingpoint = i;
                }
                
                if(al.get(i).contains("ENTER MASM"))
                {
                    al.remove(i);
                }
                
                if(al.get(i).contains("ERROR-STATUS-RDMS"))
                {
                    al.remove(i);
                }
            }
            List<String> sl = al.subList(startingpoint, endingpoint+1);
            
            for(int j = 0; j < sl.size(); j++)
            {
                System.out.println(sl.get(j));
                if(sl.get(j).contains("'"))
                {
                    String str = sl.get(j); 
                    str = str.replaceAll("'", "");
                    sl.set(j, str.trim());
                }
                
                if(sl.get(j).contains(";"))
                {
                    String str = sl.get(j);
                    str = str.replaceAll(";", "");
                    sl.set(j, str.trim());
                }
                
                if(sl.get(j).contains(","))
                {
                    String str = sl.get(j);
                    str = str.replaceAll(",", "");
                    sl.set(j, str.trim());
                }
                
                if(sl.get(j).contains("QUO"))
                {
                    String str = sl.get(j);
                    str = str.replace("QUO","");
                    sl.set(j, str.trim());  
                }
                
                if(sl.get(j).contains("W-"))
                {
                    String str = sl.get(j);
                    str = str.replaceAll("W-", ":W-");
                    sl.set(j, str.trim());
                }
            }
            
            for(int j=0; j<sl.size(); j++)
            {
                System.out.println(sl.get(j));
            }
            
            for(int i = 0; i < al.size(); i++)
            {
                System.out.println(al.get(i));
            }
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
    
    public static void main(String[] args)
    {
        DEL_STPI_PROC dsp = new DEL_STPI_PROC();
        dsp.readFile();
    }
}
