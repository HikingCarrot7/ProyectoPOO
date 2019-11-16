package com.sw.utilities;

/**
 *
 * @author Mohammed
 */
public class Temporizador implements Runnable
{

    private Time time;

    public Temporizador(Time time)
    {
        this.time = time;
    }

    public Temporizador()
    {
        time = new Time(1);
    }

    public void iniciarTemporizador()
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

                System.out.println(time);

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
        this.time = time;
    }

    @Override
    public String toString()
    {
        return time.toString();
    }

}
