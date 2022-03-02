package co.edu.unisinu.ingsistemas.ds2.ventanas;

import co.edu.unisinu.ingsistemas.ds2.ventasAlumno.*;
import co.edu.unisinu.ingsistemas.ds2.ventasDocente.ContenidoDocente;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;

/**
 *
 * @author gasler (Miller vargas mola)
 * 
 */

public class Contenedor extends javax.swing.JPanel {

    /**
     * Creates new form HomeAlumno
     */
    public Contenedor(String encabezado) {
        initComponents();
        
        String fecha = new SimpleDateFormat("yy/MM/dd").format(Calendar.getInstance().getTime());
        jlFecha.setText(fecha);
        jlEncabezado.setText(encabezado);
        
        jpContenedor.removeAll();
        jpContenedor.updateUI();
        
        jpContenedor.setLayout(new BoxLayout(jpContenedor,BoxLayout.Y_AXIS));
        
        if( ("Practica").equals(jlEncabezado.getText()) ){
        jpContenedor.add(new ContenidoAlumno("¿Que es perico?","Un ave","Drogas","Comida"));
         jpContenedor.add(new ContenidoAlumno("¿Cuanto es 2 +2 ?","2","0","4"));
       
        }else{
        jpContenedor.add(new ContenidoDocente("Crear Practica"));

        }
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
        jlEncabezado = new javax.swing.JLabel();
        jlFecha = new javax.swing.JLabel();
        jlTiempo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jpContenedor = new javax.swing.JPanel();
        jbtnEnviar = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setBackground(new java.awt.Color(1, 25, 54));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlEncabezado.setFont(new java.awt.Font("DejaVu Math TeX Gyre", 3, 24)); // NOI18N
        jlEncabezado.setForeground(new java.awt.Color(248, 217, 15));
        jlEncabezado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlEncabezado.setText("&tipo");
        add(jlEncabezado, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 490, -1));

        jlFecha.setFont(new java.awt.Font("DejaVu Math TeX Gyre", 3, 13)); // NOI18N
        jlFecha.setForeground(new java.awt.Color(248, 217, 15));
        jlFecha.setText("fecha");
        add(jlFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, -1));

        jlTiempo.setFont(new java.awt.Font("DejaVu Math TeX Gyre", 3, 13)); // NOI18N
        jlTiempo.setForeground(new java.awt.Color(248, 217, 15));
        jlTiempo.setText("Tiempo");
        add(jlTiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 60, 80, -1));

        jSeparator1.setBackground(new java.awt.Color(248, 217, 15));
        jSeparator1.setForeground(new java.awt.Color(248, 217, 15));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 700, 10));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(248, 217, 15)));

        jpContenedor.setBackground(new java.awt.Color(1, 25, 54));
        jpContenedor.setLayout(new javax.swing.BoxLayout(jpContenedor, javax.swing.BoxLayout.LINE_AXIS));
        jScrollPane1.setViewportView(jpContenedor);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 550, 420));

        jbtnEnviar.setBackground(new java.awt.Color(1, 25, 54));
        jbtnEnviar.setForeground(new java.awt.Color(248, 217, 15));
        jbtnEnviar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/edu/unisinu/ingsistemas/ds2/img/enviar.png"))); // NOI18N
        jbtnEnviar.setText("Enviar");
        jbtnEnviar.setHideActionText(true);
        jbtnEnviar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jbtnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEnviarActionPerformed(evt);
            }
        });
        add(jbtnEnviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 90, 110, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEnviarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnEnviarActionPerformed


    public JPanel ver(){
    return this;}
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jbtnEnviar;
    private javax.swing.JLabel jlEncabezado;
    private javax.swing.JLabel jlFecha;
    private javax.swing.JLabel jlTiempo;
    private javax.swing.JPanel jpContenedor;
    // End of variables declaration//GEN-END:variables
}
