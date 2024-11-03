/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mayware.eeg;

import java.io.File;
import java.io.FileWriter;

/*
 * @author May Fontenot
 */

public class Logger
{
    private File file;
    private FileWriter writer;
    private final long initialMilliseconds = System.currentTimeMillis();
    
    public Logger() throws Exception
    {
        file = new File(initialMilliseconds + "_sample.txt");
        file.createNewFile();
        
        writer = new FileWriter(file.getName());
    }
    
    public void log(byte electrode, float microVolts) throws Exception
    {
        writer.append(System.currentTimeMillis() - initialMilliseconds + "ms " + electrode + ": " + microVolts + "µV\t");
    }
    
    public void segment() throws Exception
    {
        writer.append("\n");
    }
}
