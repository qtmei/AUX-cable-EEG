/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mayware.eeg;

import javax.sound.sampled.*;

/*
 * @author May Fontenot
 */

public class Modem
{
    private TargetDataLine[] lines;
    private boolean ACsignFlip = false;
    
    public Modem(byte electrodes) throws Exception
    {
        lines = new TargetDataLine[electrodes];
        
        Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
            
        for(int i = 0; i < mixerInfo.length; i++)
        {
            if(mixerInfo[i].getName().startsWith("Microphone"))
            {
                TargetDataLine line = (TargetDataLine)AudioSystem.getMixer(mixerInfo[i]).getLine(new DataLine.Info(TargetDataLine.class, null));
                line.open();
                line.start();

                lines[lines.length - 1] = line;
            }
        }
    }
    
    public float getMicroVolts(byte electrode)
    {
        float microvolts = lines[electrode].getLevel() * 1000; //volume 0:1 = millivolts * 1000 = microvolts
        
        ACsignFlip = !ACsignFlip;
        
        return ACsignFlip ? -microvolts : microvolts; //make voltage alternating
    }
}
