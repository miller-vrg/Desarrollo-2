package co.edu.unisinu.ingsistemas.ds2.ventanasEmerjentes;

import co.edu.unisinu.ingsistemas.ds2.conector.Conector;
import co.edu.unisinu.ingsistemas.ds2.ventanas.Home;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
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
    private String name;
    /**
     * Creates new form Login
     */

    public Listado(java.awt.Frame parent, boolean modal, String encabezado, int usuario) {
        super(parent, modal);
  
        initComponents();
        jlEncabezado.setText(encabezado);
        this.usuario = usuario;
        
        advertencia = "/co/edu/unisinu/ingsistemas/ds2/icons/advertencia.png";
        iconAdvertencia = new ImageIcon(getClass().getResource(advertencia));
        
        correcto = "/co/edu/unisinu/ingsistemas/ds2/icons/correcto.png";
        iconCorrecto = new ImageIcon(getClass().getResource(correcto));
        
        error = "/co/edu/unisinu/ingsistemas/ds2/icons/error.png";
        iconError = new ImageIcon(getClass().getResource(error));
       
        llenarTabla();
        run();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JtableLista = new javax.swing.JTable();
        jlEncabezado = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(48, 49, 88));
        setModal(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(41, 41, 77));
        jPanel1.setMaximumSize(new java.awt.Dimension(415, 490));
        jPanel1.setMinimumSize(new java.awt.Dimension(415, 490));
        jPanel1.setLayout(null);

        JtableLista.setBackground(new java.awt.Color(41, 41, 77));
        JtableLista.setFont(new java.awt.Font("URW Bookman", 1, 13)); // NOI18N
        JtableLista.setForeground(new java.awt.Color(255, 214, 141));
        JtableLista.setModel(new javax.swing.table.DefaultTableModel(
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
        JtableLista.setGridColor(new java.awt.Color(255, 214, 141));
        JtableLista.setSelectionBackground(new java.awt.Color(255, 214, 141));
        JtableLista.setSelectionForeground(new java.awt.Color(41, 41, 77));
        JtableLista.setShowGrid(true);
        jScrollPane1.setViewportView(JtableLista);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(40, 50, 452, 402);

        jlEncabezado.setFont(new java.awt.Font("URW Bookman", 1, 13)); // NOI18N
        jlEncabezado.setForeground(new java.awt.Color(255, 214, 141));
        jlEncabezado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlEncabezado.setText("jLabel1");
        jPanel1.add(jlEncabezado);
        jlEncabezado.setBounds(130, 10, 290, 30);

        jButton1.setBackground(new java.awt.Color(255, 214, 141));
        jButton1.setFont(new java.awt.Font("URW Bookman", 1, 13)); // NOI18N
        jButton1.setForeground(new java.awt.Color(41, 41, 77));
        jButton1.setText("Ver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(440, 10, 72, 23);

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
    }// </editor-fold>                        

     private void llenarTabla(){
    modelo  = new DefaultTableModel();
    JtableLista.setModel(modelo);
  
    Conector objConect = null;
    Connection cn = null;
    PreparedStatement prepared = null;
    ResultSet resul = null;
    
    String consulta = "SELECT nombre FROM creaciones"
                    + " WHERE tipo = '" + jlEncabezado.getText() + "';";
        
        try {
            
            objConect = new Conector();
            cn = objConect.getDriver();
            prepared = cn.prepareStatement(consulta);
            resul = prepared.executeQuery();
            
            modelo.addColumn("N°");
            modelo.addColumn("Name");
            
            int con = 1;
            while(resul.next()){
                
            Object[] datos = new Object[3];
            datos[0]= con;
            datos[1]= resul.getString("nombre");
            con++;
            modelo.addRow(datos);
            
            }
            
            for (int i = 0; i < 30; i++) {
                Object[] datos = new Object[3];
                datos[0] = "";
                datos[1] = "";
                modelo.addRow(datos);
            }
        
            
        } catch (SQLException ex) {
           System.out.println(ex);
           JOptionPane.showMessageDialog(null,"Error al optener el listado de las parcticas","ERROR",JOptionPane.ERROR_MESSAGE,iconError);
        }finally{
        objConect.close(cn, resul, prepared);
        }
    }
    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        
        if (JtableLista.getSelectedRow() != -1) {
            int opcion = JOptionPane.showConfirmDialog(null, "Solo contara con 1 hora para terminar su prueba y las respuestas no pueden ser cambiadas", "Confirmar eleccion", JOptionPane.YES_NO_OPTION, 0, iconAdvertencia);

            if (opcion == JOptionPane.YES_OPTION) {
                setName((String) modelo.getValueAt(JtableLista.getSelectedRow(), 1));
                System.out.println(getName());

                if (getName().equalsIgnoreCase(null)) {
                    JOptionPane.showMessageDialog(null, "Seleccione una fila con argumentos", "WARNING", JOptionPane.WARNING_MESSAGE, iconAdvertencia);
                } else {
                    dispose();
                }

            } else {
                setName(null);
                JOptionPane.showMessageDialog(null, "Seleccione una fila primero", "WARNING", JOptionPane.WARNING_MESSAGE, iconAdvertencia);
                System.out.println("Seleccione una fila primero");

            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    public void run() {
       
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        dispose();
                    }
                });
            }
        });
    }
    
    private ImageIcon iconAdvertencia;
    private ImageIcon iconError;
    private ImageIcon iconCorrecto;
    private String advertencia;
    private String error;
    private String correcto;
    private DefaultTableModel modelo;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JtableLista;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
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
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
