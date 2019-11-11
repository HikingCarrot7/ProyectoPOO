package com.sw.renderer;

import com.sw.controller.MouseMotionManager;
import com.sw.controller.MouseMotionModel;
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
public class ComboRenderer extends JLabel implements ListCellRenderer, MouseMotionModel
{

    private int y;
    private int x;

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
    {
        list.addMouseMotionListener(new MouseMotionManager(this));

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

        if (encimaItem(index, list.getModel().getSize()))
            setBackground(Color.cyan);

        setFont(list.getFont());

        return this;

    }

    private boolean encimaItem(int index, int tamanioLista)
    {
        if ((getY() >= tamanioLista * 20 || getY() < 7) && getY() != 0)
            return true;

        return index == (getY() / 20);

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

    @Override
    public int getY()
    {
        return y;
    }

    @Override
    public void setY(int y)
    {
        this.y = y;
    }

    @Override
    public void setX(int x)
    {
        this.x = x;
    }

    @Override
    public int getX()
    {
        return x;
    }

}
