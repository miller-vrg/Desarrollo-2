
package clasesdatos;

/**
 *
 * @author gasler
 */
class ControlEscrito {
    
private int numeroControl;
private int numeroPreguntas;
private String fecha;
private float nota;

    /**
     * @return the numeroControl
     */
    public int getNumeroControl() {
        return numeroControl;
    }

    /**
     * @param numeroControl the numeroControl to set
     */
    public void setNumeroControl(int numeroControl) {
        this.numeroControl = numeroControl;
    }

    /**
     * @return the numeroPreguntas
     */
    public int getNumeroPreguntas() {
        return numeroPreguntas;
    }

    /**
     * @param numeroPreguntas the numeroPreguntas to set
     */
    public void setNumeroPreguntas(int numeroPreguntas) {
        this.numeroPreguntas = numeroPreguntas;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the nota
     */
    public float getNota() {
        return nota;
    }

    /**
     * @param nota the nota to set
     */
    public void setNota(float nota) {
        this.nota = nota;
    }
    
}
