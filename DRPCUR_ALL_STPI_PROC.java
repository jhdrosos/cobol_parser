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
/**
 *
 * @author C22701
 */
public class DRPCUR_ALL_STPI_PROC 
{
    public DRPCUR_ALL_STPI_PROC()
    {}
    
    String str = "C:\\Users\\c22701\\Documents\\NetBeansProjects\\EclipseFinalProject\\src\\Outputfiles\\SeperatedFiles\\DRPCUR_ALL_STPI_PROC.txt";;
    File f = new File(str);
    String out =  "C:\\Users\\c22701\\Documents\\NetBeansProjects\\EclipseFinalProject\\src\\Outputfiles\\SeperatedFiles\\TotalConvertedFiles.txt";
    ArrayList<String> al = new ArrayList<String>();
    int startingpoint=0;
    int endingpoint = 0;
    
    public void openFile() throws IOException
    {
        try
        {
            FileWriter fw = new FileWriter(out, true);
            Scanner scan = new Scanner(f).useDelimiter("\\n");
            
            while(scan.hasNext())
            {
                al.add(scan.next().trim());
            }
            scan.close();
            
            for(int i = 0; i < al.size(); i++)
            {
                if(al.get(i).contains("MOVE 'R'"))
                {
                    System.out.println("To vrika: " + al.get(i));
                    
                    al.remove(i);
                }
                
                if(al.get(i).contains("ENTER MASM "))
                {
                    System.out.println("To vrika " + al.get(i));
                    al.set(i, "EXEC SQL");
                }
                
                if(al.get(i).contains("ERROR-STATUS"))
                {
                    System.out.println("To vrika " + al.get(i));
                    al.set(i, "END-EXEC.");
                }
                
            }
            
            
            for(int i = 0; i < al.size(); i++)
            {
                System.out.println(al.get(i));
                if(al.get(i).contains("EXEC SQL"))
                {
                    startingpoint = i;
                    System.out.println("In line: " + i);
                }
                if(al.get(i).contains("END-EXEC."))
                {
                    endingpoint = i;
                    System.out.println("In line: " + i);
                }
            }
            
            List<String> sl = al.subList(startingpoint, endingpoint+1);
            
            
            System.out.println("Sublist");
            for(int i = 0; i < sl.size(); i++)
            {
                if(sl.get(i).startsWith("'"))
                {
                    String str = sl.get(i);
                    sl.set(i, str.substring(1,str.length()));
                }
                if(sl.get(i).endsWith("'"))
                {   
                    String str = sl.get(i);
                    sl.set(i, str.substring(0,str.length()-1));
                }
                if(sl.get(i).endsWith("' "))
                {   
                    String str = sl.get(i);
                    sl.set(i, str.substring(0,str.length()-2));
                }
                if(sl.get(i).endsWith(";  "))
                {   
                    String str = sl.get(i);
                    sl.set(i, str.substring(0,str.length()-3));
                }    
            }
            
            for(int i = 0; i < sl.size(); i ++)
            {
                System.out.println(sl.get(i));
            }
            
            for(int i = 0; i < al.size(); i++)
            {
                System.out.println(al.get(i));
            }
        }
        catch(FileNotFoundException fnfe)
        {
            fnfe.printStackTrace();
        }
            
    }
    
    
    public static void main(String[] args) throws IOException
    {
        DRPCUR_ALL_STPI_PROC dasp = new DRPCUR_ALL_STPI_PROC();
        dasp.openFile();
    }
}
