/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edit.database;

import co.edu.unisinu.ingsistemas.ds2.datos.Estudiantes;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.unisinu.ingsistemas.ds2.datos.Registros;
import edit.database.exceptions.IllegalOrphanException;
import edit.database.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author gasler
 */
public class EstudiantesJpaController implements Serializable {

    public EstudiantesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estudiantes estudiantes) {
        if (estudiantes.getRegistrosCollection() == null) {
            estudiantes.setRegistrosCollection(new ArrayList<Registros>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Registros> attachedRegistrosCollection = new ArrayList<Registros>();
            for (Registros registrosCollectionRegistrosToAttach : estudiantes.getRegistrosCollection()) {
                registrosCollectionRegistrosToAttach = em.getReference(registrosCollectionRegistrosToAttach.getClass(), registrosCollectionRegistrosToAttach.getId());
                attachedRegistrosCollection.add(registrosCollectionRegistrosToAttach);
            }
            estudiantes.setRegistrosCollection(attachedRegistrosCollection);
            em.persist(estudiantes);
            for (Registros registrosCollectionRegistros : estudiantes.getRegistrosCollection()) {
                Estudiantes oldEstudianteIdOfRegistrosCollectionRegistros = registrosCollectionRegistros.getEstudianteId();
                registrosCollectionRegistros.setEstudianteId(estudiantes);
                registrosCollectionRegistros = em.merge(registrosCollectionRegistros);
                if (oldEstudianteIdOfRegistrosCollectionRegistros != null) {
                    oldEstudianteIdOfRegistrosCollectionRegistros.getRegistrosCollection().remove(registrosCollectionRegistros);
                    oldEstudianteIdOfRegistrosCollectionRegistros = em.merge(oldEstudianteIdOfRegistrosCollectionRegistros);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estudiantes estudiantes) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estudiantes persistentEstudiantes = em.find(Estudiantes.class, estudiantes.getMatricula());
            Collection<Registros> registrosCollectionOld = persistentEstudiantes.getRegistrosCollection();
            Collection<Registros> registrosCollectionNew = estudiantes.getRegistrosCollection();
            List<String> illegalOrphanMessages = null;
            for (Registros registrosCollectionOldRegistros : registrosCollectionOld) {
                if (!registrosCollectionNew.contains(registrosCollectionOldRegistros)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Registros " + registrosCollectionOldRegistros + " since its estudianteId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Registros> attachedRegistrosCollectionNew = new ArrayList<Registros>();
            for (Registros registrosCollectionNewRegistrosToAttach : registrosCollectionNew) {
                registrosCollectionNewRegistrosToAttach = em.getReference(registrosCollectionNewRegistrosToAttach.getClass(), registrosCollectionNewRegistrosToAttach.getId());
                attachedRegistrosCollectionNew.add(registrosCollectionNewRegistrosToAttach);
            }
            registrosCollectionNew = attachedRegistrosCollectionNew;
            estudiantes.setRegistrosCollection(registrosCollectionNew);
            estudiantes = em.merge(estudiantes);
            for (Registros registrosCollectionNewRegistros : registrosCollectionNew) {
                if (!registrosCollectionOld.contains(registrosCollectionNewRegistros)) {
                    Estudiantes oldEstudianteIdOfRegistrosCollectionNewRegistros = registrosCollectionNewRegistros.getEstudianteId();
                    registrosCollectionNewRegistros.setEstudianteId(estudiantes);
                    registrosCollectionNewRegistros = em.merge(registrosCollectionNewRegistros);
                    if (oldEstudianteIdOfRegistrosCollectionNewRegistros != null && !oldEstudianteIdOfRegistrosCollectionNewRegistros.equals(estudiantes)) {
                        oldEstudianteIdOfRegistrosCollectionNewRegistros.getRegistrosCollection().remove(registrosCollectionNewRegistros);
                        oldEstudianteIdOfRegistrosCollectionNewRegistros = em.merge(oldEstudianteIdOfRegistrosCollectionNewRegistros);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estudiantes.getMatricula();
                if (findEstudiantes(id) == null) {
                    throw new NonexistentEntityException("The estudiantes with id " + id + " no longer exists.");
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
            Estudiantes estudiantes;
            try {
                estudiantes = em.getReference(Estudiantes.class, id);
                estudiantes.getMatricula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estudiantes with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Registros> registrosCollectionOrphanCheck = estudiantes.getRegistrosCollection();
            for (Registros registrosCollectionOrphanCheckRegistros : registrosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estudiantes (" + estudiantes + ") cannot be destroyed since the Registros " + registrosCollectionOrphanCheckRegistros + " in its registrosCollection field has a non-nullable estudianteId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(estudiantes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estudiantes> findEstudiantesEntities() {
        return findEstudiantesEntities(true, -1, -1);
    }

    public List<Estudiantes> findEstudiantesEntities(int maxResults, int firstResult) {
        return findEstudiantesEntities(false, maxResults, firstResult);
    }

    private List<Estudiantes> findEstudiantesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estudiantes.class));
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

    public Estudiantes findEstudiantes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estudiantes.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstudiantesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estudiantes> rt = cq.from(Estudiantes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
