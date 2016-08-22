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

public class STPI_TPARMS_PROC 

{
	public String path = "C:\\Users\\c22701\\Documents\\NetBeansProjects\\EclipseFinalProject\\src\\Outputfiles\\SeperatedFiles\\STPI_TPARMS_PROC.txt";
	ArrayList<String> al = new ArrayList<String>();
	File readf  = new File(path);
	String out = "C:\\Users\\c22701\\Documents\\NetBeansProjects\\EclipseFinalProject\\src\\Outputfiles\\SeperatedFiles\\TotalConvertedFiles.txt";
	
	public STPI_TPARMS_PROC()
	{
		
	}
	
	public void openFile()
	{
		try 
		{
			FileWriter fw = new FileWriter(out, true);
			Scanner scan = new Scanner(readf).useDelimiter("\\n");
			while(scan.hasNext())
			{
				al.add(scan.next());
			}
			scan.close();
			for(int i = 0; i < al.size(); i++)
			{
				if(al.get(i).contains("      ***          WORKING STORAGE VARIABLES              ***   "))
				{
					al.add(i+1, "           EXEC SQL BEGIN DECLARE SECTION END-EXEC.   ");
				}
				if(al.get(i).contains("           02  K-STPI-OBS-END-DT         PIC  9(08)."))
				{
					al.add(i+1, "           EXEC SQL END DECLARE SECTION END-EXEC.   ");
				}
				fw.write(al.get(i));
				fw.write(System.lineSeparator());
			}
                        fw.close();

		}
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
