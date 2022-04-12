package test;

import javax.swing.*;
import  java.awt.*;

public class Home extends JFrame{

    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btnSalir;
    private JLabel jlBienvenida;
    private JLabel jlMensaje;
    private JLabel jlEtiqueta1;
    private JLabel jlEtiqueta2;
    private JLabel jlEtiqueta3;
    private JPanel jpIntercambio;
    private JPanel jpHome;
    
    Home( String user, String etiqueta1, String  etiqueta3){
        
        construir( user, etiqueta1, etiqueta3 );

    }
    private void construir(String user, String etiqueta1, String etiqueta3) {
    final Font myFont = new Font("Serif", Font.BOLD, 14);
    final Color fondo = new Color(1,25,54);
    final Color colorFuente = new Color(248,217,15);
    
    final Icon salir = new ImageIcon(getClass().getResource("/co/edu/unisinu/ingsistemas/ds2/icons/cerrarsesion.png"));
    final Icon practica = new ImageIcon(getClass().getResource("/co/edu/unisinu/ingsistemas/ds2/icons/practica.png"));
    final Icon examen = new ImageIcon(getClass().getResource("/co/edu/unisinu/ingsistemas/ds2/icons/examen.png"));
    final Icon notas = new ImageIcon(getClass().getResource("/co/edu/unisinu/ingsistemas/ds2/icons/calificaciones.png"));
    
    jlBienvenida  = new JLabel("Bienvenido");
    jlBienvenida.setFont (new Font("Serif", Font.BOLD, 40));
    jlBienvenida.setForeground(colorFuente);
    jlBienvenida.setBounds (244,90,240,60);
        
    jlMensaje = new JLabel(user);
    jlMensaje.setFont(new Font("Serif", Font.BOLD, 20));
    jlMensaje.setForeground(colorFuente);
    jlMensaje.setBounds(244, 97, 250, 150);

    btnSalir = new JButton();
    btnSalir.setIcon(salir);
    btnSalir.setBackground(fondo);
    btnSalir.setBounds(663, 10, 24, 24);
    
    btn1 = new JButton();
    btn1.setIcon(practica);
    btn1.setBackground(fondo);
    btn1.setBounds(195, 275, 64, 64);
    
    btn2 = new JButton();
    btn2.setIcon(notas);
    btn2.setBackground(fondo);
    btn2.setBounds(318, 275, 64, 64);
    
    btn3 = new JButton();
    btn3.setIcon(examen);
    btn3.setBackground(fondo);
    btn3.setBounds(448, 275, 64, 64);
    
    jlEtiqueta1 = new JLabel(etiqueta1);
    jlEtiqueta1.setFont(myFont);
    jlEtiqueta1.setForeground(colorFuente);
    jlEtiqueta1.setBounds(180, 348, 144, 25);
    
    jlEtiqueta2 = new JLabel("Notas");
    jlEtiqueta2.setFont(myFont);
    jlEtiqueta2.setForeground(colorFuente);
    jlEtiqueta2.setBounds(327, 348, 51, 25);
    
    jlEtiqueta3 = new JLabel(etiqueta3);
    jlEtiqueta3.setFont(myFont);
    jlEtiqueta3.setForeground(colorFuente);
    jlEtiqueta3.setBounds(436, 348, 158, 25);
    
    jpHome = new JPanel();
    jpHome.setBounds(0,0, 700, 500);
    jpHome.setLayout(null);
    jpHome.setBackground(fondo);
    jpHome.add(jlBienvenida);
    jpHome.add(jlMensaje);
    jpHome.add(btnSalir);
   jpHome.add(btn1);
    jpHome.add(btn2);
    jpHome.add(btn3);
    jpHome.add(jlEtiqueta1);
    jpHome.add(jlEtiqueta2);
    jpHome.add(jlEtiqueta3);
    
    jpIntercambio = new JPanel();
    jpIntercambio.setBackground(fondo);
    jpIntercambio.setLayout(null);
    jpIntercambio.setBounds(0,0, 700, 500);
    jpIntercambio.add(jpHome);
    
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setSize(700,500);
        add(jpIntercambio);
        setLocationRelativeTo(null);
        setVisible(true);
     }

     public static void main(String args[]){
       new Home("Alumno:  Miller vargas","Practica","Examen");
     }
}
