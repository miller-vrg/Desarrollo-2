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
import co.edu.unisinu.ingsistemas.ds2.datos.Estudiantes;
import co.edu.unisinu.ingsistemas.ds2.datos.Pruebas;
import co.edu.unisinu.ingsistemas.ds2.datos.ListadoNotas;
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
public class RealizacionesEdit implements Serializable {

    public RealizacionesEdit(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Realizaciones realizaciones) {
        if (realizaciones.getListadoNotasCollection() == null) {
            realizaciones.setListadoNotasCollection(new ArrayList<ListadoNotas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estudiantes idAlumnoFk = realizaciones.getIdAlumnoFk();
            if (idAlumnoFk != null) {
                idAlumnoFk = em.getReference(idAlumnoFk.getClass(), idAlumnoFk.getMatricula());
                realizaciones.setIdAlumnoFk(idAlumnoFk);
            }
            Pruebas idPruebas = realizaciones.getIdPruebas();
            if (idPruebas != null) {
                idPruebas = em.getReference(idPruebas.getClass(), idPruebas.getName());
                realizaciones.setIdPruebas(idPruebas);
            }
            Collection<ListadoNotas> attachedListadoNotasCollection = new ArrayList<ListadoNotas>();
            for (ListadoNotas listadoNotasCollectionListadoNotasToAttach : realizaciones.getListadoNotasCollection()) {
                listadoNotasCollectionListadoNotasToAttach = em.getReference(listadoNotasCollectionListadoNotasToAttach.getClass(), listadoNotasCollectionListadoNotasToAttach.getId());
                attachedListadoNotasCollection.add(listadoNotasCollectionListadoNotasToAttach);
            }
            realizaciones.setListadoNotasCollection(attachedListadoNotasCollection);
            em.persist(realizaciones);
            if (idAlumnoFk != null) {
                idAlumnoFk.getRealizacionesCollection().add(realizaciones);
                idAlumnoFk = em.merge(idAlumnoFk);
            }
            if (idPruebas != null) {
                idPruebas.getRealizacionesCollection().add(realizaciones);
                idPruebas = em.merge(idPruebas);
            }
            for (ListadoNotas listadoNotasCollectionListadoNotas : realizaciones.getListadoNotasCollection()) {
                Realizaciones oldIdRealizacionesOfListadoNotasCollectionListadoNotas = listadoNotasCollectionListadoNotas.getIdRealizaciones();
                listadoNotasCollectionListadoNotas.setIdRealizaciones(realizaciones);
                listadoNotasCollectionListadoNotas = em.merge(listadoNotasCollectionListadoNotas);
                if (oldIdRealizacionesOfListadoNotasCollectionListadoNotas != null) {
                    oldIdRealizacionesOfListadoNotasCollectionListadoNotas.getListadoNotasCollection().remove(listadoNotasCollectionListadoNotas);
                    oldIdRealizacionesOfListadoNotasCollectionListadoNotas = em.merge(oldIdRealizacionesOfListadoNotasCollectionListadoNotas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Realizaciones realizaciones) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Realizaciones persistentRealizaciones = em.find(Realizaciones.class, realizaciones.getId());
            Estudiantes idAlumnoFkOld = persistentRealizaciones.getIdAlumnoFk();
            Estudiantes idAlumnoFkNew = realizaciones.getIdAlumnoFk();
            Pruebas idPruebasOld = persistentRealizaciones.getIdPruebas();
            Pruebas idPruebasNew = realizaciones.getIdPruebas();
            Collection<ListadoNotas> listadoNotasCollectionOld = persistentRealizaciones.getListadoNotasCollection();
            Collection<ListadoNotas> listadoNotasCollectionNew = realizaciones.getListadoNotasCollection();
            if (idAlumnoFkNew != null) {
                idAlumnoFkNew = em.getReference(idAlumnoFkNew.getClass(), idAlumnoFkNew.getMatricula());
                realizaciones.setIdAlumnoFk(idAlumnoFkNew);
            }
            if (idPruebasNew != null) {
                idPruebasNew = em.getReference(idPruebasNew.getClass(), idPruebasNew.getName());
                realizaciones.setIdPruebas(idPruebasNew);
            }
            Collection<ListadoNotas> attachedListadoNotasCollectionNew = new ArrayList<ListadoNotas>();
            for (ListadoNotas listadoNotasCollectionNewListadoNotasToAttach : listadoNotasCollectionNew) {
                listadoNotasCollectionNewListadoNotasToAttach = em.getReference(listadoNotasCollectionNewListadoNotasToAttach.getClass(), listadoNotasCollectionNewListadoNotasToAttach.getId());
                attachedListadoNotasCollectionNew.add(listadoNotasCollectionNewListadoNotasToAttach);
            }
            listadoNotasCollectionNew = attachedListadoNotasCollectionNew;
            realizaciones.setListadoNotasCollection(listadoNotasCollectionNew);
            realizaciones = em.merge(realizaciones);
            if (idAlumnoFkOld != null && !idAlumnoFkOld.equals(idAlumnoFkNew)) {
                idAlumnoFkOld.getRealizacionesCollection().remove(realizaciones);
                idAlumnoFkOld = em.merge(idAlumnoFkOld);
            }
            if (idAlumnoFkNew != null && !idAlumnoFkNew.equals(idAlumnoFkOld)) {
                idAlumnoFkNew.getRealizacionesCollection().add(realizaciones);
                idAlumnoFkNew = em.merge(idAlumnoFkNew);
            }
            if (idPruebasOld != null && !idPruebasOld.equals(idPruebasNew)) {
                idPruebasOld.getRealizacionesCollection().remove(realizaciones);
                idPruebasOld = em.merge(idPruebasOld);
            }
            if (idPruebasNew != null && !idPruebasNew.equals(idPruebasOld)) {
                idPruebasNew.getRealizacionesCollection().add(realizaciones);
                idPruebasNew = em.merge(idPruebasNew);
            }
            for (ListadoNotas listadoNotasCollectionOldListadoNotas : listadoNotasCollectionOld) {
                if (!listadoNotasCollectionNew.contains(listadoNotasCollectionOldListadoNotas)) {
                    listadoNotasCollectionOldListadoNotas.setIdRealizaciones(null);
                    listadoNotasCollectionOldListadoNotas = em.merge(listadoNotasCollectionOldListadoNotas);
                }
            }
            for (ListadoNotas listadoNotasCollectionNewListadoNotas : listadoNotasCollectionNew) {
                if (!listadoNotasCollectionOld.contains(listadoNotasCollectionNewListadoNotas)) {
                    Realizaciones oldIdRealizacionesOfListadoNotasCollectionNewListadoNotas = listadoNotasCollectionNewListadoNotas.getIdRealizaciones();
                    listadoNotasCollectionNewListadoNotas.setIdRealizaciones(realizaciones);
                    listadoNotasCollectionNewListadoNotas = em.merge(listadoNotasCollectionNewListadoNotas);
                    if (oldIdRealizacionesOfListadoNotasCollectionNewListadoNotas != null && !oldIdRealizacionesOfListadoNotasCollectionNewListadoNotas.equals(realizaciones)) {
                        oldIdRealizacionesOfListadoNotasCollectionNewListadoNotas.getListadoNotasCollection().remove(listadoNotasCollectionNewListadoNotas);
                        oldIdRealizacionesOfListadoNotasCollectionNewListadoNotas = em.merge(oldIdRealizacionesOfListadoNotasCollectionNewListadoNotas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = realizaciones.getId();
                if (findRealizaciones(id) == null) {
                    throw new NonexistentEntityException("The realizaciones with id " + id + " no longer exists.");
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
            Realizaciones realizaciones;
            try {
                realizaciones = em.getReference(Realizaciones.class, id);
                realizaciones.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The realizaciones with id " + id + " no longer exists.", enfe);
            }
            Estudiantes idAlumnoFk = realizaciones.getIdAlumnoFk();
            if (idAlumnoFk != null) {
                idAlumnoFk.getRealizacionesCollection().remove(realizaciones);
                idAlumnoFk = em.merge(idAlumnoFk);
            }
            Pruebas idPruebas = realizaciones.getIdPruebas();
            if (idPruebas != null) {
                idPruebas.getRealizacionesCollection().remove(realizaciones);
                idPruebas = em.merge(idPruebas);
            }
            Collection<ListadoNotas> listadoNotasCollection = realizaciones.getListadoNotasCollection();
            for (ListadoNotas listadoNotasCollectionListadoNotas : listadoNotasCollection) {
                listadoNotasCollectionListadoNotas.setIdRealizaciones(null);
                listadoNotasCollectionListadoNotas = em.merge(listadoNotasCollectionListadoNotas);
            }
            em.remove(realizaciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Realizaciones> findRealizacionesEntities() {
        return findRealizacionesEntities(true, -1, -1);
    }

    public List<Realizaciones> findRealizacionesEntities(int maxResults, int firstResult) {
        return findRealizacionesEntities(false, maxResults, firstResult);
    }

    private List<Realizaciones> findRealizacionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Realizaciones.class));
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

    public Realizaciones findRealizaciones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Realizaciones.class, id);
        } finally {
            em.close();
        }
    }

    public int getRealizacionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Realizaciones> rt = cq.from(Realizaciones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
