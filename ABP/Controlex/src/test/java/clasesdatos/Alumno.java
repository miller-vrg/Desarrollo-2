
package clasesdatos;

/**
 *
 * @author gasler (Miler vargas mola)
 * 
 */

public class Alumno {
    
private String nombre;
private int matricula;
private ControlEscrito controlEscrito;
private Practica practica;

public Alumno(){}

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the matricula
     */
    
    public int getMatricula() {
        return matricula;
    }

    /**
     * @param matricula the matricula to set
     */
    
    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

}
