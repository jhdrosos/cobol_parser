package eclipsefinalproject;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author C22701
 */
import java.io.File;
import java.io.IOException;
import java.lang.IllegalStateException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.FileWriter;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ReadTextFile_test {

    private static Scanner input;
    private static Formatter output;
    private static List<String> line = new ArrayList<String>();
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String proc_start = "";
        String proc_end ="";
        openFile();
        readRecords();
        addRecords();
        //closeFile();

    }
    /* Anoigei to arxeio */
    public static void openFile() {
        try 
        {
            String path = "C:\\Users\\john_\\Documents\\NetBeansProjects\\Cobol\\src\\cobol\\sep_procs1.txt";
            input
                  = new Scanner(Paths.get(path)).useDelimiter("\\n");
            System.out.println("File is opened");
        } 
        catch (IOException ioex) 
        {
            System.out.println(ioex.toString());
            System.err.println("Error opening file. Terminating.");
            System.exit(1);
        }
    }
  
    public static void readRecords() throws IOException 
    {
        try 
        {
            System.out.println("Ξ�ΞΉ Ξ³Ο�Ξ±ΞΌΞΌΞ­Ο‚ Ο€ΞΏΟ… Ξ΄ΞΉΞ±Ξ²Ξ¬Ξ¶Ο‰ Ξ±Ο€ΞΏ Ο„ΞΏ Ξ±Ο�Ο‡ΞΉΞΊΟ� Ξ±Ο�Ο‡ΞµΞ―ΞΏ... ");
            while (input.hasNext()) 
            {
                line.add(input.next());
            }
            for(int i = 0; i<line.size(); i++)
            {
                System.out.println(i + " : " + line.get(i));
            }
            
            String out_file = "C:\\Users\\john_\\Documents\\NetBeansProjects\\Cobol\\src\\cobol\\sep_procs_out.txt";
            FileWriter out = new FileWriter(out_file, true);
            /* Diagrafei tis grammes tis proc pou den yparxoyn sti nea version */
            System.out.println("");
            System.out.println("Ξ‘Ο‚ Ξ΄ΞΉΞ±Ξ³Ο�Ξ¬Ο�ΞΏΟ…ΞΌΞµ ΞΌΞµΟ�ΞΉΞΊΞ­Ο‚ Ξ³Ο�Ξ±ΞΌΞΌΞ­Ο‚...");
            //for(int i =  0; i<line.size(); i++)
            for(Iterator<String> it = line.iterator(); it.hasNext();)
            {
                String s = it.next();
                /*if(line.get(i).equals("MOVE SPACES     TO COMAND.")||
                        line.get(i).equals("STRING")||
                        line.get(i).equals("DELIMITED BY SIZE INTO COMAND.")||
                        line.get(i).equals("ENTER MASM 'ACOB$RDMR' USING COMAND,")||
                        line.get(i).equals("ERROR-STATUS-RDMS, C-RDMS-AUXINFO.")
                   )*/
                if(s.equals("MOVE SPACES     TO COMAND.")
                        ||s.equals("ENTER MASM 'ACOB$RDMR' USING COMAND,")
                        ||s.equals("ERROR-STATUS-RDMS, C-RDMS-AUXINFO.")
                        )
                {
                    it.remove();
                    
                }
                else if(s.equals("PROC"))
                {
                    System.out.println("Ξ’Ο�Ξ®ΞΊΞµΟ‚ Ο„Ξ·Ξ½ ΟƒΟ‰ΟƒΟ„Ξ® ΞµΞ³Ξ³Ο�Ξ±Ο†Ξ® ΟƒΟ„Ξ· ΞΈΞ­ΟƒΞ·: ");
                }
                else 
                {
                    System.out.println(s);
                }
            }
            System.out.println("Ξ“ΞΉΞ± Ξ½Ξ± Ξ΄ΞΏΟ�ΞΌΞµ Ο„ΞΉ ΞµΞΌΟ†Ξ±Ξ½Ξ―Ξ¶ΞµΞΉ Ξ· Ξ»Ξ―ΟƒΟ„Ξ± ΞΌΞµΟ„Ξ¬ Ξ±Ο€ΞΏ Ο„ΞΉΟ‚ Ξ΄ΞΉΞ±Ξ³Ο�Ξ±Ο†Ξ­Ο‚");
            for(int p = 0; p<line.size(); p++)
            {
                System.out.println(line.get(p));
            }
            
            System.out.println("Ξ‘Ο‚ Ξ±Ξ»Ξ»Ξ¬ΞΎΞΏΟ…ΞΌΞµ Ο„ΞΉΟ‚ ΞµΞ³Ξ³Ο�Ξ±Ο†Ξ­Ο‚...");
            
            for(int o = 0; o<line.size(); o++)
            {
                if(line.get(o).equals("STRING"))
                    line.set(o, "EXEC SQL");
                else if(line.get(o).equals("DELIMITED BY SIZE INTO COMAND."))
                {
                     line.set(o, "END-EXEC");
                }    
            }
            
            System.out.println("Ξ‘Ο‚ Ξ΄ΞΏΟ�ΞΌΞµ Ξ±Ξ½ Ξ±Ξ»Ξ»Ξ¬ΞΎΞ±ΞΌΞµ Ο„ΞΉΟ‚ ΞµΞ³Ξ³Ο�Ξ±Ο†Ξ­Ο‚...");
                    
            for(int k = 0; k<line.size(); k++)
            {
                System.out.println("index: " + k + " values: " + line.get(k));
                out.write(line.get(k));
                out.write(System.getProperty("line.separator"));
            }
                    
            out.close();
            
           File f = new File(out_file);  
           
           if(f.exists())
           {
               System.out.println("Ξ¤ΞΏ Ξ±Ο�Ο‡ΞµΞ―ΞΏ Ξ­Ο‡ΞµΞΉ Ξ΄Ξ·ΞΌΞΉΞΏΟ…Ο�Ξ³Ξ·ΞΈΞµΞ―...");
           }
           else
           {
               System.out.println("Ξ¤ΞΏ Ξ±Ο�Ο‡ΞµΞ―ΞΏ Ξ΄ΞµΞ½ Ο…Ο€Ξ¬Ο�Ο‡ΞµΞΉ. Ξ”ΞµΟ‚ ΞΎΞ±Ξ½Ξ¬ Ο„ΞΏΞ½ ΞΊΟ�Ξ΄ΞΉΞΊΞ±...!!!");
           }
        } 
        catch (NoSuchElementException nsex) 
        {
            System.err.println("File improperly formed. Terminating");
        } 
        catch (IllegalStateException ilex) 
        {
            System.err.println("Error reading from file. Terminating");
        }
    }
    
    /* Pros8etei grammes sto arxeio */
    public static void addRecords() 
    {
        
        while (input.hasNext()) {
            try {
                output.format(input.next());
            } catch (FormatterClosedException fce) {
                System.err.println("Invalid input. Terminating");
            }
        }
    }
    
    /* Kleinei to arxeio */
    public static void closeFile() 
    {
        if(1==1)
            ;
    }
    
    public void ValidateProcname(String proc)
    {
    //return proc.matches("[a-zA-Z]*[-][a-zA-Z]* PROC");
      while(input.hasNext())
      {
          
      }  
    }
    
}




