package com.sw.view;

import com.sw.controller.TableManager;
import com.sw.renderer.TableCellRenderer;
import com.sw.renderer.TableHeaderRenderer;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Mohammed
 */
public class Prendas extends javax.swing.JFrame
{

    /**
     * Creates new form PrendasInterfaz
     */
    public Prendas()
    {
        initMyComponents();

        initComponents();

        renderTable();

    }

    private void initMyComponents()
    {

        tipoPrenda = new ArrayList<>();
        eliminar = new ArrayList<>();

        for (int i = 0; i < 10; i++)
        {
            tipoPrenda.add(new JButton(new ImageIcon(getClass().getResource("/com/src/images/tshirt.png"))));
            eliminar.add(new JButton(new ImageIcon(getClass().getResource("/com/src/images/delete.png"))));

        }

    }

    private void renderTable()
    {

        TableCellRenderer cellRenderer = new TableCellRenderer();
        prendas.setDefaultRenderer(Object.class, cellRenderer);
        JTableHeader jTableHeader = prendas.getTableHeader();
        jTableHeader.setDefaultRenderer(new TableHeaderRenderer());
        prendas.setTableHeader(jTableHeader);

        TableManager tableManager = new TableManager();
        Object[][] items = new Object[10][4];

        prendas.setModel(new DefaultTableModel(tableManager.getItems(tableManager.getItems(items, tipoPrenda, 1), eliminar, 3), new String[]
        {

            "Prenda", "Tipo de prenda", "Cantidad (piezas)", "Eliminar"

        }));

        prendas.addMouseMotionListener(new MouseMotionListener()
        {
            @Override
            public void mouseDragged(MouseEvent e)
            {

            }

            @Override
            public void mouseMoved(MouseEvent e)
            {

                cellRenderer.setX(e.getX());
                cellRenderer.setY(e.getY());

            }

        });

        prendas.setName("Prendas");
        prendas.revalidate();

    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        scrollPrendas = new javax.swing.JScrollPane();
        prendas = new javax.swing.JTable();
        addPrenda = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        totalKg = new javax.swing.JTextField();
        totalPrecio = new javax.swing.JTextField();
        totalPiezas = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        editarPrenda = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Añadir prendas");
        setMaximumSize(new java.awt.Dimension(579, 700));
        setMinimumSize(new java.awt.Dimension(579, 700));
        setName("prendasInterfaz"); // NOI18N
        setPreferredSize(new java.awt.Dimension(579, 700));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scrollPrendas.setMaximumSize(new java.awt.Dimension(575, 550));
        scrollPrendas.setMinimumSize(new java.awt.Dimension(575, 550));
        scrollPrendas.setPreferredSize(new java.awt.Dimension(575, 550));

        prendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null},

            },
            new String []
            {
                null, null, null, null
            }
        )
        {
            Class[] types = new Class []
            {
                java.lang.Object.class, Object.class, java.lang.Integer.class, Object.class
            };

            public Class getColumnClass(int columnIndex)
            {
                return types [columnIndex];
            }
        });
        scrollPrendas.setViewportView(prendas);

        getContentPane().add(scrollPrendas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, -1, 550));

        addPrenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/src/images/add.png"))); // NOI18N
        addPrenda.setToolTipText("Añadir nueva prenda");
        addPrenda.setMaximumSize(new java.awt.Dimension(100, 80));
        addPrenda.setMinimumSize(new java.awt.Dimension(100, 80));
        addPrenda.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                addPrendaActionPerformed(evt);
            }
        });
        getContentPane().add(addPrenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 100, 40));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/src/images/kilos.png"))); // NOI18N
        jLabel1.setText(" Total kg        :");
        jLabel1.setMaximumSize(new java.awt.Dimension(105, 25));
        jLabel1.setMinimumSize(new java.awt.Dimension(105, 25));
        jLabel1.setPreferredSize(new java.awt.Dimension(105, 25));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/src/images/precio.png"))); // NOI18N
        jLabel2.setText(" Precio total   :");
        jLabel2.setMaximumSize(new java.awt.Dimension(105, 25));
        jLabel2.setMinimumSize(new java.awt.Dimension(105, 25));
        jLabel2.setPreferredSize(new java.awt.Dimension(105, 25));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/src/images/tshirt.png"))); // NOI18N
        jLabel3.setText(" Piezas           :");
        jLabel3.setMaximumSize(new java.awt.Dimension(105, 25));
        jLabel3.setMinimumSize(new java.awt.Dimension(105, 25));
        jLabel3.setPreferredSize(new java.awt.Dimension(105, 25));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        totalKg.setText("Total kg...");
        totalKg.setEnabled(false);
        getContentPane().add(totalKg, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 100, -1));

        totalPrecio.setText("Precio total...");
        totalPrecio.setEnabled(false);
        getContentPane().add(totalPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 100, -1));

        totalPiezas.setText("Total piezas...");
        totalPiezas.setEnabled(false);
        getContentPane().add(totalPiezas, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 100, -1));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/src/images/logo.jpg"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 230, -1));

        editarPrenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/src/images/edit.png"))); // NOI18N
        editarPrenda.setToolTipText("Editar prenda");
        editarPrenda.setMaximumSize(new java.awt.Dimension(100, 40));
        editarPrenda.setMinimumSize(new java.awt.Dimension(100, 40));
        editarPrenda.setPreferredSize(new java.awt.Dimension(100, 40));
        editarPrenda.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editarPrendaActionPerformed(evt);
            }
        });
        getContentPane().add(editarPrenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 60, 100, 40));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/src/images/fondo.jpg"))); // NOI18N
        jLabel5.setMaximumSize(new java.awt.Dimension(600, 700));
        jLabel5.setMinimumSize(new java.awt.Dimension(600, 700));
        jLabel5.setPreferredSize(new java.awt.Dimension(600, 700));
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 580, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addPrendaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_addPrendaActionPerformed
    {//GEN-HEADEREND:event_addPrendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addPrendaActionPerformed

    private void editarPrendaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editarPrendaActionPerformed
    {//GEN-HEADEREND:event_editarPrendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editarPrendaActionPerformed

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
            java.util.logging.Logger.getLogger(Prendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() ->
        {

            Prendas prendas = new Prendas();

            prendas.setVisible(true);
            prendas.setLocationRelativeTo(null);

        });

    }

    private ArrayList<JButton> tipoPrenda;
    private ArrayList<JButton> eliminar;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addPrenda;
    private javax.swing.JButton editarPrenda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTable prendas;
    private javax.swing.JScrollPane scrollPrendas;
    private javax.swing.JTextField totalKg;
    private javax.swing.JTextField totalPiezas;
    private javax.swing.JTextField totalPrecio;
    // End of variables declaration//GEN-END:variables
}
