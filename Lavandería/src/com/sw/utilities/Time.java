package com.sw.utilities;

/**
 *
 * @author Mohammed
 */
public class Time
{

    private int seconds;
    private int minutes;
    private int hours;

    public Time(int hours, int minutes, int seconds)
    {
        this.seconds = seconds;
        this.minutes = minutes;
        this.hours = hours;

    }

    public Time(int hours, int minutes)
    {
        this(hours, minutes, 0);
    }

    public Time(int hours)
    {
        this(hours, 0, 0);
    }

    public void updateTime()
    {
        if (hours >= 0)
        {

            seconds--;

            if (seconds < 0)
            {
                minutes--;
                seconds = 59;

            }

            if (minutes < 0)
            {
                hours--;
                minutes = 59;

            }

        }

    }

    public int getSeconds()
    {
        return seconds;
    }

    public void setSeconds(int seconds)
    {
        this.seconds = seconds;
    }

    public int getMinutes()
    {
        return minutes;
    }

    public void setMinutes(int minutes)
    {
        this.minutes = minutes;
    }

    public int getHours()
    {
        return hours;
    }

    public void setHours(int hours)
    {
        this.hours = hours;
    }

    @Override
    public String toString()
    {
        return String.format("%02d:%02d:%02d", getHours(), getMinutes(), getSeconds());
    }

}
