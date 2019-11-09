package com.sw.view;

import com.sw.controller.TableManager;
import com.sw.input.MouseMotionManager;
import com.sw.renderer.TableCellRenderer;
import com.sw.renderer.TableHeaderRenderer;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Mohammed
 */
public class VistaPrincipal extends javax.swing.JFrame
{

    public VistaPrincipal()
    {

        initMyComponents();

        initComponents();

        renderTableEnCola(new TableManager());

        renderTableEnProceso(new TableManager());

        renderTableTerminado(new TableManager());

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
        empaquetado = new ArrayList<>();
        eliminarTerminado = new ArrayList<>();

        for (int i = 0; i < 10; i++)
        {
            verPrendasEnCola.add(new JButton(getIcon("/com/src/images/tshirt.png")));
            moverLavadoEnCola.add(new JButton(getIcon("/com/src/images/down.png")));
            eliminarEnCola.add(new JButton(getIcon("/com/src/images/delete.png")));

        }

        for (int i = 0; i < 10; i++)
        {
            verPrendasEnProceso.add(new JButton(getIcon("/com/src/images/tshirt.png")));
            subirColaEnProceso.add(new JButton(getIcon("/com/src/images/up.png")));
            moverTerminadoEnProceso.add(new JButton(getIcon("/com/src/images/down.png")));
            eliminarEnProceso.add(new JButton(getIcon("/com/src/images/delete.png")));

        }

        for (int i = 0; i < 10; i++)
        {
            verPrendasTerminado.add(new JButton(getIcon("/com/src/images/tshirt.png")));
            subirProcesoTerminado.add(new JButton(getIcon("/com/src/images/up.png")));
            eliminarTerminado.add(new JButton(getIcon("/com/src/images/delete.png")));
            empaquetado.add(new JCheckBox());

        }

    }

    private void renderTableEnCola(TableManager tableManager)
    {

        Object[][] items = new Object[10][6];
        TableCellRenderer tableCellRenderer = new TableCellRenderer();

        renderTableHeader(enCola, tableCellRenderer, "En cola");

        enCola.setModel(new DefaultTableModel(tableManager.getItems(tableManager.getItems(tableManager.getItems(items, verPrendasEnCola, 1), moverLavadoEnCola, 4), eliminarEnCola, 5), new String[]
        {

            "Cliente", "Prendas", "Kilos", "Total", "Mover a lavado", "Eliminar"

        }));

        enCola.addMouseMotionListener(new MouseMotionManager(tableCellRenderer));

    }

    private void renderTableEnProceso(TableManager tableManager)
    {
        Object[][] items = new Object[10][7];
        TableCellRenderer tableCellRenderer = new TableCellRenderer();

        renderTableHeader(enProceso, tableCellRenderer, "En proceso");

        enProceso.setModel(new DefaultTableModel(tableManager.getItems(tableManager.getItems(tableManager.getItems(tableManager.getItems(items, verPrendasEnProceso, 1), subirColaEnProceso, 4), moverTerminadoEnProceso, 5), eliminarEnProceso, 6), new String[]
        {

            "Cliente", "Prendas", "Kilos", "Tiempo estimado", "Subir a la cola", "Mover a terminado", "Eliminar"

        }));

        enProceso.addMouseMotionListener(new MouseMotionManager(tableCellRenderer));

    }

    private void renderTableTerminado(TableManager tableManager)
    {

        Object[][] items = new Object[10][7];
        TableCellRenderer tableCellRenderer = new TableCellRenderer();

        renderTableHeader(terminado, tableCellRenderer, "Terminado");

        terminado.setModel(new DefaultTableModel(tableManager.getItems(tableManager.getItems(tableManager.getItems(tableManager.getItems(items, verPrendasTerminado, 1), subirProcesoTerminado, 4), empaquetado, 5), eliminarTerminado, 6), new String[]
        {

            "Cliente", "Prendas", "Kilos", "Total", "Subir a proceso", "¿Empaquetado?", "Eliminar"

        }));

        terminado.addMouseMotionListener(new MouseMotionManager(tableCellRenderer));

    }

    private void renderTableHeader(JTable table, TableCellRenderer tableCellRenderer, String name)
    {

        JTableHeader jTableHeader = table.getTableHeader();
        jTableHeader.setDefaultRenderer(new TableHeaderRenderer());
        table.setDefaultRenderer(Object.class, tableCellRenderer);
        table.setTableHeader(jTableHeader);

        table.setName(name);
        table.revalidate();

    }

    private ImageIcon getIcon(String ruta)
    {
        return new ImageIcon(getClass().getResource(ruta));
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jTextField1 = new javax.swing.JTextField();
        nuevoServicio = new javax.swing.JButton();
        panelPrincipal = new javax.swing.JTabbedPane();
        scrollTablaEnCola = new javax.swing.JScrollPane();
        enCola = new javax.swing.JTable();
        scrollTablaEnProceso = new javax.swing.JScrollPane();
        enProceso = new javax.swing.JTable();
        scrollTablaTerminado = new javax.swing.JScrollPane();
        terminado = new javax.swing.JTable();
        buscar = new javax.swing.JTextField();
        logo = new javax.swing.JLabel();
        buscarIcon = new javax.swing.JLabel();
        verHIstorial = new javax.swing.JButton();
        verClientes = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        config = new javax.swing.JMenu();
        utilidades = new javax.swing.JMenu();
        miscelaneos = new javax.swing.JMenu();
        juegos = new javax.swing.JMenu();
        wave = new javax.swing.JMenuItem();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Lavandería");
        setBackground(new java.awt.Color(204, 204, 204));
        setLocation(new java.awt.Point(0, 0));
        setMinimumSize(new java.awt.Dimension(1230, 800));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nuevoServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/src/images/nuevo.png"))); // NOI18N
        nuevoServicio.setToolTipText("Nuevo servicio");
        nuevoServicio.setMaximumSize(new java.awt.Dimension(200, 75));
        nuevoServicio.setMinimumSize(new java.awt.Dimension(200, 75));
        nuevoServicio.setPreferredSize(new java.awt.Dimension(200, 100));
        nuevoServicio.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                nuevoServicioActionPerformed(evt);
            }
        });
        getContentPane().add(nuevoServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 0, -1, 35));

        panelPrincipal.setBackground(new java.awt.Color(204, 204, 204));
        panelPrincipal.setMaximumSize(new java.awt.Dimension(1180, 700));
        panelPrincipal.setPreferredSize(new java.awt.Dimension(1180, 700));
        panelPrincipal.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                panelPrincipalMouseClicked(evt);
            }
        });

        scrollTablaEnCola.setBackground(new java.awt.Color(204, 204, 204));

        enCola.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null, null, null}
            },
            new String []
            {
                null, null, null, null, null, null
            }
        )
        {
            Class[] types = new Class []
            {
                java.lang.String.class, java.lang.Object.class, java.lang.Double.class, java.lang.Double.class, java.lang.Object.class, Object.class
            };

            public Class getColumnClass(int columnIndex)
            {
                return types [columnIndex];
            }

            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }

        });
        enCola.setRowHeight(25);
        enCola.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                enColaMouseClicked(evt);
            }
        });
        scrollTablaEnCola.setViewportView(enCola);

        panelPrincipal.addTab("En cola", null, scrollTablaEnCola, "Conjunto de prendas que está en la cola.");

        scrollTablaEnProceso.setBackground(new java.awt.Color(204, 204, 204));

        enProceso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null, null, null, null},

            },
            new String []
            {
                null, null, null, null, null, null, null
            }
        )
        {
            Class[] types = new Class []
            {
                java.lang.String.class, java.lang.Object.class, java.lang.Double.class, java.lang.Double.class, java.lang.Object.class, Object.class, Object.class
            };

            public Class getColumnClass(int columnIndex)
            {
                return types [columnIndex];
            }

            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }

        }
    );
    scrollTablaEnProceso.setViewportView(enProceso);

    panelPrincipal.addTab("En proceso", scrollTablaEnProceso);

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
    {
        Class[] types = new Class []
        {
            java.lang.String.class, java.lang.Object.class, java.lang.Double.class, java.lang.Double.class, java.lang.Object.class, Object.class
        };

        public Class getColumnClass(int columnIndex)
        {
            return types [columnIndex];
        }

        @Override
        public boolean isCellEditable(int row, int column)
        {
            return false;
        }

    });
    terminado.addMouseListener(new java.awt.event.MouseAdapter()
    {
        public void mouseClicked(java.awt.event.MouseEvent evt)
        {
            terminadoMouseClicked(evt);
        }
    });
    scrollTablaTerminado.setViewportView(terminado);

    panelPrincipal.addTab("Terminado", scrollTablaTerminado);

    getContentPane().add(panelPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1210, 676));

    buscar.setToolTipText("Buscar cliente");
    buscar.setAlignmentX(0.0F);
    buscar.setAlignmentY(0.0F);
    buscar.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar cliente"));
    buscar.setMaximumSize(new java.awt.Dimension(550, 40));
    buscar.setMinimumSize(new java.awt.Dimension(550, 40));
    buscar.setPreferredSize(new java.awt.Dimension(550, 40));
    getContentPane().add(buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(321, 11, 550, 60));

    logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/src/images/logo.jpg"))); // NOI18N
    getContentPane().add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 215, 95));

    buscarIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/src/images/search.png"))); // NOI18N
    getContentPane().add(buscarIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 20, 50, 40));

    verHIstorial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/src/images/historial.png"))); // NOI18N
    verHIstorial.setToolTipText("Ver historial");
    verHIstorial.setMaximumSize(new java.awt.Dimension(200, 35));
    verHIstorial.setMinimumSize(new java.awt.Dimension(200, 35));
    verHIstorial.setPreferredSize(new java.awt.Dimension(200, 35));
    verHIstorial.addActionListener(new java.awt.event.ActionListener()
    {
        public void actionPerformed(java.awt.event.ActionEvent evt)
        {
            verHIstorialActionPerformed(evt);
        }
    });
    getContentPane().add(verHIstorial, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 40, 200, 35));

    verClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/src/images/clienteCombo.png"))); // NOI18N
    verClientes.setToolTipText("Ver clientes");
    verClientes.setMaximumSize(new java.awt.Dimension(200, 35));
    verClientes.setMinimumSize(new java.awt.Dimension(200, 35));
    verClientes.setPreferredSize(new java.awt.Dimension(200, 35));
    verClientes.addActionListener(new java.awt.event.ActionListener()
    {
        public void actionPerformed(java.awt.event.ActionEvent evt)
        {
            verClientesActionPerformed(evt);
        }
    });
    getContentPane().add(verClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 80, 200, 35));

    jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/src/images/fondo.jpg"))); // NOI18N
    getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1210, 780));

    config.setText("Configuración");
    menuBar.add(config);

    utilidades.setText("Utilidades");
    menuBar.add(utilidades);

    miscelaneos.setText("Misceláneos");
    menuBar.add(miscelaneos);

    juegos.setText("Juegos");

    wave.setText("Wave");
    wave.setToolTipText("Wave");
    wave.addActionListener(new java.awt.event.ActionListener()
    {
        public void actionPerformed(java.awt.event.ActionEvent evt)
        {
            waveActionPerformed(evt);
        }
    });
    juegos.add(wave);

    menuBar.add(juegos);

    setJMenuBar(menuBar);

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void enColaMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_enColaMouseClicked
    {//GEN-HEADEREND:event_enColaMouseClicked

    }//GEN-LAST:event_enColaMouseClicked

    private void waveActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_waveActionPerformed
    {//GEN-HEADEREND:event_waveActionPerformed

    }//GEN-LAST:event_waveActionPerformed

    private void panelPrincipalMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_panelPrincipalMouseClicked
    {//GEN-HEADEREND:event_panelPrincipalMouseClicked

        scrollTablaEnProceso.revalidate();
        scrollTablaEnProceso.repaint();

        scrollTablaTerminado.revalidate();
        scrollTablaTerminado.repaint();

    }//GEN-LAST:event_panelPrincipalMouseClicked

    private void terminadoMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_terminadoMouseClicked
    {//GEN-HEADEREND:event_terminadoMouseClicked

    }//GEN-LAST:event_terminadoMouseClicked

    private void nuevoServicioActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_nuevoServicioActionPerformed
    {//GEN-HEADEREND:event_nuevoServicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nuevoServicioActionPerformed

    private void verHIstorialActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_verHIstorialActionPerformed
    {//GEN-HEADEREND:event_verHIstorialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_verHIstorialActionPerformed

    private void verClientesActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_verClientesActionPerformed
    {//GEN-HEADEREND:event_verClientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_verClientesActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
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
            java.util.logging.Logger.getLogger(VistaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() ->
        {

            VistaPrincipal vistaPrincipal = new VistaPrincipal();

            vistaPrincipal.setVisible(true);
            vistaPrincipal.setLocationRelativeTo(null);

        });

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
    private ArrayList<JCheckBox> empaquetado;
    private ArrayList<JButton> eliminarTerminado;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField buscar;
    private javax.swing.JLabel buscarIcon;
    private javax.swing.JMenu config;
    private javax.swing.JTable enCola;
    private javax.swing.JTable enProceso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JMenu juegos;
    private javax.swing.JLabel logo;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu miscelaneos;
    private javax.swing.JButton nuevoServicio;
    private javax.swing.JTabbedPane panelPrincipal;
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
