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
    private float[] microVoltages;
    
    public Modem(byte electrodes)
    {
        microVoltages = new float[electrodes];
        
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
                    
                    float level = line.getLevel(); //0:1
                    float millivolts = level * 2 - 1; //-1:1
                    microVoltages[microVoltages.length] = (millivolts / 1000 + 1) * 200 - 200; //-200:200
                }
            }
        }
        catch(LineUnavailableException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    public float microVolts(char electrode)
    {
        return microVoltages[electrode];
    }
}
