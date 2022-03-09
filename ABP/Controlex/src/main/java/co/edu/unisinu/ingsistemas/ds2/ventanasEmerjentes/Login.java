package co.edu.unisinu.ingsistemas.ds2.ventanasEmerjentes;

import co.edu.unisinu.ingsistemas.ds2.ventanas.Home;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;


/**
 *
 * @author gasler (miler vargas mola)
 */
public class Login extends javax.swing.JDialog implements ItemListener{

    private boolean veri = true;
    private String seleccion;
    
    /**
     * Creates new form Login
     */

    public Login(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        seleccion = "Alumno";
        jcTipo.addItemListener(this);
    }

    public Login() {
    }

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jpPassword = new javax.swing.JPasswordField();
        jtUsuario = new javax.swing.JTextField();
        bntLogin = new javax.swing.JButton();
        jcTipo = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(48, 49, 88));
        setModal(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(1, 25, 54));
        jPanel1.setMaximumSize(new java.awt.Dimension(415, 490));
        jPanel1.setMinimumSize(new java.awt.Dimension(415, 490));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/edu/unisinu/ingsistemas/ds2/img/usuario.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, -1, -1));

        jpPassword.setColumns(20);
        jpPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(248, 217, 15)));
        jpPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jpPasswordActionPerformed(evt);
            }
        });
        jPanel1.add(jpPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 320, 220, 20));

        jtUsuario.setColumns(20);
        jtUsuario.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jtUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(248, 217, 15)));
        jtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtUsuarioActionPerformed(evt);
            }
        });
        jPanel1.add(jtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 290, 220, 20));

        bntLogin.setBackground(new java.awt.Color(248, 217, 15));
        bntLogin.setText("Iniciar");
        bntLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntLoginActionPerformed(evt);
            }
        });
        jPanel1.add(bntLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 360, 90, -1));

        jcTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Alumno", "Docente" }));
        jPanel1.add(jcTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, 220, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtUsuarioActionPerformed

    private void jpPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jpPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jpPasswordActionPerformed

    private void bntLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntLoginActionPerformed

       usuario = jtUsuario.getText();
       password = (String) jpPassword.getSelectedText();
       
       veri();
    }//GEN-LAST:event_bntLoginActionPerformed

    public void run() {
       
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Login dialog = new Login(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
    }

    public void veri() {

        usuario = jtUsuario.getText();
        password = new String(jpPassword.getPassword());

        try {

            File arch = new File("json/usuarios/usuarios.json");

            Object ob = new JSONParser().parse(new FileReader(arch));
            JSONObject js = (JSONObject) ob;

            HashMap<String,String> p = (HashMap<String, String>) (Map) js.get("87223");

            if ((usuario).equals(p.get("matricula")) && (password).equals(p.get("password"))) {

                JOptionPane.showMessageDialog(null, "Inicio exitoso");
                //setVisible(false);
               dispose();
                
                if (("Alumno").equals(seleccion)) {

                    Home h = new Home("Alumno: " + p.get("name"), "Practica", "Examen");

                } else {

                    Home h = new Home("Docente: " + p.get("name"), "Crear Practica", "Craer Examen");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña no valido", "Error!", JOptionPane.WARNING_MESSAGE);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Algo salio mal", "Error!", JOptionPane.WARNING_MESSAGE);

        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> jcTipo;
    private javax.swing.JPasswordField jpPassword;
    private javax.swing.JTextField jtUsuario;
    // End of variables declaration//GEN-END:variables

    private String Selecionado;
    private String password;
    private String usuario;
    
    public String getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(String seleccion) {
        this.seleccion = seleccion;
    }
    
    public String getUsuario(){
    return usuario;}

 public void itemStateChanged(ItemEvent e) {
        if (e.getSource()==jcTipo) {
          seleccion =(String)jcTipo.getSelectedItem();
            
        }
        if(e.getSource()==jpPassword){
          password = (String) jpPassword.getSelectedText();
        }
        usuario = jtUsuario.getText();
    }
 
    /**
     * @return the jpPassword
     */
    public String getJpPassword() {

        return password;
    }

}
