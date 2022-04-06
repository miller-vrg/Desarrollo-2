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
@Table(name = "listado_notas")
@NamedQueries({
    @NamedQuery(name = "ListadoNotas.findAll", query = "SELECT l FROM ListadoNotas l"),
    @NamedQuery(name = "ListadoNotas.findById", query = "SELECT l FROM ListadoNotas l WHERE l.id = :id")})
public class ListadoNotas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_realizaciones", referencedColumnName = "id")
    @ManyToOne
    private Realizaciones idRealizaciones;
    @OneToMany(mappedBy = "idListadoNotas")
    private Collection<Docentes> docentesCollection;

    public ListadoNotas() {
    }

    public ListadoNotas(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Realizaciones getIdRealizaciones() {
        return idRealizaciones;
    }

    public void setIdRealizaciones(Realizaciones idRealizaciones) {
        this.idRealizaciones = idRealizaciones;
    }

    public Collection<Docentes> getDocentesCollection() {
        return docentesCollection;
    }

    public void setDocentesCollection(Collection<Docentes> docentesCollection) {
        this.docentesCollection = docentesCollection;
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
        if (!(object instanceof ListadoNotas)) {
            return false;
        }
        ListadoNotas other = (ListadoNotas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unisinu.ingsistemas.ds2.datos.ListadoNotas[ id=" + id + " ]";
    }
    
}
