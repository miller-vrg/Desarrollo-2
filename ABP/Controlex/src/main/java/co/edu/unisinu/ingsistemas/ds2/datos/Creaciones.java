
package co.edu.unisinu.ingsistemas.ds2.datos;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author gasler
 */
@Entity
@Table(name = "creaciones")
@NamedQueries({
    @NamedQuery(name = "Creaciones.findAll", query = "SELECT c FROM Creaciones c"),
    @NamedQuery(name = "Creaciones.findById", query = "SELECT c FROM Creaciones c WHERE c.id = :id"),
    @NamedQuery(name = "Creaciones.findByName", query = "SELECT c FROM Creaciones c WHERE c.name = :name"),
    @NamedQuery(name = "Creaciones.findByTipo", query = "SELECT c FROM Creaciones c WHERE c.tipo = :tipo")})
public class Creaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Basic(optional = false)
    @Column(name = "tipo", nullable = false, length = 20)
    private String tipo;
    @Lob
    @Column(name = "colaboradores", length = 65535)
    private String colaboradores;
    @JoinColumn(name = "docentes_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Docentes docentesId;
    @OneToMany(mappedBy = "creacionesId")
    private Collection<Datos> datosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creacionesId")
    private Collection<Registros> registrosCollection;

    public Creaciones() {
    }

    public Creaciones(Integer id) {
        this.id = id;
    }

    public Creaciones(Integer id, String name, String tipo) {
        this.id = id;
        this.name = name;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(String colaboradores) {
        this.colaboradores = colaboradores;
    }

    public Docentes getDocentesId() {
        return docentesId;
    }

    public void setDocentesId(Docentes docentesId) {
        this.docentesId = docentesId;
    }

    public Collection<Datos> getDatosCollection() {
        return datosCollection;
    }

    public void setDatosCollection(Collection<Datos> datosCollection) {
        this.datosCollection = datosCollection;
    }

    public Collection<Registros> getRegistrosCollection() {
        return registrosCollection;
    }

    public void setRegistrosCollection(Collection<Registros> registrosCollection) {
        this.registrosCollection = registrosCollection;
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
