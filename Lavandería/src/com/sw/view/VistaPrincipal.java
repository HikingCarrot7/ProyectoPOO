package com.sw.view;

import com.sw.renderer.ComboRenderer.ComboItem;
import com.sw.renderer.ListRenderer.ListItem;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Mohammed
 */
public class VistaPrincipal extends javax.swing.JFrame
{

    public VistaPrincipal()
    {

        initWindow();

        initMyComponents();

        initComponents();

    }

    private void initWindow()
    {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex)
        {
            System.out.println(ex.getMessage());
        }
        //</editor-fold>

    }

    private void initMyComponents()
    {

        verPrendasEnCola = new ArrayList<>();
        moverLavadoEnCola = new ArrayList<>();
        eliminarEnCola = new ArrayList<>();

        verPrendasEnProceso = new ArrayList<>();
        subirColaEnProceso = new ArrayList<>();
        moverTerminadoEnProceso = new ArrayList<>();
        eliminarEnProceso = new ArrayList<>();

        verPrendasTerminado = new ArrayList<>();
        subirProcesoTerminado = new ArrayList<>();
        generarTicket = new ArrayList<>();
        eliminarTerminado = new ArrayList<>();

        timersScreen = new ArrayList<>();

    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        nuevoServicio = new javax.swing.JButton();
        panelPrincipal = new javax.swing.JTabbedPane();
        scrollTablaEnCola = new javax.swing.JScrollPane();
        enCola = new javax.swing.JTable();
        scrollTablaEnProceso = new javax.swing.JScrollPane();
        enProceso = new javax.swing.JTable();
        scrollTablaTerminado = new javax.swing.JScrollPane();
        terminado = new javax.swing.JTable();
        scrollLista = new javax.swing.JScrollPane();
        lista = new javax.swing.JList<>();
        buscar = new javax.swing.JTextField();
        editar = new javax.swing.JButton();
        logo = new javax.swing.JLabel();
        verHIstorial = new javax.swing.JButton();
        verClientes = new javax.swing.JButton();
        ordenarPorLabel = new javax.swing.JLabel();
        ordenarPor = new javax.swing.JComboBox<>();
        display = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        config = new javax.swing.JMenu();
        configurar = new javax.swing.JMenuItem();
        utilidades = new javax.swing.JMenu();
        juegos = new javax.swing.JMenu();
        wave = new javax.swing.JMenuItem();
        satyrrun = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Lavandería");
        setBackground(new java.awt.Color(204, 204, 204));
        setLocation(new java.awt.Point(0, 0));
        setMaximumSize(new java.awt.Dimension(1210, 780));
        setMinimumSize(new java.awt.Dimension(1210, 780));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nuevoServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/src/images/nuevo.png"))); // NOI18N
        nuevoServicio.setToolTipText("Nuevo servicio");
        nuevoServicio.setActionCommand("Nuevo servicio");
        nuevoServicio.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        nuevoServicio.setMaximumSize(new java.awt.Dimension(200, 75));
        nuevoServicio.setMinimumSize(new java.awt.Dimension(200, 75));
        nuevoServicio.setPreferredSize(new java.awt.Dimension(200, 100));
        getContentPane().add(nuevoServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 20, -1, 35));

        panelPrincipal.setBackground(new java.awt.Color(204, 204, 204));
        panelPrincipal.setMaximumSize(new java.awt.Dimension(1180, 700));
        panelPrincipal.setPreferredSize(new java.awt.Dimension(1180, 700));

        scrollTablaEnCola.setBackground(new java.awt.Color(204, 204, 204));

        enCola.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null}
            },
            new String []
            {
                null
            }
        )
    );
    enCola.setRowHeight(25);
    scrollTablaEnCola.setViewportView(enCola);

    panelPrincipal.addTab("En cola", new javax.swing.ImageIcon(getClass().getResource("/com/src/images/fila.png")), scrollTablaEnCola, "Conjunto de prendas que está en la cola."); // NOI18N
    scrollTablaEnCola.getAccessibleContext().setAccessibleName("");

    scrollTablaEnProceso.setBackground(new java.awt.Color(204, 204, 204));

    enProceso.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][]
        {
            {null}

        },
        new String []
        {
            null
        }
    )
    );
    scrollTablaEnProceso.setViewportView(enProceso);

    panelPrincipal.addTab("En proceso", new javax.swing.ImageIcon(getClass().getResource("/com/src/images/proceso.png")), scrollTablaEnProceso, "Conjunto de prendas que se encuentra en proceso."); // NOI18N

    terminado.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][]
        {
            {null},

        },
        new String []
        {
            null
        }
    )
    );
    scrollTablaTerminado.setViewportView(terminado);
    terminado.getAccessibleContext().setAccessibleName("Terminado");

    panelPrincipal.addTab("Terminado", new javax.swing.ImageIcon(getClass().getResource("/com/src/images/terminado.png")), scrollTablaTerminado, "Conjunto de prendas listas para empaquetar."); // NOI18N

    getContentPane().add(panelPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 136, 1210, 640));
    panelPrincipal.getAccessibleContext().setAccessibleName("panelPrincipal");

    scrollLista.setMaximumSize(new java.awt.Dimension(546, 65));
    scrollLista.setMinimumSize(new java.awt.Dimension(546, 65));
    scrollLista.setPreferredSize(new java.awt.Dimension(546, 65));

    lista.setModel(new javax.swing.AbstractListModel<ListItem>()
        {
            ListItem[] strings = { null };
            public int getSize() { return strings.length; }
            public ListItem getElementAt(int i) { return strings[i]; }
        });
        scrollLista.setViewportView(lista);

        getContentPane().add(scrollLista, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 58, -1, 90));

        buscar.setToolTipText("Buscar servicio");
        buscar.setAlignmentX(0.0F);
        buscar.setAlignmentY(0.0F);
        buscar.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar servicio"));
        buscar.setMaximumSize(new java.awt.Dimension(550, 40));
        buscar.setMinimumSize(new java.awt.Dimension(550, 40));
        buscar.setPreferredSize(new java.awt.Dimension(550, 40));
        getContentPane().add(buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 550, 60));

        editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/src/images/edit.png"))); // NOI18N
        editar.setToolTipText("Editar servicio");
        editar.setActionCommand("Editar");
        editar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        editar.setMaximumSize(new java.awt.Dimension(200, 75));
        editar.setMinimumSize(new java.awt.Dimension(200, 75));
        editar.setPreferredSize(new java.awt.Dimension(200, 100));
        getContentPane().add(editar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 60, -1, 35));

        logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/src/images/logo.jpg"))); // NOI18N
        getContentPane().add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 30, 215, 95));

        verHIstorial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/src/images/historial.png"))); // NOI18N
        verHIstorial.setToolTipText("Ver historial");
        verHIstorial.setActionCommand("Historial");
        verHIstorial.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        verHIstorial.setMaximumSize(new java.awt.Dimension(200, 35));
        verHIstorial.setMinimumSize(new java.awt.Dimension(200, 35));
        verHIstorial.setPreferredSize(new java.awt.Dimension(95, 35));
        getContentPane().add(verHIstorial, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 100, -1, -1));

        verClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/src/images/clienteCombo.png"))); // NOI18N
        verClientes.setToolTipText("Ver clientes");
        verClientes.setActionCommand("Ver clientes");
        verClientes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        verClientes.setMaximumSize(new java.awt.Dimension(200, 35));
        verClientes.setMinimumSize(new java.awt.Dimension(200, 35));
        verClientes.setPreferredSize(new java.awt.Dimension(95, 35));
        getContentPane().add(verClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(1105, 100, -1, -1));

        ordenarPorLabel.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        ordenarPorLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ordenarPorLabel.setText("Ordenar por:");
        ordenarPorLabel.setMaximumSize(new java.awt.Dimension(90, 30));
        ordenarPorLabel.setMinimumSize(new java.awt.Dimension(90, 30));
        ordenarPorLabel.setPreferredSize(new java.awt.Dimension(90, 30));
        getContentPane().add(ordenarPorLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        ordenarPor.setModel(new javax.swing.DefaultComboBoxModel<>(new ComboItem[] { null }));
        ordenarPor.setToolTipText("Ordenar servicios por...");
        ordenarPor.setLightWeightPopupEnabled(false);
        ordenarPor.setMaximumSize(new java.awt.Dimension(80, 30));
        ordenarPor.setMinimumSize(new java.awt.Dimension(80, 30));
        ordenarPor.setPreferredSize(new java.awt.Dimension(80, 30));
        getContentPane().add(ordenarPor, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 120, -1));

        display.setFont(new java.awt.Font("Tahoma", 0, 40)); // NOI18N
        display.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        display.setMaximumSize(new java.awt.Dimension(230, 60));
        display.setMinimumSize(new java.awt.Dimension(230, 60));
        display.setPreferredSize(new java.awt.Dimension(230, 60));
        getContentPane().add(display, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, -1, -1));

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/src/images/fondo.jpg"))); // NOI18N
        fondo.setToolTipText("");
        getContentPane().add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1210, 780));

        config.setText("Configuración");
        config.setActionCommand("Config");

        configurar.setText("Configurar");
        configurar.setActionCommand("Config");
        config.add(configurar);

        menuBar.add(config);

        utilidades.setText("Utilidades");
        menuBar.add(utilidades);

        juegos.setText("Juegos");

        wave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/src/images/wave.png"))); // NOI18N
        wave.setText("Wave");
        wave.setToolTipText("Wave");
        wave.setActionCommand("wave");
        juegos.add(wave);

        satyrrun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/src/images/LD27.png"))); // NOI18N
        satyrrun.setText("Satyr run!");
        juegos.add(satyrrun);

        menuBar.add(juegos);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JTable getTablaEnCola()
    {
        return enCola;
    }

    public static JTable getTablaEnProceso()
    {
        return enProceso;
    }

    public JTable getTablaTerminado()
    {
        return terminado;
    }

    public ArrayList<JButton> getVerPrendasEnCola()
    {
        return verPrendasEnCola;
    }

    public ArrayList<JButton> getMoverLavadoEnCola()
    {
        return moverLavadoEnCola;
    }

    public ArrayList<JButton> getEliminarEnCola()
    {
        return eliminarEnCola;
    }

    public ArrayList<JButton> getVerPrendasEnProceso()
    {
        return verPrendasEnProceso;
    }

    public ArrayList<JButton> getSubirColaEnProceso()
    {
        return subirColaEnProceso;
    }

    public ArrayList<JButton> getMoverTerminadoEnProceso()
    {
        return moverTerminadoEnProceso;
    }

    public ArrayList<JButton> getEliminarEnProceso()
    {
        return eliminarEnProceso;
    }

    public ArrayList<JButton> getVerPrendasTerminado()
    {
        return verPrendasTerminado;
    }

    public ArrayList<JButton> getSubirProcesoTerminado()
    {
        return subirProcesoTerminado;
    }

    public ArrayList<JButton> getGenerarTicket()
    {
        return generarTicket;
    }

    public ArrayList<JButton> getEliminarTerminado()
    {
        return eliminarTerminado;
    }

    public JTextField getBuscar()
    {
        return buscar;
    }

    public JButton getNuevoServicio()
    {
        return nuevoServicio;
    }

    public JButton getVerClientes()
    {
        return verClientes;
    }

    public JButton getVerHIstorial()
    {
        return verHIstorial;
    }

    public JButton getEditar()
    {
        return editar;
    }

    public JMenuItem getConfigurar()
    {
        return configurar;
    }

    public JMenuItem getWave()
    {
        return wave;
    }

    public JMenuItem getSatyrrun()
    {
        return satyrrun;
    }

    public JTabbedPane getPanelPrincipal()
    {
        return panelPrincipal;
    }

    public JComboBox<ComboItem> getOrdenarPor()
    {
        return ordenarPor;
    }

    public JScrollPane getScrollTablaEnCola()
    {
        return scrollTablaEnCola;
    }

    public JScrollPane getScrollTablaEnProceso()
    {
        return scrollTablaEnProceso;
    }

    public JScrollPane getScrollTablaTerminado()
    {
        return scrollTablaTerminado;
    }

    public JLabel getDisplay()
    {
        return display;
    }

    public ArrayList<JLabel> getTimersScreen()
    {
        return timersScreen;
    }

    public JList<ListItem> getLista()
    {
        return lista;
    }

    public JScrollPane getScrollLista()
    {
        return scrollLista;
    }

    private ArrayList<JButton> verPrendasEnCola;
    private ArrayList<JButton> moverLavadoEnCola;
    private ArrayList<JButton> eliminarEnCola;

    private ArrayList<JButton> verPrendasEnProceso;
    private ArrayList<JButton> subirColaEnProceso;
    private ArrayList<JButton> moverTerminadoEnProceso;
    private ArrayList<JButton> eliminarEnProceso;

    private ArrayList<JButton> verPrendasTerminado;
    private ArrayList<JButton> subirProcesoTerminado;
    private ArrayList<JButton> generarTicket;
    private ArrayList<JButton> eliminarTerminado;

    private ArrayList<JLabel> timersScreen;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField buscar;
    private javax.swing.JMenu config;
    private javax.swing.JMenuItem configurar;
    private javax.swing.JLabel display;
    private javax.swing.JButton editar;
    private javax.swing.JTable enCola;
    private static javax.swing.JTable enProceso;
    private javax.swing.JLabel fondo;
    private javax.swing.JMenu juegos;
    private javax.swing.JList<ListItem> lista;
    private javax.swing.JLabel logo;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JButton nuevoServicio;
    private javax.swing.JComboBox<ComboItem> ordenarPor;
    private javax.swing.JLabel ordenarPorLabel;
    private javax.swing.JTabbedPane panelPrincipal;
    private javax.swing.JMenuItem satyrrun;
    private javax.swing.JScrollPane scrollLista;
    private javax.swing.JScrollPane scrollTablaEnCola;
    private javax.swing.JScrollPane scrollTablaEnProceso;
    private javax.swing.JScrollPane scrollTablaTerminado;
    private javax.swing.JTable terminado;
    private javax.swing.JMenu utilidades;
    private javax.swing.JButton verClientes;
    private javax.swing.JButton verHIstorial;
    private javax.swing.JMenuItem wave;
    // End of variables declaration//GEN-END:variables

}
