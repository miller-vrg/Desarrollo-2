
package millervargas.ejemplodb.v1.basedatos.crud;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import millervargas.ejemplodb.v1.basedatos.crud.exceptions.NonexistentEntityException;
import millervargas.ejemplodb.v1.basedatos.entidades.Gastos;
import millervargas.ejemplodb.v1.basedatos.entidades.Usuarios;

/**
 *
 * @author gasler
 */
public class CrudGasto implements Serializable {

    public CrudGasto(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void insertar(Gastos gastos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios usuarioId = gastos.getUsuarioId();
            if (usuarioId != null) {
                usuarioId = em.getReference(usuarioId.getClass(), usuarioId.getId());
                gastos.setUsuarioId(usuarioId);
            }
            em.persist(gastos);
            if (usuarioId != null) {
                usuarioId.getGastosCollection().add(gastos);
                usuarioId = em.merge(usuarioId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void modificar(Gastos gastos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Gastos persistentGastos = em.find(Gastos.class, gastos.getId());
            Usuarios usuarioIdOld = persistentGastos.getUsuarioId();
            Usuarios usuarioIdNew = gastos.getUsuarioId();
            if (usuarioIdNew != null) {
                usuarioIdNew = em.getReference(usuarioIdNew.getClass(), usuarioIdNew.getId());
                gastos.setUsuarioId(usuarioIdNew);
            }
            gastos = em.merge(gastos);
            if (usuarioIdOld != null && !usuarioIdOld.equals(usuarioIdNew)) {
                usuarioIdOld.getGastosCollection().remove(gastos);
                usuarioIdOld = em.merge(usuarioIdOld);
            }
            if (usuarioIdNew != null && !usuarioIdNew.equals(usuarioIdOld)) {
                usuarioIdNew.getGastosCollection().add(gastos);
                usuarioIdNew = em.merge(usuarioIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = gastos.getId();
                if (buscarGastoPorId(id) == null) {
                    throw new NonexistentEntityException("The gastos with id " + id + " no longer exists.");
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
            Gastos gastos;
            try {
                gastos = em.getReference(Gastos.class, id);
                gastos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The gastos with id " + id + " no longer exists.", enfe);
            }
            Usuarios usuarioId = gastos.getUsuarioId();
            if (usuarioId != null) {
                usuarioId.getGastosCollection().remove(gastos);
                usuarioId = em.merge(usuarioId);
            }
            em.remove(gastos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Gastos> busarTodosLosGastos() {
        return findGastosEntities(true, -1, -1);
    }

    public List<Gastos> findGastosEntities(int maxResults, int firstResult) {
        return findGastosEntities(false, maxResults, firstResult);
    }

    private List<Gastos> findGastosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Gastos.class));
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

    public Gastos buscarGastoPorId(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Gastos.class, id);
        } finally {
            em.close();
        }
    }

    public int contarUsuarios() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Gastos> rt = cq.from(Gastos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
