package com.sw.utilities;

import javax.swing.JLabel;

/**
 *
 * @author Me
 */
public class TableTimer extends Timer
{

    private static final long serialVersionUID = -7987864950655752437L;

    private JLabel label;
    private int id;
    private volatile boolean alive;

    public TableTimer(JLabel label, Time time, int id)
    {

        super(time);

        this.label = label;
        this.id = id;

    }

    @Override
    public void run()
    {

        alive = true;

        while (imAlive())
            try
            {

                Thread.sleep(1000);

                if (!imAlive())
                    break;

                time.updateTime();

                label.setText(time.toString());

            } catch (InterruptedException ex)
            {
                System.out.println(ex.getMessage());
            }

    }

    public JLabel getLabel()
    {
        return label;
    }

    public void setLabel(JLabel label)
    {
        this.label = label;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public boolean imAlive()
    {
        return alive;
    }

    public void setAlive(boolean alive)
    {
        this.alive = alive;
    }

}
