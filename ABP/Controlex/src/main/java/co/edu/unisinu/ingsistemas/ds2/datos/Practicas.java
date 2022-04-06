/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unisinu.ingsistemas.ds2.datos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author gasler
 */
@Entity
@Table(name = "practicas")
@NamedQueries({
    @NamedQuery(name = "Practicas.findAll", query = "SELECT p FROM Practicas p"),
    @NamedQuery(name = "Practicas.findById", query = "SELECT p FROM Practicas p WHERE p.id = :id"),
    @NamedQuery(name = "Practicas.findByIntentos", query = "SELECT p FROM Practicas p WHERE p.intentos = :intentos")})
public class Practicas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "intentos")
    private int intentos;
    @JoinColumn(name = "id", referencedColumnName = "name", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Pruebas pruebas;

    public Practicas() {
    }

    public Practicas(String id) {
        this.id = id;
    }

    public Practicas(String id, int intentos) {
        this.id = id;
        this.intentos = intentos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public Pruebas getPruebas() {
        return pruebas;
    }

    public void setPruebas(Pruebas pruebas) {
        this.pruebas = pruebas;
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
        if (!(object instanceof Practicas)) {
            return false;
        }
        Practicas other = (Practicas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unisinu.ingsistemas.ds2.datos.Practicas[ id=" + id + " ]";
    }
    
}
