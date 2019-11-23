package com.sw.controller;

import com.sw.model.Cliente;
import com.sw.model.Historial;
import com.sw.model.Servicio;
import com.sw.persistence.ConfigDAO;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Mohammed
 */
public class DataSorterManager
{

    private int orden;

    public DataSorterManager()
    {
        orden = new ConfigDAO().getOrden();
    }

    public void ordenarPorNombreServicios(ArrayList<Servicio> servicio)
    {

        servicio.sort((c1, c2) ->
        {

            return orden == 0 ? c1.getCliente().getNombre().compareTo(c2.getCliente().getNombre())
                    : c2.getCliente().getNombre().compareTo(c1.getCliente().getNombre());

        });

    }

    public void ordenarPorPrecioTotal(ArrayList<Servicio> servicio)
    {

        if (orden == 0)
            servicio.sort(Comparator.comparing(Servicio::getPrecioTotal));

        else
            servicio.sort(Comparator.comparing(Servicio::getPrecioTotal).reversed());

    }

    public void ordenarPorTotalKg(ArrayList<Servicio> servicio)
    {

        if (orden == 0)
            servicio.sort(Comparator.comparing(Servicio::getTotalKg));

        else
            servicio.sort(Comparator.comparing(Servicio::getTotalKg).reversed());

    }

    public void ordenarPorTotalPiezas(ArrayList<Servicio> servicio)
    {

        if (orden == 0)
            servicio.sort(Comparator.comparing(Servicio::getTotalPrendas));

        else
            servicio.sort(Comparator.comparing(Servicio::getTotalPrendas).reversed());

    }

    public void ordenarPorNombreClientes(ArrayList<Cliente> clientes)
    {

        if (orden == 0)
            clientes.sort(Comparator.comparing(Cliente::getNombre));

        else
            clientes.sort(Comparator.comparing(Cliente::getNombre).reversed());

    }

    public void ordenarPorNServiciosClientes(ArrayList<Cliente> clientes)
    {

        if (orden == 0)
            clientes.sort(Comparator.comparing(Cliente::getnServicios));

        else
            clientes.sort(Comparator.comparing(Cliente::getnServicios).reversed());
    }

    public void ordenarPorNombreHistorial(ArrayList<Historial> historial)
    {

        if (orden == 0)
            historial.sort((c1, c2) ->
            {

                return orden == 0 ? c1.getCliente().getNombre().compareTo(c2.getCliente().getNombre())
                        : c2.getCliente().getNombre().compareTo(c1.getCliente().getNombre());

            });

    }

    public void ordenarPorFechaHistorial(ArrayList<Historial> historial)
    {

        if (orden == 0)
            historial.sort(Comparator.comparing(Historial::getFecha));

        else
            historial.sort(Comparator.comparing(Historial::getFecha).reversed());

    }

    public void ordenarPorPrecioTotalHistorial(ArrayList<Historial> historial)
    {

        if (orden == 0)
            historial.sort(Comparator.comparing(Historial::getPrecioTotal));

        else
            historial.sort(Comparator.comparing(Historial::getPrecioTotal).reversed());

    }

}
