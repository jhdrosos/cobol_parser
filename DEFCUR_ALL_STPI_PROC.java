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
public class DEFCUR_ALL_STPI_PROC 
{
    String input = "C:\\Users\\c22701\\Documents\\NetBeansProjects\\EclipseFinalProject\\src\\Outputfiles\\SeperatedFiles\\proc_6.txt";
    String output = "C:\\Users\\c22701\\Documents\\NetBeansProjects\\EclipseFinalProject\\src\\Outputfiles\\SeperatedFiles\\TotalConvertedFiles.txt";
    File f = new File(input);
    ArrayList<String> al = new ArrayList<String>();
    
    public void openFile() throws IOException
    {
        Scanner scan = new Scanner(f).useDelimiter("\\n");
        FileWriter fw  = new FileWriter(output);
        
        while(scan.hasNext())
        {
            al.add(scan.next().trim());
        }
        
        for(int i = 0; i < al.size(); i++)
        {
            System.out.println(al.get(i));
        }
        scan.close();
        
        for(int i = 0; i < al.size(); i++)
        {
            if(al.get(i).contains("STPIC1"))
            {
                System.out.println("to vrika sti grammi " +  i);
            }
           
            if(al.get(i).contains("MOVE 'R'"))
            {
                System.out.println("to vrika sti grammi " + i);
                al.remove(i+1);
            }
            
            if(al.get(i).contains("MOVE SPACES"))
            {
                System.out.println("to vrika sti grammi " + i);
                al.remove(i);
                al.set(i+1, "EXEC SQL");
            }
            
            if(al.get(i).contains("ENTER MASM"))
            {
                System.out.println("to vrika sti grammi " + i);
                al.remove(i);
            }
            
            if(al.get(i).contains("ERROR-STATUS-RDMS"))
            {
                System.out.println("to vrika sti grammi " + i);
                al.set(i, "END-EXEC.");
            }
        }
        
        for(int i = 0; i < al.size(); i++)
        {
            if(al.get(i).startsWith("'"))
            {
                //System.out.println(al.get(i).substring(1, al.get(i).length()));
                String str = al.get(i);
                System.out.println("to vrika sti grammi " + str);
                al.set(i, str.substring(1, str.length()));
            }
            
            if(al.get(i).endsWith("'"))
            {
                String str = al.get(i);
                al.set(i, str.substring(0, str.length()-1)).trim();
            }
            else if(al.get(i).endsWith("  ',"))
            {
                String str = al.get(i);
                al.set(i, str.substring(0, str.length()-3).trim());
            }
            
            else if(al.get(i).endsWith("  '"))
            {
                String str = al.get(i);
                al.set(i, str.substring(0,str.length()-2)).trim();
            }

            
            if(al.get(i).contains(";"))
            {
                String str = al.get(i);
                int s = str.length();
                System.out.println("mege8os string " + s);
                //al.set(i,str.substring(0, str.length()-4));
            }
            
            if(al.get(i).contains("; "))
            {
                String str = al.get(i);
                al.set(i, str.substring(0, str.length()-2));
            }
        }
        
        System.out.println("Η λίστα μετά τις αλλαγές");
        System.out.println();
        for(int i = 0; i < al.size(); i++)
        {
            System.out.println(al.get(i));
        }
         
        for(int i = 0; i < al.size(); i++)
        {
            if(i==6)
            {
                String str = al.get(i);
                System.out.println(str);
                al.set(i, "EXEC SQL");
            }
        }
        
        for(int i = 0; i < al.size(); i++)
        {
            System.out.println(al.get(i));
        }
    }
    
    
    public static void main(String[] args) throws IOException
    {
        DEFCUR_ALL_STPI_PROC def = new DEFCUR_ALL_STPI_PROC();
        def.openFile();
    }
}
