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
    @NamedQuery(name = "Estudiantes.findByPassword", query = "SELECT e FROM Estudiantes e WHERE e.password = :password"),
    @NamedQuery(name = "Estudiantes.findByName", query = "SELECT e FROM Estudiantes e WHERE e.name = :name"),
    @NamedQuery(name = "Estudiantes.findByApellido1", query = "SELECT e FROM Estudiantes e WHERE e.apellido1 = :apellido1"),
    @NamedQuery(name = "Estudiantes.findByApellido2", query = "SELECT e FROM Estudiantes e WHERE e.apellido2 = :apellido2")})
public class Estudiantes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "matricula")
    private Integer matricula;
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "apellido_1")
    private String apellido1;
    @Basic(optional = false)
    @Column(name = "apellido_2")
    private String apellido2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAlumnoFk")
    private Collection<Realizaciones> realizacionesCollection;

    public Estudiantes() {
    }

    public Estudiantes(Integer matricula) {
        this.matricula = matricula;
    }

    public Estudiantes(Integer matricula, String name, String apellido1, String apellido2) {
        this.matricula = matricula;
        this.name = name;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public Collection<Realizaciones> getRealizacionesCollection() {
        return realizacionesCollection;
    }

    public void setRealizacionesCollection(Collection<Realizaciones> realizacionesCollection) {
        this.realizacionesCollection = realizacionesCollection;
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
