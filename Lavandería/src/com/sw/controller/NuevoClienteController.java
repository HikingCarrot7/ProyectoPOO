package com.sw.controller;

import com.sw.model.Cliente;
import com.sw.model.Persona;
import com.sw.view.NuevoCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Mohammed
 */
public class NuevoClienteController implements ActionListener
{

    private boolean modificandoCliente;
    private Cliente clienteModificando;
    private NuevoCliente nuevoCliente;
    private ClientesRegistradosController clientesRegistradosController;
    private NuevoServicioController nuevoServicioController;
    private TextFieldListener[] textFieldListeners;

    public NuevoClienteController(NuevoCliente nuevoCliente, ClientesRegistradosController clientesRegistradosController)
    {

        this.nuevoCliente = nuevoCliente;
        this.clientesRegistradosController = clientesRegistradosController;

        initAllMyComponents();

    }

    public NuevoClienteController(NuevoCliente nuevoCliente, NuevoServicioController nuevoServicioController)
    {

        this.nuevoCliente = nuevoCliente;
        this.nuevoServicioController = nuevoServicioController;

        initAllMyComponents();

    }

    private void initAllMyComponents()
    {

        textFieldListeners = new TextFieldListener[4];

        textFieldListeners[0] = new TextFieldListener("^[a-zA-Zá-ú ]{5,}+$", nuevoCliente.getNombreValidoLabel(), nuevoCliente.getNombre());
        textFieldListeners[1] = new TextFieldListener("([a-zA-zá-ú0-9_]{4,}.?)+@([a-zA-Z]{3,}.?)+.([a-zA-Z]+){2,}", nuevoCliente.getCorreoValidoLabel(), nuevoCliente.getCorreo());
        textFieldListeners[2] = new TextFieldListener("^[0-9]{5,10}+$", nuevoCliente.getTelefonoValidoLabel(), nuevoCliente.getTelefono());
        textFieldListeners[3] = new TextFieldListener("^[a-zA-Zá-ú0-9@ ._-]+$", nuevoCliente.getDireccionValidaLabel(), nuevoCliente.getDireccion());

        nuevoCliente.getNombre().getDocument().addDocumentListener(textFieldListeners[0]);
        nuevoCliente.getCorreo().getDocument().addDocumentListener(textFieldListeners[1]);
        nuevoCliente.getTelefono().getDocument().addDocumentListener(textFieldListeners[2]);
        nuevoCliente.getDireccion().getDocument().addDocumentListener(textFieldListeners[3]);

        nuevoCliente.getNombre().addFocusListener(textFieldListeners[0]);
        nuevoCliente.getCorreo().addFocusListener(textFieldListeners[1]);
        nuevoCliente.getTelefono().addFocusListener(textFieldListeners[2]);
        nuevoCliente.getDireccion().addFocusListener(textFieldListeners[3]);

        nuevoCliente.getOk().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        Persona persona;

        if (!textFieldListeners[0].isValido())
        {

            JOptionPane.showMessageDialog(nuevoCliente, "Al menos el nombre del cliente debe ser válido", "Nombre no válido", JOptionPane.ERROR_MESSAGE);
            return;

        } else if (!todosCamposValidos())
            switch (JOptionPane.showConfirmDialog(nuevoCliente, "Los campo inválidos no se tomarán en cuenta. ¿Continuar?", "Datos inválidos", JOptionPane.YES_NO_OPTION))
            {

                case 0: // Si se presiona Sí

                    persona = obtenerDatosPersona();

                    break;

                default:
                    return;

            }

        else
            persona = obtenerDatosPersona();

        nuevoCliente.dispose();

        if (clientesRegistradosController != null)
            if (!getModificandoCliente())
                clientesRegistradosController.addClienteRegistrado(new Cliente(persona));

            else
                clientesRegistradosController.modificarClienteRegistrado(clienteModificando, new Cliente(persona));

        else
            nuevoServicioController.addCliente(new Cliente(persona));

    }

    private Persona obtenerDatosPersona()
    {

        return new Persona(nuevoCliente.getNombre().getText(),
                textFieldListeners[1].isValido() ? nuevoCliente.getCorreo().getText() : "",
                textFieldListeners[2].isValido() ? nuevoCliente.getTelefono().getText() : "",
                textFieldListeners[3].isValido() ? nuevoCliente.getDireccion().getText() : "");

    }

    public void establecerDatosDefecto(Cliente clienteModificando)
    {

        setModificandoCliente(true);

        this.clienteModificando = clienteModificando;

        nuevoCliente.getNombre().setText(clienteModificando.getNombre());
        nuevoCliente.getCorreo().setText(clienteModificando.getCorreo());
        nuevoCliente.getTelefono().setText(clienteModificando.getTelefono());
        nuevoCliente.getDireccion().setText(clienteModificando.getDireccion());

    }

    private boolean todosCamposValidos()
    {

        for (TextFieldListener listener : textFieldListeners)
            if (!listener.isValido())
                return false;

        return true;

    }

    private boolean getModificandoCliente()
    {
        return modificandoCliente;
    }

    private void setModificandoCliente(boolean modificandoCliente)
    {
        this.modificandoCliente = modificandoCliente;
    }

}
