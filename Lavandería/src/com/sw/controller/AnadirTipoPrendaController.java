package com.sw.controller;

import com.sw.others.TextFieldListener;
import com.sw.view.AnadirTipoPrendaInterfaz;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Mohammed
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

    public void establecerTipoPrendaDefecto(int indexTipoPrendaEditando)
    {

        anadirTipoPrendaInterfaz.getEntradaTipoPrenda().setText(tiposPrendaController.getTiposPrendas().get(indexTipoPrendaEditando));
        this.indexTipoPrendaEditando = indexTipoPrendaEditando;

        setEditandoTipoPrenda(true);

        initMyComponents();

    }

    private void initMyComponents()
    {

        textFieldListener = new TextFieldListener("^[a-zA-ZÁ-Úá-ú0-9 @._-]{3,}+$", anadirTipoPrendaInterfaz.getEntradaTipoPrenda());

        anadirTipoPrendaInterfaz.getEntradaTipoPrenda().getDocument().addDocumentListener(textFieldListener);
        anadirTipoPrendaInterfaz.getEntradaTipoPrenda().addFocusListener(textFieldListener);

        anadirTipoPrendaInterfaz.getAnadirTipoPrenda().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        switch (e.getActionCommand())
        {

            case "add":

                if (textFieldListener.isValido())
                {

                    if (!isEditandoTipoPrenda())
                        tiposPrendaController.anadirTipoPrenda(anadirTipoPrendaInterfaz.getEntradaTipoPrenda().getText());

                    else
                        tiposPrendaController.modificarTipoPrenda(indexTipoPrendaEditando, anadirTipoPrendaInterfaz.getEntradaTipoPrenda().getText());

                    anadirTipoPrendaInterfaz.dispose();

                } else
                    mostrarMensaje("Error.", "El tipo de prenda no es válida.", JOptionPane.ERROR_MESSAGE);

                break;

            default:
                break;

        }

    }

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
