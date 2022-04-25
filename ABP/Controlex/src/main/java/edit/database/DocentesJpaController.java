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
import java.util.ArrayList;
import java.util.Collection;
import co.edu.unisinu.ingsistemas.ds2.datos.Registros;
import edit.database.exceptions.IllegalOrphanException;
import edit.database.exceptions.NonexistentEntityException;
import edit.database.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author gasler
 */
public class DocentesJpaController implements Serializable {

    public DocentesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Docentes docentes) throws PreexistingEntityException, Exception {
        if (docentes.getCreacionesCollection() == null) {
            docentes.setCreacionesCollection(new ArrayList<Creaciones>());
        }
        if (docentes.getRegistrosCollection() == null) {
            docentes.setRegistrosCollection(new ArrayList<Registros>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Creaciones> attachedCreacionesCollection = new ArrayList<Creaciones>();
            for (Creaciones creacionesCollectionCreacionesToAttach : docentes.getCreacionesCollection()) {
                creacionesCollectionCreacionesToAttach = em.getReference(creacionesCollectionCreacionesToAttach.getClass(), creacionesCollectionCreacionesToAttach.getId());
                attachedCreacionesCollection.add(creacionesCollectionCreacionesToAttach);
            }
            docentes.setCreacionesCollection(attachedCreacionesCollection);
            Collection<Registros> attachedRegistrosCollection = new ArrayList<Registros>();
            for (Registros registrosCollectionRegistrosToAttach : docentes.getRegistrosCollection()) {
                registrosCollectionRegistrosToAttach = em.getReference(registrosCollectionRegistrosToAttach.getClass(), registrosCollectionRegistrosToAttach.getId());
                attachedRegistrosCollection.add(registrosCollectionRegistrosToAttach);
            }
            docentes.setRegistrosCollection(attachedRegistrosCollection);
            em.persist(docentes);
            for (Creaciones creacionesCollectionCreaciones : docentes.getCreacionesCollection()) {
                Docentes oldDocentesIdOfCreacionesCollectionCreaciones = creacionesCollectionCreaciones.getDocentesId();
                creacionesCollectionCreaciones.setDocentesId(docentes);
                creacionesCollectionCreaciones = em.merge(creacionesCollectionCreaciones);
                if (oldDocentesIdOfCreacionesCollectionCreaciones != null) {
                    oldDocentesIdOfCreacionesCollectionCreaciones.getCreacionesCollection().remove(creacionesCollectionCreaciones);
                    oldDocentesIdOfCreacionesCollectionCreaciones = em.merge(oldDocentesIdOfCreacionesCollectionCreaciones);
                }
            }
            for (Registros registrosCollectionRegistros : docentes.getRegistrosCollection()) {
                Docentes oldDocentesIdOfRegistrosCollectionRegistros = registrosCollectionRegistros.getDocentesId();
                registrosCollectionRegistros.setDocentesId(docentes);
                registrosCollectionRegistros = em.merge(registrosCollectionRegistros);
                if (oldDocentesIdOfRegistrosCollectionRegistros != null) {
                    oldDocentesIdOfRegistrosCollectionRegistros.getRegistrosCollection().remove(registrosCollectionRegistros);
                    oldDocentesIdOfRegistrosCollectionRegistros = em.merge(oldDocentesIdOfRegistrosCollectionRegistros);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDocentes(docentes.getId()) != null) {
                throw new PreexistingEntityException("Docentes " + docentes + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Docentes docentes) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Docentes persistentDocentes = em.find(Docentes.class, docentes.getId());
            Collection<Creaciones> creacionesCollectionOld = persistentDocentes.getCreacionesCollection();
            Collection<Creaciones> creacionesCollectionNew = docentes.getCreacionesCollection();
            Collection<Registros> registrosCollectionOld = persistentDocentes.getRegistrosCollection();
            Collection<Registros> registrosCollectionNew = docentes.getRegistrosCollection();
            List<String> illegalOrphanMessages = null;
            for (Creaciones creacionesCollectionOldCreaciones : creacionesCollectionOld) {
                if (!creacionesCollectionNew.contains(creacionesCollectionOldCreaciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Creaciones " + creacionesCollectionOldCreaciones + " since its docentesId field is not nullable.");
                }
            }
            for (Registros registrosCollectionOldRegistros : registrosCollectionOld) {
                if (!registrosCollectionNew.contains(registrosCollectionOldRegistros)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Registros " + registrosCollectionOldRegistros + " since its docentesId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Creaciones> attachedCreacionesCollectionNew = new ArrayList<Creaciones>();
            for (Creaciones creacionesCollectionNewCreacionesToAttach : creacionesCollectionNew) {
                creacionesCollectionNewCreacionesToAttach = em.getReference(creacionesCollectionNewCreacionesToAttach.getClass(), creacionesCollectionNewCreacionesToAttach.getId());
                attachedCreacionesCollectionNew.add(creacionesCollectionNewCreacionesToAttach);
            }
            creacionesCollectionNew = attachedCreacionesCollectionNew;
            docentes.setCreacionesCollection(creacionesCollectionNew);
            Collection<Registros> attachedRegistrosCollectionNew = new ArrayList<Registros>();
            for (Registros registrosCollectionNewRegistrosToAttach : registrosCollectionNew) {
                registrosCollectionNewRegistrosToAttach = em.getReference(registrosCollectionNewRegistrosToAttach.getClass(), registrosCollectionNewRegistrosToAttach.getId());
                attachedRegistrosCollectionNew.add(registrosCollectionNewRegistrosToAttach);
            }
            registrosCollectionNew = attachedRegistrosCollectionNew;
            docentes.setRegistrosCollection(registrosCollectionNew);
            docentes = em.merge(docentes);
            for (Creaciones creacionesCollectionNewCreaciones : creacionesCollectionNew) {
                if (!creacionesCollectionOld.contains(creacionesCollectionNewCreaciones)) {
                    Docentes oldDocentesIdOfCreacionesCollectionNewCreaciones = creacionesCollectionNewCreaciones.getDocentesId();
                    creacionesCollectionNewCreaciones.setDocentesId(docentes);
                    creacionesCollectionNewCreaciones = em.merge(creacionesCollectionNewCreaciones);
                    if (oldDocentesIdOfCreacionesCollectionNewCreaciones != null && !oldDocentesIdOfCreacionesCollectionNewCreaciones.equals(docentes)) {
                        oldDocentesIdOfCreacionesCollectionNewCreaciones.getCreacionesCollection().remove(creacionesCollectionNewCreaciones);
                        oldDocentesIdOfCreacionesCollectionNewCreaciones = em.merge(oldDocentesIdOfCreacionesCollectionNewCreaciones);
                    }
                }
            }
            for (Registros registrosCollectionNewRegistros : registrosCollectionNew) {
                if (!registrosCollectionOld.contains(registrosCollectionNewRegistros)) {
                    Docentes oldDocentesIdOfRegistrosCollectionNewRegistros = registrosCollectionNewRegistros.getDocentesId();
                    registrosCollectionNewRegistros.setDocentesId(docentes);
                    registrosCollectionNewRegistros = em.merge(registrosCollectionNewRegistros);
                    if (oldDocentesIdOfRegistrosCollectionNewRegistros != null && !oldDocentesIdOfRegistrosCollectionNewRegistros.equals(docentes)) {
                        oldDocentesIdOfRegistrosCollectionNewRegistros.getRegistrosCollection().remove(registrosCollectionNewRegistros);
                        oldDocentesIdOfRegistrosCollectionNewRegistros = em.merge(oldDocentesIdOfRegistrosCollectionNewRegistros);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = docentes.getId();
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Docentes docentes;
            try {
                docentes = em.getReference(Docentes.class, id);
                docentes.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The docentes with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Creaciones> creacionesCollectionOrphanCheck = docentes.getCreacionesCollection();
            for (Creaciones creacionesCollectionOrphanCheckCreaciones : creacionesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Docentes (" + docentes + ") cannot be destroyed since the Creaciones " + creacionesCollectionOrphanCheckCreaciones + " in its creacionesCollection field has a non-nullable docentesId field.");
            }
            Collection<Registros> registrosCollectionOrphanCheck = docentes.getRegistrosCollection();
            for (Registros registrosCollectionOrphanCheckRegistros : registrosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Docentes (" + docentes + ") cannot be destroyed since the Registros " + registrosCollectionOrphanCheckRegistros + " in its registrosCollection field has a non-nullable docentesId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
