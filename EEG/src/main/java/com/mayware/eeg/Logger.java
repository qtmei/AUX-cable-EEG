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

public class Logger extends Thread
{
    public void run()
    {
        final long INIT_MS = System.currentTimeMillis();

        File file = new File(INIT_MS + "_sample.txt");
        FileWriter writer = null;
        
        try {
            file.createNewFile();
            
            writer = new FileWriter(file.getName());
        } catch(Exception ex){}

        while(true)
        {
            for(byte i = 0; i < EEG.ELECTRODES; i++)
            {
                try {
                    writer.append(System.currentTimeMillis() - INIT_MS + "ms " + i + ": " + Modem.microVolts[i] + "µV ");
                } catch(Exception ex){}
            }

            try {
                writer.append("\n");

                Logger.sleep(1000 / EEG.SAMPLE_RATE);
            } catch(Exception ex){}
        }
    }
}
