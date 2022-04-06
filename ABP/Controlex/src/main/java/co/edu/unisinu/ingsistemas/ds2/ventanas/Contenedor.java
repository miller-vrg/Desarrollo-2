package co.edu.unisinu.ingsistemas.ds2.ventanas;

import co.edu.unisinu.ingsistemas.ds2.conector.Conector;
import co.edu.unisinu.ingsistemas.ds2.ventasAlumno.*;
import co.edu.unisinu.ingsistemas.ds2.ventasDocente.ContenidoDocente;
import java.awt.FlowLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author gasler (Miller vargas mola)
 * 
 */

public class Contenedor extends javax.swing.JPanel {

    private final int usuario;

    /**
     * Creates new form HomeAlumno
     */
    public Contenedor(String encabezado,int usuario) {
        initComponents();
        this.usuario = usuario;
        String fecha = new SimpleDateFormat("yy/MM/dd").format(Calendar.getInstance().getTime());
        jlFecha.setText(fecha);
        jlEncabezado.setText(encabezado);
        
        jpContenedor.removeAll();
        jpContenedor.updateUI();
        
        jpContenedor.setLayout(new BoxLayout(jpContenedor,BoxLayout.Y_AXIS));
        
        Conector objCn = null;
        Connection cn = null;
        PreparedStatement prepared = null;
        ResultSet resul = null;
                
        if( ("Practica").equals(jlEncabezado.getText()) ){
                
            try {
                objCn = new Conector();
                cn = objCn.getDriver();
               prepared = cn.prepareStatement("SELECT * FROM datos "
                                             + "JOIN creaciones  ON "
                                             + "datos.creaciones_id = creaciones.id "
                                             + "AND creaciones.tipo='Practicas';");
               resul = prepared.executeQuery();
                int con =0;
                while (resul.next()) {
                    ContenidoAlumno prueba = new ContenidoAlumno();
                    jpContenedor.add(prueba.conte(
                            (String) resul.getString("pregunta"),
                            (String) resul.getString("op_a"),
                            (String) resul.getString("op_b"),
                            (String) resul.getString("op_c")
                    ));
                    con++;

                }
                
               
              
              this.valor = 5.0 / con;
              System.out.println("Valor: " + valor);
              System.out.println("Preguntas: " + con);
               // JOptionPane.showMessageDialog(null,"Se abrio con exito la practica");
            } catch (Exception e) {
                System.out.print(e);
                JOptionPane.showMessageDialog(null,"Error al optener datos de la practica en la DB","ERROR!!!",JOptionPane.ERROR_MESSAGE);
            }finally{
            objCn.close(cn, resul, prepared);
            }

         
       
        } else if (("Examen").equals(jlEncabezado.getText())) {

            try {
                objCn = new Conector();
                cn = objCn.getDriver();
               prepared = cn.prepareStatement("SELECT * FROM datos "
                                             + "JOIN creaciones  ON "
                                             + "datos.creaciones_id = creaciones.id "
                                             + "AND creaciones.tipo='Examenes';");
               resul = prepared.executeQuery();
                int con =0;
                while (resul.next()) {
                    ContenidoAlumno prueba = new ContenidoAlumno();
                    jpContenedor.add(prueba.conte(
                            (String) resul.getString("pregunta"),
                            (String) resul.getString("op_a"),
                            (String) resul.getString("op_b"),
                            (String) resul.getString("op_c")
                    ));
                    con++;

                }
                
               
              
              this.valor = 5.0 / con;
              System.out.println("Valor: " + valor);
              System.out.println("Preguntas: " + con);
               // JOptionPane.showMessageDialog(null,"Se abrio con exito la practica");
            } catch (Exception e) {
                System.out.print(e);
                JOptionPane.showMessageDialog(null,"Error al optener datos de la practica en la DB","ERROR!!!",JOptionPane.ERROR_MESSAGE);
            }finally{
            objCn.close(cn, resul, prepared);
            }

        }else if ( ("Creación de Examen").equals(jlEncabezado.getText())  ){
                    jpContenedor.add(new ContenidoDocente("Crear Practica"));

        }else{
                jpContenedor.add(new Notas());

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

        setBackground(new java.awt.Color(41, 41, 77));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlEncabezado.setFont(new java.awt.Font("URW Bookman", 1, 24)); // NOI18N
        jlEncabezado.setForeground(new java.awt.Color(255, 214, 141));
        jlEncabezado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlEncabezado.setText("&tipo");
        add(jlEncabezado, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 490, -1));

        jlFecha.setFont(new java.awt.Font("URW Bookman", 1, 13)); // NOI18N
        jlFecha.setForeground(new java.awt.Color(255, 214, 141));
        jlFecha.setText("fecha");
        add(jlFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, -1));

        jlTiempo.setFont(new java.awt.Font("URW Bookman", 1, 13)); // NOI18N
        jlTiempo.setForeground(new java.awt.Color(255, 214, 141));
        jlTiempo.setText("Tiempo");
        add(jlTiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 60, 80, -1));

        jSeparator1.setBackground(new java.awt.Color(255, 214, 141));
        jSeparator1.setForeground(new java.awt.Color(255, 214, 141));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 700, 10));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 214, 141)));

        jpContenedor.setBackground(new java.awt.Color(41, 41, 77));
        jpContenedor.setLayout(new javax.swing.BoxLayout(jpContenedor, javax.swing.BoxLayout.LINE_AXIS));
        jScrollPane1.setViewportView(jpContenedor);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 550, 370));

        jbtnEnviar.setBackground(new java.awt.Color(255, 214, 141));
        jbtnEnviar.setFont(new java.awt.Font("URW Bookman", 1, 13)); // NOI18N
        jbtnEnviar.setForeground(new java.awt.Color(41, 41, 77));
        jbtnEnviar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/edu/unisinu/ingsistemas/ds2/icons/enviar.png"))); // NOI18N
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

        calcularNota();
        asignarNota();
        eliminarArchivo();
        jbtnEnviar.setEnabled(false);

    }//GEN-LAST:event_jbtnEnviarActionPerformed

    private void calcularNota(){
        
        try {
            File arch = new File("respuestas.txt");
            Scanner leer = new Scanner(arch);
            String contenido="";
            while (leer.hasNextLine()){
                contenido = leer.nextLine();
            }
            String[] respuestas;
            respuestas = (contenido).split("-");
            System.out.println(respuestas);
            Conector objCn = null;
            Connection cn = null;
            PreparedStatement prepared = null;
            ResultSet resul = null;
            
            try {
                objCn = new Conector();
                cn = objCn.getDriver();
                prepared = cn.prepareStatement("SELECT respuesta FROM " +
                                                "datos JOIN creaciones ON " +
                                                "creaciones.tipo ='Practicas' " +
                                                "AND datos.creaciones_id = creaciones.id;");
                resul = prepared.executeQuery();
                int con = 0;
                while( resul.next() ){
                   con++;
                nota += (resul.getString("respuesta").equalsIgnoreCase(respuestas[con]))? valor: 0;

                System.out.println(respuestas[con] + "-> nota: " + nota + " -> Valor: " + valor);
                
                }
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"No se pudo calcular su resultados","ERROR!!!",JOptionPane.ERROR_MESSAGE);
               System.out.println(ex);
            }finally{
            objCn.close(cn, resul, prepared);
            }
            
            JOptionPane.showMessageDialog(null,"Se guardaron con exito los resultados, puede revisar su nota en la seccion de notas");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"No se pudo guardar los resultados","ERROR!!!",JOptionPane.ERROR_MESSAGE);
            System.out.print(ex);
        }
    }
    private void asignarNota(){
    llenarId();
      Conector objCn = null;
            Connection cn = null;
            PreparedStatement prepared = null;
            
            try {
                objCn = new Conector();
                cn = objCn.getDriver();
                prepared = cn.prepareStatement("INSERT INTO registros(nota,creaciones_id,docentes_id,estudiante_id)"
                                             + "VALUE(?,?,?,?)");
                prepared.setFloat(1, nota);
                prepared.setFloat(2, creacionesId);
                prepared.setInt(3, docentesId);
                prepared.setInt(4, usuario);
                prepared.executeUpdate();
                JOptionPane.showMessageDialog(null,"Se guardo con exito su nota");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"No se pudo guardar su nota","ERROR!!!",JOptionPane.ERROR_MESSAGE);
               System.out.println(ex);
            }finally{
            objCn.close(cn, prepared);
            }
    
    }
    private void eliminarArchivo(){
    File myObj = new File("respuestas.txt"); 
    if (myObj.delete()) { 
      System.out.println("Deleted the file: " + myObj.getName());
    } else {
      System.out.println("Failed to delete the file.");
  } 
}
   
    private void llenarId() {
        Conector objCn = null;
        Connection cn = null;
        PreparedStatement prepared = null;
        ResultSet resul = null;
        try {
            objCn = new Conector();
            cn = objCn.getDriver();
            prepared = cn.prepareStatement("SELECT * FROM"
                    + " creaciones  WHERE "
                    + "'" + creacionesId + "' = creaciones.id;");
            resul = prepared.executeQuery();
            int con = 0;
            while (resul.next()) {
                ContenidoAlumno prueba = new ContenidoAlumno();
                docentesId  = resul.getInt("docentes_id");
            }
            // JOptionPane.showMessageDialog(null,"Se abrio con exito la practica");
        } catch (Exception e) {
            System.out.print(e);
        } finally {
            objCn.close(cn, resul, prepared);
        }
    }
    
    public void setNota(float nota){
    this.nota = nota; 
    }
    
    public float getNota(float nota){
    return this.nota; 
    }
    
    public void setValor(double valor){
    this.valor = valor; 
    }
    
    public double getValor(float nota){
    return this.valor; 
    }
    
    public JPanel ver(){
    return this;}
    

    private float nota;
    private double valor;
    private int creacionesId = 1;
    private int docentesId;
    
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
