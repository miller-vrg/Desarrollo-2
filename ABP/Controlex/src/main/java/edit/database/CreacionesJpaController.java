/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edit.database;

import co.edu.unisinu.ingsistemas.ds2.datos.Creaciones;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.unisinu.ingsistemas.ds2.datos.Docentes;
import co.edu.unisinu.ingsistemas.ds2.datos.Datos;
import java.util.ArrayList;
import java.util.Collection;
import co.edu.unisinu.ingsistemas.ds2.datos.Registros;
import edit.database.exceptions.IllegalOrphanException;
import edit.database.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author gasler
 */
public class CreacionesJpaController implements Serializable {

    public CreacionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Creaciones creaciones) {
        if (creaciones.getDatosCollection() == null) {
            creaciones.setDatosCollection(new ArrayList<Datos>());
        }
        if (creaciones.getRegistrosCollection() == null) {
            creaciones.setRegistrosCollection(new ArrayList<Registros>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Docentes docentesId = creaciones.getDocentesId();
            if (docentesId != null) {
                docentesId = em.getReference(docentesId.getClass(), docentesId.getId());
                creaciones.setDocentesId(docentesId);
            }
            Collection<Datos> attachedDatosCollection = new ArrayList<Datos>();
            for (Datos datosCollectionDatosToAttach : creaciones.getDatosCollection()) {
                datosCollectionDatosToAttach = em.getReference(datosCollectionDatosToAttach.getClass(), datosCollectionDatosToAttach.getId());
                attachedDatosCollection.add(datosCollectionDatosToAttach);
            }
            creaciones.setDatosCollection(attachedDatosCollection);
            Collection<Registros> attachedRegistrosCollection = new ArrayList<Registros>();
            for (Registros registrosCollectionRegistrosToAttach : creaciones.getRegistrosCollection()) {
                registrosCollectionRegistrosToAttach = em.getReference(registrosCollectionRegistrosToAttach.getClass(), registrosCollectionRegistrosToAttach.getId());
                attachedRegistrosCollection.add(registrosCollectionRegistrosToAttach);
            }
            creaciones.setRegistrosCollection(attachedRegistrosCollection);
            em.persist(creaciones);
            if (docentesId != null) {
                docentesId.getCreacionesCollection().add(creaciones);
                docentesId = em.merge(docentesId);
            }
            for (Datos datosCollectionDatos : creaciones.getDatosCollection()) {
                Creaciones oldCreacionesIdOfDatosCollectionDatos = datosCollectionDatos.getCreacionesId();
                datosCollectionDatos.setCreacionesId(creaciones);
                datosCollectionDatos = em.merge(datosCollectionDatos);
                if (oldCreacionesIdOfDatosCollectionDatos != null) {
                    oldCreacionesIdOfDatosCollectionDatos.getDatosCollection().remove(datosCollectionDatos);
                    oldCreacionesIdOfDatosCollectionDatos = em.merge(oldCreacionesIdOfDatosCollectionDatos);
                }
            }
            for (Registros registrosCollectionRegistros : creaciones.getRegistrosCollection()) {
                Creaciones oldCreacionesIdOfRegistrosCollectionRegistros = registrosCollectionRegistros.getCreacionesId();
                registrosCollectionRegistros.setCreacionesId(creaciones);
                registrosCollectionRegistros = em.merge(registrosCollectionRegistros);
                if (oldCreacionesIdOfRegistrosCollectionRegistros != null) {
                    oldCreacionesIdOfRegistrosCollectionRegistros.getRegistrosCollection().remove(registrosCollectionRegistros);
                    oldCreacionesIdOfRegistrosCollectionRegistros = em.merge(oldCreacionesIdOfRegistrosCollectionRegistros);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Creaciones creaciones) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Creaciones persistentCreaciones = em.find(Creaciones.class, creaciones.getId());
            Docentes docentesIdOld = persistentCreaciones.getDocentesId();
            Docentes docentesIdNew = creaciones.getDocentesId();
            Collection<Datos> datosCollectionOld = persistentCreaciones.getDatosCollection();
            Collection<Datos> datosCollectionNew = creaciones.getDatosCollection();
            Collection<Registros> registrosCollectionOld = persistentCreaciones.getRegistrosCollection();
            Collection<Registros> registrosCollectionNew = creaciones.getRegistrosCollection();
            List<String> illegalOrphanMessages = null;
            for (Registros registrosCollectionOldRegistros : registrosCollectionOld) {
                if (!registrosCollectionNew.contains(registrosCollectionOldRegistros)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Registros " + registrosCollectionOldRegistros + " since its creacionesId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (docentesIdNew != null) {
                docentesIdNew = em.getReference(docentesIdNew.getClass(), docentesIdNew.getId());
                creaciones.setDocentesId(docentesIdNew);
            }
            Collection<Datos> attachedDatosCollectionNew = new ArrayList<Datos>();
            for (Datos datosCollectionNewDatosToAttach : datosCollectionNew) {
                datosCollectionNewDatosToAttach = em.getReference(datosCollectionNewDatosToAttach.getClass(), datosCollectionNewDatosToAttach.getId());
                attachedDatosCollectionNew.add(datosCollectionNewDatosToAttach);
            }
            datosCollectionNew = attachedDatosCollectionNew;
            creaciones.setDatosCollection(datosCollectionNew);
            Collection<Registros> attachedRegistrosCollectionNew = new ArrayList<Registros>();
            for (Registros registrosCollectionNewRegistrosToAttach : registrosCollectionNew) {
                registrosCollectionNewRegistrosToAttach = em.getReference(registrosCollectionNewRegistrosToAttach.getClass(), registrosCollectionNewRegistrosToAttach.getId());
                attachedRegistrosCollectionNew.add(registrosCollectionNewRegistrosToAttach);
            }
            registrosCollectionNew = attachedRegistrosCollectionNew;
            creaciones.setRegistrosCollection(registrosCollectionNew);
            creaciones = em.merge(creaciones);
            if (docentesIdOld != null && !docentesIdOld.equals(docentesIdNew)) {
                docentesIdOld.getCreacionesCollection().remove(creaciones);
                docentesIdOld = em.merge(docentesIdOld);
            }
            if (docentesIdNew != null && !docentesIdNew.equals(docentesIdOld)) {
                docentesIdNew.getCreacionesCollection().add(creaciones);
                docentesIdNew = em.merge(docentesIdNew);
            }
            for (Datos datosCollectionOldDatos : datosCollectionOld) {
                if (!datosCollectionNew.contains(datosCollectionOldDatos)) {
                    datosCollectionOldDatos.setCreacionesId(null);
                    datosCollectionOldDatos = em.merge(datosCollectionOldDatos);
                }
            }
            for (Datos datosCollectionNewDatos : datosCollectionNew) {
                if (!datosCollectionOld.contains(datosCollectionNewDatos)) {
                    Creaciones oldCreacionesIdOfDatosCollectionNewDatos = datosCollectionNewDatos.getCreacionesId();
                    datosCollectionNewDatos.setCreacionesId(creaciones);
                    datosCollectionNewDatos = em.merge(datosCollectionNewDatos);
                    if (oldCreacionesIdOfDatosCollectionNewDatos != null && !oldCreacionesIdOfDatosCollectionNewDatos.equals(creaciones)) {
                        oldCreacionesIdOfDatosCollectionNewDatos.getDatosCollection().remove(datosCollectionNewDatos);
                        oldCreacionesIdOfDatosCollectionNewDatos = em.merge(oldCreacionesIdOfDatosCollectionNewDatos);
                    }
                }
            }
            for (Registros registrosCollectionNewRegistros : registrosCollectionNew) {
                if (!registrosCollectionOld.contains(registrosCollectionNewRegistros)) {
                    Creaciones oldCreacionesIdOfRegistrosCollectionNewRegistros = registrosCollectionNewRegistros.getCreacionesId();
                    registrosCollectionNewRegistros.setCreacionesId(creaciones);
                    registrosCollectionNewRegistros = em.merge(registrosCollectionNewRegistros);
                    if (oldCreacionesIdOfRegistrosCollectionNewRegistros != null && !oldCreacionesIdOfRegistrosCollectionNewRegistros.equals(creaciones)) {
                        oldCreacionesIdOfRegistrosCollectionNewRegistros.getRegistrosCollection().remove(registrosCollectionNewRegistros);
                        oldCreacionesIdOfRegistrosCollectionNewRegistros = em.merge(oldCreacionesIdOfRegistrosCollectionNewRegistros);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = creaciones.getId();
                if (findCreaciones(id) == null) {
                    throw new NonexistentEntityException("The creaciones with id " + id + " no longer exists.");
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
            Creaciones creaciones;
            try {
                creaciones = em.getReference(Creaciones.class, id);
                creaciones.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The creaciones with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Registros> registrosCollectionOrphanCheck = creaciones.getRegistrosCollection();
            for (Registros registrosCollectionOrphanCheckRegistros : registrosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Creaciones (" + creaciones + ") cannot be destroyed since the Registros " + registrosCollectionOrphanCheckRegistros + " in its registrosCollection field has a non-nullable creacionesId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Docentes docentesId = creaciones.getDocentesId();
            if (docentesId != null) {
                docentesId.getCreacionesCollection().remove(creaciones);
                docentesId = em.merge(docentesId);
            }
            Collection<Datos> datosCollection = creaciones.getDatosCollection();
            for (Datos datosCollectionDatos : datosCollection) {
                datosCollectionDatos.setCreacionesId(null);
                datosCollectionDatos = em.merge(datosCollectionDatos);
            }
            em.remove(creaciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Creaciones> findCreacionesEntities() {
        return findCreacionesEntities(true, -1, -1);
    }

    public List<Creaciones> findCreacionesEntities(int maxResults, int firstResult) {
        return findCreacionesEntities(false, maxResults, firstResult);
    }

    private List<Creaciones> findCreacionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Creaciones.class));
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

    public Creaciones findCreaciones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Creaciones.class, id);
        } finally {
            em.close();
        }
    }

    public int getCreacionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Creaciones> rt = cq.from(Creaciones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
