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

    private String regex;
    private String text;
    private JLabel etiqueta;
    private JTextField campo;

    public TextFieldListener(String regex, JLabel etiqueta, JTextField campo)
    {
        this.regex = regex;
        this.etiqueta = etiqueta;
        this.campo = campo;

        text = "";

    }

    @Override
    public void insertUpdate(DocumentEvent e)
    {

        text += campo.getText();

        updateCampos();

    }

    @Override
    public void removeUpdate(DocumentEvent e)
    {

        text += campo.getText();

        updateCampos();

    }

    private void updateCampos()
    {

        if (!campo.getText().trim().equals(""))
        {

            campo.setBackground(campo.getText().matches(regex) ? Color.green : Color.red);
            etiqueta.setText(campo.getText().matches(regex) ? "" : "Dato inválido");

        }

    }

    @Override
    public void focusGained(FocusEvent e)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void focusLost(FocusEvent e)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void changedUpdate(DocumentEvent e)
    {

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
        return etiqueta;
    }

    public void setEtiqueta(JLabel etiqueta)
    {
        this.etiqueta = etiqueta;
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
