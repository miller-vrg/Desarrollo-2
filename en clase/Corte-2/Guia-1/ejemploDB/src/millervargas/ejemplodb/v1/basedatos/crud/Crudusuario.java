/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package millervargas.ejemplodb.v1.basedatos.crud;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import millervargas.ejemplodb.v1.basedatos.entidades.Gastos;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import millervargas.ejemplodb.v1.basedatos.crud.exceptions.NonexistentEntityException;
import millervargas.ejemplodb.v1.basedatos.entidades.Usuarios;

/**
 *
 * @author gasler
 */
public class Crudusuario implements Serializable {

    public Crudusuario(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void insertar(Usuarios usuarios) {
        if (usuarios.getGastosCollection() == null) {
            usuarios.setGastosCollection(new ArrayList<Gastos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Gastos> attachedGastosCollection = new ArrayList<Gastos>();
            for (Gastos gastosCollectionGastosToAttach : usuarios.getGastosCollection()) {
                gastosCollectionGastosToAttach = em.getReference(gastosCollectionGastosToAttach.getClass(), gastosCollectionGastosToAttach.getId());
                attachedGastosCollection.add(gastosCollectionGastosToAttach);
            }
            usuarios.setGastosCollection(attachedGastosCollection);
            em.persist(usuarios);
            for (Gastos gastosCollectionGastos : usuarios.getGastosCollection()) {
                Usuarios oldUsuarioIdOfGastosCollectionGastos = gastosCollectionGastos.getUsuarioId();
                gastosCollectionGastos.setUsuarioId(usuarios);
                gastosCollectionGastos = em.merge(gastosCollectionGastos);
                if (oldUsuarioIdOfGastosCollectionGastos != null) {
                    oldUsuarioIdOfGastosCollectionGastos.getGastosCollection().remove(gastosCollectionGastos);
                    oldUsuarioIdOfGastosCollectionGastos = em.merge(oldUsuarioIdOfGastosCollectionGastos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void modificar(Usuarios usuarios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios persistentUsuarios = em.find(Usuarios.class, usuarios.getId());
            Collection<Gastos> gastosCollectionOld = persistentUsuarios.getGastosCollection();
            Collection<Gastos> gastosCollectionNew = usuarios.getGastosCollection();
            Collection<Gastos> attachedGastosCollectionNew = new ArrayList<Gastos>();
            for (Gastos gastosCollectionNewGastosToAttach : gastosCollectionNew) {
                gastosCollectionNewGastosToAttach = em.getReference(gastosCollectionNewGastosToAttach.getClass(), gastosCollectionNewGastosToAttach.getId());
                attachedGastosCollectionNew.add(gastosCollectionNewGastosToAttach);
            }
            gastosCollectionNew = attachedGastosCollectionNew;
            usuarios.setGastosCollection(gastosCollectionNew);
            usuarios = em.merge(usuarios);
            for (Gastos gastosCollectionOldGastos : gastosCollectionOld) {
                if (!gastosCollectionNew.contains(gastosCollectionOldGastos)) {
                    gastosCollectionOldGastos.setUsuarioId(null);
                    gastosCollectionOldGastos = em.merge(gastosCollectionOldGastos);
                }
            }
            for (Gastos gastosCollectionNewGastos : gastosCollectionNew) {
                if (!gastosCollectionOld.contains(gastosCollectionNewGastos)) {
                    Usuarios oldUsuarioIdOfGastosCollectionNewGastos = gastosCollectionNewGastos.getUsuarioId();
                    gastosCollectionNewGastos.setUsuarioId(usuarios);
                    gastosCollectionNewGastos = em.merge(gastosCollectionNewGastos);
                    if (oldUsuarioIdOfGastosCollectionNewGastos != null && !oldUsuarioIdOfGastosCollectionNewGastos.equals(usuarios)) {
                        oldUsuarioIdOfGastosCollectionNewGastos.getGastosCollection().remove(gastosCollectionNewGastos);
                        oldUsuarioIdOfGastosCollectionNewGastos = em.merge(oldUsuarioIdOfGastosCollectionNewGastos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuarios.getId();
                if (buscarUsuariosPorId(id) == null) {
                    throw new NonexistentEntityException("The usuarios with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void eliminar(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios usuarios;
            try {
                usuarios = em.getReference(Usuarios.class, id);
                usuarios.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuarios with id " + id + " no longer exists.", enfe);
            }
            Collection<Gastos> gastosCollection = usuarios.getGastosCollection();
            for (Gastos gastosCollectionGastos : gastosCollection) {
                gastosCollectionGastos.setUsuarioId(null);
                gastosCollectionGastos = em.merge(gastosCollectionGastos);
            }
            em.remove(usuarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuarios> buscarTodosLosUsuarios() {
        return findUsuariosEntities(true, -1, -1);
    }

    public List<Usuarios> findUsuariosEntities(int maxResults, int firstResult) {
        return findUsuariosEntities(false, maxResults, firstResult);
    }

    private List<Usuarios> findUsuariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuarios.class));
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

    public Usuarios buscarUsuariosPorId(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuarios.class, id);
        } finally {
            em.close();
        }
    }

    public int contarUsuarios() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuarios> rt = cq.from(Usuarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
