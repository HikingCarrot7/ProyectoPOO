package com.sw.controller;

import com.sw.model.Cliente;
import com.sw.view.NuevoCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Mohammed
 */
public class NuevoClienteController implements ActionListener
{

    private NuevoCliente nuevoCliente;
    private ClientesRegistradosController clientesRegistradosController;

    public NuevoClienteController(NuevoCliente nuevoCliente, ClientesRegistradosController clientesRegistradosController)
    {
        this.nuevoCliente = nuevoCliente;
        this.clientesRegistradosController = clientesRegistradosController;

        nuevoCliente.getOk().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        clientesRegistradosController.anadirClienteTabla(new Cliente("Nicolás Canul", "ricardoibarra2044@gmail.com", "9992676253", "nowhere"));

        nuevoCliente.dispose();

    }

}
