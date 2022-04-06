package co.edu.unisinu.ingsistemas.ds2.ventasAlumno;

import co.edu.unisinu.ingsistemas.ds2.conector.Conector;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author gasler (Miller vargas mola)
 */

public class ContenidoAlumno extends javax.swing.JPanel {

    /**
     * Creates new form Contenido
     */
    
    
    public ContenidoAlumno(){};
    public JPanel conte(String enunciado, String resA, String resB, String resC) {
        initComponents();
        
        jtEnunciado.setText(enunciado);
        jrA.setText(resA);
        jrB.setText(resB);
        jrC.setText(resC);
        
        group = new ButtonGroup();
        group.add(jrA);
        group.add(jrB);
        group.add(jrC);
        
        return this;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        group = new javax.swing.ButtonGroup();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtEnunciado = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        jrC = new javax.swing.JRadioButton();
        jrA = new javax.swing.JRadioButton();
        jrB = new javax.swing.JRadioButton();

        jButton1.setText("jButton1");

        setBackground(new java.awt.Color(41, 41, 77));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtEnunciado.setEditable(false);
        jtEnunciado.setBackground(new java.awt.Color(41, 41, 77));
        jtEnunciado.setColumns(20);
        jtEnunciado.setFont(new java.awt.Font("URW Bookman", 1, 13)); // NOI18N
        jtEnunciado.setForeground(new java.awt.Color(255, 214, 141));
        jtEnunciado.setRows(5);
        jtEnunciado.setText("Enunciado");
        jtEnunciado.setBorder(null);
        jScrollPane1.setViewportView(jtEnunciado);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 450, 110));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 500, 20));

        jrC.setFont(new java.awt.Font("URW Bookman", 0, 13)); // NOI18N
        jrC.setForeground(new java.awt.Color(255, 214, 141));
        jrC.setText("C.");
        jrC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrCActionPerformed(evt);
            }
        });
        add(jrC, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 450, -1));

        jrA.setFont(new java.awt.Font("URW Bookman", 0, 13)); // NOI18N
        jrA.setForeground(new java.awt.Color(255, 214, 141));
        jrA.setText("A.");
        jrA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrAActionPerformed(evt);
            }
        });
        add(jrA, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 450, -1));

        jrB.setFont(new java.awt.Font("URW Bookman", 0, 13)); // NOI18N
        jrB.setForeground(new java.awt.Color(255, 214, 141));
        jrB.setText("B.");
        jrB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrBActionPerformed(evt);
            }
        });
        add(jrB, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 450, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jrCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrCActionPerformed

        int dialogButton = JOptionPane.showConfirmDialog(null, "Despues de elejir la respuesta no la podras cabiar,\n ¿estas seguro de tu elecciòn?", "Confirmaciòn", JOptionPane.YES_NO_OPTION);
        if (dialogButton == JOptionPane.YES_OPTION) {

            try {
                File arch = new File("respuestas.txt");
                if (arch.createNewFile()) {
                    System.out.println("Se creo creo el archivo de respuestas de usuario " + arch.getName());
                    FileWriter escribir = new FileWriter(arch, true);
                    escribir.write("-" + jrC.getText());
                    escribir.close();
                    System.out.println("Successfully");
                } else {
                    System.out.println("El archivo de respuestas de usuario ya existe");
                    FileWriter escribir = new FileWriter(arch, true);
                    escribir.write("-" + jrC.getText());
                    escribir.close();
                    System.out.println("Successfully");
                }
            } catch (IOException e) {
                System.out.println("error al crear archivo de respuesta");
                JOptionPane.showMessageDialog(null, "Error no se pueden guardar las respuestas", "ERROR!!!", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
            jrA.setEnabled(false);
            jrB.setEnabled(false);
            jrC.setEnabled(false);
        }
        if (dialogButton == JOptionPane.NO_OPTION) {

        }

        System.out.print(respuesta);

    }//GEN-LAST:event_jrCActionPerformed

    private void jrAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrAActionPerformed

        int dialogButton = JOptionPane.showConfirmDialog(null, "Despues de elejir la respuesta no la podras cabiar,\n ¿estas seguro de tu elecciòn?", "Confirmaciòn", JOptionPane.YES_NO_OPTION);
        if (dialogButton == JOptionPane.YES_OPTION) {

            try {
                File arch = new File("respuestas.txt");
                if (arch.createNewFile()) {
                    System.out.println("Se creo creo el archivo de respuestas de usuario " + arch.getName());
                    FileWriter escribir = new FileWriter(arch, true);
                    escribir.write("-" + jrA.getText());
                    escribir.close();
                    System.out.println("Successfully");
                } else {
                    System.out.println("El archivo de respuestas de usuario ya existe");
                    FileWriter escribir = new FileWriter(arch, true);
                    escribir.write("-" + jrA.getText());
                    escribir.close();
                    System.out.println("Successfully");
                }
            } catch (IOException e) {
                System.out.println("error al crear archivo de respuesta");
                JOptionPane.showMessageDialog(null, "Error no se pueden guardar las respuestas", "ERROR!!!", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
            jrA.setEnabled(false);
            jrB.setEnabled(false);
            jrC.setEnabled(false);
        }
        if (dialogButton == JOptionPane.NO_OPTION) {

        }

        System.out.print(respuesta);
    }//GEN-LAST:event_jrAActionPerformed

    private void jrBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrBActionPerformed

        int dialogButton = JOptionPane.showConfirmDialog(null, "Despues de elejir la respuesta no la podras cabiar,\n ¿estas seguro de tu elecciòn?", "Confirmaciòn", JOptionPane.YES_NO_OPTION);
        if (dialogButton == JOptionPane.YES_OPTION) {
            try {
                File arch = new File("respuestas.txt");
                if (arch.createNewFile()) {
                    System.out.println("Se creo creo el archivo de respuestas de usuario " + arch.getName());
                    FileWriter escribir = new FileWriter(arch, true);
                    escribir.write("-" + jrB.getText());
                    escribir.close();
                    System.out.println("Successfully");
                } else {
                    System.out.println("El archivo de respuestas de usuario ya existe");
                    FileWriter escribir = new FileWriter(arch, true);
                    escribir.write("-" + jrB.getText());
                    escribir.close();
                    System.out.println("Successfully");
                }
            } catch (IOException e) {
                System.out.println("error al crear archivo de respuesta");
                JOptionPane.showMessageDialog(null, "Error no se pueden guardar las respuestas", "ERROR!!!", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
            jrA.setEnabled(false);
            jrB.setEnabled(false);
            jrC.setEnabled(false);
        }
        if (dialogButton == JOptionPane.NO_OPTION) {

        }
        System.out.print(respuesta);
    }//GEN-LAST:event_jrBActionPerformed

    public String getRespuesta(){
    return respuesta;
    }
   
    public JPanel ver(){
    return this;}

    private String respuesta;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup group;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JRadioButton jrA;
    private javax.swing.JRadioButton jrB;
    private javax.swing.JRadioButton jrC;
    private javax.swing.JTextArea jtEnunciado;
    // End of variables declaration//GEN-END:variables
}
