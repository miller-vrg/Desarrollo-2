/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edit.database;

import co.edu.unisinu.ingsistemas.ds2.conector.exceptions.IllegalOrphanException;
import co.edu.unisinu.ingsistemas.ds2.conector.exceptions.NonexistentEntityException;
import co.edu.unisinu.ingsistemas.ds2.datos.Opciones;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.unisinu.ingsistemas.ds2.datos.Respuestas;
import co.edu.unisinu.ingsistemas.ds2.datos.Preguntas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author gasler
 */
public class OpcionesEdit implements Serializable {

    public OpcionesEdit(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Opciones opciones) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Respuestas respuestas = opciones.getRespuestas();
            if (respuestas != null) {
                respuestas = em.getReference(respuestas.getClass(), respuestas.getId());
                opciones.setRespuestas(respuestas);
            }
            Preguntas idPreguntas = opciones.getIdPreguntas();
            if (idPreguntas != null) {
                idPreguntas = em.getReference(idPreguntas.getClass(), idPreguntas.getId());
                opciones.setIdPreguntas(idPreguntas);
            }
            em.persist(opciones);
            if (respuestas != null) {
                Opciones oldOpcionesOfRespuestas = respuestas.getOpciones();
                if (oldOpcionesOfRespuestas != null) {
                    oldOpcionesOfRespuestas.setRespuestas(null);
                    oldOpcionesOfRespuestas = em.merge(oldOpcionesOfRespuestas);
                }
                respuestas.setOpciones(opciones);
                respuestas = em.merge(respuestas);
            }
            if (idPreguntas != null) {
                idPreguntas.getOpcionesCollection().add(opciones);
                idPreguntas = em.merge(idPreguntas);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Opciones opciones) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Opciones persistentOpciones = em.find(Opciones.class, opciones.getId());
            Respuestas respuestasOld = persistentOpciones.getRespuestas();
            Respuestas respuestasNew = opciones.getRespuestas();
            Preguntas idPreguntasOld = persistentOpciones.getIdPreguntas();
            Preguntas idPreguntasNew = opciones.getIdPreguntas();
            List<String> illegalOrphanMessages = null;
            if (respuestasOld != null && !respuestasOld.equals(respuestasNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Respuestas " + respuestasOld + " since its opciones field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (respuestasNew != null) {
                respuestasNew = em.getReference(respuestasNew.getClass(), respuestasNew.getId());
                opciones.setRespuestas(respuestasNew);
            }
            if (idPreguntasNew != null) {
                idPreguntasNew = em.getReference(idPreguntasNew.getClass(), idPreguntasNew.getId());
                opciones.setIdPreguntas(idPreguntasNew);
            }
            opciones = em.merge(opciones);
            if (respuestasNew != null && !respuestasNew.equals(respuestasOld)) {
                Opciones oldOpcionesOfRespuestas = respuestasNew.getOpciones();
                if (oldOpcionesOfRespuestas != null) {
                    oldOpcionesOfRespuestas.setRespuestas(null);
                    oldOpcionesOfRespuestas = em.merge(oldOpcionesOfRespuestas);
                }
                respuestasNew.setOpciones(opciones);
                respuestasNew = em.merge(respuestasNew);
            }
            if (idPreguntasOld != null && !idPreguntasOld.equals(idPreguntasNew)) {
                idPreguntasOld.getOpcionesCollection().remove(opciones);
                idPreguntasOld = em.merge(idPreguntasOld);
            }
            if (idPreguntasNew != null && !idPreguntasNew.equals(idPreguntasOld)) {
                idPreguntasNew.getOpcionesCollection().add(opciones);
                idPreguntasNew = em.merge(idPreguntasNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = opciones.getId();
                if (findOpciones(id) == null) {
                    throw new NonexistentEntityException("The opciones with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Opciones opciones;
            try {
                opciones = em.getReference(Opciones.class, id);
                opciones.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opciones with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Respuestas respuestasOrphanCheck = opciones.getRespuestas();
            if (respuestasOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Opciones (" + opciones + ") cannot be destroyed since the Respuestas " + respuestasOrphanCheck + " in its respuestas field has a non-nullable opciones field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Preguntas idPreguntas = opciones.getIdPreguntas();
            if (idPreguntas != null) {
                idPreguntas.getOpcionesCollection().remove(opciones);
                idPreguntas = em.merge(idPreguntas);
            }
            em.remove(opciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Opciones> findOpcionesEntities() {
        return findOpcionesEntities(true, -1, -1);
    }

    public List<Opciones> findOpcionesEntities(int maxResults, int firstResult) {
        return findOpcionesEntities(false, maxResults, firstResult);
    }

    private List<Opciones> findOpcionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Opciones.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Opciones findOpciones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Opciones.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpcionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Opciones> rt = cq.from(Opciones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
