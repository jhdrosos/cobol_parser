/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eclipsefinalproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author john_
 */
public class EclipseFinalProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException 
    {
        // TODO code application logic here
        
        STPI_TPARMS_PROC stp = new STPI_TPARMS_PROC();
        STPI_WPARMS_PROC swp = new STPI_WPARMS_PROC();
        stp.openFile();
        //swp.readFile();
        
        
        //LOCK_STPI_PROC lsp = new LOCK_STPI_PROC();
        //lsp.openFile();
        
        //DRPCUR_ALL_STPI_PROC dasp = new DRPCUR_ALL_STPI_PROC();
        //dasp.openFile();
        
        //INS_STPI_PROC isp= new INS_STPI_PROC();
        //isp.openFile();

        
    }
    
}
