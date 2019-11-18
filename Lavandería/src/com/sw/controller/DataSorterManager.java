package com.sw.controller;

import com.sw.model.Cliente;
import com.sw.model.ServicioInicial;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Mohammed
 */
public class DataSorterManager
{

    public void ordenarPorNombreServicios(ArrayList<ServicioInicial> servicio)
    {

        servicio.sort((c1, c2) ->
        {
            return c1.getCliente().getNombre().compareTo(c2.getCliente().getNombre());
        }
        );

    }

    public void ordenarPorPrecioTotal(ArrayList<ServicioInicial> servicio)
    {
        servicio.sort(Comparator.comparing(ServicioInicial::getPrecioTotal));
    }

    public void ordenarPorTotalKg(ArrayList<ServicioInicial> servicio)
    {
        servicio.sort(Comparator.comparing(ServicioInicial::getTotalKg));
    }

    public void ordenarPorTotalPiezas(ArrayList<ServicioInicial> servicio)
    {
        servicio.sort(Comparator.comparing(ServicioInicial::getTotalPrendas));
    }

    public void ordenarPorNombreClientes(ArrayList<Cliente> clientes)
    {
        clientes.sort(Comparator.comparing(Cliente::getNombre));
    }

    public void ordenarPorNServiciosClientes(ArrayList<Cliente> clientes)
    {
        clientes.sort(Comparator.comparing(Cliente::getnServicios));
    }

}
