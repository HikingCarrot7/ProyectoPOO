package com.sw.utilities;

import com.sw.controller.TableManager;
import java.io.Serializable;
import javax.swing.JTable;

/**
 *
 * @author Mohammed
 */
public class Temporizador implements Runnable, Serializable
{

    private static final long serialVersionUID = 8178588319113726860L;

    private Time time;
    private JTable table;
    private int fila;
    private int columna;

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

                new TableManager().updateField(table, time.toString(), fila, columna);

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

    public void setTable(JTable table)
    {
        this.table = table;
    }

    public void setFila(int fila)
    {
        this.fila = fila;
    }

    public void setColumna(int columna)
    {
        this.columna = columna;
    }

    @Override
    public String toString()
    {
        return time.toString();
    }

}
