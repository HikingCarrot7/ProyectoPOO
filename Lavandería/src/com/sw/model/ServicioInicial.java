package com.sw.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Mohammed
 */
public class ServicioInicial extends Servicio implements Serializable
{

    private static final long serialVersionUID = -3305366944888817314L;

    private ArrayList<Prenda> prendas;

    public ServicioInicial(Cliente cliente, Calendar fecha, ArrayList<Prenda> prendas)
    {
        super(cliente, fecha);

        this.prendas = prendas;

    }

    public ServicioInicial(Cliente cliente, ArrayList<Prenda> prendas)
    {
        super(cliente);

        this.prendas = prendas;

    }

    public void anadirPrenda(Prenda prenda)
    {
        prendas.add(prenda);
    }

    public void eliminarPrenda(Prenda prenda)
    {
        prendas.remove(prenda);
    }

    public ArrayList<Prenda> getPrendas()
    {
        return prendas;
    }

    public void setPrendas(ArrayList<Prenda> prendas)
    {
        this.prendas = prendas;
    }

}
