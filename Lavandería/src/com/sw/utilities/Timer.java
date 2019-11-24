package com.sw.utilities;

import java.io.Serializable;

/**
 *
 * @author Mohammed
 */
public class Timer implements Runnable, Serializable
{

    private static final long serialVersionUID = 8178588319113726860L;

    protected Time time;

    public Timer(Time time)
    {
        this.time = new Time(time.getHours(), time.getMinutes(), time.getSeconds());
    }

    public Timer()
    {
        time = new Time(1);
    }

    public void iniciarTimer()
    {
        new Thread(this).start();
    }

    @Override
    public void run()
    {

        while (true)
            try
            {

                Thread.sleep(1000);

                time.updateTime();

            } catch (InterruptedException ex)
            {
                System.out.println(ex.getMessage());
            }

    }

    public Time getTime()
    {
        return time;
    }

    public void setTime(Time time)
    {
        this.time = new Time(time.getHours(), time.getMinutes(), time.getSeconds());
    }

    @Override
    public String toString()
    {
        return time.toString();
    }

}
