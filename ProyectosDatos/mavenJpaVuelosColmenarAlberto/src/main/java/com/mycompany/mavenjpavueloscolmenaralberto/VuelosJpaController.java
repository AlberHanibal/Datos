/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenjpavueloscolmenaralberto;

import com.mycompany.mavenjpavueloscolmenaralberto.exceptions.IllegalOrphanException;
import com.mycompany.mavenjpavueloscolmenaralberto.exceptions.NonexistentEntityException;
import com.mycompany.mavenjpavueloscolmenaralberto.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author DAM2A-03
 */
public class VuelosJpaController implements Serializable {

    public VuelosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public VuelosJpaController() {
        emf = Persistence.createEntityManagerFactory("com.mycompany.mavenJpaVuelosColmenarAlbertoPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vuelos vuelos) throws PreexistingEntityException, Exception {
        if (vuelos.getPasajerosCollection() == null) {
            vuelos.setPasajerosCollection(new ArrayList<Pasajeros>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Pasajeros> attachedPasajerosCollection = new ArrayList<Pasajeros>();
            for (Pasajeros pasajerosCollectionPasajerosToAttach : vuelos.getPasajerosCollection()) {
                pasajerosCollectionPasajerosToAttach = em.getReference(pasajerosCollectionPasajerosToAttach.getClass(), pasajerosCollectionPasajerosToAttach.getPasajerosPK());
                attachedPasajerosCollection.add(pasajerosCollectionPasajerosToAttach);
            }
            vuelos.setPasajerosCollection(attachedPasajerosCollection);
            em.persist(vuelos);
            for (Pasajeros pasajerosCollectionPasajeros : vuelos.getPasajerosCollection()) {
                Vuelos oldVuelosOfPasajerosCollectionPasajeros = pasajerosCollectionPasajeros.getVuelos();
                pasajerosCollectionPasajeros.setVuelos(vuelos);
                pasajerosCollectionPasajeros = em.merge(pasajerosCollectionPasajeros);
                if (oldVuelosOfPasajerosCollectionPasajeros != null) {
                    oldVuelosOfPasajerosCollectionPasajeros.getPasajerosCollection().remove(pasajerosCollectionPasajeros);
                    oldVuelosOfPasajerosCollectionPasajeros = em.merge(oldVuelosOfPasajerosCollectionPasajeros);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVuelos(vuelos.getCodVuelo()) != null) {
                throw new PreexistingEntityException("Vuelos " + vuelos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vuelos vuelos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vuelos persistentVuelos = em.find(Vuelos.class, vuelos.getCodVuelo());
            Collection<Pasajeros> pasajerosCollectionOld = persistentVuelos.getPasajerosCollection();
            Collection<Pasajeros> pasajerosCollectionNew = vuelos.getPasajerosCollection();
            List<String> illegalOrphanMessages = null;
            for (Pasajeros pasajerosCollectionOldPasajeros : pasajerosCollectionOld) {
                if (!pasajerosCollectionNew.contains(pasajerosCollectionOldPasajeros)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pasajeros " + pasajerosCollectionOldPasajeros + " since its vuelos field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Pasajeros> attachedPasajerosCollectionNew = new ArrayList<Pasajeros>();
            for (Pasajeros pasajerosCollectionNewPasajerosToAttach : pasajerosCollectionNew) {
                pasajerosCollectionNewPasajerosToAttach = em.getReference(pasajerosCollectionNewPasajerosToAttach.getClass(), pasajerosCollectionNewPasajerosToAttach.getPasajerosPK());
                attachedPasajerosCollectionNew.add(pasajerosCollectionNewPasajerosToAttach);
            }
            pasajerosCollectionNew = attachedPasajerosCollectionNew;
            vuelos.setPasajerosCollection(pasajerosCollectionNew);
            vuelos = em.merge(vuelos);
            for (Pasajeros pasajerosCollectionNewPasajeros : pasajerosCollectionNew) {
                if (!pasajerosCollectionOld.contains(pasajerosCollectionNewPasajeros)) {
                    Vuelos oldVuelosOfPasajerosCollectionNewPasajeros = pasajerosCollectionNewPasajeros.getVuelos();
                    pasajerosCollectionNewPasajeros.setVuelos(vuelos);
                    pasajerosCollectionNewPasajeros = em.merge(pasajerosCollectionNewPasajeros);
                    if (oldVuelosOfPasajerosCollectionNewPasajeros != null && !oldVuelosOfPasajerosCollectionNewPasajeros.equals(vuelos)) {
                        oldVuelosOfPasajerosCollectionNewPasajeros.getPasajerosCollection().remove(pasajerosCollectionNewPasajeros);
                        oldVuelosOfPasajerosCollectionNewPasajeros = em.merge(oldVuelosOfPasajerosCollectionNewPasajeros);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = vuelos.getCodVuelo();
                if (findVuelos(id) == null) {
                    throw new NonexistentEntityException("The vuelos with id " + id + " no longer exists.");
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
            Vuelos vuelos;
            try {
                vuelos = em.getReference(Vuelos.class, id);
                vuelos.getCodVuelo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vuelos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Pasajeros> pasajerosCollectionOrphanCheck = vuelos.getPasajerosCollection();
            for (Pasajeros pasajerosCollectionOrphanCheckPasajeros : pasajerosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Vuelos (" + vuelos + ") cannot be destroyed since the Pasajeros " + pasajerosCollectionOrphanCheckPasajeros + " in its pasajerosCollection field has a non-nullable vuelos field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(vuelos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vuelos> findVuelosEntities() {
        return findVuelosEntities(true, -1, -1);
    }

    public List<Vuelos> findVuelosEntities(int maxResults, int firstResult) {
        return findVuelosEntities(false, maxResults, firstResult);
    }

    private List<Vuelos> findVuelosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vuelos.class));
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

    public Vuelos findVuelos(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vuelos.class, id);
        } finally {
            em.close();
        }
    }

    public int getVuelosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vuelos> rt = cq.from(Vuelos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public void modificarVuelo(Vuelos vuelo, String atributo, String valor) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Vuelos vueloAModificar = em.find(Vuelos.class, vuelo.getCodVuelo());
        if (atributo.equals("procedencia")) {
            vueloAModificar.setProcedencia(valor);
        } else if (atributo.equals("destino")) {
            vueloAModificar.setDestino(valor);
        } else if (atributo.equals("horaSalida")) {
            vueloAModificar.setHoraSalida(valor);
        }
        em.getTransaction().commit();
        em.close();
    }
}
