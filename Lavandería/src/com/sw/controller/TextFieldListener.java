package com.sw.controller;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author HikingC7
 */
public class TextFieldListener implements DocumentListener, FocusListener
{

    private boolean valido;
    private String regex;
    private JLabel label;
    private JTextField campo;

    public TextFieldListener(String regex, JLabel etiqueta, JTextField campo)
    {
        this.regex = regex;
        this.label = etiqueta;
        this.campo = campo;

    }

    public TextFieldListener(String regex, JTextField campo)
    {
        this(regex, new JLabel(), campo);
    }

    @Override
    public void insertUpdate(DocumentEvent e)
    {
        updateFields();
    }

    @Override
    public void removeUpdate(DocumentEvent e)
    {
        updateFields();
    }

    private void updateFields()
    {

        if (!campo.getText().trim().equals(""))
        {

            boolean valid = campo.getText().matches(regex);

            campo.setBackground(valid ? Color.green : Color.red);
            label.setText(valid ? "" : "Dato inválido");
            setValido(valid);

        } else
        {
            campo.setBackground(Color.white);
            label.setText("");
            setValido(false);

        }

    }

    @Override
    public void focusGained(FocusEvent e)
    {

        campo.selectAll();

        if (!isValido())
        {
            campo.setBackground(Color.white);
            label.setText("");

        }

    }

    @Override
    public void focusLost(FocusEvent e)
    {
        updateFields();
    }

    @Override
    public void changedUpdate(DocumentEvent e)
    {

    }

    public boolean isValido()
    {
        return valido;
    }

    public void setValido(boolean valido)
    {
        this.valido = valido;
    }

    public String getRegex()
    {
        return regex;
    }

    public void setRegex(String regex)
    {
        this.regex = regex;
    }

    public JLabel getEtiqueta()
    {
        return label;
    }

    public void setEtiqueta(JLabel label)
    {
        this.label = label;
    }

    public JTextField getCampo()
    {
        return campo;
    }

    public void setCampo(JTextField campo)
    {
        this.campo = campo;
    }

}
