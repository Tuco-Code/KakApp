package presentacio;

import javax.swing.*;
import java.awt.*;
import java.text.*;
import java.util.*;
public class DigitalWatch extends JLabel implements Runnable{
    Thread t=null;
    long seconds=0, secFin=0;
    int time = 0;

    String timeString = "";


    DigitalWatch(int te){
        t = new Thread(this);
        t.start();

        time = te;
        seconds=System.currentTimeMillis();

    }

    public void run() {
        try {
            while (true) {

                secFin = System.currentTimeMillis();

                timeString = new String( (((secFin-seconds)/1000)+time)+"" );

                printTime();

                t.sleep( 1000 );  // interval given in milliseconds
            }
        }
        catch (Exception e) { }
    }

    public void printTime(){
        setText(timeString);
    }

    public int getTime(){
        long d =(((secFin-seconds)/1000)+time);
        return (int)d;}
}