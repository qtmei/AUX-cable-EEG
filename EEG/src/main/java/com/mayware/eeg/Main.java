/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mayware.eeg;

/**
 *
 * @author May Fontenot
 */

public class Main
{
    public static void main(String[] args)
    {
        byte electrodes = 1;
        short sampleRate = 256; //typical sample rate: 256 Hz to 2048 Hz
        
        Modem modem = new Modem(electrodes);
        Logger logger = new Logger();
        Oscilloscope oscilloscope = new Oscilloscope();
        
        while(true)
        {
            for(byte i = 0; i < electrodes; i++)
            {
                float microVolts = modem.getMicroVolts(i);
                
                logger.log(i, microVolts);
                oscilloscope.display(i, microVolts);
            }
            
            logger.segment();
            oscilloscope.segment();
            
            try
            {
                Thread.sleep(1000 / sampleRate);
            }
            catch(InterruptedException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }
}
