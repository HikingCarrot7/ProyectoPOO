package com.sw.controller;

import com.sw.model.Cliente;
import com.sw.others.TextFieldListener;
import com.sw.view.NuevoCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * @author Me
 * @since 1.0
 *
 */
public class NuevoClienteController implements ActionListener
{

    private Cliente clienteModificando;
    private NuevoCliente nuevoCliente;
    private ClientesRegistradosController clientesRegistradosController;
    private NuevoServicioController nuevoServicioController;
    private TextFieldListener[] textFieldListeners;
    private boolean modificandoCliente;

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

    public void establecerDatosDefecto(Cliente clienteModificando)
    {

        setModificandoCliente(true); // Estamos modificando a un cliente.

        this.clienteModificando = clienteModificando;

        // Rellenamos los campo con los datos del cliente que estamos modificando.
        nuevoCliente.getNombre().setText(clienteModificando.getNombre());
        nuevoCliente.getCorreo().setText(clienteModificando.getCorreo());
        nuevoCliente.getTelefono().setText(clienteModificando.getTelefono());
        nuevoCliente.getDireccion().setText(clienteModificando.getDireccion());

    }

    /**
     * Iniciamos los componentes para esta interfaz.
     */
    private void initAllMyComponents()
    {

        textFieldListeners = new TextFieldListener[4];

        textFieldListeners[0] = new TextFieldListener("^[a-zA-ZÁ-Úá-ú ]{5,}+$", nuevoCliente.getNombreValidoLabel(), nuevoCliente.getNombre());
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

    /**
     * Gestiona los eventos que ocurren cuando se presiona un botón.
     *
     * @param e El objeto de tipo ActionEvent que se crea cuando se presiona un botón en esta interfaz.
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {

        if (e.getSource() instanceof JButton)
            ((JButton) e.getSource()).setMultiClickThreshhold(1000);

        Cliente cliente;

        if (!textFieldListeners[0].isValido())
        {

            JOptionPane.showMessageDialog(nuevoCliente, "Al menos el nombre del cliente debe ser válido", "Nombre no válido", JOptionPane.ERROR_MESSAGE);
            return;

        } else if (!todosCamposValidos())
            switch (JOptionPane.showConfirmDialog(nuevoCliente, "Los campo inválidos no se tomarán en cuenta. ¿Continuar?", "Datos inválidos", JOptionPane.YES_NO_OPTION))
            {

                case 0: // Si se presiona Sí

                    cliente = obtenerDatosCliente();

                    break;

                default:
                    return;

            }

        else
            cliente = obtenerDatosCliente();

        nuevoCliente.dispose();

        if (clientesRegistradosController != null)
        {

            if (!isModificandoCliente())
                clientesRegistradosController.addClienteRegistrado(cliente);

            else
                clientesRegistradosController.modificarClienteRegistrado(clienteModificando, cliente);

            clientesRegistradosController.getClientesRegistradosInterfaz().setVisible(true);

        } else
        {

            nuevoServicioController.addCliente(cliente);
            nuevoServicioController.getNuevoServicio().setVisible(true);

        }

    }

    /**
     * Obtenemos los datos de la interfaz y creamos al cliente nuevo.
     *
     * @return El cliente con los datos de la interfaz.
     */
    private Cliente obtenerDatosCliente()
    {

        return new Cliente(nuevoCliente.getNombre().getText(),
                textFieldListeners[1].isValido() ? nuevoCliente.getCorreo().getText() : "",
                textFieldListeners[2].isValido() ? nuevoCliente.getTelefono().getText() : "",
                textFieldListeners[3].isValido() ? nuevoCliente.getDireccion().getText() : "");

    }

    /**
     * Todos los campos son válidos.
     *
     * @return @return <code> verdadero </code> si todos los campos son correctos, <code>falso</code> en caso contrario.
     */
    private boolean todosCamposValidos()
    {

        for (TextFieldListener listener : textFieldListeners)
            if (!listener.isValido())
                return false;

        return true;

    }

    private boolean isModificandoCliente()
    {
        return modificandoCliente;
    }

    private void setModificandoCliente(boolean modificandoCliente)
    {
        this.modificandoCliente = modificandoCliente;
    }

}
