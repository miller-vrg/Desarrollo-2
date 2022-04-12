/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unisinu.ingsistemas.ds2.datos;

import java.io.Serializable;
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
import javax.persistence.Table;

/**
 *
 * @author gasler
 */
@Entity
@Table(name = "registros")
@NamedQueries({
    @NamedQuery(name = "Registros.findAll", query = "SELECT r FROM Registros r"),
    @NamedQuery(name = "Registros.findById", query = "SELECT r FROM Registros r WHERE r.id = :id"),
    @NamedQuery(name = "Registros.findByNota", query = "SELECT r FROM Registros r WHERE r.nota = :nota")})
public class Registros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "nota", precision = 12, scale = 0)
    private Float nota;
    @JoinColumn(name = "creaciones_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Creaciones creacionesId;
    @JoinColumn(name = "docentes_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Docentes docentesId;
    @JoinColumn(name = "estudiante_id", referencedColumnName = "matricula", nullable = false)
    @ManyToOne(optional = false)
    private Estudiantes estudianteId;

    public Registros() {
    }

    public Registros(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getNota() {
        return nota;
    }

    public void setNota(Float nota) {
        this.nota = nota;
    }

    public Creaciones getCreacionesId() {
        return creacionesId;
    }

    public void setCreacionesId(Creaciones creacionesId) {
        this.creacionesId = creacionesId;
    }

    public Docentes getDocentesId() {
        return docentesId;
    }

    public void setDocentesId(Docentes docentesId) {
        this.docentesId = docentesId;
    }

    public Estudiantes getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Estudiantes estudianteId) {
        this.estudianteId = estudianteId;
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
        if (!(object instanceof Registros)) {
            return false;
        }
        Registros other = (Registros) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unisinu.ingsistemas.ds2.datos.Registros[ id=" + id + " ]";
    }
    
}
