package co.edu.unisinu.ingsistemas.ds2.ventanasEmerjentes;

import co.edu.unisinu.ingsistemas.ds2.conector.Conector;
import co.edu.unisinu.ingsistemas.ds2.ventanas.Home;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
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
    private String advertencia;
    private ImageIcon iconAdvertencia;
    private String correcto;
    private ImageIcon iconCorrecto;
    private String error;
    private ImageIcon iconError;

    public Login(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
                
        advertencia = "/co/edu/unisinu/ingsistemas/ds2/icons/advertencia.png";
        iconAdvertencia = new ImageIcon(getClass().getResource(advertencia));
        
        correcto = "/co/edu/unisinu/ingsistemas/ds2/icons/correcto.png";
        iconCorrecto = new ImageIcon(getClass().getResource(correcto));
        
        error = "/co/edu/unisinu/ingsistemas/ds2/icons/error.png";
        iconError = new ImageIcon(getClass().getResource(error));
        
        ImageIcon img = new ImageIcon(getClass().getResource("/co/edu/unisinu/ingsistemas/ds2/img/login.png"));
        jlImg.setLocation(0, 0);
        jlImg.setIcon(img);
        seleccion = "Alumno";
    }

    public Login() {
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        checkcontra = new javax.swing.JCheckBox();
        jpPassword = new javax.swing.JPasswordField();
        jtUsuario = new javax.swing.JTextField();
        bntLogin = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jlImg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(48, 49, 88));
        setModal(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(41, 41, 77));
        jPanel1.setMaximumSize(new java.awt.Dimension(415, 490));
        jPanel1.setMinimumSize(new java.awt.Dimension(415, 490));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 214, 141), 2));
        jPanel2.setLayout(null);

        checkcontra.setFont(new java.awt.Font("URW Gothic", 1, 14)); // NOI18N
        checkcontra.setText("ver");
        checkcontra.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkcontra.setFocusable(false);
        checkcontra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkcontraActionPerformed(evt);
            }
        });
        jPanel2.add(checkcontra);
        checkcontra.setBounds(310, 200, 44, 22);

        jpPassword.setColumns(20);
        jpPassword.setFont(new java.awt.Font("URW Gothic", 0, 14)); // NOI18N
        jpPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 214, 141), 2));
        jpPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jpPasswordActionPerformed(evt);
            }
        });
        jPanel2.add(jpPassword);
        jpPassword.setBounds(310, 160, 230, 30);

        jtUsuario.setColumns(20);
        jtUsuario.setFont(new java.awt.Font("URW Gothic", 0, 14)); // NOI18N
        jtUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 214, 141), 2));
        jtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtUsuarioActionPerformed(evt);
            }
        });
        jPanel2.add(jtUsuario);
        jtUsuario.setBounds(310, 70, 230, 30);

        bntLogin.setBackground(new java.awt.Color(255, 214, 141));
        bntLogin.setFont(new java.awt.Font("URW Gothic", 1, 14)); // NOI18N
        bntLogin.setText("Iniciar");
        bntLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntLoginActionPerformed(evt);
            }
        });
        jPanel2.add(bntLogin);
        bntLogin.setBounds(310, 253, 230, 30);

        jLabel2.setFont(new java.awt.Font("URW Gothic", 1, 14)); // NOI18N
        jLabel2.setText("Contraseña");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(310, 130, 77, 18);

        jLabel3.setFont(new java.awt.Font("URW Gothic", 1, 14)); // NOI18N
        jLabel3.setText("Usuario");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(310, 40, 48, 18);

        jlImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/edu/unisinu/ingsistemas/ds2/img/login.png"))); // NOI18N
        jPanel2.add(jlImg);
        jlImg.setBounds(0, 0, 270, 330);

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 580, 330));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
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
       
       veri();
    }//GEN-LAST:event_bntLoginActionPerformed

    private void checkcontraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkcontraActionPerformed
       if (checkcontra.isSelected()){
           jpPassword.setEchoChar((char) 0);
       }
       else{
       
       jpPassword.setEchoChar('*');
       }
    }//GEN-LAST:event_checkcontraActionPerformed

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
  
        Conector cn = null;
        Connection conect = null;
        PreparedStatement prepared = null ;
        ResultSet resul = null;
        
        try {
            System.out.println("180 ");
            cn = new Conector();System.out.println("181 ");
            conect = cn.getDriver();System.out.println("182 ");
            prepared = conect.prepareStatement( "SELECT * FROM estudiantes");System.out.println("183 ");
            resul = prepared.executeQuery();System.out.println("184 ");

            System.out.println("188 ");
            boolean veriUser = false, veriPassword = false;
            String tipo = "Alumno";
            String name = "";
            
            while(resul.next()){
            System.out.println("192 " + tipo);
            
                if( veriUser == false ){
                    System.out.println("193 ");
                    
                    veriUser = (resul.getInt("matricula") == Integer.parseInt(usuario));
                    
                    System.out.println("194 ");
                }
                if( veriPassword == false ){
                    System.out.println("196 ");
                    veriPassword = ((resul.getString("password")).equals(password));
                    System.out.println("197 ");
                }
                 if( veriUser == true && veriPassword == true){System.out.println("199 ");
                 
                 name = resul.getString("name") + " "
                         + resul.getString("apellidos");
                 tipo += ": ";
                 
                 System.out.println("205" + tipo);
                 
                 JOptionPane.showMessageDialog(null, "Inicio exitoso","Exito",0,iconCorrecto);
                 break;
                 }
            }
            
            if( veriUser == false && veriPassword == false){
            prepared = conect.prepareStatement( "SELECT * FROM docentes");
            resul = prepared.executeQuery();
            while(resul.next()){
                if( veriUser == false ){
                    veriUser = ((""+resul.getInt("id")).equalsIgnoreCase(usuario));
                    System.out.println("262 id = " + resul.getInt("id") + " ");
                }
                if( veriPassword == false ){
                    veriPassword = ((resul.getString("password")).equals(password));
                    System.out.println("266 pass = " + resul.getString("password") + " ");
                }
                 if( veriUser == true && veriPassword == true){
                 
                 name = resul.getString("name") + " "
                         + " " + resul.getString("apellidos");
                 tipo = "Docente: ";
                 JOptionPane.showMessageDialog(null, "Inicio exitoso","Exito",0,iconCorrecto);
                 break;
                 }
            }
            }
            if (veriUser == true && veriPassword == true) {
                dispose();
                if( ("Alumno: ").equals(tipo) ){
                new Home(tipo + name, "Practicas", "Examenes",Integer.parseInt(usuario));
                }else{
                dispose();
                new Home(tipo + name, "Crear practica", "Crear examen",Integer.parseInt(usuario));
                }
            }
            if (veriUser == false && veriPassword == true) {
                JOptionPane.showMessageDialog(null, "El usuario no existe","Advertencia!!!",JOptionPane.ERROR_MESSAGE,iconAdvertencia);
            }
            if (veriPassword == false && veriUser == true) {
                JOptionPane.showMessageDialog(null, "Contraseña invalida", "ERROR!!", JOptionPane.ERROR_MESSAGE,iconError);
            }


        } catch (SQLException e) {
            System.out.print(e);
            JOptionPane.showMessageDialog(null, "Fue imposible conectarse con la base de datos", "Error!", JOptionPane.ERROR_MESSAGE,iconError);

        }finally{
        cn.close(conect,resul,prepared);
        
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntLogin;
    private javax.swing.JCheckBox checkcontra;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel jlImg;
    private javax.swing.JPasswordField jpPassword;
    private javax.swing.JTextField jtUsuario;
    // End of variables declaration//GEN-END:variables


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

 /*public void itemStateChanged(ItemEvent e) {
        if (e.getSource()==jcTipo) {
        }
        if(e.getSource()==jpPassword){
          password = (String) jpPassword.getSelectedText();
        }
        usuario = jtUsuario.getText();
    }
    */
 
    /**
     * @return the jpPassword
     */
    public String getJpPassword() {

        return password;
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
