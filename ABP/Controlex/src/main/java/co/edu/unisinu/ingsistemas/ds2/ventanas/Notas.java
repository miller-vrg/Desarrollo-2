package co.edu.unisinu.ingsistemas.ds2.ventanas;

import co.edu.unisinu.ingsistemas.ds2.conector.Conector;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author gasler
 */
public class Notas extends javax.swing.JPanel {

    /**
     * Creates new form Notas
     */
    private int usuario;
    private String tipo;

    public Notas(int usuario, String tipo) {
        initComponents();
        this.tipo = tipo;
        this.usuario = usuario;
        setBounds(0, 0, 500, 300);
        llenarTabla(usuario);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        JtableLista = new javax.swing.JTable();

        setBackground(new java.awt.Color(1, 25, 54));

        JtableLista.setBackground(new java.awt.Color(41, 41, 77));
        JtableLista.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 214, 141), 1, true));
        JtableLista.setFont(new java.awt.Font("URW Bookman", 0, 12)); // NOI18N
        JtableLista.setForeground(new java.awt.Color(255, 214, 141));
        JtableLista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nombres", "tipo", "Nota"
            }
        ));
        JtableLista.setEditingColumn(0);
        JtableLista.setEditingRow(0);
        JtableLista.setGridColor(new java.awt.Color(255, 214, 141));
        JtableLista.setSelectionBackground(new java.awt.Color(255, 214, 141));
        JtableLista.setSelectionForeground(new java.awt.Color(41, 41, 77));
        JtableLista.setShowGrid(true);
        jScrollPane1.setViewportView(JtableLista);
        if (JtableLista.getColumnModel().getColumnCount() > 0) {
            JtableLista.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void llenarTabla(int usuario) {

        modelo = new DefaultTableModel();
        JtableLista.getTableHeader().setBackground(new Color(255, 214, 141));
        JtableLista.getTableHeader().setForeground(new Color(41, 41, 77));
        JtableLista.getTableHeader().setFont(new Font("URWBookman", 1, 12));
        JtableLista.setModel(modelo);

        Conector objConect = null;
        Connection cn = null;
        PreparedStatement prepared = null;
        ResultSet resul = null;

        System.out.println("95");
        if (tipo.equalsIgnoreCase("Alumno")) {
            System.out.println("97");
            String consulta = "SELECT nombre, nota, tipo FROM registros r, creaciones c "
                    + "WHERE r.estudiante_id = " + usuario + " ;";

            try {

                objConect = new Conector();
                cn = objConect.getDriver();
                prepared = cn.prepareStatement(consulta);
                resul = prepared.executeQuery();

                modelo.addColumn("N°.");
                modelo.addColumn("Name");
                modelo.addColumn("Tipo");
                modelo.addColumn("Notas");

                int[] anchos = {50, 200, 50, 50};

                for (int i = 0; i < 4; i++) {

                    JtableLista.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);

                }

                int con = 1;
                while (resul.next()) {
                    Object[] datos = new Object[4];
                    datos[0] = con;
                    datos[1] = resul.getString("nombre");
                    datos[2] = resul.getString("tipo");
                    datos[3] = resul.getString("nota");
                    con++;
                    modelo.addRow(datos);
                }

                for (int i = 0; i < 30; i++) {
                    Object[] datos = new Object[4];
                    datos[0] = "";
                    datos[1] = "";
                    datos[2] = "";
                    datos[3] = "";
                    modelo.addRow(datos);
                }

            } catch (SQLException ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(null, "Error al optener el listado de las parcticas", "ERROR", JOptionPane.ERROR_MESSAGE);
            } finally {
                objConect.close(cn, resul, prepared);
            }

        } else {
            System.out.println("150");
            String consulta = "SELECT e.name, apellidos, c.nombre, tipo, nota "
                    + "FROM registros r,creaciones c, estudiantes e"
                    + " WHERE r.docentes_id = " + usuario + " ;";

            try {

                objConect = new Conector();
                cn = objConect.getDriver();
                prepared = cn.prepareStatement(consulta);
                resul = prepared.executeQuery();

                modelo.addColumn("N°.");
                modelo.addColumn("N. Estudiante");
                modelo.addColumn("A. Estudiante");
                modelo.addColumn("Prueba");
                modelo.addColumn("Tipo");
                modelo.addColumn("Notas");

                int[] anchos = {40, 60, 100, 150, 70, 20};

                for (int i = 0; i < 6; i++) {

                    JtableLista.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);

                }

                int con = 1;
                while (resul.next()) {
                    Object[] datos = new Object[6];
                    datos[0] = con;
                    datos[1] = resul.getString("e.name");
                    datos[2] = resul.getString("apellidos");
                    datos[3] = resul.getString("c.name");
                    datos[4] = resul.getString("tipo");
                    datos[5] = resul.getString("nota");
                    con++;
                    modelo.addRow(datos);
                }

                for (int i = 0; i < 22; i++) {
                    Object[] datos = new Object[6];
                    datos[0] = "";
                    datos[1] = "";
                    datos[2] = "";
                    datos[3] = "";
                    datos[4] = "";
                    datos[5] = "";
                    modelo.addRow(datos);
                }

            } catch (SQLException ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(null, "Error al optener el listado de las parcticas", "ERROR", JOptionPane.ERROR_MESSAGE);
            } finally {
                objConect.close(cn, resul, prepared);
            }

        }

    }

    private DefaultTableModel modelo;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JtableLista;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
