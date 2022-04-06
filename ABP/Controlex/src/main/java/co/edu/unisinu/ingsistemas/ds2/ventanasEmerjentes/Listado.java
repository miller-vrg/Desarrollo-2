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
public class Listado extends javax.swing.JDialog implements ItemListener{

    private boolean veri = true;
    private String seleccion;
    private int usuario;
    /**
     * Creates new form Login
     */

    public Listado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        seleccion = "Alumno";
    }

    public Listado( String encabezado, int usuario) {
        jlEncabezado.setText(encabezado);
        this.usuario = usuario;
        new Listado(new javax.swing.JFrame(), true);
    }

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jlEncabezado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(48, 49, 88));
        setModal(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(41, 41, 77));
        jPanel1.setMaximumSize(new java.awt.Dimension(415, 490));
        jPanel1.setMinimumSize(new java.awt.Dimension(415, 490));
        jPanel1.setLayout(null);

        jTable1.setFont(new java.awt.Font("URW Bookman", 0, 13)); // NOI18N
        jTable1.setForeground(new java.awt.Color(255, 214, 141));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nº", "Nombre", "Preguntas"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(40, 50, 452, 402);

        jlEncabezado.setFont(new java.awt.Font("URW Bookman", 1, 13)); // NOI18N
        jlEncabezado.setForeground(new java.awt.Color(255, 214, 141));
        jlEncabezado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlEncabezado.setText("jLabel1");
        jPanel1.add(jlEncabezado);
        jlEncabezado.setBounds(80, 7, 350, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 477, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void run() {
       
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Listado dialog = new Listado(new javax.swing.JFrame(), true);
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel jlEncabezado;
    // End of variables declaration//GEN-END:variables

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

    @Override
    public void itemStateChanged(ItemEvent ie) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
