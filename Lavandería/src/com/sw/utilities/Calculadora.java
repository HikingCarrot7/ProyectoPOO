package com.sw.utilities;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public final class Calculadora extends JFrame
{

    private static final long serialVersionUID = 1L;

    private Display lamina;

    public Calculadora()
    {

        lamina = new Display();

        setBounds(0, 0, 400, 350);
        setLocationRelativeTo(null);
        setResizable(true);
        setTitle("Calculadora");
        add(lamina);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

    }

}

final class Display extends JPanel
{

    private String[] etiquetas =
    {
        "7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ".", "=", "+"
    };

    //Display
    private JButton display;

    //Panel de los botones
    private JPanel botones;

    //Numeros del display
    private String text = "";

    //Numero
    private String ultimaOperacion = "=";
    private double resultado = 0;

    public Display()
    {

        setLayout(new BorderLayout());

        display = new JButton("0.0");

        display.setEnabled(false);

        add(display, BorderLayout.NORTH);

        botones = new JPanel();

        botones.setLayout(new GridLayout(4, 4));

        for (String etiqueta : etiquetas)
            anadirBotones(etiqueta);

        add(botones, BorderLayout.CENTER);

    }

    public void anadirBotones(String boton)
    {

        JButton auxBoton = new JButton(boton);
        auxBoton.addActionListener(new AccionNumeros());

        botones.add(auxBoton);

    }

    private class AccionNumeros implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (!e.getActionCommand().contentEquals("+") && !e.getActionCommand().contentEquals("*") && !e.getActionCommand().contentEquals("-")
                    && !e.getActionCommand().contentEquals("/") && !e.getActionCommand().contentEquals("="))
            {

                if (!(text.contentEquals("") && e.getActionCommand().contentEquals("0")))
                {
                    text += e.getActionCommand();

                    display.setText(text);
                }

            } else
            {

                calcular(Double.parseDouble(display.getText()));

                ultimaOperacion = e.getActionCommand();

                text = "";

            }

        }

        public void calcular(double numero)
        {

            switch (ultimaOperacion)
            {
                case "+":
                    resultado += numero;
                    break;
                case "*":
                    resultado *= numero;
                    break;
                case "-":
                    resultado -= numero;
                    break;
                case "/":
                    resultado /= numero;
                    break;
                default:
                    resultado = numero;
                    break;

            }

            display.setText("" + resultado);

        }

    }

}
