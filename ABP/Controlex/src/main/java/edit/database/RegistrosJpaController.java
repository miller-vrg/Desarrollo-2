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
import co.edu.unisinu.ingsistemas.ds2.datos.Docentes;
import co.edu.unisinu.ingsistemas.ds2.datos.Estudiantes;
import co.edu.unisinu.ingsistemas.ds2.datos.Registros;
import edit.database.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author gasler
 */
public class RegistrosJpaController implements Serializable {

    public RegistrosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Registros registros) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Creaciones creacionesId = registros.getCreacionesId();
            if (creacionesId != null) {
                creacionesId = em.getReference(creacionesId.getClass(), creacionesId.getId());
                registros.setCreacionesId(creacionesId);
            }
            Docentes docentesId = registros.getDocentesId();
            if (docentesId != null) {
                docentesId = em.getReference(docentesId.getClass(), docentesId.getId());
                registros.setDocentesId(docentesId);
            }
            Estudiantes estudianteId = registros.getEstudianteId();
            if (estudianteId != null) {
                estudianteId = em.getReference(estudianteId.getClass(), estudianteId.getMatricula());
                registros.setEstudianteId(estudianteId);
            }
            em.persist(registros);
            if (creacionesId != null) {
                creacionesId.getRegistrosCollection().add(registros);
                creacionesId = em.merge(creacionesId);
            }
            if (docentesId != null) {
                docentesId.getRegistrosCollection().add(registros);
                docentesId = em.merge(docentesId);
            }
            if (estudianteId != null) {
                estudianteId.getRegistrosCollection().add(registros);
                estudianteId = em.merge(estudianteId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Registros registros) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registros persistentRegistros = em.find(Registros.class, registros.getId());
            Creaciones creacionesIdOld = persistentRegistros.getCreacionesId();
            Creaciones creacionesIdNew = registros.getCreacionesId();
            Docentes docentesIdOld = persistentRegistros.getDocentesId();
            Docentes docentesIdNew = registros.getDocentesId();
            Estudiantes estudianteIdOld = persistentRegistros.getEstudianteId();
            Estudiantes estudianteIdNew = registros.getEstudianteId();
            if (creacionesIdNew != null) {
                creacionesIdNew = em.getReference(creacionesIdNew.getClass(), creacionesIdNew.getId());
                registros.setCreacionesId(creacionesIdNew);
            }
            if (docentesIdNew != null) {
                docentesIdNew = em.getReference(docentesIdNew.getClass(), docentesIdNew.getId());
                registros.setDocentesId(docentesIdNew);
            }
            if (estudianteIdNew != null) {
                estudianteIdNew = em.getReference(estudianteIdNew.getClass(), estudianteIdNew.getMatricula());
                registros.setEstudianteId(estudianteIdNew);
            }
            registros = em.merge(registros);
            if (creacionesIdOld != null && !creacionesIdOld.equals(creacionesIdNew)) {
                creacionesIdOld.getRegistrosCollection().remove(registros);
                creacionesIdOld = em.merge(creacionesIdOld);
            }
            if (creacionesIdNew != null && !creacionesIdNew.equals(creacionesIdOld)) {
                creacionesIdNew.getRegistrosCollection().add(registros);
                creacionesIdNew = em.merge(creacionesIdNew);
            }
            if (docentesIdOld != null && !docentesIdOld.equals(docentesIdNew)) {
                docentesIdOld.getRegistrosCollection().remove(registros);
                docentesIdOld = em.merge(docentesIdOld);
            }
            if (docentesIdNew != null && !docentesIdNew.equals(docentesIdOld)) {
                docentesIdNew.getRegistrosCollection().add(registros);
                docentesIdNew = em.merge(docentesIdNew);
            }
            if (estudianteIdOld != null && !estudianteIdOld.equals(estudianteIdNew)) {
                estudianteIdOld.getRegistrosCollection().remove(registros);
                estudianteIdOld = em.merge(estudianteIdOld);
            }
            if (estudianteIdNew != null && !estudianteIdNew.equals(estudianteIdOld)) {
                estudianteIdNew.getRegistrosCollection().add(registros);
                estudianteIdNew = em.merge(estudianteIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = registros.getId();
                if (findRegistros(id) == null) {
                    throw new NonexistentEntityException("The registros with id " + id + " no longer exists.");
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
            Registros registros;
            try {
                registros = em.getReference(Registros.class, id);
                registros.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registros with id " + id + " no longer exists.", enfe);
            }
            Creaciones creacionesId = registros.getCreacionesId();
            if (creacionesId != null) {
                creacionesId.getRegistrosCollection().remove(registros);
                creacionesId = em.merge(creacionesId);
            }
            Docentes docentesId = registros.getDocentesId();
            if (docentesId != null) {
                docentesId.getRegistrosCollection().remove(registros);
                docentesId = em.merge(docentesId);
            }
            Estudiantes estudianteId = registros.getEstudianteId();
            if (estudianteId != null) {
                estudianteId.getRegistrosCollection().remove(registros);
                estudianteId = em.merge(estudianteId);
            }
            em.remove(registros);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Registros> findRegistrosEntities() {
        return findRegistrosEntities(true, -1, -1);
    }

    public List<Registros> findRegistrosEntities(int maxResults, int firstResult) {
        return findRegistrosEntities(false, maxResults, firstResult);
    }

    private List<Registros> findRegistrosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Registros.class));
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

    public Registros findRegistros(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Registros.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistrosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Registros> rt = cq.from(Registros.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
