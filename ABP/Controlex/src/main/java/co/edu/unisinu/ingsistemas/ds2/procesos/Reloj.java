
package co.edu.unisinu.ingsistemas.ds2.procesos;

import java.util.*;

/**
 *
 * @author gasler
 * 
 */

public class Reloj {
    
    private String hora;
    private String minuto;
    private String segundo;
    private Calendar tiempo;
    
    public Reloj(){
    
        tiempo = new GregorianCalendar();
        
        hora = Integer.toString(tiempo.get(Calendar.HOUR_OF_DAY));
        minuto = Integer.toString(tiempo.get(Calendar.MINUTE));
        segundo = Integer.toString(tiempo.get(Calendar.SECOND));
            
    }
    
    public String verTiempo(){
    
     int segundo = Integer.parseInt(this.segundo);
     int hora = Integer.parseInt(this.hora);
     int minuto = Integer.parseInt(this.minuto);
     
     this.hora = ( hora > 12 )? "" + ( hora - 12 ) : this.hora ;
     this.hora = (Integer.parseInt(this.hora) > 9)? this.hora :"0" + this.hora;
     
     this.hora = ( hora <= 9 )? "0" + hora : this.hora ;
     this.minuto = ( minuto <= 9 )? "0" + minuto : this.minuto ;
     this.segundo = ( segundo <= 9 )? "0" + segundo : this.segundo ;
     
    return ( segundo%2 == 0 )? this.hora + ":" + this.minuto + ":" + this.segundo : this.hora + " " + this.minuto + " " + this.segundo ;    
    }
    

}
