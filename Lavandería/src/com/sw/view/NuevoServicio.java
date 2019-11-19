package com.sw.view;

import com.sw.renderer.ComboRenderer.ComboItem;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSpinner;

/**
 *
 * @author Mohammed
 */
public class NuevoServicio extends javax.swing.JFrame
{

    public NuevoServicio()
    {

        initWindow();

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

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        titleLabel = new javax.swing.JLabel();
        clienteLabel = new javax.swing.JLabel();
        clientes = new javax.swing.JComboBox<>();
        prendasLabel = new javax.swing.JLabel();
        addCliente = new javax.swing.JButton();
        anadirPrendas = new javax.swing.JButton();
        tiempoEstimadoLabel = new javax.swing.JLabel();
        horas = new javax.swing.JSpinner();
        horasLabel = new javax.swing.JLabel();
        minutos = new javax.swing.JSpinner();
        minutosLabel = new javax.swing.JLabel();
        segundos = new javax.swing.JSpinner();
        segundosLabel = new javax.swing.JLabel();
        ok = new javax.swing.JButton();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nuevo servicio");
        setMaximumSize(new java.awt.Dimension(385, 350));
        setMinimumSize(new java.awt.Dimension(385, 350));
        setName("nuevoServicio"); // NOI18N
        setPreferredSize(new java.awt.Dimension(385, 350));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titleLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/src/images/servicioTitle.png"))); // NOI18N
        titleLabel.setText("Nuevo servicio");
        titleLabel.setToolTipText("Nuevo servicio");
        getContentPane().add(titleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 250, -1));

        clienteLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        clienteLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        clienteLabel.setText("Cliente:");
        clienteLabel.setPreferredSize(new java.awt.Dimension(70, 25));
        getContentPane().add(clienteLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        clientes.setModel(new javax.swing.DefaultComboBoxModel<>(new ComboItem[] { null }));
        getContentPane().add(clientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 170, 30));

        prendasLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        prendasLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        prendasLabel.setText("Prendas:");
        prendasLabel.setPreferredSize(new java.awt.Dimension(70, 25));
        getContentPane().add(prendasLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        addCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/src/images/add.png"))); // NOI18N
        addCliente.setToolTipText("Añadir cliente");
        addCliente.setActionCommand("addCliente");
        getContentPane().add(addCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 90, 30));

        anadirPrendas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/src/images/tshirt.png"))); // NOI18N
        anadirPrendas.setToolTipText("Añadir prendas");
        anadirPrendas.setActionCommand("addPrendas");
        getContentPane().add(anadirPrendas, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 125, 30));

        tiempoEstimadoLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tiempoEstimadoLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tiempoEstimadoLabel.setText("Tiempo estimado:");
        getContentPane().add(tiempoEstimadoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        horas.setModel(new javax.swing.SpinnerNumberModel(1, 0, 96, 1));
        horas.setToolTipText("Horas");
        horas.setMaximumSize(new java.awt.Dimension(35, 20));
        horas.setMinimumSize(new java.awt.Dimension(35, 20));
        horas.setPreferredSize(new java.awt.Dimension(35, 20));
        getContentPane().add(horas, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 50, 30));

        horasLabel.setText("Horas");
        getContentPane().add(horasLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, -1, -1));

        minutos.setModel(new javax.swing.SpinnerNumberModel(0, 0, 59, 1));
        minutos.setToolTipText("Minutos");
        minutos.setMaximumSize(new java.awt.Dimension(35, 20));
        minutos.setMinimumSize(new java.awt.Dimension(35, 20));
        minutos.setPreferredSize(new java.awt.Dimension(35, 20));
        getContentPane().add(minutos, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, 50, 30));

        minutosLabel.setText("Minutos");
        getContentPane().add(minutosLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, -1, -1));

        segundos.setModel(new javax.swing.SpinnerNumberModel(0, 0, 59, 1));
        segundos.setToolTipText("Segundos");
        segundos.setMaximumSize(new java.awt.Dimension(35, 20));
        segundos.setMinimumSize(new java.awt.Dimension(35, 20));
        segundos.setPreferredSize(new java.awt.Dimension(35, 20));
        getContentPane().add(segundos, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, 50, 30));

        segundosLabel.setText("Segundos");
        getContentPane().add(segundosLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, -1, -1));

        ok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/src/images/ok.png"))); // NOI18N
        ok.setToolTipText("Añadir");
        ok.setActionCommand("ok");
        ok.setMaximumSize(new java.awt.Dimension(80, 40));
        ok.setMinimumSize(new java.awt.Dimension(80, 40));
        ok.setPreferredSize(new java.awt.Dimension(80, 40));
        getContentPane().add(ok, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, -1, -1));

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/src/images/fondo.jpg"))); // NOI18N
        getContentPane().add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 350));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JButton getAddCliente()
    {
        return addCliente;
    }

    public JButton getAnadirPrendas()
    {
        return anadirPrendas;
    }

    public JComboBox<ComboItem> getClientes()
    {
        return clientes;
    }

    public JSpinner getHoras()
    {
        return horas;
    }

    public JSpinner getMinutos()
    {
        return minutos;
    }

    public JButton getOk()
    {
        return ok;
    }

    public JSpinner getSegundos()
    {
        return segundos;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addCliente;
    private javax.swing.JButton anadirPrendas;
    private javax.swing.JLabel clienteLabel;
    private javax.swing.JComboBox<ComboItem> clientes;
    private javax.swing.JLabel fondo;
    private javax.swing.JSpinner horas;
    private javax.swing.JLabel horasLabel;
    private javax.swing.JSpinner minutos;
    private javax.swing.JLabel minutosLabel;
    private javax.swing.JButton ok;
    private javax.swing.JLabel prendasLabel;
    private javax.swing.JSpinner segundos;
    private javax.swing.JLabel segundosLabel;
    private javax.swing.JLabel tiempoEstimadoLabel;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
