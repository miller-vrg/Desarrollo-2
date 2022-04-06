/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edit.database;

import co.edu.unisinu.ingsistemas.ds2.conector.exceptions.IllegalOrphanException;
import co.edu.unisinu.ingsistemas.ds2.conector.exceptions.NonexistentEntityException;
import co.edu.unisinu.ingsistemas.ds2.conector.exceptions.PreexistingEntityException;
import co.edu.unisinu.ingsistemas.ds2.datos.Examenes;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.unisinu.ingsistemas.ds2.datos.Pruebas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author gasler
 */
public class ExamenesEdit implements Serializable {

    public ExamenesEdit(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Examenes examenes) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Pruebas pruebasOrphanCheck = examenes.getPruebas();
        if (pruebasOrphanCheck != null) {
            Examenes oldExamenesOfPruebas = pruebasOrphanCheck.getExamenes();
            if (oldExamenesOfPruebas != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Pruebas " + pruebasOrphanCheck + " already has an item of type Examenes whose pruebas column cannot be null. Please make another selection for the pruebas field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pruebas pruebas = examenes.getPruebas();
            if (pruebas != null) {
                pruebas = em.getReference(pruebas.getClass(), pruebas.getName());
                examenes.setPruebas(pruebas);
            }
            em.persist(examenes);
            if (pruebas != null) {
                pruebas.setExamenes(examenes);
                pruebas = em.merge(pruebas);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findExamenes(examenes.getIdPreguntas()) != null) {
                throw new PreexistingEntityException("Examenes " + examenes + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Examenes examenes) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Examenes persistentExamenes = em.find(Examenes.class, examenes.getIdPreguntas());
            Pruebas pruebasOld = persistentExamenes.getPruebas();
            Pruebas pruebasNew = examenes.getPruebas();
            List<String> illegalOrphanMessages = null;
            if (pruebasNew != null && !pruebasNew.equals(pruebasOld)) {
                Examenes oldExamenesOfPruebas = pruebasNew.getExamenes();
                if (oldExamenesOfPruebas != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Pruebas " + pruebasNew + " already has an item of type Examenes whose pruebas column cannot be null. Please make another selection for the pruebas field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (pruebasNew != null) {
                pruebasNew = em.getReference(pruebasNew.getClass(), pruebasNew.getName());
                examenes.setPruebas(pruebasNew);
            }
            examenes = em.merge(examenes);
            if (pruebasOld != null && !pruebasOld.equals(pruebasNew)) {
                pruebasOld.setExamenes(null);
                pruebasOld = em.merge(pruebasOld);
            }
            if (pruebasNew != null && !pruebasNew.equals(pruebasOld)) {
                pruebasNew.setExamenes(examenes);
                pruebasNew = em.merge(pruebasNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = examenes.getIdPreguntas();
                if (findExamenes(id) == null) {
                    throw new NonexistentEntityException("The examenes with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Examenes examenes;
            try {
                examenes = em.getReference(Examenes.class, id);
                examenes.getIdPreguntas();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The examenes with id " + id + " no longer exists.", enfe);
            }
            Pruebas pruebas = examenes.getPruebas();
            if (pruebas != null) {
                pruebas.setExamenes(null);
                pruebas = em.merge(pruebas);
            }
            em.remove(examenes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Examenes> findExamenesEntities() {
        return findExamenesEntities(true, -1, -1);
    }

    public List<Examenes> findExamenesEntities(int maxResults, int firstResult) {
        return findExamenesEntities(false, maxResults, firstResult);
    }

    private List<Examenes> findExamenesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Examenes.class));
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

    public Examenes findExamenes(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Examenes.class, id);
        } finally {
            em.close();
        }
    }

    public int getExamenesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Examenes> rt = cq.from(Examenes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
