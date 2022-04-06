/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unisinu.ingsistemas.ds2.datos;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author gasler
 */
@Entity
@Table(name = "pruebas")
@NamedQueries({
    @NamedQuery(name = "Pruebas.findAll", query = "SELECT p FROM Pruebas p"),
    @NamedQuery(name = "Pruebas.findByName", query = "SELECT p FROM Pruebas p WHERE p.name = :name"),
    @NamedQuery(name = "Pruebas.findByApertura", query = "SELECT p FROM Pruebas p WHERE p.apertura = :apertura"),
    @NamedQuery(name = "Pruebas.findByIdPreguntas", query = "SELECT p FROM Pruebas p WHERE p.idPreguntas = :idPreguntas"),
    @NamedQuery(name = "Pruebas.findByPorcentaje", query = "SELECT p FROM Pruebas p WHERE p.porcentaje = :porcentaje"),
    @NamedQuery(name = "Pruebas.findByIdExamenes", query = "SELECT p FROM Pruebas p WHERE p.idExamenes = :idExamenes"),
    @NamedQuery(name = "Pruebas.findByIdPracticas", query = "SELECT p FROM Pruebas p WHERE p.idPracticas = :idPracticas")})
public class Pruebas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "apertura")
    @Temporal(TemporalType.DATE)
    private Date apertura;
    @Column(name = "id_preguntas")
    private String idPreguntas;
    @Basic(optional = false)
    @Column(name = "porcentaje")
    private float porcentaje;
    @Lob
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "id_examenes")
    private String idExamenes;
    @Column(name = "id_practicas")
    private String idPracticas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "namePrueba")
    private Collection<Creaciones> creacionesCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pruebas")
    private Practicas practicas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPruebas")
    private Collection<Preguntas> preguntasCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pruebas")
    private Examenes examenes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPruebas")
    private Collection<Realizaciones> realizacionesCollection;

    public Pruebas() {
    }

    public Pruebas(String name) {
        this.name = name;
    }

    public Pruebas(String name, Date apertura, float porcentaje) {
        this.name = name;
        this.apertura = apertura;
        this.porcentaje = porcentaje;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getApertura() {
        return apertura;
    }

    public void setApertura(Date apertura) {
        this.apertura = apertura;
    }

    public String getIdPreguntas() {
        return idPreguntas;
    }

    public void setIdPreguntas(String idPreguntas) {
        this.idPreguntas = idPreguntas;
    }

    public float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(float porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIdExamenes() {
        return idExamenes;
    }

    public void setIdExamenes(String idExamenes) {
        this.idExamenes = idExamenes;
    }

    public String getIdPracticas() {
        return idPracticas;
    }

    public void setIdPracticas(String idPracticas) {
        this.idPracticas = idPracticas;
    }

    public Collection<Creaciones> getCreacionesCollection() {
        return creacionesCollection;
    }

    public void setCreacionesCollection(Collection<Creaciones> creacionesCollection) {
        this.creacionesCollection = creacionesCollection;
    }

    public Practicas getPracticas() {
        return practicas;
    }

    public void setPracticas(Practicas practicas) {
        this.practicas = practicas;
    }

    public Collection<Preguntas> getPreguntasCollection() {
        return preguntasCollection;
    }

    public void setPreguntasCollection(Collection<Preguntas> preguntasCollection) {
        this.preguntasCollection = preguntasCollection;
    }

    public Examenes getExamenes() {
        return examenes;
    }

    public void setExamenes(Examenes examenes) {
        this.examenes = examenes;
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
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pruebas)) {
            return false;
        }
        Pruebas other = (Pruebas) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unisinu.ingsistemas.ds2.datos.Pruebas[ name=" + name + " ]";
    }
    
}
