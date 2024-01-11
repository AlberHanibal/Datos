/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenjpavueloscolmenaralberto;

import com.mycompany.mavenjpavueloscolmenaralberto.exceptions.NonexistentEntityException;
import com.mycompany.mavenjpavueloscolmenaralberto.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author DAM2A-03
 */
public class PasajerosJpaController implements Serializable {

    public PasajerosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public PasajerosJpaController() {
        emf = Persistence.createEntityManagerFactory("com.mycompany.mavenJpaVuelosColmenarAlbertoPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pasajeros pasajeros) throws PreexistingEntityException, Exception {
        if (pasajeros.getPasajerosPK() == null) {
            pasajeros.setPasajerosPK(new PasajerosPK());
        }
        pasajeros.getPasajerosPK().setCodVuelo(pasajeros.getVuelos().getCodVuelo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vuelos vuelos = pasajeros.getVuelos();
            if (vuelos != null) {
                vuelos = em.getReference(vuelos.getClass(), vuelos.getCodVuelo());
                pasajeros.setVuelos(vuelos);
            }
            em.persist(pasajeros);
            if (vuelos != null) {
                vuelos.getPasajerosCollection().add(pasajeros);
                vuelos = em.merge(vuelos);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPasajeros(pasajeros.getPasajerosPK()) != null) {
                throw new PreexistingEntityException("Pasajeros " + pasajeros + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pasajeros pasajeros) throws NonexistentEntityException, Exception {
        pasajeros.getPasajerosPK().setCodVuelo(pasajeros.getVuelos().getCodVuelo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pasajeros persistentPasajeros = em.find(Pasajeros.class, pasajeros.getPasajerosPK());
            Vuelos vuelosOld = persistentPasajeros.getVuelos();
            Vuelos vuelosNew = pasajeros.getVuelos();
            if (vuelosNew != null) {
                vuelosNew = em.getReference(vuelosNew.getClass(), vuelosNew.getCodVuelo());
                pasajeros.setVuelos(vuelosNew);
            }
            pasajeros = em.merge(pasajeros);
            if (vuelosOld != null && !vuelosOld.equals(vuelosNew)) {
                vuelosOld.getPasajerosCollection().remove(pasajeros);
                vuelosOld = em.merge(vuelosOld);
            }
            if (vuelosNew != null && !vuelosNew.equals(vuelosOld)) {
                vuelosNew.getPasajerosCollection().add(pasajeros);
                vuelosNew = em.merge(vuelosNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PasajerosPK id = pasajeros.getPasajerosPK();
                if (findPasajeros(id) == null) {
                    throw new NonexistentEntityException("The pasajeros with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PasajerosPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pasajeros pasajeros;
            try {
                pasajeros = em.getReference(Pasajeros.class, id);
                pasajeros.getPasajerosPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pasajeros with id " + id + " no longer exists.", enfe);
            }
            Vuelos vuelos = pasajeros.getVuelos();
            if (vuelos != null) {
                vuelos.getPasajerosCollection().remove(pasajeros);
                vuelos = em.merge(vuelos);
            }
            em.remove(pasajeros);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pasajeros> findPasajerosEntities() {
        return findPasajerosEntities(true, -1, -1);
    }

    public List<Pasajeros> findPasajerosEntities(int maxResults, int firstResult) {
        return findPasajerosEntities(false, maxResults, firstResult);
    }

    private List<Pasajeros> findPasajerosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pasajeros.class));
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

    public Pasajeros findPasajeros(PasajerosPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pasajeros.class, id);
        } finally {
            em.close();
        }
    }

    public int getPasajerosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pasajeros> rt = cq.from(Pasajeros.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
