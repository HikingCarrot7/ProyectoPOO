package com.sw.model;

import com.sw.utilities.Temporizador;
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
    private Temporizador tiempoEstimado;

    public ServicioInicial(Cliente cliente, Calendar fecha, Temporizador tiempoEstimado, ArrayList<Prenda> prendas)
    {

        super(cliente, fecha);

        this.prendas = prendas;
        this.tiempoEstimado = tiempoEstimado;

    }

    public ServicioInicial(Cliente cliente, Temporizador tiempoEstimado, ArrayList<Prenda> prendas)
    {

        super(cliente);

        this.prendas = prendas;
        this.tiempoEstimado = tiempoEstimado;

    }

    public ServicioInicial(Cliente cliente, ArrayList<Prenda> prendas)
    {
        this(cliente, new Temporizador(), prendas);
    }

    public ServicioInicial(Cliente cliente, Temporizador temporizador)
    {
        this(cliente, temporizador, new ArrayList<>());
    }

    public ServicioInicial(Cliente cliente)
    {
        this(cliente, new Temporizador());
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

    public Temporizador getTiempoEstimado()
    {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(Temporizador tiempoEstimado)
    {
        this.tiempoEstimado = tiempoEstimado;
    }

}
