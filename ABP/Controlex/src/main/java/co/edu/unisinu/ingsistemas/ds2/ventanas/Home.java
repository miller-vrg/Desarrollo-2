package co.edu.unisinu.ingsistemas.ds2.ventanas;

import co.edu.unisinu.ingsistemas.ds2.procesos.Reloj;
import co.edu.unisinu.ingsistemas.ds2.ventanasEmerjentes.Listado;
import co.edu.unisinu.ingsistemas.ds2.ventanasEmerjentes.Login;
import java.awt.Color;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 *
 * @author gasler (Miler vargas mola )
 */
public class Home extends javax.swing.JFrame implements Runnable{

    private Thread h1;
    private final int usuario;
    private String advertencia;
    private String error;
    private String correcto;
    private ImageIcon iconAdvertencia;
    private ImageIcon iconError;
    private ImageIcon iconCorrecto;
    
    /**
     * Creates new form Home
     */

    public Home(String encabezado,String btn1,String btn3,int usuario) {
        initComponents();
        setResizable(false);
        this.usuario = usuario;
        btnRegresar.setVisible(false);
        //-------------------
        //  Nuevo hilo
        //-------------------
        h1 = new Thread(this);
        h1.start();

        advertencia = "/co/edu/unisinu/ingsistemas/ds2/icons/advertencia.png";
        iconAdvertencia = new ImageIcon(getClass().getResource(advertencia));
        
        correcto = "/co/edu/unisinu/ingsistemas/ds2/icons/correcto.png";
        iconCorrecto = new ImageIcon(getClass().getResource(correcto));
        
        error = "/co/edu/unisinu/ingsistemas/ds2/icons/error.png";
        iconError = new ImageIcon(getClass().getResource(error));
        
        Calendar tiempo = new GregorianCalendar();

        String fecha = new SimpleDateFormat("yy/MM/dd").format(Calendar.getInstance().getTime());
        jlFecha.setText(fecha);
        jlMensaje.setText(encabezado);
        jlBtn1.setText(btn1);
        jlBtn3.setText(btn3);
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    
    //--------------------------------------------
    //              Metodos
    //--------------------------------------------
    
    @Override
    public void run() {
         Thread ct = Thread.currentThread();
        
         while( ct == h1 ){
        jlReloj.setText(new Reloj().verTiempo());
        
        try{
        Thread.sleep(1000);
        }catch( Exception e ){
        JOptionPane.showMessageDialog(null,"Error al obtener hora","ERROR!",JOptionPane.ERROR_MESSAGE,iconError);
        };   
        
    }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpNavegacion = new javax.swing.JPanel();
        btnRegresar = new javax.swing.JButton();
        jpIntercambio = new javax.swing.JPanel();
        jpHome = new javax.swing.JPanel();
        jlBienvenido = new javax.swing.JLabel();
        jlMensaje = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jlReloj = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jlFecha = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jlBtn1 = new javax.swing.JLabel();
        jlBtn2 = new javax.swing.JLabel();
        jlBtn3 = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(48, 49, 88));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpNavegacion.setBackground(new java.awt.Color(41, 41, 77));
        jpNavegacion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegresar.setBackground(new java.awt.Color(255, 214, 141));
        btnRegresar.setFont(new java.awt.Font("URW Bookman", 1, 13)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(41, 41, 77));
        btnRegresar.setText("Regresar");
        btnRegresar.setToolTipText("");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        jpNavegacion.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 130, -1));

        getContentPane().add(jpNavegacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 700, 40));

        jpIntercambio.setBackground(new java.awt.Color(1, 25, 54));
        jpIntercambio.setLayout(new java.awt.BorderLayout());

        jpHome.setBackground(new java.awt.Color(41, 41, 77));
        jpHome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlBienvenido.setFont(new java.awt.Font("URW Bookman", 3, 48)); // NOI18N
        jlBienvenido.setForeground(new java.awt.Color(255, 214, 141));
        jlBienvenido.setText("Bienvenido");
        jpHome.add(jlBienvenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, 330, -1));

        jlMensaje.setFont(new java.awt.Font("URW Bookman", 1, 18)); // NOI18N
        jlMensaje.setForeground(new java.awt.Color(255, 214, 141));
        jlMensaje.setText("$mensaje");
        jpHome.add(jlMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 300, 30));

        jPanel2.setBackground(new java.awt.Color(255, 214, 141));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(48, 49, 88), 1, true));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlReloj.setFont(new java.awt.Font("URW Bookman", 1, 13)); // NOI18N
        jlReloj.setForeground(new java.awt.Color(41, 41, 77));
        jlReloj.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlReloj.setText("Reloj");
        jPanel2.add(jlReloj, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 80, 30));

        jpHome.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 130, 20));

        jPanel3.setBackground(new java.awt.Color(255, 214, 141));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(48, 49, 88), 1, true));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlFecha.setFont(new java.awt.Font("URW Bookman", 1, 14)); // NOI18N
        jlFecha.setForeground(new java.awt.Color(41, 41, 77));
        jlFecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlFecha.setText("fecha");
        jPanel3.add(jlFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 90, 30));

        jpHome.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 150, 20));

        jButton1.setBackground(new java.awt.Color(255, 214, 141));
        jButton1.setFont(new java.awt.Font("URW Bookman", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/edu/unisinu/ingsistemas/ds2/img/practica1.jpg"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jpHome.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, 140, 110));

        jButton2.setBackground(new java.awt.Color(255, 214, 141));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/edu/unisinu/ingsistemas/ds2/img/notas1.jpg"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jpHome.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 260, 130, 110));

        jButton3.setBackground(new java.awt.Color(255, 214, 141));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/edu/unisinu/ingsistemas/ds2/img/examen1.jpg"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jpHome.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 260, 130, 110));

        jlBtn1.setBackground(new java.awt.Color(255, 214, 141));
        jlBtn1.setFont(new java.awt.Font("URW Bookman", 1, 14)); // NOI18N
        jlBtn1.setForeground(new java.awt.Color(255, 214, 141));
        jlBtn1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlBtn1.setText("$Btn1");
        jpHome.add(jlBtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 380, 140, 30));

        jlBtn2.setFont(new java.awt.Font("URW Bookman", 1, 14)); // NOI18N
        jlBtn2.setForeground(new java.awt.Color(255, 214, 141));
        jlBtn2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlBtn2.setText("Notas");
        jpHome.add(jlBtn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 380, 130, 30));

        jlBtn3.setFont(new java.awt.Font("URW Bookman", 1, 14)); // NOI18N
        jlBtn3.setForeground(new java.awt.Color(255, 214, 141));
        jlBtn3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlBtn3.setText("$Btn1");
        jpHome.add(jlBtn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 380, 120, 30));

        btnCerrar.setBackground(new java.awt.Color(255, 214, 141));
        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cerrarsesion.png"))); // NOI18N
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        jpHome.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 10, 30, 30));

        jpIntercambio.add(jpHome, java.awt.BorderLayout.CENTER);

        getContentPane().add(jpIntercambio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        btnRegresar.setVisible(false);
        jpNavegacion.updateUI();
        btnRegresar.setVisible(false);
        if( v ){ 
            int opcion = JOptionPane.showConfirmDialog(null, "Si sale sin terminar se guaradar su nota incompleta", "Confirmar eleccion", JOptionPane.YES_NO_OPTION,0,iconAdvertencia);
            if (opcion == JOptionPane.YES_OPTION) {
                jpIntercambio.removeAll();
                jpIntercambio.updateUI();
                jpIntercambio.add(jpHome);
        }
        }else{
         btnRegresar.setVisible(true);
            jpIntercambio.removeAll();
            jpIntercambio.updateUI();
            jpIntercambio.add(jpHome);
            
        }
        
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        btnRegresar.setVisible(true);
        System.out.println("258");
        if (("Practicas").equalsIgnoreCase(jlBtn1.getText())) {
            v = true;
            String name = new Listado(new JFrame(), true, jlBtn1.getText(), usuario).getName();
            System.out.println("260");
            System.out.println(name);
            if (name != null) {
                jpIntercambio.removeAll();
                jpIntercambio.updateUI();
               jpIntercambio.add(new Contenedor(jlBtn1.getText(), usuario, name));
            }
            }else {
            System.out.println("269");
                v = false;
                jpIntercambio.removeAll();
                jpIntercambio.updateUI();
                jpIntercambio.add(new Contenedor(jlBtn1.getText(), usuario, ""));
            }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        String tipo = ( jlBtn1.getText().equalsIgnoreCase("Crear practica") )? "Docente" : "Alumno" ;
        btnRegresar.setVisible(true);
        v = false;
        jpIntercambio.removeAll();
        jpIntercambio.updateUI();
        jpIntercambio.add(new Contenedor("Notas", usuario,tipo));

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        btnRegresar.setVisible(true);
        if( ("Examenes").equalsIgnoreCase(jlBtn3.getText()) ){
        v = true;
        String name = new Listado(new JFrame(), true, jlBtn3.getText(), usuario).getName();

        if (name != null) {
            jpIntercambio.removeAll();
            jpIntercambio.updateUI();
            jpIntercambio.add(new Contenedor(jlBtn3.getText(), usuario, name));
        }
       }else{
            v = false;
        jpIntercambio.removeAll();
            jpIntercambio.updateUI();
            jpIntercambio.add(new Contenedor(jlBtn3.getText(), usuario, ""));
        
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed

        int op = JOptionPane.showConfirmDialog(this, "Seguro que deseas cerrar sesion?","Confirmar",JOptionPane.WARNING_MESSAGE,0,iconAdvertencia);
        
        if( op == JOptionPane.YES_OPTION ){
            
        this.setVisible(false);
        this.setEnabled(false);
       
        Login l = new Login();
        l.run();
        }
    }//GEN-LAST:event_btnCerrarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        boolean c = false;

        Login l = new Login();
        l.run();

    }

    private boolean v;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jlBienvenido;
    private javax.swing.JLabel jlBtn1;
    private javax.swing.JLabel jlBtn2;
    private javax.swing.JLabel jlBtn3;
    private javax.swing.JLabel jlFecha;
    private javax.swing.JLabel jlMensaje;
    private javax.swing.JLabel jlReloj;
    private javax.swing.JPanel jpHome;
    private javax.swing.JPanel jpIntercambio;
    private javax.swing.JPanel jpNavegacion;
    // End of variables declaration//GEN-END:variables
}
