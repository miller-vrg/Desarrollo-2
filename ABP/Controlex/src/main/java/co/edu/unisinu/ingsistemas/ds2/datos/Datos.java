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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author gasler
 */
@Entity
@Table(name = "datos")
@NamedQueries({
    @NamedQuery(name = "Datos.findAll", query = "SELECT d FROM Datos d"),
    @NamedQuery(name = "Datos.findById", query = "SELECT d FROM Datos d WHERE d.id = :id")})
public class Datos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Lob
    @Column(name = "pregunta", nullable = false, length = 65535)
    private String pregunta;
    @Basic(optional = false)
    @Lob
    @Column(name = "op_a", nullable = false, length = 65535)
    private String opA;
    @Basic(optional = false)
    @Lob
    @Column(name = "op_b", nullable = false, length = 65535)
    private String opB;
    @Basic(optional = false)
    @Lob
    @Column(name = "op_c", nullable = false, length = 65535)
    private String opC;
    @Basic(optional = false)
    @Lob
    @Column(name = "respuesta", nullable = false, length = 65535)
    private String respuesta;
    @JoinColumn(name = "creaciones_id", referencedColumnName = "id")
    @ManyToOne
    private Creaciones creacionesId;

    public Datos() {
    }

    public Datos(Integer id) {
        this.id = id;
    }

    public Datos(Integer id, String pregunta, String opA, String opB, String opC, String respuesta) {
        this.id = id;
        this.pregunta = pregunta;
        this.opA = opA;
        this.opB = opB;
        this.opC = opC;
        this.respuesta = respuesta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getOpA() {
        return opA;
    }

    public void setOpA(String opA) {
        this.opA = opA;
    }

    public String getOpB() {
        return opB;
    }

    public void setOpB(String opB) {
        this.opB = opB;
    }

    public String getOpC() {
        return opC;
    }

    public void setOpC(String opC) {
        this.opC = opC;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Creaciones getCreacionesId() {
        return creacionesId;
    }

    public void setCreacionesId(Creaciones creacionesId) {
        this.creacionesId = creacionesId;
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
        if (!(object instanceof Datos)) {
            return false;
        }
        Datos other = (Datos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unisinu.ingsistemas.ds2.datos.Datos[ id=" + id + " ]";
    }
    
}
