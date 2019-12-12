package com.sw.controller;

import com.sw.others.TextFieldListener;
import com.sw.view.AnadirTipoPrendaInterfaz;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Me
 * @since 1.0
 */
public class AnadirTipoPrendaController implements ActionListener
{

    private AnadirTipoPrendaInterfaz anadirTipoPrendaInterfaz;
    private TiposPrendasController tiposPrendaController;
    private TextFieldListener textFieldListener;
    private int indexTipoPrendaEditando;
    private boolean editandoTipoPrenda;

    public AnadirTipoPrendaController(AnadirTipoPrendaInterfaz anadirTipoPrendaInterfaz, TiposPrendasController tiposPrendaController)
    {

        this.anadirTipoPrendaInterfaz = anadirTipoPrendaInterfaz;
        this.tiposPrendaController = tiposPrendaController;

        initMyComponents();

    }

    /**
     * Si se está editando una prenda, establecemos su valores en los campos.
     *
     * @param indexTipoPrendaEditando El index del cual vamos a tomar los datos del tipo de prenda.
     */
    public void establecerTipoPrendaDefecto(int indexTipoPrendaEditando)
    {

        anadirTipoPrendaInterfaz.getEntradaTipoPrenda().setText(tiposPrendaController.getTiposPrendas().get(indexTipoPrendaEditando));
        this.indexTipoPrendaEditando = indexTipoPrendaEditando;

        setEditandoTipoPrenda(true);

        initMyComponents();

    }

    /**
     *
     * Iniciamos los componentes para esta ventana.
     */
    private void initMyComponents()
    {

        textFieldListener = new TextFieldListener("^[a-zA-ZÁ-Úá-ú0-9 @._-]{3,}+$", anadirTipoPrendaInterfaz.getEntradaTipoPrenda());

        anadirTipoPrendaInterfaz.getEntradaTipoPrenda().getDocument().addDocumentListener(textFieldListener);
        anadirTipoPrendaInterfaz.getEntradaTipoPrenda().addFocusListener(textFieldListener);

        anadirTipoPrendaInterfaz.getAnadirTipoPrenda().addActionListener(this);

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

        switch (e.getActionCommand())
        {

            case "add": // Cuando se prensiona "Añadir".

                if (textFieldListener.isValido())
                {

                    if (!isEditandoTipoPrenda())
                        tiposPrendaController.anadirTipoPrenda(anadirTipoPrendaInterfaz.getEntradaTipoPrenda().getText());

                    else
                        tiposPrendaController.modificarTipoPrenda(indexTipoPrendaEditando, anadirTipoPrendaInterfaz.getEntradaTipoPrenda().getText());

                    tiposPrendaController.getTiposPrendasInterfaz().setVisible(true);

                    anadirTipoPrendaInterfaz.dispose();

                } else
                    mostrarMensaje("Error.", "El tipo de prenda no es válida.", JOptionPane.ERROR_MESSAGE);

                break;

            default:
                break;

        }

    }

    /**
     * Mostramos un mensaje.
     *
     * @param titulo El título.
     * @param text El texto a mostrar.
     * @param tipo El tipo de mensaje.
     *
     */
    private void mostrarMensaje(String titulo, String text, int tipo)
    {
        JOptionPane.showMessageDialog(anadirTipoPrendaInterfaz, text, titulo, tipo);
    }

    public boolean isEditandoTipoPrenda()
    {
        return editandoTipoPrenda;
    }

    public void setEditandoTipoPrenda(boolean editandoTipoPrenda)
    {
        this.editandoTipoPrenda = editandoTipoPrenda;
    }

}
