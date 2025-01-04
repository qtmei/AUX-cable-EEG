package com.mayware.eeg;

public class Oscilloscope extends Thread
{   
    public void run()
    {
        final long INIT_MS = System.currentTimeMillis();

        while(true)
        {
            for(byte i = 0; i < EEG.ELECTRODES; i++)
            {
                float microVolts = Modem.microVolts[i];

                String leftSpace = "", rightSpace = "";

                for(byte j = -1000 / 50; j < microVolts / 50; j++)
                    leftSpace += " ";

                for(byte j = 1000 / 50; j > microVolts / 50; j--)
                    rightSpace += " ";

                System.out.print(System.currentTimeMillis() - INIT_MS + "ms " + i + ": -1000µV" + leftSpace + '*' + rightSpace + "1000µV ");
            }

            System.out.println();

            try {
                Oscilloscope.sleep(1000 / EEG.SAMPLE_RATE);
            } catch(Exception ex){}
        }
    }
}
