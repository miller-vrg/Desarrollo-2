/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edit.database;

import co.edu.unisinu.ingsistemas.ds2.conector.exceptions.IllegalOrphanException;
import co.edu.unisinu.ingsistemas.ds2.conector.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.unisinu.ingsistemas.ds2.datos.Opciones;
import co.edu.unisinu.ingsistemas.ds2.datos.Respuestas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author gasler
 */
public class RespuestasEdit implements Serializable {

    public RespuestasEdit(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Respuestas respuestas) throws IllegalOrphanException {
        List<String> illegalOrphanMessages = null;
        Opciones opcionesOrphanCheck = respuestas.getOpciones();
        if (opcionesOrphanCheck != null) {
            Respuestas oldRespuestasOfOpciones = opcionesOrphanCheck.getRespuestas();
            if (oldRespuestasOfOpciones != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Opciones " + opcionesOrphanCheck + " already has an item of type Respuestas whose opciones column cannot be null. Please make another selection for the opciones field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Opciones opciones = respuestas.getOpciones();
            if (opciones != null) {
                opciones = em.getReference(opciones.getClass(), opciones.getId());
                respuestas.setOpciones(opciones);
            }
            em.persist(respuestas);
            if (opciones != null) {
                opciones.setRespuestas(respuestas);
                opciones = em.merge(opciones);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Respuestas respuestas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Respuestas persistentRespuestas = em.find(Respuestas.class, respuestas.getId());
            Opciones opcionesOld = persistentRespuestas.getOpciones();
            Opciones opcionesNew = respuestas.getOpciones();
            List<String> illegalOrphanMessages = null;
            if (opcionesNew != null && !opcionesNew.equals(opcionesOld)) {
                Respuestas oldRespuestasOfOpciones = opcionesNew.getRespuestas();
                if (oldRespuestasOfOpciones != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Opciones " + opcionesNew + " already has an item of type Respuestas whose opciones column cannot be null. Please make another selection for the opciones field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (opcionesNew != null) {
                opcionesNew = em.getReference(opcionesNew.getClass(), opcionesNew.getId());
                respuestas.setOpciones(opcionesNew);
            }
            respuestas = em.merge(respuestas);
            if (opcionesOld != null && !opcionesOld.equals(opcionesNew)) {
                opcionesOld.setRespuestas(null);
                opcionesOld = em.merge(opcionesOld);
            }
            if (opcionesNew != null && !opcionesNew.equals(opcionesOld)) {
                opcionesNew.setRespuestas(respuestas);
                opcionesNew = em.merge(opcionesNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = respuestas.getId();
                if (findRespuestas(id) == null) {
                    throw new NonexistentEntityException("The respuestas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Respuestas respuestas;
            try {
                respuestas = em.getReference(Respuestas.class, id);
                respuestas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The respuestas with id " + id + " no longer exists.", enfe);
            }
            Opciones opciones = respuestas.getOpciones();
            if (opciones != null) {
                opciones.setRespuestas(null);
                opciones = em.merge(opciones);
            }
            em.remove(respuestas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Respuestas> findRespuestasEntities() {
        return findRespuestasEntities(true, -1, -1);
    }

    public List<Respuestas> findRespuestasEntities(int maxResults, int firstResult) {
        return findRespuestasEntities(false, maxResults, firstResult);
    }

    private List<Respuestas> findRespuestasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Respuestas.class));
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

    public Respuestas findRespuestas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Respuestas.class, id);
        } finally {
            em.close();
        }
    }

    public int getRespuestasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Respuestas> rt = cq.from(Respuestas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
