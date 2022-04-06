
package edit.database;

import co.edu.unisinu.ingsistemas.ds2.conector.exceptions.NonexistentEntityException;
import co.edu.unisinu.ingsistemas.ds2.conector.exceptions.PreexistingEntityException;
import co.edu.unisinu.ingsistemas.ds2.datos.Creaciones;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.unisinu.ingsistemas.ds2.datos.Docentes;
import co.edu.unisinu.ingsistemas.ds2.datos.Pruebas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author gasler (miler vargas mola)
 */
public class CreacionesEdit implements Serializable {

    public CreacionesEdit(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Creaciones creaciones) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Docentes dniDocente = creaciones.getDniDocente();
            if (dniDocente != null) {
                dniDocente = em.getReference(dniDocente.getClass(), dniDocente.getDni());
                creaciones.setDniDocente(dniDocente);
            }
            Pruebas namePrueba = creaciones.getNamePrueba();
            if (namePrueba != null) {
                namePrueba = em.getReference(namePrueba.getClass(), namePrueba.getName());
                creaciones.setNamePrueba(namePrueba);
            }
            em.persist(creaciones);
            if (dniDocente != null) {
                dniDocente.getCreacionesCollection().add(creaciones);
                dniDocente = em.merge(dniDocente);
            }
            if (namePrueba != null) {
                namePrueba.getCreacionesCollection().add(creaciones);
                namePrueba = em.merge(namePrueba);
            }
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null,"El proceso fue un exito");
        } catch (Exception ex) {
            if (findCreaciones(creaciones.getId()) != null) {
                throw new PreexistingEntityException("Creaciones " + creaciones + " already exists.", ex);
            }
            JOptionPane.showMessageDialog(null,"Algo salio mal en el proceso" ,"ERROR!!",JOptionPane.ERROR_MESSAGE);
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Creaciones creaciones) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Creaciones persistentCreaciones = em.find(Creaciones.class, creaciones.getId());
            Docentes dniDocenteOld = persistentCreaciones.getDniDocente();
            Docentes dniDocenteNew = creaciones.getDniDocente();
            Pruebas namePruebaOld = persistentCreaciones.getNamePrueba();
            Pruebas namePruebaNew = creaciones.getNamePrueba();
            if (dniDocenteNew != null) {
                dniDocenteNew = em.getReference(dniDocenteNew.getClass(), dniDocenteNew.getDni());
                creaciones.setDniDocente(dniDocenteNew);
            }
            if (namePruebaNew != null) {
                namePruebaNew = em.getReference(namePruebaNew.getClass(), namePruebaNew.getName());
                creaciones.setNamePrueba(namePruebaNew);
            }
            creaciones = em.merge(creaciones);
            if (dniDocenteOld != null && !dniDocenteOld.equals(dniDocenteNew)) {
                dniDocenteOld.getCreacionesCollection().remove(creaciones);
                dniDocenteOld = em.merge(dniDocenteOld);
            }
            if (dniDocenteNew != null && !dniDocenteNew.equals(dniDocenteOld)) {
                dniDocenteNew.getCreacionesCollection().add(creaciones);
                dniDocenteNew = em.merge(dniDocenteNew);
            }
            if (namePruebaOld != null && !namePruebaOld.equals(namePruebaNew)) {
                namePruebaOld.getCreacionesCollection().remove(creaciones);
                namePruebaOld = em.merge(namePruebaOld);
            }
            if (namePruebaNew != null && !namePruebaNew.equals(namePruebaOld)) {
                namePruebaNew.getCreacionesCollection().add(creaciones);
                namePruebaNew = em.merge(namePruebaNew);
            }
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null,"El proceso termino con exito" );

        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = creaciones.getId();
                if (findCreaciones(id) == null) {
                    throw new NonexistentEntityException("The creaciones with id " + id + " no longer exists.");
                }
            }
            JOptionPane.showMessageDialog(null,"Algo salio mal en el proceso" ,"ERROR!!",JOptionPane.ERROR_MESSAGE);
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroip(Integer id) throws NonexistentEntityException {
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
            Docentes dniDocente = creaciones.getDniDocente();
            if (dniDocente != null) {
                dniDocente.getCreacionesCollection().remove(creaciones);
                dniDocente = em.merge(dniDocente);
            }
            Pruebas namePrueba = creaciones.getNamePrueba();
            if (namePrueba != null) {
                namePrueba.getCreacionesCollection().remove(creaciones);
                namePrueba = em.merge(namePrueba);
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
