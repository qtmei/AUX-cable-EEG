/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mayware.eeg;

import javax.sound.sampled.*;

/**
 *
 * @author May Fontenot
 */

public class Modem
{
    private TargetDataLine[] lines;
    
    public Modem(byte electrodes)
    {
        lines = new TargetDataLine[electrodes];
        
        try
        {
            Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
            
            for(int i = 0; i < mixerInfo.length; i++)
            {
                if(mixerInfo[i].getName().contains("USB Microphone"))
                {
                    Mixer mixer = AudioSystem.getMixer(mixerInfo[i]);

                    TargetDataLine line = (TargetDataLine)mixer.getLine(new DataLine.Info(TargetDataLine.class, null));
                    line.open();
                    line.start();
                    
                    lines[lines.length - 1] = line;
                }
            }
        }
        catch(LineUnavailableException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    public float getMicroVolts(char electrode)
    {
        float level = lines[electrode].getLevel(); //0:1
        float millivolts = level * 2 - 1; //-1:1
        
        return (millivolts / 1000 + 1) * 200 - 200; //-200:200
    }
}
