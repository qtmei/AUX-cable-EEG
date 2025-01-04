package com.mayware.eeg;

import javax.sound.sampled.*;

public class Modem extends Thread
{
    public static float[] microVolts = new float[EEG.ELECTRODES];
    
    public void run()
    {
        Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
        TargetDataLine[] lines = new TargetDataLine[EEG.ELECTRODES];

        for(int i = 0; i < mixerInfo.length; i++)
        {
            if(mixerInfo[i].getName().startsWith("Microphone"))
            {
                try {
                    TargetDataLine line = (TargetDataLine)AudioSystem.getMixer(mixerInfo[i]).getLine(new DataLine.Info(TargetDataLine.class, null));
                    line.open();
                    line.start();
                    
                    lines[lines.length - 1] = line;
                } catch(Exception ex){}
            }
        }

        boolean ACsignFlip = false;

        while(true)
        {
            for(byte i = 0; i < EEG.ELECTRODES; i++)
            {
                float level = lines[i].getLevel(); //volume 0:1 = millivolts * 1000 = microvolts

                microVolts[i] = ACsignFlip ? -level * 1000 : level * 1000; //make voltage alternating
                
                ACsignFlip = !ACsignFlip;
            }

            try {
                Modem.sleep(1000 / EEG.SAMPLE_RATE);
            } catch(Exception ex){}
        }
    }
}
