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
import java.io.FileReader;
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
import java.util.StringTokenizer;
/**
 *
 * @author C22701
 */
public class LOCK_STPI_PROC 
{
    public LOCK_STPI_PROC()
    {
        
    }
    
    String path = "C:\\Users\\c22701\\Documents\\NetBeansProjects\\EclipseFinalProject\\src\\Outputfiles\\SeperatedFiles\\LOCK_STPI_PROC.txt";
    ArrayList<String> a = new ArrayList<String>();
    File f = new File(path);
    String out = "C:\\Users\\c22701\\Documents\\NetBeansProjects\\EclipseFinalProject\\src\\Outputfiles\\SeperatedFiles\\TotalConvertedFiles.txt";
    int startingpoint = 0;
    int endingpoint = 0;
    public void openFile() throws IOException
    {
        try
        {
            FileWriter fw = new FileWriter(out,true);
            Scanner s = new Scanner(f).useDelimiter("\\n");
        
            while(s.hasNext())
            {
                a.add(s.next().trim());
            }
            s.close();
            
            for(int i = 0; i < a.size(); i++)
            {
                System.out.println(a.get(i));
                
                if(a.get(i).contains("MOVE SPACES")|| a.get(i).contains("ENTER MASM"))
                {
                    a.remove(i);
                }
                
                if(a.get(i).contains("ERROR-STATUS"))
                {
                    a.remove(i);
                }
                
                if(a.get(i).contains("STRING"))
                {
                    startingpoint = i;
                    a.set(i, "EXEC SQL");
                }
                
                if(a.get(i).contains("DELIMITED"))
                {
                    endingpoint = i;
                    a.set(i, "END-EXEC");
                }
                
                fw.write(a.get(i));
                fw.write(System.lineSeparator());
            }
            
            List<String> sl = a.subList(startingpoint, endingpoint+1);
            System.out.println();
            System.out.println("Inside Sublist...!");
            for(int i = 0; i < sl.size(); i++)
            {
                System.out.println(sl.get(i));
                String str = sl.get(i);
                
                if(str.contains("'"))
                {
                    str = str.replaceAll("'","");
                    sl.set(i, str.trim());
                }
                
                if(str.contains(";"))
                {
                    str = str.replaceAll(";", "");
                    sl.set(i, str.trim());
                }
                
                if(str.contains(","))
                {
                    str = str.replaceAll(",", "");
                    sl.set(i, str.trim());
                }
            }
            
            for(int i = 0; i < sl.size(); i++)
            {
                System.out.println(sl.get(i));
            }
            
            for(int i = 0; i < a.size(); i++)
            {
                System.out.println(a.get(i));
            }
            fw.close();
        }
       catch (FileNotFoundException e) 
       {
           e.printStackTrace();
       } 
       catch (IOException e)
       {
           e.printStackTrace();
       } 
    }
    
    public static void main(String[] args) throws IOException
    {
        LOCK_STPI_PROC lsp = new LOCK_STPI_PROC();
        lsp.openFile();
    }
}
