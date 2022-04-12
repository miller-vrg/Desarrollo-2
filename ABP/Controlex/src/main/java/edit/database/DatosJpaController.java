/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edit.database;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.unisinu.ingsistemas.ds2.datos.Creaciones;
import co.edu.unisinu.ingsistemas.ds2.datos.Datos;
import edit.database.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author gasler
 */
public class DatosJpaController implements Serializable {

    public DatosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Datos datos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Creaciones creacionesId = datos.getCreacionesId();
            if (creacionesId != null) {
                creacionesId = em.getReference(creacionesId.getClass(), creacionesId.getId());
                datos.setCreacionesId(creacionesId);
            }
            em.persist(datos);
            if (creacionesId != null) {
                creacionesId.getDatosCollection().add(datos);
                creacionesId = em.merge(creacionesId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Datos datos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Datos persistentDatos = em.find(Datos.class, datos.getId());
            Creaciones creacionesIdOld = persistentDatos.getCreacionesId();
            Creaciones creacionesIdNew = datos.getCreacionesId();
            if (creacionesIdNew != null) {
                creacionesIdNew = em.getReference(creacionesIdNew.getClass(), creacionesIdNew.getId());
                datos.setCreacionesId(creacionesIdNew);
            }
            datos = em.merge(datos);
            if (creacionesIdOld != null && !creacionesIdOld.equals(creacionesIdNew)) {
                creacionesIdOld.getDatosCollection().remove(datos);
                creacionesIdOld = em.merge(creacionesIdOld);
            }
            if (creacionesIdNew != null && !creacionesIdNew.equals(creacionesIdOld)) {
                creacionesIdNew.getDatosCollection().add(datos);
                creacionesIdNew = em.merge(creacionesIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = datos.getId();
                if (findDatos(id) == null) {
                    throw new NonexistentEntityException("The datos with id " + id + " no longer exists.");
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
            Datos datos;
            try {
                datos = em.getReference(Datos.class, id);
                datos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The datos with id " + id + " no longer exists.", enfe);
            }
            Creaciones creacionesId = datos.getCreacionesId();
            if (creacionesId != null) {
                creacionesId.getDatosCollection().remove(datos);
                creacionesId = em.merge(creacionesId);
            }
            em.remove(datos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Datos> findDatosEntities() {
        return findDatosEntities(true, -1, -1);
    }

    public List<Datos> findDatosEntities(int maxResults, int firstResult) {
        return findDatosEntities(false, maxResults, firstResult);
    }

    private List<Datos> findDatosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Datos.class));
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

    public Datos findDatos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Datos.class, id);
        } finally {
            em.close();
        }
    }

    public int getDatosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Datos> rt = cq.from(Datos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
