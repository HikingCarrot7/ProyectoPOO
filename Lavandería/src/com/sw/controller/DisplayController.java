package com.sw.controller;

import java.util.Calendar;
import javax.swing.JLabel;

/**
 *
 * @author Mohammed
 */
public class DisplayController implements Runnable
{

    private JLabel display;
    private final int DISPLAY_SIZE = 10;

    public DisplayController(JLabel display)
    {
        this.display = display;
    }

    public void iniciarAnimacion()
    {
        new Thread(this).start();
    }

    @Override
    public void run()
    {

        try
        {

            realizarAnimacionPausada("Bienvenido");

            Thread.sleep(1000);

            realizarAnimacionCompleta(getFixedString("Las burbujas mágicas"), 150);

            Thread.sleep(1000);

            while (true)
                try
                {

                    realizarAnimacionCompleta(getFixedString(String.format("%1$tA, %1$tB %1$td, %1$tY", Calendar.getInstance())), 150);

                    Thread.sleep(1000);

                    realizarAnimacionCompleta(getFixedString(String.format("%1$tZ %1$tI:%1$tM:%1$tS %Tp", Calendar.getInstance())), 150);

                    Thread.sleep(1000);

                } catch (InterruptedException ex)
                {
                    System.out.println(ex.getMessage());
                }

        } catch (InterruptedException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    private void realizarAnimacionCompleta(String text, int delay)
    {

        for (int i = 0; i < text.length() - DISPLAY_SIZE; i++)
            try
            {

                Thread.sleep(delay);

                String temp = text.substring(i, i + 10);

                display.setText(temp);

            } catch (InterruptedException ex)
            {
                System.out.println(ex.getMessage());
            }

        display.setText("");

    }

    private void realizarAnimacionPausada(String text)
    {
        realizarAnimacionSalida(realizarAnimacionLlegada(text));
    }

    private String realizarAnimacionLlegada(String text)
    {

        String in = "";

        for (int i = 0; i < DISPLAY_SIZE; i++)
            try
            {

                in = "";

                Thread.sleep(200);

                in += getEspacios(DISPLAY_SIZE - i - 1);

                in += i < text.length() ? text.substring(0, i + 1) : text;

                in += getEspacios(DISPLAY_SIZE - in.length());

                display.setText(in);

            } catch (InterruptedException ex)
            {
                System.out.println(ex.getMessage());
            }

        return in;

    }

    private void realizarAnimacionSalida(String in)
    {

        try
        {

            Thread.sleep(3000);

            for (int i = 0; i < DISPLAY_SIZE + 1; i++)
            {

                String out = "";

                Thread.sleep(200);

                out += in.substring(i, in.length());

                out += getEspacios(out.length() - DISPLAY_SIZE);

                display.setText(out);

            }

        } catch (InterruptedException ex)
        {
            System.out.println(ex.getMessage());
        }

        display.setText("");

    }

    private String getEspacios(int nEspacios)
    {

        String temp = "";

        for (int i = 0; i < nEspacios; i++)
            temp += " ";

        return temp;

    }

    private String getFixedString(String text)
    {

        String temp = "";

        for (int i = 0; i < DISPLAY_SIZE; i++)
            temp += " ";

        temp += text;

        for (int i = 0; i < DISPLAY_SIZE; i++)
            temp += " ";

        return temp;

    }

}
