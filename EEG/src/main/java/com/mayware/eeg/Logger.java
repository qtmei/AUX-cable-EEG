/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mayware.eeg;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author May Fontenot
 */

public class Logger
{
    private FileWriter writer;
    public long initialMilliseconds;
    
    public Logger()
    {
        initialMilliseconds = System.currentTimeMillis();
        
        try
        {
            writer = new FileWriter(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "_sample.txt");
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
