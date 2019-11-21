package com.sw.controller;

import com.sw.others.TextFieldListener;
import com.sw.model.Prenda;
import com.sw.view.AnadirPrendaInterfaz;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Mohammed
 */
public class AnadirPrendaController implements ActionListener
{

    private AnadirPrendaInterfaz anadirPrendaInterfaz;
    private PrendasController prendasController;
    private TextFieldListener[] textFieldListeners;
    private Prenda prendaAModificar;
    private boolean modificandoPrenda;

    public AnadirPrendaController(AnadirPrendaInterfaz anadirPrendaInterfaz, PrendasController prendasController)
    {

        this.anadirPrendaInterfaz = anadirPrendaInterfaz;
        this.prendasController = prendasController;

        textFieldListeners = new TextFieldListener[2];

        textFieldListeners[0] = new TextFieldListener("^[á-úÁ-Úa-zA-Z0-9 ,_-]+$", anadirPrendaInterfaz.getPrenda());
        textFieldListeners[1] = new TextFieldListener("^[1-90*]+$", anadirPrendaInterfaz.getCantidad());

        anadirPrendaInterfaz.getPrenda().getDocument().addDocumentListener(textFieldListeners[0]);
        anadirPrendaInterfaz.getCantidad().getDocument().addDocumentListener(textFieldListeners[1]);

        anadirPrendaInterfaz.getPrenda().addFocusListener(textFieldListeners[0]);
        anadirPrendaInterfaz.getCantidad().addFocusListener(textFieldListeners[1]);

        anadirPrendaInterfaz.getCantidad().setText("1");

        anadirPrendaInterfaz.getOk().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        if (!todosLosCampoValidos())
            JOptionPane.showMessageDialog(anadirPrendaInterfaz, "Alguno de los campos no es válido", "Campo no válido", JOptionPane.ERROR_MESSAGE);

        else if (!isModificandoPrenda())
            prendasController.anadirPrenda(getPrenda());

        else
            prendasController.modificarPrenda(prendaAModificar, getPrenda());

        if (todosLosCampoValidos())
            anadirPrendaInterfaz.dispose();

    }

    public void establecerPrendaDefecto(Prenda prenda)
    {

        setModificandoPrenda(true);

        prendaAModificar = prenda;

        anadirPrendaInterfaz.getPrenda().setText(prenda.getDescripcion());
        anadirPrendaInterfaz.getCantidad().setText(String.valueOf(prenda.getCantidad()));
        anadirPrendaInterfaz.getTiposPrenda().setSelectedItem(prenda.getTipo());

    }

    private Prenda getPrenda()
    {
        return new Prenda(anadirPrendaInterfaz.getPrenda().getText(),
                String.valueOf(anadirPrendaInterfaz.getTiposPrenda().getSelectedItem()),
                Integer.parseInt(anadirPrendaInterfaz.getCantidad().getText()));

    }

    private boolean todosLosCampoValidos()
    {

        for (TextFieldListener textFieldListener : textFieldListeners)
            if (!textFieldListener.isValido())
                return false;

        return true;

    }

    private boolean isModificandoPrenda()
    {
        return modificandoPrenda;
    }

    private void setModificandoPrenda(boolean modificandoPrenda)
    {
        this.modificandoPrenda = modificandoPrenda;
    }

}
