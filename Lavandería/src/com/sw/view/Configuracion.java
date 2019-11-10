package com.sw.view;

/**
 *
 * @author Mohammed
 */
public class Configuracion extends javax.swing.JFrame
{

    public Configuracion()
    {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        precio = new javax.swing.JTextField();
        precioValido = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        colorChooser = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Configuración");
        setMaximumSize(new java.awt.Dimension(500, 370));
        setMinimumSize(new java.awt.Dimension(500, 370));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/src/images/config.png"))); // NOI18N
        jLabel2.setText("Configuración");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Precio por kg:");
        jLabel3.setToolTipText("");
        jLabel3.setMaximumSize(new java.awt.Dimension(140, 30));
        jLabel3.setMinimumSize(new java.awt.Dimension(140, 30));
        jLabel3.setPreferredSize(new java.awt.Dimension(140, 30));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 170, -1));

        precio.setMaximumSize(new java.awt.Dimension(140, 30));
        precio.setMinimumSize(new java.awt.Dimension(140, 30));
        precio.setPreferredSize(new java.awt.Dimension(140, 30));
        getContentPane().add(precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, -1, -1));

        precioValido.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        precioValido.setMaximumSize(new java.awt.Dimension(110, 30));
        precioValido.setMinimumSize(new java.awt.Dimension(110, 30));
        precioValido.setPreferredSize(new java.awt.Dimension(110, 30));
        getContentPane().add(precioValido, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 110, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Tipos de prendas:");
        jLabel4.setMaximumSize(new java.awt.Dimension(140, 30));
        jLabel4.setMinimumSize(new java.awt.Dimension(140, 30));
        jLabel4.setPreferredSize(new java.awt.Dimension(140, 30));
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 170, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/src/images/tshirt.png"))); // NOI18N
        jButton1.setToolTipText("Añadir tipos de prendas");
        jButton1.setMaximumSize(new java.awt.Dimension(90, 40));
        jButton1.setMinimumSize(new java.awt.Dimension(90, 40));
        jButton1.setPreferredSize(new java.awt.Dimension(90, 40));
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Color de las tablas:");
        jLabel5.setMaximumSize(new java.awt.Dimension(140, 30));
        jLabel5.setMinimumSize(new java.awt.Dimension(140, 30));
        jLabel5.setPreferredSize(new java.awt.Dimension(140, 30));
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 170, -1));

        colorChooser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/src/images/color.png"))); // NOI18N
        colorChooser.setMaximumSize(new java.awt.Dimension(90, 40));
        colorChooser.setMinimumSize(new java.awt.Dimension(90, 40));
        colorChooser.setPreferredSize(new java.awt.Dimension(90, 40));
        getContentPane().add(colorChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, -1, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/src/images/ok.png"))); // NOI18N
        jButton2.setMaximumSize(new java.awt.Dimension(90, 40));
        jButton2.setMinimumSize(new java.awt.Dimension(90, 40));
        jButton2.setPreferredSize(new java.awt.Dimension(90, 40));
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 290, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/src/images/fondo.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 370));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            System.out.println(ex.getMessage());
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() ->
        {
            Configuracion config = new Configuracion();

            config.setVisible(true);
            config.setLocationRelativeTo(null);

        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton colorChooser;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField precio;
    private javax.swing.JLabel precioValido;
    // End of variables declaration//GEN-END:variables
}
