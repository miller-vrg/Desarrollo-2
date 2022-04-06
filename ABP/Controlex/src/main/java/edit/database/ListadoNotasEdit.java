/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edit.database;

import co.edu.unisinu.ingsistemas.ds2.conector.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.unisinu.ingsistemas.ds2.datos.Realizaciones;
import co.edu.unisinu.ingsistemas.ds2.datos.Docentes;
import co.edu.unisinu.ingsistemas.ds2.datos.ListadoNotas;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author gasler
 */
public class ListadoNotasEdit implements Serializable {

    public ListadoNotasEdit(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ListadoNotas listadoNotas) {
        if (listadoNotas.getDocentesCollection() == null) {
            listadoNotas.setDocentesCollection(new ArrayList<Docentes>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Realizaciones idRealizaciones = listadoNotas.getIdRealizaciones();
            if (idRealizaciones != null) {
                idRealizaciones = em.getReference(idRealizaciones.getClass(), idRealizaciones.getId());
                listadoNotas.setIdRealizaciones(idRealizaciones);
            }
            Collection<Docentes> attachedDocentesCollection = new ArrayList<Docentes>();
            for (Docentes docentesCollectionDocentesToAttach : listadoNotas.getDocentesCollection()) {
                docentesCollectionDocentesToAttach = em.getReference(docentesCollectionDocentesToAttach.getClass(), docentesCollectionDocentesToAttach.getDni());
                attachedDocentesCollection.add(docentesCollectionDocentesToAttach);
            }
            listadoNotas.setDocentesCollection(attachedDocentesCollection);
            em.persist(listadoNotas);
            if (idRealizaciones != null) {
                idRealizaciones.getListadoNotasCollection().add(listadoNotas);
                idRealizaciones = em.merge(idRealizaciones);
            }
            for (Docentes docentesCollectionDocentes : listadoNotas.getDocentesCollection()) {
                ListadoNotas oldIdListadoNotasOfDocentesCollectionDocentes = docentesCollectionDocentes.getIdListadoNotas();
                docentesCollectionDocentes.setIdListadoNotas(listadoNotas);
                docentesCollectionDocentes = em.merge(docentesCollectionDocentes);
                if (oldIdListadoNotasOfDocentesCollectionDocentes != null) {
                    oldIdListadoNotasOfDocentesCollectionDocentes.getDocentesCollection().remove(docentesCollectionDocentes);
                    oldIdListadoNotasOfDocentesCollectionDocentes = em.merge(oldIdListadoNotasOfDocentesCollectionDocentes);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ListadoNotas listadoNotas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ListadoNotas persistentListadoNotas = em.find(ListadoNotas.class, listadoNotas.getId());
            Realizaciones idRealizacionesOld = persistentListadoNotas.getIdRealizaciones();
            Realizaciones idRealizacionesNew = listadoNotas.getIdRealizaciones();
            Collection<Docentes> docentesCollectionOld = persistentListadoNotas.getDocentesCollection();
            Collection<Docentes> docentesCollectionNew = listadoNotas.getDocentesCollection();
            if (idRealizacionesNew != null) {
                idRealizacionesNew = em.getReference(idRealizacionesNew.getClass(), idRealizacionesNew.getId());
                listadoNotas.setIdRealizaciones(idRealizacionesNew);
            }
            Collection<Docentes> attachedDocentesCollectionNew = new ArrayList<Docentes>();
            for (Docentes docentesCollectionNewDocentesToAttach : docentesCollectionNew) {
                docentesCollectionNewDocentesToAttach = em.getReference(docentesCollectionNewDocentesToAttach.getClass(), docentesCollectionNewDocentesToAttach.getDni());
                attachedDocentesCollectionNew.add(docentesCollectionNewDocentesToAttach);
            }
            docentesCollectionNew = attachedDocentesCollectionNew;
            listadoNotas.setDocentesCollection(docentesCollectionNew);
            listadoNotas = em.merge(listadoNotas);
            if (idRealizacionesOld != null && !idRealizacionesOld.equals(idRealizacionesNew)) {
                idRealizacionesOld.getListadoNotasCollection().remove(listadoNotas);
                idRealizacionesOld = em.merge(idRealizacionesOld);
            }
            if (idRealizacionesNew != null && !idRealizacionesNew.equals(idRealizacionesOld)) {
                idRealizacionesNew.getListadoNotasCollection().add(listadoNotas);
                idRealizacionesNew = em.merge(idRealizacionesNew);
            }
            for (Docentes docentesCollectionOldDocentes : docentesCollectionOld) {
                if (!docentesCollectionNew.contains(docentesCollectionOldDocentes)) {
                    docentesCollectionOldDocentes.setIdListadoNotas(null);
                    docentesCollectionOldDocentes = em.merge(docentesCollectionOldDocentes);
                }
            }
            for (Docentes docentesCollectionNewDocentes : docentesCollectionNew) {
                if (!docentesCollectionOld.contains(docentesCollectionNewDocentes)) {
                    ListadoNotas oldIdListadoNotasOfDocentesCollectionNewDocentes = docentesCollectionNewDocentes.getIdListadoNotas();
                    docentesCollectionNewDocentes.setIdListadoNotas(listadoNotas);
                    docentesCollectionNewDocentes = em.merge(docentesCollectionNewDocentes);
                    if (oldIdListadoNotasOfDocentesCollectionNewDocentes != null && !oldIdListadoNotasOfDocentesCollectionNewDocentes.equals(listadoNotas)) {
                        oldIdListadoNotasOfDocentesCollectionNewDocentes.getDocentesCollection().remove(docentesCollectionNewDocentes);
                        oldIdListadoNotasOfDocentesCollectionNewDocentes = em.merge(oldIdListadoNotasOfDocentesCollectionNewDocentes);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = listadoNotas.getId();
                if (findListadoNotas(id) == null) {
                    throw new NonexistentEntityException("The listadoNotas with id " + id + " no longer exists.");
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
            ListadoNotas listadoNotas;
            try {
                listadoNotas = em.getReference(ListadoNotas.class, id);
                listadoNotas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The listadoNotas with id " + id + " no longer exists.", enfe);
            }
            Realizaciones idRealizaciones = listadoNotas.getIdRealizaciones();
            if (idRealizaciones != null) {
                idRealizaciones.getListadoNotasCollection().remove(listadoNotas);
                idRealizaciones = em.merge(idRealizaciones);
            }
            Collection<Docentes> docentesCollection = listadoNotas.getDocentesCollection();
            for (Docentes docentesCollectionDocentes : docentesCollection) {
                docentesCollectionDocentes.setIdListadoNotas(null);
                docentesCollectionDocentes = em.merge(docentesCollectionDocentes);
            }
            em.remove(listadoNotas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ListadoNotas> findListadoNotasEntities() {
        return findListadoNotasEntities(true, -1, -1);
    }

    public List<ListadoNotas> findListadoNotasEntities(int maxResults, int firstResult) {
        return findListadoNotasEntities(false, maxResults, firstResult);
    }

    private List<ListadoNotas> findListadoNotasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ListadoNotas.class));
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

    public ListadoNotas findListadoNotas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ListadoNotas.class, id);
        } finally {
            em.close();
        }
    }

    public int getListadoNotasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ListadoNotas> rt = cq.from(ListadoNotas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
