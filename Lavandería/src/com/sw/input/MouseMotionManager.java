package com.sw.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Mohammed
 *
 * @param <E>
 */
public class MouseMotionManager<E extends MouseMotionModel> implements MouseMotionListener
{

    private E item;

    public MouseMotionManager(E item)
    {
        this.item = item;
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {

    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        item.setY(e.getY());
        item.setX(e.getX());

    }

    public E getItem()
    {
        return item;
    }

    public void setItem(E item)
    {
        this.item = item;
    }

}
