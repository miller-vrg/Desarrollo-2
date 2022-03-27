/**
 * Esta clase al igual que la VentanaPrincipal tambien hereda el codigo
 * las propiedades (elementos) y operaciones (metodos o funiones) de otra clase
 * java, pero a diferencia de la ventana principal, esta hereda de la clase Jdialog
 * es un tipo especial de ventana, es muy utilizada para construir formularios de campo
 * (formatos de campo donde se ingresa y se muestra informacion relevante para aplicaciones
 * y para los usuarios de la misma).
 * un JDialog a diferencia de un JFrame, no posee el boton minimizar, ni tampoco
 * el boton de maximizar o restaurar, tampoco se le puede colocar una barra de menu, 
 * este tipo de ventanas acostumbramos abrirlas justamenete sobre la ventana previamente
 * abierta, la cual comummente es una ventana de tipo JFrame
 * 
 */
package millervargas.ejemplodb.v1.ventanas;

/**
 *
 * @author gasler
 */
public class VentanaCrudUsuario extends javax.swing.JDialog {

    /**
     * Creates new form VentanaCrudUsuario
     */
    public VentanaCrudUsuario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        campoId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        campoApellido = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        campoClave = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        campoTelefono = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        campoNombre = new javax.swing.JTextField();
        campoEmail = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaDireccion = new javax.swing.JTextArea();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        campoPassword = new javax.swing.JPasswordField();

        jLabel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Datos del usuario"));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEliminar.setText("Eliminar");
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 570, -1, -1));

        btnAgregar.setText("Agregar");
        getContentPane().add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 570, -1, -1));

        btnBuscar.setText("Buscar");
        getContentPane().add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 570, -1, -1));

        btnModificar.setText("Modificar");
        getContentPane().add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 570, -1, -1));

        jLabel11.setFont(new java.awt.Font("TSCu_Times", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 255));
        jLabel11.setText("Operaciones con Usuarios");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, -1, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255)), "DATOS USUARIO:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("ID:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 110, -1));

        campoId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoIdActionPerformed(evt);
            }
        });
        jPanel1.add(campoId, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, 220, -1));

        jLabel3.setText("CLAVE:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 110, -1));

        jLabel4.setText("CEDULA:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 110, -1));
        jPanel1.add(campoApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 190, 220, -1));

        jLabel5.setText("NOMBRE:*");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 110, -1));

        jLabel6.setText("APELLIDO:*");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 110, -1));
        jPanel1.add(campoClave, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 220, -1));

        jLabel7.setText("EMAIL:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 110, -1));
        jPanel1.add(campoTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 310, 220, -1));

        jLabel8.setText("NACIMIENTO:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 110, -1));

        jLabel9.setText("GENERO:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 110, -1));

        jLabel10.setText("TELEFONO:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 110, -1));

        campoNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoNombreActionPerformed(evt);
            }
        });
        jPanel1.add(campoNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 220, -1));
        jPanel1.add(campoEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 340, 220, -1));

        jRadioButton1.setText("FEMENINO");
        jPanel1.add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 270, -1, -1));

        jRadioButton2.setText("MASCULINO");
        jPanel1.add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 270, -1, -1));

        areaDireccion.setColumns(20);
        areaDireccion.setRows(5);
        areaDireccion.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)), "DIRRECION:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(0, 51, 255))); // NOI18N
        jScrollPane1.setViewportView(areaDireccion);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 350, -1));
        jPanel1.add(jXDatePicker1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, 220, -1));

        campoPassword.setText("jPasswordField1");
        jPanel1.add(campoPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 220, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 430, 490));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoIdActionPerformed

    private void campoNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoNombreActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaCrudUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaCrudUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaCrudUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaCrudUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VentanaCrudUsuario dialog = new VentanaCrudUsuario(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaDireccion;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JTextField campoApellido;
    private javax.swing.JTextField campoClave;
    private javax.swing.JTextField campoEmail;
    private javax.swing.JTextField campoId;
    private javax.swing.JTextField campoNombre;
    private javax.swing.JPasswordField campoPassword;
    private javax.swing.JTextField campoTelefono;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    // End of variables declaration//GEN-END:variables
}