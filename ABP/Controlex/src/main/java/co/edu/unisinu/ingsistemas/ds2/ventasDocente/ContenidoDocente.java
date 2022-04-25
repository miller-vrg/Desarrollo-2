package co.edu.unisinu.ingsistemas.ds2.ventasDocente;

import co.edu.unisinu.ingsistemas.ds2.conector.Conector;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author gasler
 */
public class ContenidoDocente extends javax.swing.JPanel {

    private final String advertencia;
    private final ImageIcon iconAdvertencia;
    private final String correcto;
    private final ImageIcon iconCorrecto;
    private final String error;
    private final ImageIcon iconError;
    private String tipo;
    private String encabezado;
    private String colaboradores;
    private final int usuario;
    private String name;

    /**
     * Creates new form ContenidoDocente
     */
    public ContenidoDocente(String enunciado,int usuario) {
        i = 0;
        this.usuario = usuario;
        contenido = new HashMap<String,HashMap>();
        initComponents();
        encabezado = enunciado;
        jtEnunciado.setText(enunciado);
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(jrA);
        grupo.add(jrB);
        grupo.add(jrC);
        
        advertencia = "/co/edu/unisinu/ingsistemas/ds2/icons/advertencia.png";
        iconAdvertencia = new ImageIcon(getClass().getResource(advertencia));
        
        correcto = "/co/edu/unisinu/ingsistemas/ds2/icons/correcto.png";
        iconCorrecto = new ImageIcon(getClass().getResource(correcto));
        
        error = "/co/edu/unisinu/ingsistemas/ds2/icons/error.png";
        iconError = new ImageIcon(getClass().getResource(error));
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtEnunciado = new javax.swing.JTextArea();
        jrC = new javax.swing.JRadioButton();
        jrA = new javax.swing.JRadioButton();
        jrB = new javax.swing.JRadioButton();
        jtResC = new javax.swing.JTextField();
        jtResA = new javax.swing.JTextField();
        jtResB = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnTerminal = new javax.swing.JButton();

        setBackground(new java.awt.Color(41, 41, 77));
        setForeground(new java.awt.Color(255, 214, 141));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtEnunciado.setBackground(new java.awt.Color(41, 41, 77));
        jtEnunciado.setColumns(20);
        jtEnunciado.setFont(new java.awt.Font("URW Bookman", 1, 13)); // NOI18N
        jtEnunciado.setForeground(new java.awt.Color(255, 214, 141));
        jtEnunciado.setRows(5);
        jtEnunciado.setText("Enunciado");
        jtEnunciado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(1, 25, 54)));
        jScrollPane1.setViewportView(jtEnunciado);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 450, 110));

        jrC.setFont(new java.awt.Font("URW Bookman", 1, 13)); // NOI18N
        jrC.setForeground(new java.awt.Color(255, 214, 141));
        jrC.setText("C");
        add(jrC, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 60, -1));

        jrA.setFont(new java.awt.Font("URW Bookman", 1, 13)); // NOI18N
        jrA.setForeground(new java.awt.Color(255, 214, 141));
        jrA.setText("A");
        add(jrA, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 50, -1));

        jrB.setFont(new java.awt.Font("URW Bookman", 1, 13)); // NOI18N
        jrB.setForeground(new java.awt.Color(255, 214, 141));
        jrB.setText("B");
        add(jrB, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 50, -1));

        jtResC.setBackground(new java.awt.Color(41, 41, 77));
        jtResC.setFont(new java.awt.Font("URW Bookman", 0, 13)); // NOI18N
        jtResC.setForeground(new java.awt.Color(255, 214, 141));
        add(jtResC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 410, -1));

        jtResA.setBackground(new java.awt.Color(41, 41, 77));
        jtResA.setColumns(20);
        jtResA.setFont(new java.awt.Font("URW Bookman", 0, 13)); // NOI18N
        jtResA.setForeground(new java.awt.Color(255, 214, 141));
        add(jtResA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 410, -1));

        jtResB.setBackground(new java.awt.Color(41, 41, 77));
        jtResB.setFont(new java.awt.Font("URW Bookman", 0, 13)); // NOI18N
        jtResB.setForeground(new java.awt.Color(255, 214, 141));
        add(jtResB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 410, -1));

        btnLimpiar.setBackground(new java.awt.Color(255, 214, 141));
        btnLimpiar.setFont(new java.awt.Font("URW Bookman", 1, 13)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(41, 41, 77));
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/edu/unisinu/ingsistemas/ds2/icons/limpiar.png"))); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, -1, 40));

        btnGuardar.setBackground(new java.awt.Color(255, 214, 141));
        btnGuardar.setFont(new java.awt.Font("URW Bookman", 1, 13)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(41, 41, 77));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/edu/unisinu/ingsistemas/ds2/icons/guardar.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 310, 130, 40));

        btnTerminal.setBackground(new java.awt.Color(255, 214, 141));
        btnTerminal.setFont(new java.awt.Font("URW Bookman", 1, 13)); // NOI18N
        btnTerminal.setForeground(new java.awt.Color(41, 41, 77));
        btnTerminal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/edu/unisinu/ingsistemas/ds2/icons/tarea.png"))); // NOI18N
        btnTerminal.setText("Finalizar");
        btnTerminal.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnTerminal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTerminalActionPerformed(evt);
            }
        });
        add(btnTerminal, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 310, 150, 40));
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        int op = JOptionPane.showConfirmDialog(this, "¿Seguro que quiere guardar este bloque?","Confirmarmar",JOptionPane.YES_NO_OPTION,0,iconAdvertencia);
        
        if( op == JOptionPane.YES_OPTION ){
        i ++;
        guardar();
    }
        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnTerminalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerminalActionPerformed

        int op = JOptionPane.showConfirmDialog(this, "¿Seguro que desea guardar los " + contenido.size() + " bloques en la base de datos?","Confirmar",JOptionPane.YES_NO_OPTION,0,iconAdvertencia);
        
        if( op == JOptionPane.YES_OPTION){
        String name2=null;
        
        if ( contenido.size() == 0 ) {
            JOptionPane.showMessageDialog(this, "Por favor primero guarde datos para finalizar", "Aviso", 0, iconAdvertencia);
        } else {

            name = JOptionPane.showInputDialog(this, "Dele un nombre a su creacion (Un nombre unico)",iconAdvertencia);
            Conector objCn = null;
            Connection cn = null;
            PreparedStatement prepared = null;
            ResultSet resul = null;

            try {
                objCn = new Conector();
                cn = objCn.getDriver();
                prepared = cn.prepareStatement("SELECT nombre FROM creaciones"
                        + " WHERE nombre = '" + name + "';");
                resul = prepared.executeQuery();
                while (resul.next()) {

                    name2 = resul.getString("nombre");
                    System.out.println(name2);
                }
                System.out.println("222 "+name2);
            } catch (SQLException e) {

            } finally {
                objCn.close(cn, resul, prepared);
            }

            if (name2 == null) {
                System.out.println(jtEnunciado.getText());
                tipo = ((encabezado).equalsIgnoreCase("Crear examen"))?"Examenes":"Practicas";
                
                int op1 = JOptionPane.showConfirmDialog(this,"¿Tubo colaboradores?","Pregunta",JOptionPane.YES_NO_OPTION,0,iconAdvertencia);

                if(op1 == JOptionPane.YES_OPTION){
                colaboradores = JOptionPane.showInputDialog(this,"Dijite los nombres separados por comas (,)",iconAdvertencia);
                }else{
                colaboradores = "Ninguno";
                }
                    try {

                        int c = contenido.size();
                        System.out.println(c);
                        System.out.println(contenido);
                        
                        objCn = new Conector();
                                cn = objCn.getDriver();
                                prepared = cn.prepareStatement("INSERT INTO creaciones(nombre,tipo,docentes_id,colaboradores)"
                                        + "VALUE(?,?,?,?)");
                                prepared.setString(1, name);
                                prepared.setString(2, tipo);
                                prepared.setInt(3, usuario);
                                prepared.setString(4, colaboradores);
                                prepared.executeUpdate();
                        

                        for (int a = 1; a <= c; a++) {
                            HashMap<String, String> datos = contenido.get("pre-" + a);

                            System.out.println(datos.get("enunciado"));
                            System.out.println(datos.get("res-A"));
                            System.out.println(datos.get("res-B"));
                            System.out.println(datos.get("res-C"));
                            System.out.println(datos.get("correcta"));
                            
                            
                            try {
                                cn = objCn.getDriver();
                                prepared = cn.prepareStatement("INSERT INTO datos(pregunta,op_a,op_b,op_c,respuesta,creaciones_id)"
                                        + "VALUE(?,?,?,?,?,?)");
                                 
                                prepared.setString(1, datos.get("enunciado"));
                                prepared.setString(2, datos.get("res-A"));
                                prepared.setString(3, datos.get("res-B"));
                                prepared.setString(4, datos.get("res-C"));
                                prepared.setString(5, datos.get("correcta"));
                                prepared.setInt(6, idCreaciones());System.out.println("line 267");
                                prepared.executeUpdate();
                                 
                            } catch (SQLException ex) {
                                System.out.println(ex);
                                JOptionPane.showMessageDialog(null, "No se pudo guardar su prueba", "ERROR!!!", JOptionPane.ERROR_MESSAGE, iconError);
                                System.out.println(ex);
                            } finally {
                                objCn.close(cn, prepared);
                            }

                        }

                        JOptionPane.showMessageDialog(null, "Guardado con exito", "Exito", 0, iconCorrecto);

                        limpiar();
                        contenido.clear();
                    } catch (Exception e) {
                        System.out.println(e);
                        JOptionPane.showMessageDialog(null, "Archivo no existete o fuera de la ruta", "Error al guardar!!!", JOptionPane.ERROR_MESSAGE, iconError);
                    }

                }else{
            JOptionPane.showMessageDialog(this, "Por favor asigne un nombre a su creacion","Aviso",0,iconAdvertencia);
            }
        }
        }else{
        
        }

    }//GEN-LAST:event_btnTerminalActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed

        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    
    private void limpiar(){
    
        jtEnunciado.setText("");
        jtResA.setText("");
        jtResB.setText("");
        jtResC.setText("");
        
    }
    
    
    private void guardar() {

            String res = jtResA.getText();
            res = (jrB.isSelected()) ? jtResB.getText() : res;
            res = (jrC.isSelected()) ? jtResC.getText() : res;
            
            HashMap<String,String> contenidoPreguntas = new HashMap<String,String>();
            contenidoPreguntas.put("enunciado", jtEnunciado.getText());
            contenidoPreguntas.put("res-A",jtResA.getText());
            contenidoPreguntas.put("res-B",jtResB.getText());
            contenidoPreguntas.put("res-C",jtResC.getText());
            contenidoPreguntas.put("correcta", res);
            contenido.put("pre-" + i, contenidoPreguntas);
            JOptionPane.showMessageDialog(null, "Guardado con exito el bloque " + contenido.size() + " de preguntas y respuestas", "Exito", 0, iconCorrecto);

    }

    private int i;
    private String nameCuestion;
    private HashMap<String,HashMap> contenido;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnTerminal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton jrA;
    private javax.swing.JRadioButton jrB;
    private javax.swing.JRadioButton jrC;
    private javax.swing.JTextArea jtEnunciado;
    private javax.swing.JTextField jtResA;
    private javax.swing.JTextField jtResB;
    private javax.swing.JTextField jtResC;
    // End of variables declaration//GEN-END:variables

    private int idCreaciones() {
int r = 0;
System.out.println("351");
            Conector objcn = null;
            Connection cn = null;
            PreparedStatement prepared = null;
            ResultSet resul = null;
        
        try {
            objcn = new Conector();
            cn = objcn.getDriver();
            prepared = cn.prepareStatement("SELECT id FROM creaciones "
                                         + "WHERE nombre = '" + name + "';");
            resul = prepared.executeQuery();
            System.out.println("r: " +r);
            while(resul.next()){
            r = resul.getInt("id");
            }
            System.out.println("367r: " +r);
        } catch (SQLException ex) {
            Logger.getLogger(ContenidoDocente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
}
