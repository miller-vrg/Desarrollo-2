
package co.edu.unisinu.ingsistemas.ds2.datos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author gasler
 */
@Entity
@Table(name = "creaciones")
@NamedQueries({
    @NamedQuery(name = "Creaciones.findAll", query = "SELECT c FROM Creaciones c"),
    @NamedQuery(name = "Creaciones.findById", query = "SELECT c FROM Creaciones c WHERE c.id = :id"),
    @NamedQuery(name = "Creaciones.findByTipo", query = "SELECT c FROM Creaciones c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "Creaciones.findByApertura", query = "SELECT c FROM Creaciones c WHERE c.apertura = :apertura"),
    @NamedQuery(name = "Creaciones.findByColaboradores", query = "SELECT c FROM Creaciones c WHERE c.colaboradores = :colaboradores"),
    @NamedQuery(name = "Creaciones.findByFechaCrecion", query = "SELECT c FROM Creaciones c WHERE c.fechaCrecion = :fechaCrecion")})
public class Creaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "apertura")
    @Temporal(TemporalType.DATE)
    private Date apertura;
    @Column(name = "colaboradores")
    private String colaboradores;
    @Basic(optional = false)
    @Column(name = "fecha_crecion")
    @Temporal(TemporalType.DATE)
    private Date fechaCrecion;
    @JoinColumn(name = "dni_docente", referencedColumnName = "dni")
    @ManyToOne
    private Docentes dniDocente;
    @JoinColumn(name = "name_prueba", referencedColumnName = "name")
    @ManyToOne(optional = false)
    private Pruebas namePrueba;

    public Creaciones() {
    }

    public Creaciones(Integer id) {
        this.id = id;
    }

    public Creaciones(Integer id, String tipo, Date apertura, Date fechaCrecion) {
        this.id = id;
        this.tipo = tipo;
        this.apertura = apertura;
        this.fechaCrecion = fechaCrecion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getApertura() {
        return apertura;
    }

    public void setApertura(Date apertura) {
        this.apertura = apertura;
    }

    public String getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(String colaboradores) {
        this.colaboradores = colaboradores;
    }

    public Date getFechaCrecion() {
        return fechaCrecion;
    }

    public void setFechaCrecion(Date fechaCrecion) {
        this.fechaCrecion = fechaCrecion;
    }

    public Docentes getDniDocente() {
        return dniDocente;
    }

    public void setDniDocente(Docentes dniDocente) {
        this.dniDocente = dniDocente;
    }

    public Pruebas getNamePrueba() {
        return namePrueba;
    }

    public void setNamePrueba(Pruebas namePrueba) {
        this.namePrueba = namePrueba;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Creaciones)) {
            return false;
        }
        Creaciones other = (Creaciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unisinu.ingsistemas.ds2.datos.Creaciones[ id=" + id + " ]";
    }
    
}
