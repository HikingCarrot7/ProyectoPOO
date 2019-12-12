package com.sw.controller;

import com.sw.model.Prenda;
import com.sw.others.TextFieldListener;
import com.sw.persistence.DAO;
import com.sw.renderer.ComboRenderer;
import com.sw.utilities.Utilities;
import com.sw.view.AnadirPrendaInterfaz;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Me
 * @since 1.0
 *
 */
public class AnadirPrendaController implements ActionListener
{

    private AnadirPrendaInterfaz anadirPrendaInterfaz;
    private PrendasController prendasController;
    private TextFieldListener[] textFieldListeners;
    private ArrayList<String> tiposPrendas;
    private Prenda prendaAModificar;
    private boolean modificandoPrenda;

    public AnadirPrendaController(AnadirPrendaInterfaz anadirPrendaInterfaz, PrendasController prendasController)
    {

        this.anadirPrendaInterfaz = anadirPrendaInterfaz;
        this.prendasController = prendasController;

        initMyComponents();

    }

    /**
     * Iniciamos los componentes para esta ventana.
     */
    private void initMyComponents()
    {

        textFieldListeners = new TextFieldListener[2];

        textFieldListeners[0] = new TextFieldListener("^[á-úÁ-Úa-zA-Z0-9 ,_-]+$", anadirPrendaInterfaz.getPrenda());
        textFieldListeners[1] = new TextFieldListener("^[1-90*]+$", anadirPrendaInterfaz.getCantidad());

        anadirPrendaInterfaz.getPrenda().getDocument().addDocumentListener(textFieldListeners[0]);
        anadirPrendaInterfaz.getCantidad().getDocument().addDocumentListener(textFieldListeners[1]);

        anadirPrendaInterfaz.getPrenda().addFocusListener(textFieldListeners[0]);
        anadirPrendaInterfaz.getCantidad().addFocusListener(textFieldListeners[1]);

        anadirPrendaInterfaz.getCantidad().setText("1");

        anadirPrendaInterfaz.getOk().addActionListener(this);

        loadComboModel();

    }

    /**
     * Establece el renderer y los ítems para este JComboBox.
     */
    private void loadComboModel()
    {

        anadirPrendaInterfaz.getTiposPrenda().setRenderer(new ComboRenderer());
        anadirPrendaInterfaz.getTiposPrenda().setModel(loadComboItems());

    }

    /**
     * Carga los ítems que muestra este JComoBox.
     *
     * @return El DefaultComboBoxModel con los elementos cargados.
     */
    private DefaultComboBoxModel<ComboRenderer.ComboItem> loadComboItems()
    {

        DefaultComboBoxModel<ComboRenderer.ComboItem> dm = new DefaultComboBoxModel<>();
        tiposPrendas = getTiposPrendas();

        for (int i = tiposPrendas.isEmpty() ? -1 : 0; i < tiposPrendas.size(); i++)
            dm.addElement(new ComboRenderer.ComboItem(Utilities.getIcon("/com/src/images/tshirtCombo.png"),
                    tiposPrendas.isEmpty() ? "No existen tipos de prendas" : tiposPrendas.get(i)));

        return dm;

    }

    /**
     * Gestiona los eventos que ocurren cuando se presiona un botón.
     *
     * @param e El objeto de tipo ActionEvent que se crea cuando se presiona un botón en esta interfaz.
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {

        if (!todosLosCampoValidos()) // Si alguno de los campo es inválido, se muestra la siguiente error.
            JOptionPane.showMessageDialog(anadirPrendaInterfaz, "Alguno de los campos no es válido", "Campo no válido", JOptionPane.ERROR_MESSAGE);

        else
        {

            if (!isModificandoPrenda()) // Si no se está modificando una prenda la añadimos a la tabla, en caso contrario, la modificamos.
                prendasController.anadirPrenda(getPrenda());

            else
                prendasController.modificarPrenda(prendaAModificar, getPrenda());

            prendasController.getPrendasInterfaz().setVisible(true);

            anadirPrendaInterfaz.dispose();

        }

    }

    /**
     * Rellenamos los campos con valores por defecto (cuando se está modificando una prenda).
     *
     * @param prenda La prenda con la cual rellenaremos los campos.
     */
    public void establecerPrendaDefecto(Prenda prenda)
    {

        setModificandoPrenda(true);

        prendaAModificar = prenda;

        anadirPrendaInterfaz.getPrenda().setText(prenda.getDescripcion());
        anadirPrendaInterfaz.getCantidad().setText(String.valueOf(prenda.getCantidad()));
        anadirPrendaInterfaz.getTiposPrenda().setSelectedIndex(getIndexTipoPrenda(prenda));

    }

    /**
     * Obtenemos la prenda a partir de las datos insertados por el usuario.
     *
     * @return La prenda que se obtiene de los datos de los campos.
     */
    private Prenda getPrenda()
    {

        return new Prenda(anadirPrendaInterfaz.getPrenda().getText(),
                tiposPrendas.isEmpty() ? " " : tiposPrendas.get(anadirPrendaInterfaz.getTiposPrenda().getSelectedIndex()),
                Integer.parseInt(anadirPrendaInterfaz.getCantidad().getText()));

    }

    /**
     * Retornamos el índice del tipo de prenda que estamos modificando.
     *
     * @param prenda La prenda a la cual queremos saber su tipo.
     *
     * @return El índice del tipo de prenda para la prenda que estamos modificando.
     */
    private int getIndexTipoPrenda(Prenda prenda)
    {

        for (int i = 0; i < tiposPrendas.size(); i++)
            if (tiposPrendas.get(i).equals(prenda.getTipo()))
                return i;

        return 0;

    }

    /**
     * Valida si todos los campos insertados por el usuario son válidos.
     *
     * @return <code> verdadero </code> si todos los campos son correctos, <code>falso</code> en caso contrario.
     */
    private boolean todosLosCampoValidos()
    {

        for (TextFieldListener textFieldListener : textFieldListeners)
            if (!textFieldListener.isValido())
                return false;

        return true;

    }

    private ArrayList<String> getTiposPrendas()
    {
        return (ArrayList<String>) new DAO(DAO.RUTA_TIPOSPRENDAS).getObjects();
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
