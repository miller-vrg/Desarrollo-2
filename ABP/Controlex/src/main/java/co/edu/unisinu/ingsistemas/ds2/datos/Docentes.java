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
@Table(name = "docentes")
@NamedQueries({
    @NamedQuery(name = "Docentes.findAll", query = "SELECT d FROM Docentes d"),
    @NamedQuery(name = "Docentes.findByDni", query = "SELECT d FROM Docentes d WHERE d.dni = :dni"),
    @NamedQuery(name = "Docentes.findByName", query = "SELECT d FROM Docentes d WHERE d.name = :name"),
    @NamedQuery(name = "Docentes.findByApellido1", query = "SELECT d FROM Docentes d WHERE d.apellido1 = :apellido1"),
    @NamedQuery(name = "Docentes.findByApellido2", query = "SELECT d FROM Docentes d WHERE d.apellido2 = :apellido2"),
    @NamedQuery(name = "Docentes.findByPassword", query = "SELECT d FROM Docentes d WHERE d.password = :password")})
public class Docentes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "dni")
    private Integer dni;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "apellido_1")
    private String apellido1;
    @Basic(optional = false)
    @Column(name = "apellido_2")
    private String apellido2;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy = "dniDocente")
    private Collection<Creaciones> creacionesCollection;
    @JoinColumn(name = "id_listado_notas", referencedColumnName = "id")
    @ManyToOne
    private ListadoNotas idListadoNotas;

    public Docentes() {
    }

    public Docentes(Integer dni) {
        this.dni = dni;
    }

    public Docentes(Integer dni, String name, String apellido1, String apellido2, String password) {
        this.dni = dni;
        this.name = name;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.password = password;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
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

    public ListadoNotas getIdListadoNotas() {
        return idListadoNotas;
    }

    public void setIdListadoNotas(ListadoNotas idListadoNotas) {
        this.idListadoNotas = idListadoNotas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dni != null ? dni.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Docentes)) {
            return false;
        }
        Docentes other = (Docentes) object;
        if ((this.dni == null && other.dni != null) || (this.dni != null && !this.dni.equals(other.dni))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unisinu.ingsistemas.ds2.datos.Docentes[ dni=" + dni + " ]";
    }
    
}
