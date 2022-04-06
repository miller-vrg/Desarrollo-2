/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edit.database;

import co.edu.unisinu.ingsistemas.ds2.conector.exceptions.IllegalOrphanException;
import co.edu.unisinu.ingsistemas.ds2.conector.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.unisinu.ingsistemas.ds2.datos.Pruebas;
import co.edu.unisinu.ingsistemas.ds2.datos.Opciones;
import co.edu.unisinu.ingsistemas.ds2.datos.Preguntas;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author gasler
 */
public class PreguntasEdit implements Serializable {

    public PreguntasEdit(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Preguntas preguntas) {
        if (preguntas.getOpcionesCollection() == null) {
            preguntas.setOpcionesCollection(new ArrayList<Opciones>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pruebas idPruebas = preguntas.getIdPruebas();
            if (idPruebas != null) {
                idPruebas = em.getReference(idPruebas.getClass(), idPruebas.getName());
                preguntas.setIdPruebas(idPruebas);
            }
            Collection<Opciones> attachedOpcionesCollection = new ArrayList<Opciones>();
            for (Opciones opcionesCollectionOpcionesToAttach : preguntas.getOpcionesCollection()) {
                opcionesCollectionOpcionesToAttach = em.getReference(opcionesCollectionOpcionesToAttach.getClass(), opcionesCollectionOpcionesToAttach.getId());
                attachedOpcionesCollection.add(opcionesCollectionOpcionesToAttach);
            }
            preguntas.setOpcionesCollection(attachedOpcionesCollection);
            em.persist(preguntas);
            if (idPruebas != null) {
                idPruebas.getPreguntasCollection().add(preguntas);
                idPruebas = em.merge(idPruebas);
            }
            for (Opciones opcionesCollectionOpciones : preguntas.getOpcionesCollection()) {
                Preguntas oldIdPreguntasOfOpcionesCollectionOpciones = opcionesCollectionOpciones.getIdPreguntas();
                opcionesCollectionOpciones.setIdPreguntas(preguntas);
                opcionesCollectionOpciones = em.merge(opcionesCollectionOpciones);
                if (oldIdPreguntasOfOpcionesCollectionOpciones != null) {
                    oldIdPreguntasOfOpcionesCollectionOpciones.getOpcionesCollection().remove(opcionesCollectionOpciones);
                    oldIdPreguntasOfOpcionesCollectionOpciones = em.merge(oldIdPreguntasOfOpcionesCollectionOpciones);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Preguntas preguntas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Preguntas persistentPreguntas = em.find(Preguntas.class, preguntas.getId());
            Pruebas idPruebasOld = persistentPreguntas.getIdPruebas();
            Pruebas idPruebasNew = preguntas.getIdPruebas();
            Collection<Opciones> opcionesCollectionOld = persistentPreguntas.getOpcionesCollection();
            Collection<Opciones> opcionesCollectionNew = preguntas.getOpcionesCollection();
            List<String> illegalOrphanMessages = null;
            for (Opciones opcionesCollectionOldOpciones : opcionesCollectionOld) {
                if (!opcionesCollectionNew.contains(opcionesCollectionOldOpciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Opciones " + opcionesCollectionOldOpciones + " since its idPreguntas field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idPruebasNew != null) {
                idPruebasNew = em.getReference(idPruebasNew.getClass(), idPruebasNew.getName());
                preguntas.setIdPruebas(idPruebasNew);
            }
            Collection<Opciones> attachedOpcionesCollectionNew = new ArrayList<Opciones>();
            for (Opciones opcionesCollectionNewOpcionesToAttach : opcionesCollectionNew) {
                opcionesCollectionNewOpcionesToAttach = em.getReference(opcionesCollectionNewOpcionesToAttach.getClass(), opcionesCollectionNewOpcionesToAttach.getId());
                attachedOpcionesCollectionNew.add(opcionesCollectionNewOpcionesToAttach);
            }
            opcionesCollectionNew = attachedOpcionesCollectionNew;
            preguntas.setOpcionesCollection(opcionesCollectionNew);
            preguntas = em.merge(preguntas);
            if (idPruebasOld != null && !idPruebasOld.equals(idPruebasNew)) {
                idPruebasOld.getPreguntasCollection().remove(preguntas);
                idPruebasOld = em.merge(idPruebasOld);
            }
            if (idPruebasNew != null && !idPruebasNew.equals(idPruebasOld)) {
                idPruebasNew.getPreguntasCollection().add(preguntas);
                idPruebasNew = em.merge(idPruebasNew);
            }
            for (Opciones opcionesCollectionNewOpciones : opcionesCollectionNew) {
                if (!opcionesCollectionOld.contains(opcionesCollectionNewOpciones)) {
                    Preguntas oldIdPreguntasOfOpcionesCollectionNewOpciones = opcionesCollectionNewOpciones.getIdPreguntas();
                    opcionesCollectionNewOpciones.setIdPreguntas(preguntas);
                    opcionesCollectionNewOpciones = em.merge(opcionesCollectionNewOpciones);
                    if (oldIdPreguntasOfOpcionesCollectionNewOpciones != null && !oldIdPreguntasOfOpcionesCollectionNewOpciones.equals(preguntas)) {
                        oldIdPreguntasOfOpcionesCollectionNewOpciones.getOpcionesCollection().remove(opcionesCollectionNewOpciones);
                        oldIdPreguntasOfOpcionesCollectionNewOpciones = em.merge(oldIdPreguntasOfOpcionesCollectionNewOpciones);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = preguntas.getId();
                if (findPreguntas(id) == null) {
                    throw new NonexistentEntityException("The preguntas with id " + id + " no longer exists.");
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
            Preguntas preguntas;
            try {
                preguntas = em.getReference(Preguntas.class, id);
                preguntas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The preguntas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Opciones> opcionesCollectionOrphanCheck = preguntas.getOpcionesCollection();
            for (Opciones opcionesCollectionOrphanCheckOpciones : opcionesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Preguntas (" + preguntas + ") cannot be destroyed since the Opciones " + opcionesCollectionOrphanCheckOpciones + " in its opcionesCollection field has a non-nullable idPreguntas field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Pruebas idPruebas = preguntas.getIdPruebas();
            if (idPruebas != null) {
                idPruebas.getPreguntasCollection().remove(preguntas);
                idPruebas = em.merge(idPruebas);
            }
            em.remove(preguntas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Preguntas> findPreguntasEntities() {
        return findPreguntasEntities(true, -1, -1);
    }

    public List<Preguntas> findPreguntasEntities(int maxResults, int firstResult) {
        return findPreguntasEntities(false, maxResults, firstResult);
    }

    private List<Preguntas> findPreguntasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Preguntas.class));
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

    public Preguntas findPreguntas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Preguntas.class, id);
        } finally {
            em.close();
        }
    }

    public int getPreguntasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Preguntas> rt = cq.from(Preguntas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
