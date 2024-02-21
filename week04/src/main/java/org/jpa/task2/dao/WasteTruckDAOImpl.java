package org.jpa.task2.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.jpa.task2.model.Driver;
import org.jpa.task2.model.WasteTruck;

import java.util.List;

public class WasteTruckDAOImpl implements IWasteTruckDAO{
    EntityManagerFactory emf;

    public WasteTruckDAOImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public int saveWasteTruck(String brand, String registrationNumber, int capacity)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            em.persist(new WasteTruck(brand,capacity,registrationNumber));
            em.getTransaction().commit();
            return 3;
        }
    }
    public WasteTruck getWasteTruckById(int id)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            return em.find(WasteTruck.class,id);
        }
    }
    public void setWasteTruckAvailable(WasteTruck wasteTruck, boolean available)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            WasteTruck ws1 = em.find(WasteTruck.class,wasteTruck);
            ws1.setAvailable(available);
            em.merge(ws1);
            em.getTransaction().commit();
        }
    }
    public void deleteWasteTruck(int id)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            em.remove(id);
            em.getTransaction().commit();
        }
    }
    public void addDriverToWasteTruck(WasteTruck wasteTruck, Driver driver)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            WasteTruck ws1 = em.find(WasteTruck.class,wasteTruck);
            ws1.getDrivers().add(driver);
            em.merge(ws1);
            em.getTransaction().commit();
        }
    }
    public void removeDriverFromWasteTruck(WasteTruck wasteTruck, String id)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            WasteTruck ws1 = em.find(WasteTruck.class,wasteTruck);
            Driver d1 = em.find(Driver.class,id);
            ws1.getDrivers().remove(d1);
            em.merge(ws1);
            em.getTransaction().commit();
        }
    }
    public List<WasteTruck> getAllAvailableTrucks()
    {
        try(EntityManager em = emf.createEntityManager())
        {
            return em.createQuery("select t from WasteTruck t where isAvailable = true", WasteTruck.class).getResultList();
        }
    }
}
