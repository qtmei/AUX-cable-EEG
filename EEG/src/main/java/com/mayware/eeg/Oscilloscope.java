/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mayware.eeg;

/**
 *
 * @author May Fontenot
 */

public class Oscilloscope
{
    private final long initialMilliseconds = System.currentTimeMillis();;
    
    public void display(byte electrode, float microVolts)
    {
        String leftSpace = "", rightSpace = "";
        
        for(float i = -1000 / 64; i < microVolts / 64; i++)
            leftSpace += " ";
        
        for(float i = 1000 / 64; i > microVolts / 64; i--)
            rightSpace += " ";
        
        System.out.print(System.currentTimeMillis() - initialMilliseconds + "ms " + electrode + ": -1000µV" + leftSpace + "*" + rightSpace + "1000µV\t");
    }
    
    public void segment()
    {
        System.out.println();
    }
}
