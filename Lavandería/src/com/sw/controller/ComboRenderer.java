package com.sw.controller;

import java.awt.Color;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Mohammed
 */
public class ComboRenderer extends JLabel implements ListCellRenderer
{

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
    {

        setIcon(((ComboItem) value).getIcon());
        setText(((ComboItem) value).getText());

        if (isSelected)
        {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());

        } else
        {
            setBackground(list.getBackground());
            setForeground(list.getForeground());

        }

        setBackground(index % 2 == 0 ? new Color(180, 180, 180) : Color.white);

        setFont(list.getFont());

        return this;

    }

    public static class ComboItem
    {

        private ImageIcon icon;
        private String text;

        public ComboItem(ImageIcon icon, String text)
        {
            this.icon = icon;
            this.text = text;
        }

        public Icon getIcon()
        {
            return icon;
        }

        public void setIcon(ImageIcon icon)
        {
            this.icon = icon;
        }

        public String getText()
        {
            return text;
        }

        public void setText(String text)
        {
            this.text = text;
        }

    }

}
