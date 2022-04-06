/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unisinu.ingsistemas.ds2.datos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author gasler
 */
@Entity
@Table(name = "examenes")
@NamedQueries({
    @NamedQuery(name = "Examenes.findAll", query = "SELECT e FROM Examenes e"),
    @NamedQuery(name = "Examenes.findByIdPreguntas", query = "SELECT e FROM Examenes e WHERE e.idPreguntas = :idPreguntas"),
    @NamedQuery(name = "Examenes.findByCierre", query = "SELECT e FROM Examenes e WHERE e.cierre = :cierre")})
public class Examenes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_preguntas")
    private String idPreguntas;
    @Basic(optional = false)
    @Column(name = "cierre")
    @Temporal(TemporalType.DATE)
    private Date cierre;
    @JoinColumn(name = "id_preguntas", referencedColumnName = "name", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Pruebas pruebas;

    public Examenes() {
    }

    public Examenes(String idPreguntas) {
        this.idPreguntas = idPreguntas;
    }

    public Examenes(String idPreguntas, Date cierre) {
        this.idPreguntas = idPreguntas;
        this.cierre = cierre;
    }

    public String getIdPreguntas() {
        return idPreguntas;
    }

    public void setIdPreguntas(String idPreguntas) {
        this.idPreguntas = idPreguntas;
    }

    public Date getCierre() {
        return cierre;
    }

    public void setCierre(Date cierre) {
        this.cierre = cierre;
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
        hash += (idPreguntas != null ? idPreguntas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Examenes)) {
            return false;
        }
        Examenes other = (Examenes) object;
        if ((this.idPreguntas == null && other.idPreguntas != null) || (this.idPreguntas != null && !this.idPreguntas.equals(other.idPreguntas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unisinu.ingsistemas.ds2.datos.Examenes[ idPreguntas=" + idPreguntas + " ]";
    }
    
}
