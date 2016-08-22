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
/**
 *
 * @author john_
 */
public class Seperate_Procs {

    private static String sp = "C:\\Users\\c22701\\Documents\\NetBeansProjects\\EclipseFinalProject\\src\\InputFiles\\SourceFiles\\sep_procs.txt";
    //private static String spout = "C:\\Users\\c22701\\Documents\\NetBeansProjects\\Cobol\\src\\sep_procs_out.txt";
    //private static String log = "C:\\Users\\c22701\\Documents\\NetBeansProjects\\Cobol\\src\\log_out.txt";
    private static File f = new File(sp);
    //private static File sf = new File(log);
    private static Scanner scan;
    private static Scanner scan1;
    private static List<String> al = new ArrayList<String>();
    //private static FileWriter fout;
    private static Pattern pattern; 
    private static Matcher matcher;
    private static Iterator<String> it;
    private static int end = 0;
    private static List<String> li = new ArrayList<String>();
    
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            readFile();
        } catch (IOException ex) {
            Logger.getLogger(Seperate_Procs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * @throws IOException
     */
    private static void readFile() throws IOException 
    {
        try 
        {
            // Με αυτο θα κρατάω πληροφορία στο Log αρχείο
            //fout = new FileWriter(log);
            scan = new Scanner(f).useDelimiter("\\n");
            scan1 = new Scanner(f).useDelimiter("\\n");
            while (scan.hasNext()) 
            {
                al.add(scan.next());
            }
            scan.close();
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }
        System.out.println("Ας διαβάσουμε τη λίστα...");
        for(int i = 0; i<al.size(); i++)
        {  
            //System.out.println("Index: " + i + " Value: " + al.get(i));
            System.out.println(al.get(i));
        }
        System.out.println("Ας βρούμε πόσα αρχεία θα δημιουργήσουμε...");
        for(int j = 0; j<al.size(); j++)
        {
            //fout.flush();
            if(al.get(j).contains(" END"))
            {
                System.out.println("Βρήκες την σωστή εγγραφή στη θέση: " + j);
                System.out.println("Και η τιμή της είναι: " + al.get(j));
                end = end + 1;
            }
            else
            {
                System.out.println("Άλλαξε το λεκτικό που ψάχνεις. Δεν υπάρχει στη θέση: " + j);
                System.out.println("Index: " + j + " Value: " + al.get(j));
            }
            System.out.println("Θα πρέπει να δημιουργήσουμε " + end + " αρχεία");
        }
        //fout.write("θα δημιουργήσουμε " + end + " αρχεία.");
        //fout.close();
        
        //Δημιουργία πίνακα που θα αποθηκεύει το Path των αρχείων
        String[] paths = new String[end];
        
        //Ας επιβεβαιώσουμε οτι ο πίνακας έχει πάρει τις σωστές θέσεις
        for(int p = 0; p < paths.length; p++)
        {
            System.out.println("Θέση με αριθμό: " +p);
            //fout.write("Θέση με αριθμό: " +p);
            //fout.write(System.getProperty("line.seperator"));
        }
        //fout.close();
        //Δημιουργία αρχείων μέσα απο τον πίνακα Paths
        for(int i = 0; i < paths.length; i++)
        {
            File f = new File("C:\\Users\\c22701\\Documents\\NetBeansProjects\\EclipseFinalProject\\src\\Outputfiles\\SeperatedFiles\\proc_"+i +".txt"); 
            f.createNewFile();
            String name = f.getAbsolutePath();
            paths[i] = name;
        }
        //Ας επιβεβαιώσουμε οτι κρατήσαμε την σωστή πληροφορία
        for(int j = 0; j < paths.length; j++)
        {
            System.out.println("Το directory είναι το εξής: " + paths[j]);
        }
        System.out.println("");
        for(int h = 0; h < al.size(); h++)
        {
           li.add(al.get(h));
        }
        System.out.println("Ας προσπαθήσουμε να χωρίσουμε τις procs ανα αρχείο");
        for(int k = 0; k < paths.length; k++)
        { 
          System.out.println(k + "->" + paths[k]);
          File f = new File(paths[k]);
          FileWriter fw = new FileWriter(f.getPath());
          //System.out.println("Canonical Path " + f.getCanonicalPath());
          //System.out.println("Absolute Path  " + f.getAbsolutePath());
          //System.out.println("Get Path       " + f.getPath());
            while(scan1.hasNext())
            {
               String line = scan1.next();
               fw.write(line);
               fw.write(System.lineSeparator());
               if(!line.contains(" END"))
               {
                   System.out.println("Paths: " + k + " " +paths[k] +" " + line);
               }
               else
               {
                   System.out.println("Paths: " + k + " " +paths[k] +" END");
                   fw.close();
                   break;
               }
            }continue;
        }
        System.out.println();
        
        String pat = "[a-zA-Z]+[-]+[a-zA-Z]+[-]*[a-zA-Z]*[pPa-zA-Z]*";
        Pattern p = Pattern.compile(pat);
        
        /*
        for(int i = 0; i < paths.length; i++)
        {
        	System.out.println("Arxeio " + paths[i]);
        	System.out.println(paths[i]);
        	File fl  = new File(paths[i]);
        	Scanner scan2 = new Scanner(fl).useDelimiter("\\n");
        	while(scan2.hasNext())
        	{
        		String line = scan2.next();
        		Matcher match = p.matcher(line);
        		if(match.find())
        		{
        			System.out.println("You find the title of Stored Procedure: " + line);
        			String rename = line;
        			rename = rename.replaceAll("-", "_");
        			rename = rename.replace(" PROC", "_PROC");
        			System.out.println(paths[i].replace("proc_"+i, rename.trim()));
        			File file = new File(paths[i].replace("proc_"+i, rename.trim()));
        			System.out.println(": " + file.getName());
        			//fl.delete();
        			file.createNewFile();
        			fl.renameTo(file);

        			break;
        		}
        	}continue;
        }*/
        

    }
 }

