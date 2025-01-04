package com.mayware.eeg;

public class EEG
{
    public static final byte ELECTRODES = 1; //typical electrode count: 16-32
    public static final short SAMPLE_RATE = 256; //typical sample rate: 256 Hz to 2048 Hz
    
    public static void main(String[] args) throws Exception
    {
        Modem modem = new Modem();
        Logger logger = new Logger();
        Oscilloscope oscilloscope = new Oscilloscope();
        
        modem.start();
        logger.start();
        oscilloscope.start();
    }
}
