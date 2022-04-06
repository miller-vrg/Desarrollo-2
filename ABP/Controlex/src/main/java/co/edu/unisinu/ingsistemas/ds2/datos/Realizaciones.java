/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unisinu.ingsistemas.ds2.datos;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "realizaciones")
@NamedQueries({
    @NamedQuery(name = "Realizaciones.findAll", query = "SELECT r FROM Realizaciones r"),
    @NamedQuery(name = "Realizaciones.findById", query = "SELECT r FROM Realizaciones r WHERE r.id = :id"),
    @NamedQuery(name = "Realizaciones.findByNotas", query = "SELECT r FROM Realizaciones r WHERE r.notas = :notas")})
public class Realizaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "notas")
    private Float notas;
    @OneToMany(mappedBy = "idRealizaciones")
    private Collection<ListadoNotas> listadoNotasCollection;
    @JoinColumn(name = "id_alumno_fk", referencedColumnName = "matricula")
    @ManyToOne(optional = false)
    private Estudiantes idAlumnoFk;
    @JoinColumn(name = "id_pruebas", referencedColumnName = "name")
    @ManyToOne(optional = false)
    private Pruebas idPruebas;

    public Realizaciones() {
    }

    public Realizaciones(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getNotas() {
        return notas;
    }

    public void setNotas(Float notas) {
        this.notas = notas;
    }

    public Collection<ListadoNotas> getListadoNotasCollection() {
        return listadoNotasCollection;
    }

    public void setListadoNotasCollection(Collection<ListadoNotas> listadoNotasCollection) {
        this.listadoNotasCollection = listadoNotasCollection;
    }

    public Estudiantes getIdAlumnoFk() {
        return idAlumnoFk;
    }

    public void setIdAlumnoFk(Estudiantes idAlumnoFk) {
        this.idAlumnoFk = idAlumnoFk;
    }

    public Pruebas getIdPruebas() {
        return idPruebas;
    }

    public void setIdPruebas(Pruebas idPruebas) {
        this.idPruebas = idPruebas;
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
        if (!(object instanceof Realizaciones)) {
            return false;
        }
        Realizaciones other = (Realizaciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unisinu.ingsistemas.ds2.datos.Realizaciones[ id=" + id + " ]";
    }
    
}
