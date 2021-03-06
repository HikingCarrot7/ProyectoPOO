package com.sw.renderer;

import com.sw.renderer.ListRenderer.ListItem;
import java.awt.Component;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Me
 */
public class ListRenderer extends JLabel implements ListCellRenderer<ListItem>
{

    @Override
    public Component getListCellRendererComponent(JList<? extends ListItem> list, ListItem value, int index, boolean isSelected, boolean cellHasFocus)
    {
        setOpaque(true);
        setIcon(value.getIcon());
        setText(value.getText());

        if (isSelected)
        {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());

        } else
        {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        setFont(new Font("Consolas", Font.PLAIN, 11));
        return this;
    }

    public static class ListItem
    {

        private ImageIcon icon;
        private String text;

        public ListItem(ImageIcon icon, String text)
        {
            this.icon = icon;
            this.text = text;
        }

        public ImageIcon getIcon()
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
