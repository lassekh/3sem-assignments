package org.example.task6;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class PackageDAO {
    EntityManagerFactory emf;

    public PackageDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void save(Package p)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        }
    }

    public Package getByTrackingNumber(String trackingNum)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            return em.find(Package.class,trackingNum);
        }
    }

    public void updateStatus(Package p, Package.Status s)
    {
        try(EntityManager em = emf.createEntityManager())
        {

        }
    }

    public void delete(Package p)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            em.remove(p);
        }
    }
}
