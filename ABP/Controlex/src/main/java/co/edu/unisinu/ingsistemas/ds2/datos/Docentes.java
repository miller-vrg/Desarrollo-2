/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unisinu.ingsistemas.ds2.datos;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author gasler
 */
@Entity
@Table(name = "docentes")
@NamedQueries({
    @NamedQuery(name = "Docentes.findAll", query = "SELECT d FROM Docentes d"),
    @NamedQuery(name = "Docentes.findById", query = "SELECT d FROM Docentes d WHERE d.id = :id"),
    @NamedQuery(name = "Docentes.findByName", query = "SELECT d FROM Docentes d WHERE d.name = :name"),
    @NamedQuery(name = "Docentes.findByApellidos", query = "SELECT d FROM Docentes d WHERE d.apellidos = :apellidos"),
    @NamedQuery(name = "Docentes.findByPassword", query = "SELECT d FROM Docentes d WHERE d.password = :password")})
public class Docentes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 20)
    private String name;
    @Basic(optional = false)
    @Column(name = "apellidos", nullable = false, length = 100)
    private String apellidos;
    @Basic(optional = false)
    @Column(name = "password", nullable = false, length = 10)
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "docentesId")
    private Collection<Creaciones> creacionesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "docentesId")
    private Collection<Registros> registrosCollection;

    public Docentes() {
    }

    public Docentes(Integer id) {
        this.id = id;
    }

    public Docentes(Integer id, String name, String apellidos, String password) {
        this.id = id;
        this.name = name;
        this.apellidos = apellidos;
        this.password = password;
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Creaciones> getCreacionesCollection() {
        return creacionesCollection;
    }

    public void setCreacionesCollection(Collection<Creaciones> creacionesCollection) {
        this.creacionesCollection = creacionesCollection;
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
        if (!(object instanceof Docentes)) {
            return false;
        }
        Docentes other = (Docentes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unisinu.ingsistemas.ds2.datos.Docentes[ id=" + id + " ]";
    }
    
}
