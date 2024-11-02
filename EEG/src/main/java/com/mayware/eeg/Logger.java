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
    public long initialMilliseconds;
    
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
    
    public void log(char electrode, float microVolts)
    {
        try
        {
            writer.append(System.currentTimeMillis() - initialMilliseconds + "ms" + electrode + ":" + microVolts + "µV");
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
