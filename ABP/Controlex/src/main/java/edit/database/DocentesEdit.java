
package edit.database;

import co.edu.unisinu.ingsistemas.ds2.conector.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.unisinu.ingsistemas.ds2.datos.ListadoNotas;
import co.edu.unisinu.ingsistemas.ds2.datos.Creaciones;
import co.edu.unisinu.ingsistemas.ds2.datos.Docentes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author gasler
 */
public class DocentesEdit implements Serializable {

    public DocentesEdit(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Docentes docentes) {
        if (docentes.getCreacionesCollection() == null) {
            docentes.setCreacionesCollection(new ArrayList<Creaciones>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ListadoNotas idListadoNotas = docentes.getIdListadoNotas();
            if (idListadoNotas != null) {
                idListadoNotas = em.getReference(idListadoNotas.getClass(), idListadoNotas.getId());
                docentes.setIdListadoNotas(idListadoNotas);
            }
            Collection<Creaciones> attachedCreacionesCollection = new ArrayList<Creaciones>();
            for (Creaciones creacionesCollectionCreacionesToAttach : docentes.getCreacionesCollection()) {
                creacionesCollectionCreacionesToAttach = em.getReference(creacionesCollectionCreacionesToAttach.getClass(), creacionesCollectionCreacionesToAttach.getId());
                attachedCreacionesCollection.add(creacionesCollectionCreacionesToAttach);
            }
            docentes.setCreacionesCollection(attachedCreacionesCollection);
            em.persist(docentes);
            if (idListadoNotas != null) {
                idListadoNotas.getDocentesCollection().add(docentes);
                idListadoNotas = em.merge(idListadoNotas);
            }
            for (Creaciones creacionesCollectionCreaciones : docentes.getCreacionesCollection()) {
                Docentes oldDniDocenteOfCreacionesCollectionCreaciones = creacionesCollectionCreaciones.getDniDocente();
                creacionesCollectionCreaciones.setDniDocente(docentes);
                creacionesCollectionCreaciones = em.merge(creacionesCollectionCreaciones);
                if (oldDniDocenteOfCreacionesCollectionCreaciones != null) {
                    oldDniDocenteOfCreacionesCollectionCreaciones.getCreacionesCollection().remove(creacionesCollectionCreaciones);
                    oldDniDocenteOfCreacionesCollectionCreaciones = em.merge(oldDniDocenteOfCreacionesCollectionCreaciones);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Docentes docentes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Docentes persistentDocentes = em.find(Docentes.class, docentes.getDni());
            ListadoNotas idListadoNotasOld = persistentDocentes.getIdListadoNotas();
            ListadoNotas idListadoNotasNew = docentes.getIdListadoNotas();
            Collection<Creaciones> creacionesCollectionOld = persistentDocentes.getCreacionesCollection();
            Collection<Creaciones> creacionesCollectionNew = docentes.getCreacionesCollection();
            if (idListadoNotasNew != null) {
                idListadoNotasNew = em.getReference(idListadoNotasNew.getClass(), idListadoNotasNew.getId());
                docentes.setIdListadoNotas(idListadoNotasNew);
            }
            Collection<Creaciones> attachedCreacionesCollectionNew = new ArrayList<Creaciones>();
            for (Creaciones creacionesCollectionNewCreacionesToAttach : creacionesCollectionNew) {
                creacionesCollectionNewCreacionesToAttach = em.getReference(creacionesCollectionNewCreacionesToAttach.getClass(), creacionesCollectionNewCreacionesToAttach.getId());
                attachedCreacionesCollectionNew.add(creacionesCollectionNewCreacionesToAttach);
            }
            creacionesCollectionNew = attachedCreacionesCollectionNew;
            docentes.setCreacionesCollection(creacionesCollectionNew);
            docentes = em.merge(docentes);
            if (idListadoNotasOld != null && !idListadoNotasOld.equals(idListadoNotasNew)) {
                idListadoNotasOld.getDocentesCollection().remove(docentes);
                idListadoNotasOld = em.merge(idListadoNotasOld);
            }
            if (idListadoNotasNew != null && !idListadoNotasNew.equals(idListadoNotasOld)) {
                idListadoNotasNew.getDocentesCollection().add(docentes);
                idListadoNotasNew = em.merge(idListadoNotasNew);
            }
            for (Creaciones creacionesCollectionOldCreaciones : creacionesCollectionOld) {
                if (!creacionesCollectionNew.contains(creacionesCollectionOldCreaciones)) {
                    creacionesCollectionOldCreaciones.setDniDocente(null);
                    creacionesCollectionOldCreaciones = em.merge(creacionesCollectionOldCreaciones);
                }
            }
            for (Creaciones creacionesCollectionNewCreaciones : creacionesCollectionNew) {
                if (!creacionesCollectionOld.contains(creacionesCollectionNewCreaciones)) {
                    Docentes oldDniDocenteOfCreacionesCollectionNewCreaciones = creacionesCollectionNewCreaciones.getDniDocente();
                    creacionesCollectionNewCreaciones.setDniDocente(docentes);
                    creacionesCollectionNewCreaciones = em.merge(creacionesCollectionNewCreaciones);
                    if (oldDniDocenteOfCreacionesCollectionNewCreaciones != null && !oldDniDocenteOfCreacionesCollectionNewCreaciones.equals(docentes)) {
                        oldDniDocenteOfCreacionesCollectionNewCreaciones.getCreacionesCollection().remove(creacionesCollectionNewCreaciones);
                        oldDniDocenteOfCreacionesCollectionNewCreaciones = em.merge(oldDniDocenteOfCreacionesCollectionNewCreaciones);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = docentes.getDni();
                if (findDocentes(id) == null) {
                    throw new NonexistentEntityException("The docentes with id " + id + " no longer exists.");
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
            Docentes docentes;
            try {
                docentes = em.getReference(Docentes.class, id);
                docentes.getDni();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The docentes with id " + id + " no longer exists.", enfe);
            }
            ListadoNotas idListadoNotas = docentes.getIdListadoNotas();
            if (idListadoNotas != null) {
                idListadoNotas.getDocentesCollection().remove(docentes);
                idListadoNotas = em.merge(idListadoNotas);
            }
            Collection<Creaciones> creacionesCollection = docentes.getCreacionesCollection();
            for (Creaciones creacionesCollectionCreaciones : creacionesCollection) {
                creacionesCollectionCreaciones.setDniDocente(null);
                creacionesCollectionCreaciones = em.merge(creacionesCollectionCreaciones);
            }
            em.remove(docentes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Docentes> findDocentesEntities() {
        return findDocentesEntities(true, -1, -1);
    }

    public List<Docentes> findDocentesEntities(int maxResults, int firstResult) {
        return findDocentesEntities(false, maxResults, firstResult);
    }

    private List<Docentes> findDocentesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Docentes.class));
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

    public Docentes findDocentes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Docentes.class, id);
        } finally {
            em.close();
        }
    }

    public int getDocentesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Docentes> rt = cq.from(Docentes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
