/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mayware.eeg;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author May Fontenot
 */

public class Logger
{
    private File file;
    private FileWriter writer;
    private long initialMilliseconds;
    
    public Logger()
    {
        initialMilliseconds = System.currentTimeMillis();
        
        file = new File(initialMilliseconds + "_sample.txt");
        
        try
        {
            file.createNewFile();
            
            writer = new FileWriter(file.getName());
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    public void log(byte electrode, float microVolts)
    {
        try
        {
            writer.append(System.currentTimeMillis() - initialMilliseconds + " " + electrode + " " + microVolts + "\n");
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
