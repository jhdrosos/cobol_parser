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

public class INS_STPI_PROC 
{   
    public INS_STPI_PROC()
    {}
    String str = "C:\\Users\\c22701\\Documents\\NetBeansProjects\\EclipseFinalProject\\src\\Outputfiles\\SeperatedFiles\\INS_STPI_PROC.txt";
    File f = new File(str);
    String out =  "C:\\Users\\c22701\\Documents\\NetBeansProjects\\EclipseFinalProject\\src\\Outputfiles\\SeperatedFiles\\TotalConvertedFiles.txt";
    ArrayList<String> arl = new ArrayList<String>();
    int startdelete =0;
    int enddelete = 0;
    Pattern pat;
    Matcher m;
    String pattern = "[a-zA-Z]+[.][T][_][a-zA-Z]+";
    String tab_name="";
    public void openFile() throws IOException
    {
        try
        {
            FileWriter fw = new FileWriter(out, true);
            Scanner scan = new Scanner(f).useDelimiter("\\n");   
            while(scan.hasNext())
            {
                arl.add(scan.next().trim());
            }
            scan.close(); 
            pat = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);
            for(int i = 0; i < arl.size(); i++)
            {
               Matcher m = pat.matcher(arl.get(i));
               
               if(m.find())
               {
                   System.out.println(pattern + " start: " +  m.start() + " end: " + m.end());
                   tab_name = arl.get(i).substring(m.start(), m.end());
               }
            } 
            System.out.println(tab_name);
            for(int i = 0; i < arl.size(); i++)
            {
                System.out.println(arl.get(i));
            }         
            for(int i = 0; i < arl.size(); i++)
            {
                if(arl.get(i).contains("MOVE SPACES"))
                {
                    arl.remove(i);
                }              
                if(arl.get(i).contains("ENTER MASM"))
                {
                    arl.remove(i); 
                }               
                if(arl.get(i).contains("ERROR-STATUS-RDMS"))
                {
                    arl.remove(i);
                }                
                if(arl.get(i).contains("STRING"))
                {
                    arl.remove(i);
                }
                if(arl.get(i).contains("MOVE 'R'"))
                {
                    arl.set(i+1, "EXEC SQL");
                }                
                if(arl.get(i).startsWith("'"))
                {
                    String str = arl.get(i);
                    str = str.substring(1,str.length());
                    arl.set(i, str.trim());
                }
                
                if(arl.get(i).endsWith("',") || arl.get(i).endsWith(";'"))
                {
                    String str = arl.get(i);
                    str = str.substring(0,str.length()-2);
                    arl.set(i, str.trim());
                }  
                if(arl.get(i).contains("INSERT INTO " + tab_name))
                {
                    startdelete = i;
                }
                
                if(arl.get(i).contains("DELIMITED"))
                {
                    enddelete = i;
                }
            } 

            List<String> dsl = arl.subList(startdelete,enddelete+1);
            
            for(int i = 0; i < dsl.size(); i++)
            {
                if(i<dsl.size())
                {
                    dsl.removeAll(dsl);
                }
            }
            
            for(int i = 0; i < dsl.size(); i++)
            {
                System.out.println(dsl.get(i));
            }
            
            for(int i = 0; i < arl.size(); i++)
            {
                //System.out.println(arl.get(i));
                
                if(arl.get(i).contains("EXEC SQL"))
                {
                    arl.set(i+1, "INSERT INTO "+ tab_name);
                    arl.set(i+2, "VALUES (");
                }
                
                if(arl.get(i).contains("K-"))
                {
                    String str = arl.get(i);
                    str = str.replaceAll("K-", ":K-");
                    arl.set(i,str.trim());
                }  
                
                if(arl.get(i).endsWith("."))
                {
                    String str = arl.get(i);
                    str = str.replace(".", ",");
                    arl.set(i, str.trim());
                }
            }

            for(int i = 0; i < arl.size(); i++)
            {
                System.out.println(arl.get(i));
            }
            
            boolean found = true;
            int last_var =0;
            while(found)
            {
                for(int i = 0; i < arl.size(); i++)
                {
                    if(arl.get(i).contains(":K-"))
                    {
                        last_var = i;
                    }
                    found=false;
                }
                System.out.println("The last variable is in line: " + last_var);
            }
            String last_line = arl.get(last_var);
            last_line = last_line.substring(0, last_line.length()-1);
            last_line = last_line.concat(". )");
            arl.set(last_var, last_line.trim());
            arl.add(last_var+1, "END-EXEC.");
            for(int i = 0; i < arl.size(); i++)
            {
                System.out.println(arl.get(i));
            }
        }
        
        catch(FileNotFoundException fnfe)
        {
            fnfe.printStackTrace();
        }    
    }
    public static void main(String[] args) throws IOException
    {
        INS_STPI_PROC isp = new INS_STPI_PROC();
        isp.openFile();
    }
}
