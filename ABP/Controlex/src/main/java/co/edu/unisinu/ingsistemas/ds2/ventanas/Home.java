package co.edu.unisinu.ingsistemas.ds2.ventanas;

import co.edu.unisinu.ingsistemas.ds2.procesos.Reloj;
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
    /**
     * Creates new form Home
     */

    public Home(String encabezado,String btn1,String btn3) {
        initComponents();
        setResizable(false);
        
        //-------------------
        //  Nuevo hilo
        //-------------------
        h1 = new Thread(this);
        h1.start();

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
        JOptionPane.showMessageDialog(null,"Error al obtener hora","ERROR!",JOptionPane.ERROR_MESSAGE);
        };   
        
    }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpNavegacion = new javax.swing.JPanel();
        btnSalir = new javax.swing.JButton();
        jpIntercambio = new javax.swing.JPanel();
        jpHome = new javax.swing.JPanel();
        jlBienvenido = new javax.swing.JLabel();
        jlMensaje = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jlBtn3 = new javax.swing.JLabel();
        jlBtn1 = new javax.swing.JLabel();
        jlBtn2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jlReloj = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jlFecha = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(48, 49, 88));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpNavegacion.setBackground(new java.awt.Color(1, 25, 54));
        jpNavegacion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSalir.setBackground(new java.awt.Color(1, 25, 54));
        btnSalir.setForeground(new java.awt.Color(248, 217, 15));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/edu/unisinu/ingsistemas/ds2/img/cerrarsesion.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setToolTipText("");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jpNavegacion.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 100, -1));

        getContentPane().add(jpNavegacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 700, 40));

        jpIntercambio.setBackground(new java.awt.Color(1, 25, 54));
        jpIntercambio.setLayout(new java.awt.BorderLayout());

        jpHome.setBackground(new java.awt.Color(1, 25, 54));
        jpHome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlBienvenido.setFont(new java.awt.Font("DejaVu Math TeX Gyre", 3, 48)); // NOI18N
        jlBienvenido.setForeground(new java.awt.Color(248, 217, 15));
        jlBienvenido.setText("Bienvenido");
        jpHome.add(jlBienvenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, 330, -1));

        jlMensaje.setFont(new java.awt.Font("DejaVu Math TeX Gyre", 1, 18)); // NOI18N
        jlMensaje.setForeground(new java.awt.Color(248, 217, 15));
        jlMensaje.setText("$mensaje");
        jpHome.add(jlMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 300, 30));

        jButton1.setBackground(new java.awt.Color(1, 25, 54));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/edu/unisinu/ingsistemas/ds2/img/practica.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jpHome.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, -1, -1));

        jButton2.setBackground(new java.awt.Color(1, 25, 54));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/edu/unisinu/ingsistemas/ds2/img/calificaciones.png"))); // NOI18N
        jButton2.setBorder(null);
        jpHome.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 290, -1, -1));

        jButton3.setBackground(new java.awt.Color(1, 25, 54));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/edu/unisinu/ingsistemas/ds2/img/examen.png"))); // NOI18N
        jButton3.setBorder(null);
        jpHome.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 290, -1, -1));

        jlBtn3.setFont(new java.awt.Font("DejaVu Math TeX Gyre", 1, 14)); // NOI18N
        jlBtn3.setForeground(new java.awt.Color(248, 217, 15));
        jlBtn3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlBtn3.setText("$Btn1");
        jpHome.add(jlBtn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 360, 160, 30));

        jlBtn1.setFont(new java.awt.Font("DejaVu Math TeX Gyre", 1, 14)); // NOI18N
        jlBtn1.setForeground(new java.awt.Color(248, 217, 15));
        jlBtn1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlBtn1.setText("$Btn1");
        jpHome.add(jlBtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 360, 170, 30));

        jlBtn2.setFont(new java.awt.Font("DejaVu Math TeX Gyre", 1, 14)); // NOI18N
        jlBtn2.setForeground(new java.awt.Color(248, 217, 15));
        jlBtn2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlBtn2.setText("Notas");
        jpHome.add(jlBtn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 360, 150, 30));

        jPanel2.setBackground(new java.awt.Color(248, 217, 15));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(48, 49, 88), 1, true));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlReloj.setFont(new java.awt.Font("DejaVu Math TeX Gyre", 3, 13)); // NOI18N
        jlReloj.setForeground(new java.awt.Color(1, 25, 54));
        jlReloj.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlReloj.setText("Reloj");
        jPanel2.add(jlReloj, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 80, 30));

        jpHome.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 130, 20));

        jPanel3.setBackground(new java.awt.Color(248, 217, 15));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(48, 49, 88), 1, true));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlFecha.setFont(new java.awt.Font("DejaVu Math TeX Gyre", 1, 14)); // NOI18N
        jlFecha.setForeground(new java.awt.Color(1, 25, 54));
        jlFecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlFecha.setText("fecha");
        jPanel3.add(jlFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 90, 30));

        jpHome.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 150, 20));

        jpIntercambio.add(jpHome, java.awt.BorderLayout.CENTER);

        getContentPane().add(jpIntercambio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed

        jpIntercambio.removeAll();
        jpIntercambio.updateUI();
        jpIntercambio.add(jpHome);
        
    }//GEN-LAST:event_btnSalirActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        jpIntercambio.removeAll();
        jpIntercambio.updateUI();
       // jpIntercambio.setLayout();
        
        if( ("Practica").equals(jlBtn1.getText()) ){
            
        jpIntercambio.add(new Contenedor("Practica").ver());
        }else{
        jpIntercambio.add(new Contenedor("Creación de practica").ver());

        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

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
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        boolean c = false;

        Login l = new Login();
        l.run();

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalir;
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
