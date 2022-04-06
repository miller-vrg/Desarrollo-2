/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edit.database;

import co.edu.unisinu.ingsistemas.ds2.conector.exceptions.IllegalOrphanException;
import co.edu.unisinu.ingsistemas.ds2.conector.exceptions.NonexistentEntityException;
import co.edu.unisinu.ingsistemas.ds2.datos.Estudiantes;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.unisinu.ingsistemas.ds2.datos.Realizaciones;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author gasler
 */
public class EstudiantesEdit implements Serializable {

    public EstudiantesEdit(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estudiantes estudiantes) {
        if (estudiantes.getRealizacionesCollection() == null) {
            estudiantes.setRealizacionesCollection(new ArrayList<Realizaciones>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Realizaciones> attachedRealizacionesCollection = new ArrayList<Realizaciones>();
            for (Realizaciones realizacionesCollectionRealizacionesToAttach : estudiantes.getRealizacionesCollection()) {
                realizacionesCollectionRealizacionesToAttach = em.getReference(realizacionesCollectionRealizacionesToAttach.getClass(), realizacionesCollectionRealizacionesToAttach.getId());
                attachedRealizacionesCollection.add(realizacionesCollectionRealizacionesToAttach);
            }
            estudiantes.setRealizacionesCollection(attachedRealizacionesCollection);
            em.persist(estudiantes);
            for (Realizaciones realizacionesCollectionRealizaciones : estudiantes.getRealizacionesCollection()) {
                Estudiantes oldIdAlumnoFkOfRealizacionesCollectionRealizaciones = realizacionesCollectionRealizaciones.getIdAlumnoFk();
                realizacionesCollectionRealizaciones.setIdAlumnoFk(estudiantes);
                realizacionesCollectionRealizaciones = em.merge(realizacionesCollectionRealizaciones);
                if (oldIdAlumnoFkOfRealizacionesCollectionRealizaciones != null) {
                    oldIdAlumnoFkOfRealizacionesCollectionRealizaciones.getRealizacionesCollection().remove(realizacionesCollectionRealizaciones);
                    oldIdAlumnoFkOfRealizacionesCollectionRealizaciones = em.merge(oldIdAlumnoFkOfRealizacionesCollectionRealizaciones);
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
            Collection<Realizaciones> realizacionesCollectionOld = persistentEstudiantes.getRealizacionesCollection();
            Collection<Realizaciones> realizacionesCollectionNew = estudiantes.getRealizacionesCollection();
            List<String> illegalOrphanMessages = null;
            for (Realizaciones realizacionesCollectionOldRealizaciones : realizacionesCollectionOld) {
                if (!realizacionesCollectionNew.contains(realizacionesCollectionOldRealizaciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Realizaciones " + realizacionesCollectionOldRealizaciones + " since its idAlumnoFk field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Realizaciones> attachedRealizacionesCollectionNew = new ArrayList<Realizaciones>();
            for (Realizaciones realizacionesCollectionNewRealizacionesToAttach : realizacionesCollectionNew) {
                realizacionesCollectionNewRealizacionesToAttach = em.getReference(realizacionesCollectionNewRealizacionesToAttach.getClass(), realizacionesCollectionNewRealizacionesToAttach.getId());
                attachedRealizacionesCollectionNew.add(realizacionesCollectionNewRealizacionesToAttach);
            }
            realizacionesCollectionNew = attachedRealizacionesCollectionNew;
            estudiantes.setRealizacionesCollection(realizacionesCollectionNew);
            estudiantes = em.merge(estudiantes);
            for (Realizaciones realizacionesCollectionNewRealizaciones : realizacionesCollectionNew) {
                if (!realizacionesCollectionOld.contains(realizacionesCollectionNewRealizaciones)) {
                    Estudiantes oldIdAlumnoFkOfRealizacionesCollectionNewRealizaciones = realizacionesCollectionNewRealizaciones.getIdAlumnoFk();
                    realizacionesCollectionNewRealizaciones.setIdAlumnoFk(estudiantes);
                    realizacionesCollectionNewRealizaciones = em.merge(realizacionesCollectionNewRealizaciones);
                    if (oldIdAlumnoFkOfRealizacionesCollectionNewRealizaciones != null && !oldIdAlumnoFkOfRealizacionesCollectionNewRealizaciones.equals(estudiantes)) {
                        oldIdAlumnoFkOfRealizacionesCollectionNewRealizaciones.getRealizacionesCollection().remove(realizacionesCollectionNewRealizaciones);
                        oldIdAlumnoFkOfRealizacionesCollectionNewRealizaciones = em.merge(oldIdAlumnoFkOfRealizacionesCollectionNewRealizaciones);
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
            Collection<Realizaciones> realizacionesCollectionOrphanCheck = estudiantes.getRealizacionesCollection();
            for (Realizaciones realizacionesCollectionOrphanCheckRealizaciones : realizacionesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estudiantes (" + estudiantes + ") cannot be destroyed since the Realizaciones " + realizacionesCollectionOrphanCheckRealizaciones + " in its realizacionesCollection field has a non-nullable idAlumnoFk field.");
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
