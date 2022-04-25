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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "estudiantes")
@NamedQueries({
    @NamedQuery(name = "Estudiantes.findAll", query = "SELECT e FROM Estudiantes e"),
    @NamedQuery(name = "Estudiantes.findByMatricula", query = "SELECT e FROM Estudiantes e WHERE e.matricula = :matricula"),
    @NamedQuery(name = "Estudiantes.findByName", query = "SELECT e FROM Estudiantes e WHERE e.name = :name"),
    @NamedQuery(name = "Estudiantes.findByApellidos", query = "SELECT e FROM Estudiantes e WHERE e.apellidos = :apellidos"),
    @NamedQuery(name = "Estudiantes.findByPassword", query = "SELECT e FROM Estudiantes e WHERE e.password = :password")})
public class Estudiantes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "matricula", nullable = false)
    private Integer matricula;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 20)
    private String name;
    @Basic(optional = false)
    @Column(name = "apellidos", nullable = false, length = 255)
    private String apellidos;
    @Basic(optional = false)
    @Column(name = "password", nullable = false, length = 10)
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudianteId")
    private Collection<Registros> registrosCollection;

    public Estudiantes() {
    }

    public Estudiantes(Integer matricula) {
        this.matricula = matricula;
    }

    public Estudiantes(Integer matricula, String name, String apellidos, String password) {
        this.matricula = matricula;
        this.name = name;
        this.apellidos = apellidos;
        this.password = password;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
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

    public Collection<Registros> getRegistrosCollection() {
        return registrosCollection;
    }

    public void setRegistrosCollection(Collection<Registros> registrosCollection) {
        this.registrosCollection = registrosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matricula != null ? matricula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estudiantes)) {
            return false;
        }
        Estudiantes other = (Estudiantes) object;
        if ((this.matricula == null && other.matricula != null) || (this.matricula != null && !this.matricula.equals(other.matricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unisinu.ingsistemas.ds2.datos.Estudiantes[ matricula=" + matricula + " ]";
    }
    
}
