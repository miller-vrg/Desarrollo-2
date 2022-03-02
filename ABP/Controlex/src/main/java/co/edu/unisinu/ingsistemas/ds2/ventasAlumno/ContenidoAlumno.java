package co.edu.unisinu.ingsistemas.ds2.ventasAlumno;

import javax.swing.*;

/**
 *
 * @author gasler (Miller vargas mola)
 */

public class ContenidoAlumno extends javax.swing.JPanel {

    /**
     * Creates new form Contenido
     */
    public ContenidoAlumno(String enunciado, String resA, String resB, String resC) {
        initComponents();
        jtEnunciado.setText(enunciado);
        jlResA.setText(resA);
        jlResB.setText(resB);
        jlResC.setText(resC);
        
        ButtonGroup group = new ButtonGroup();
        group.add(jrA);
        group.add(jrB);
        group.add(jrC);
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
        jlResC = new javax.swing.JLabel();
        jlResA = new javax.swing.JLabel();
        jlResB = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setBackground(new java.awt.Color(1, 25, 54));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtEnunciado.setEditable(false);
        jtEnunciado.setBackground(new java.awt.Color(1, 25, 54));
        jtEnunciado.setColumns(20);
        jtEnunciado.setForeground(new java.awt.Color(248, 217, 15));
        jtEnunciado.setRows(5);
        jtEnunciado.setText("Enunciado");
        jtEnunciado.setBorder(null);
        jScrollPane1.setViewportView(jtEnunciado);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 450, 110));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 500, 10));

        jrC.setForeground(new java.awt.Color(248, 217, 15));
        jrC.setText("C");
        jrC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrCActionPerformed(evt);
            }
        });
        add(jrC, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 40, -1));

        jrA.setForeground(new java.awt.Color(248, 217, 15));
        jrA.setText("A");
        jrA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrAActionPerformed(evt);
            }
        });
        add(jrA, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 40, -1));

        jrB.setForeground(new java.awt.Color(248, 217, 15));
        jrB.setText("B");
        add(jrB, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 40, -1));

        jlResC.setForeground(new java.awt.Color(248, 217, 15));
        jlResC.setText("Respuesta C");
        add(jlResC, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 217, 400, 30));

        jlResA.setForeground(new java.awt.Color(248, 217, 15));
        jlResA.setText("Respuesta A");
        add(jlResA, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 137, 400, 30));

        jlResB.setForeground(new java.awt.Color(248, 217, 15));
        jlResB.setText("Respuesta B");
        add(jlResB, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 177, 400, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void jrCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jrCActionPerformed

    private void jrAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jrAActionPerformed

        public JPanel ver(){
    return this;}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup group;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel jlResA;
    private javax.swing.JLabel jlResB;
    private javax.swing.JLabel jlResC;
    private javax.swing.JRadioButton jrA;
    private javax.swing.JRadioButton jrB;
    private javax.swing.JRadioButton jrC;
    private javax.swing.JTextArea jtEnunciado;
    // End of variables declaration//GEN-END:variables
}