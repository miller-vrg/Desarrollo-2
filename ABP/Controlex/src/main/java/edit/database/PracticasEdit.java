/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edit.database;

import co.edu.unisinu.ingsistemas.ds2.conector.exceptions.IllegalOrphanException;
import co.edu.unisinu.ingsistemas.ds2.conector.exceptions.NonexistentEntityException;
import co.edu.unisinu.ingsistemas.ds2.conector.exceptions.PreexistingEntityException;
import co.edu.unisinu.ingsistemas.ds2.datos.Practicas;
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
public class PracticasEdit implements Serializable {

    public PracticasEdit(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Practicas practicas) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Pruebas pruebasOrphanCheck = practicas.getPruebas();
        if (pruebasOrphanCheck != null) {
            Practicas oldPracticasOfPruebas = pruebasOrphanCheck.getPracticas();
            if (oldPracticasOfPruebas != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Pruebas " + pruebasOrphanCheck + " already has an item of type Practicas whose pruebas column cannot be null. Please make another selection for the pruebas field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pruebas pruebas = practicas.getPruebas();
            if (pruebas != null) {
                pruebas = em.getReference(pruebas.getClass(), pruebas.getName());
                practicas.setPruebas(pruebas);
            }
            em.persist(practicas);
            if (pruebas != null) {
                pruebas.setPracticas(practicas);
                pruebas = em.merge(pruebas);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPracticas(practicas.getId()) != null) {
                throw new PreexistingEntityException("Practicas " + practicas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Practicas practicas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Practicas persistentPracticas = em.find(Practicas.class, practicas.getId());
            Pruebas pruebasOld = persistentPracticas.getPruebas();
            Pruebas pruebasNew = practicas.getPruebas();
            List<String> illegalOrphanMessages = null;
            if (pruebasNew != null && !pruebasNew.equals(pruebasOld)) {
                Practicas oldPracticasOfPruebas = pruebasNew.getPracticas();
                if (oldPracticasOfPruebas != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Pruebas " + pruebasNew + " already has an item of type Practicas whose pruebas column cannot be null. Please make another selection for the pruebas field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (pruebasNew != null) {
                pruebasNew = em.getReference(pruebasNew.getClass(), pruebasNew.getName());
                practicas.setPruebas(pruebasNew);
            }
            practicas = em.merge(practicas);
            if (pruebasOld != null && !pruebasOld.equals(pruebasNew)) {
                pruebasOld.setPracticas(null);
                pruebasOld = em.merge(pruebasOld);
            }
            if (pruebasNew != null && !pruebasNew.equals(pruebasOld)) {
                pruebasNew.setPracticas(practicas);
                pruebasNew = em.merge(pruebasNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = practicas.getId();
                if (findPracticas(id) == null) {
                    throw new NonexistentEntityException("The practicas with id " + id + " no longer exists.");
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
            Practicas practicas;
            try {
                practicas = em.getReference(Practicas.class, id);
                practicas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The practicas with id " + id + " no longer exists.", enfe);
            }
            Pruebas pruebas = practicas.getPruebas();
            if (pruebas != null) {
                pruebas.setPracticas(null);
                pruebas = em.merge(pruebas);
            }
            em.remove(practicas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Practicas> findPracticasEntities() {
        return findPracticasEntities(true, -1, -1);
    }

    public List<Practicas> findPracticasEntities(int maxResults, int firstResult) {
        return findPracticasEntities(false, maxResults, firstResult);
    }

    private List<Practicas> findPracticasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Practicas.class));
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

    public Practicas findPracticas(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Practicas.class, id);
        } finally {
            em.close();
        }
    }

    public int getPracticasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Practicas> rt = cq.from(Practicas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
