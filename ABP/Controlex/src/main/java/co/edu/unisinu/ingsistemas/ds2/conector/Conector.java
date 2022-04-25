package co.edu.unisinu.ingsistemas.ds2.conector;

/**
 *
 * @author gasler(miller vargas)
 *
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conector {


    public Connection getDriver() throws SQLException {
            String url = "jdbc:mysql://localhost:3306/"
                    + "registro_examen";
            String  user = "root";
            String password = "";
            
      return DriverManager.getConnection(url,user,password);
    }

    public void close(Connection conect, ResultSet resul, PreparedStatement prepared) {
        try {
            conect.close();
               resul.close();
                prepared.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex + "Error al cerrar conexiòn");
        }
    }
        public void close(Connection conect, PreparedStatement prepared) {
        try {
            conect.close();
                prepared.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex + "Error al cerrar conexiòn");
        }
    }

}
