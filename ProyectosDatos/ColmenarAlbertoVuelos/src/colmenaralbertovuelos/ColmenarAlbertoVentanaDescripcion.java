package colmenaralbertovuelos;

import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

/**
 *
 * @author DAM2A-03
 */
public class ColmenarAlbertoVentanaDescripcion extends javax.swing.JFrame {

    private ColmenarAlbertoMVCVuelos MVCVuelos = new ColmenarAlbertoMVCVuelos();

    public ColmenarAlbertoVentanaDescripcion() {
        initComponents();
        MVCVuelos.getConnection();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botonBD = new javax.swing.JButton();
        botonTabla = new javax.swing.JButton();
        botonEstructura = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaDatos = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        botonBD.setBackground(new java.awt.Color(255, 255, 255));
        botonBD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/colmenaralbertovuelos/imagenes/bases-de-datos.png"))); // NOI18N
        botonBD.setText("Bases de datos");
        botonBD.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonBD.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBDActionPerformed(evt);
            }
        });

        botonTabla.setBackground(new java.awt.Color(255, 255, 255));
        botonTabla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/colmenaralbertovuelos/imagenes/mesa.png"))); // NOI18N
        botonTabla.setText("Tablas");
        botonTabla.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonTabla.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonTablaActionPerformed(evt);
            }
        });

        botonEstructura.setBackground(new java.awt.Color(255, 255, 255));
        botonEstructura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/colmenaralbertovuelos/imagenes/escuadra.png"))); // NOI18N
        botonEstructura.setText("Estructura");
        botonEstructura.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonEstructura.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonEstructura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEstructuraActionPerformed(evt);
            }
        });

        areaDatos.setColumns(20);
        areaDatos.setRows(5);
        jScrollPane1.setViewportView(areaDatos);

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setText("Descripción sobre los datos. METADATOS");
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(botonEstructura, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(botonBD, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(botonTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(botonBD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonEstructura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBDActionPerformed
        String datos = MVCVuelos.informaBD();
        areaDatos.setText(datos);
    }//GEN-LAST:event_botonBDActionPerformed

    private void botonTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonTablaActionPerformed
        String[] opciones = {"PASAJEROS", "VUELOS"};
        int seleccionado = JOptionPane.showOptionDialog(this, "¿Qué tabla quieres ver?", "Información tabla", JOptionPane.DEFAULT_OPTION, INFORMATION_MESSAGE, null, opciones, opciones[0]);
        if (seleccionado > -1) {
            String datos = MVCVuelos.informaTabla(opciones[seleccionado]);
            areaDatos.setText(datos);
        }

    }//GEN-LAST:event_botonTablaActionPerformed

    private void botonEstructuraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEstructuraActionPerformed
        String[] opciones = {"PASAJEROS", "VUELOS"};
        int seleccionado = JOptionPane.showOptionDialog(this, "¿Qué tabla quieres ver?", "Información tabla", JOptionPane.DEFAULT_OPTION, INFORMATION_MESSAGE, null, opciones, opciones[0]);
        if (seleccionado > -1) {
            String datos = MVCVuelos.informaColumnas(opciones[seleccionado]);
            areaDatos.setText(datos);
        }
    }//GEN-LAST:event_botonEstructuraActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ColmenarAlbertoVentanaDescripcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ColmenarAlbertoVentanaDescripcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ColmenarAlbertoVentanaDescripcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ColmenarAlbertoVentanaDescripcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ColmenarAlbertoVentanaDescripcion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaDatos;
    private javax.swing.JButton botonBD;
    private javax.swing.JButton botonEstructura;
    private javax.swing.JButton botonTabla;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
