/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edit.database;

import co.edu.unisinu.ingsistemas.ds2.conector.exceptions.IllegalOrphanException;
import co.edu.unisinu.ingsistemas.ds2.conector.exceptions.NonexistentEntityException;
import co.edu.unisinu.ingsistemas.ds2.conector.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.unisinu.ingsistemas.ds2.datos.Practicas;
import co.edu.unisinu.ingsistemas.ds2.datos.Examenes;
import co.edu.unisinu.ingsistemas.ds2.datos.Creaciones;
import java.util.ArrayList;
import java.util.Collection;
import co.edu.unisinu.ingsistemas.ds2.datos.Preguntas;
import co.edu.unisinu.ingsistemas.ds2.datos.Pruebas;
import co.edu.unisinu.ingsistemas.ds2.datos.Realizaciones;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author gasler
 */
public class PruebasEdit implements Serializable {

    public PruebasEdit(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pruebas pruebas) throws PreexistingEntityException, Exception {
        if (pruebas.getCreacionesCollection() == null) {
            pruebas.setCreacionesCollection(new ArrayList<Creaciones>());
        }
        if (pruebas.getPreguntasCollection() == null) {
            pruebas.setPreguntasCollection(new ArrayList<Preguntas>());
        }
        if (pruebas.getRealizacionesCollection() == null) {
            pruebas.setRealizacionesCollection(new ArrayList<Realizaciones>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Practicas practicas = pruebas.getPracticas();
            if (practicas != null) {
                practicas = em.getReference(practicas.getClass(), practicas.getId());
                pruebas.setPracticas(practicas);
            }
            Examenes examenes = pruebas.getExamenes();
            if (examenes != null) {
                examenes = em.getReference(examenes.getClass(), examenes.getIdPreguntas());
                pruebas.setExamenes(examenes);
            }
            Collection<Creaciones> attachedCreacionesCollection = new ArrayList<Creaciones>();
            for (Creaciones creacionesCollectionCreacionesToAttach : pruebas.getCreacionesCollection()) {
                creacionesCollectionCreacionesToAttach = em.getReference(creacionesCollectionCreacionesToAttach.getClass(), creacionesCollectionCreacionesToAttach.getId());
                attachedCreacionesCollection.add(creacionesCollectionCreacionesToAttach);
            }
            pruebas.setCreacionesCollection(attachedCreacionesCollection);
            Collection<Preguntas> attachedPreguntasCollection = new ArrayList<Preguntas>();
            for (Preguntas preguntasCollectionPreguntasToAttach : pruebas.getPreguntasCollection()) {
                preguntasCollectionPreguntasToAttach = em.getReference(preguntasCollectionPreguntasToAttach.getClass(), preguntasCollectionPreguntasToAttach.getId());
                attachedPreguntasCollection.add(preguntasCollectionPreguntasToAttach);
            }
            pruebas.setPreguntasCollection(attachedPreguntasCollection);
            Collection<Realizaciones> attachedRealizacionesCollection = new ArrayList<Realizaciones>();
            for (Realizaciones realizacionesCollectionRealizacionesToAttach : pruebas.getRealizacionesCollection()) {
                realizacionesCollectionRealizacionesToAttach = em.getReference(realizacionesCollectionRealizacionesToAttach.getClass(), realizacionesCollectionRealizacionesToAttach.getId());
                attachedRealizacionesCollection.add(realizacionesCollectionRealizacionesToAttach);
            }
            pruebas.setRealizacionesCollection(attachedRealizacionesCollection);
            em.persist(pruebas);
            if (practicas != null) {
                Pruebas oldPruebasOfPracticas = practicas.getPruebas();
                if (oldPruebasOfPracticas != null) {
                    oldPruebasOfPracticas.setPracticas(null);
                    oldPruebasOfPracticas = em.merge(oldPruebasOfPracticas);
                }
                practicas.setPruebas(pruebas);
                practicas = em.merge(practicas);
            }
            if (examenes != null) {
                Pruebas oldPruebasOfExamenes = examenes.getPruebas();
                if (oldPruebasOfExamenes != null) {
                    oldPruebasOfExamenes.setExamenes(null);
                    oldPruebasOfExamenes = em.merge(oldPruebasOfExamenes);
                }
                examenes.setPruebas(pruebas);
                examenes = em.merge(examenes);
            }
            for (Creaciones creacionesCollectionCreaciones : pruebas.getCreacionesCollection()) {
                Pruebas oldNamePruebaOfCreacionesCollectionCreaciones = creacionesCollectionCreaciones.getNamePrueba();
                creacionesCollectionCreaciones.setNamePrueba(pruebas);
                creacionesCollectionCreaciones = em.merge(creacionesCollectionCreaciones);
                if (oldNamePruebaOfCreacionesCollectionCreaciones != null) {
                    oldNamePruebaOfCreacionesCollectionCreaciones.getCreacionesCollection().remove(creacionesCollectionCreaciones);
                    oldNamePruebaOfCreacionesCollectionCreaciones = em.merge(oldNamePruebaOfCreacionesCollectionCreaciones);
                }
            }
            for (Preguntas preguntasCollectionPreguntas : pruebas.getPreguntasCollection()) {
                Pruebas oldIdPruebasOfPreguntasCollectionPreguntas = preguntasCollectionPreguntas.getIdPruebas();
                preguntasCollectionPreguntas.setIdPruebas(pruebas);
                preguntasCollectionPreguntas = em.merge(preguntasCollectionPreguntas);
                if (oldIdPruebasOfPreguntasCollectionPreguntas != null) {
                    oldIdPruebasOfPreguntasCollectionPreguntas.getPreguntasCollection().remove(preguntasCollectionPreguntas);
                    oldIdPruebasOfPreguntasCollectionPreguntas = em.merge(oldIdPruebasOfPreguntasCollectionPreguntas);
                }
            }
            for (Realizaciones realizacionesCollectionRealizaciones : pruebas.getRealizacionesCollection()) {
                Pruebas oldIdPruebasOfRealizacionesCollectionRealizaciones = realizacionesCollectionRealizaciones.getIdPruebas();
                realizacionesCollectionRealizaciones.setIdPruebas(pruebas);
                realizacionesCollectionRealizaciones = em.merge(realizacionesCollectionRealizaciones);
                if (oldIdPruebasOfRealizacionesCollectionRealizaciones != null) {
                    oldIdPruebasOfRealizacionesCollectionRealizaciones.getRealizacionesCollection().remove(realizacionesCollectionRealizaciones);
                    oldIdPruebasOfRealizacionesCollectionRealizaciones = em.merge(oldIdPruebasOfRealizacionesCollectionRealizaciones);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPruebas(pruebas.getName()) != null) {
                throw new PreexistingEntityException("Pruebas " + pruebas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pruebas pruebas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pruebas persistentPruebas = em.find(Pruebas.class, pruebas.getName());
            Practicas practicasOld = persistentPruebas.getPracticas();
            Practicas practicasNew = pruebas.getPracticas();
            Examenes examenesOld = persistentPruebas.getExamenes();
            Examenes examenesNew = pruebas.getExamenes();
            Collection<Creaciones> creacionesCollectionOld = persistentPruebas.getCreacionesCollection();
            Collection<Creaciones> creacionesCollectionNew = pruebas.getCreacionesCollection();
            Collection<Preguntas> preguntasCollectionOld = persistentPruebas.getPreguntasCollection();
            Collection<Preguntas> preguntasCollectionNew = pruebas.getPreguntasCollection();
            Collection<Realizaciones> realizacionesCollectionOld = persistentPruebas.getRealizacionesCollection();
            Collection<Realizaciones> realizacionesCollectionNew = pruebas.getRealizacionesCollection();
            List<String> illegalOrphanMessages = null;
            if (practicasOld != null && !practicasOld.equals(practicasNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Practicas " + practicasOld + " since its pruebas field is not nullable.");
            }
            if (examenesOld != null && !examenesOld.equals(examenesNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Examenes " + examenesOld + " since its pruebas field is not nullable.");
            }
            for (Creaciones creacionesCollectionOldCreaciones : creacionesCollectionOld) {
                if (!creacionesCollectionNew.contains(creacionesCollectionOldCreaciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Creaciones " + creacionesCollectionOldCreaciones + " since its namePrueba field is not nullable.");
                }
            }
            for (Preguntas preguntasCollectionOldPreguntas : preguntasCollectionOld) {
                if (!preguntasCollectionNew.contains(preguntasCollectionOldPreguntas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Preguntas " + preguntasCollectionOldPreguntas + " since its idPruebas field is not nullable.");
                }
            }
            for (Realizaciones realizacionesCollectionOldRealizaciones : realizacionesCollectionOld) {
                if (!realizacionesCollectionNew.contains(realizacionesCollectionOldRealizaciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Realizaciones " + realizacionesCollectionOldRealizaciones + " since its idPruebas field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (practicasNew != null) {
                practicasNew = em.getReference(practicasNew.getClass(), practicasNew.getId());
                pruebas.setPracticas(practicasNew);
            }
            if (examenesNew != null) {
                examenesNew = em.getReference(examenesNew.getClass(), examenesNew.getIdPreguntas());
                pruebas.setExamenes(examenesNew);
            }
            Collection<Creaciones> attachedCreacionesCollectionNew = new ArrayList<Creaciones>();
            for (Creaciones creacionesCollectionNewCreacionesToAttach : creacionesCollectionNew) {
                creacionesCollectionNewCreacionesToAttach = em.getReference(creacionesCollectionNewCreacionesToAttach.getClass(), creacionesCollectionNewCreacionesToAttach.getId());
                attachedCreacionesCollectionNew.add(creacionesCollectionNewCreacionesToAttach);
            }
            creacionesCollectionNew = attachedCreacionesCollectionNew;
            pruebas.setCreacionesCollection(creacionesCollectionNew);
            Collection<Preguntas> attachedPreguntasCollectionNew = new ArrayList<Preguntas>();
            for (Preguntas preguntasCollectionNewPreguntasToAttach : preguntasCollectionNew) {
                preguntasCollectionNewPreguntasToAttach = em.getReference(preguntasCollectionNewPreguntasToAttach.getClass(), preguntasCollectionNewPreguntasToAttach.getId());
                attachedPreguntasCollectionNew.add(preguntasCollectionNewPreguntasToAttach);
            }
            preguntasCollectionNew = attachedPreguntasCollectionNew;
            pruebas.setPreguntasCollection(preguntasCollectionNew);
            Collection<Realizaciones> attachedRealizacionesCollectionNew = new ArrayList<Realizaciones>();
            for (Realizaciones realizacionesCollectionNewRealizacionesToAttach : realizacionesCollectionNew) {
                realizacionesCollectionNewRealizacionesToAttach = em.getReference(realizacionesCollectionNewRealizacionesToAttach.getClass(), realizacionesCollectionNewRealizacionesToAttach.getId());
                attachedRealizacionesCollectionNew.add(realizacionesCollectionNewRealizacionesToAttach);
            }
            realizacionesCollectionNew = attachedRealizacionesCollectionNew;
            pruebas.setRealizacionesCollection(realizacionesCollectionNew);
            pruebas = em.merge(pruebas);
            if (practicasNew != null && !practicasNew.equals(practicasOld)) {
                Pruebas oldPruebasOfPracticas = practicasNew.getPruebas();
                if (oldPruebasOfPracticas != null) {
                    oldPruebasOfPracticas.setPracticas(null);
                    oldPruebasOfPracticas = em.merge(oldPruebasOfPracticas);
                }
                practicasNew.setPruebas(pruebas);
                practicasNew = em.merge(practicasNew);
            }
            if (examenesNew != null && !examenesNew.equals(examenesOld)) {
                Pruebas oldPruebasOfExamenes = examenesNew.getPruebas();
                if (oldPruebasOfExamenes != null) {
                    oldPruebasOfExamenes.setExamenes(null);
                    oldPruebasOfExamenes = em.merge(oldPruebasOfExamenes);
                }
                examenesNew.setPruebas(pruebas);
                examenesNew = em.merge(examenesNew);
            }
            for (Creaciones creacionesCollectionNewCreaciones : creacionesCollectionNew) {
                if (!creacionesCollectionOld.contains(creacionesCollectionNewCreaciones)) {
                    Pruebas oldNamePruebaOfCreacionesCollectionNewCreaciones = creacionesCollectionNewCreaciones.getNamePrueba();
                    creacionesCollectionNewCreaciones.setNamePrueba(pruebas);
                    creacionesCollectionNewCreaciones = em.merge(creacionesCollectionNewCreaciones);
                    if (oldNamePruebaOfCreacionesCollectionNewCreaciones != null && !oldNamePruebaOfCreacionesCollectionNewCreaciones.equals(pruebas)) {
                        oldNamePruebaOfCreacionesCollectionNewCreaciones.getCreacionesCollection().remove(creacionesCollectionNewCreaciones);
                        oldNamePruebaOfCreacionesCollectionNewCreaciones = em.merge(oldNamePruebaOfCreacionesCollectionNewCreaciones);
                    }
                }
            }
            for (Preguntas preguntasCollectionNewPreguntas : preguntasCollectionNew) {
                if (!preguntasCollectionOld.contains(preguntasCollectionNewPreguntas)) {
                    Pruebas oldIdPruebasOfPreguntasCollectionNewPreguntas = preguntasCollectionNewPreguntas.getIdPruebas();
                    preguntasCollectionNewPreguntas.setIdPruebas(pruebas);
                    preguntasCollectionNewPreguntas = em.merge(preguntasCollectionNewPreguntas);
                    if (oldIdPruebasOfPreguntasCollectionNewPreguntas != null && !oldIdPruebasOfPreguntasCollectionNewPreguntas.equals(pruebas)) {
                        oldIdPruebasOfPreguntasCollectionNewPreguntas.getPreguntasCollection().remove(preguntasCollectionNewPreguntas);
                        oldIdPruebasOfPreguntasCollectionNewPreguntas = em.merge(oldIdPruebasOfPreguntasCollectionNewPreguntas);
                    }
                }
            }
            for (Realizaciones realizacionesCollectionNewRealizaciones : realizacionesCollectionNew) {
                if (!realizacionesCollectionOld.contains(realizacionesCollectionNewRealizaciones)) {
                    Pruebas oldIdPruebasOfRealizacionesCollectionNewRealizaciones = realizacionesCollectionNewRealizaciones.getIdPruebas();
                    realizacionesCollectionNewRealizaciones.setIdPruebas(pruebas);
                    realizacionesCollectionNewRealizaciones = em.merge(realizacionesCollectionNewRealizaciones);
                    if (oldIdPruebasOfRealizacionesCollectionNewRealizaciones != null && !oldIdPruebasOfRealizacionesCollectionNewRealizaciones.equals(pruebas)) {
                        oldIdPruebasOfRealizacionesCollectionNewRealizaciones.getRealizacionesCollection().remove(realizacionesCollectionNewRealizaciones);
                        oldIdPruebasOfRealizacionesCollectionNewRealizaciones = em.merge(oldIdPruebasOfRealizacionesCollectionNewRealizaciones);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = pruebas.getName();
                if (findPruebas(id) == null) {
                    throw new NonexistentEntityException("The pruebas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pruebas pruebas;
            try {
                pruebas = em.getReference(Pruebas.class, id);
                pruebas.getName();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pruebas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Practicas practicasOrphanCheck = pruebas.getPracticas();
            if (practicasOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pruebas (" + pruebas + ") cannot be destroyed since the Practicas " + practicasOrphanCheck + " in its practicas field has a non-nullable pruebas field.");
            }
            Examenes examenesOrphanCheck = pruebas.getExamenes();
            if (examenesOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pruebas (" + pruebas + ") cannot be destroyed since the Examenes " + examenesOrphanCheck + " in its examenes field has a non-nullable pruebas field.");
            }
            Collection<Creaciones> creacionesCollectionOrphanCheck = pruebas.getCreacionesCollection();
            for (Creaciones creacionesCollectionOrphanCheckCreaciones : creacionesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pruebas (" + pruebas + ") cannot be destroyed since the Creaciones " + creacionesCollectionOrphanCheckCreaciones + " in its creacionesCollection field has a non-nullable namePrueba field.");
            }
            Collection<Preguntas> preguntasCollectionOrphanCheck = pruebas.getPreguntasCollection();
            for (Preguntas preguntasCollectionOrphanCheckPreguntas : preguntasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pruebas (" + pruebas + ") cannot be destroyed since the Preguntas " + preguntasCollectionOrphanCheckPreguntas + " in its preguntasCollection field has a non-nullable idPruebas field.");
            }
            Collection<Realizaciones> realizacionesCollectionOrphanCheck = pruebas.getRealizacionesCollection();
            for (Realizaciones realizacionesCollectionOrphanCheckRealizaciones : realizacionesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pruebas (" + pruebas + ") cannot be destroyed since the Realizaciones " + realizacionesCollectionOrphanCheckRealizaciones + " in its realizacionesCollection field has a non-nullable idPruebas field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(pruebas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pruebas> findPruebasEntities() {
        return findPruebasEntities(true, -1, -1);
    }

    public List<Pruebas> findPruebasEntities(int maxResults, int firstResult) {
        return findPruebasEntities(false, maxResults, firstResult);
    }

    private List<Pruebas> findPruebasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pruebas.class));
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

    public Pruebas findPruebas(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pruebas.class, id);
        } finally {
            em.close();
        }
    }

    public int getPruebasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pruebas> rt = cq.from(Pruebas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
