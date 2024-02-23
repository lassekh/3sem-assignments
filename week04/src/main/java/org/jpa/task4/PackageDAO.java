package org.jpa.task4;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

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
            TypedQuery<Package> query = em.createQuery("select p from Package p where trackingNumber = ?1",Package.class);
            query.setParameter(1,trackingNum);
            return query.getSingleResult();
        }
    }

    public void updateStatus(Package p, Package.Status s)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            Package pack = em.find(Package.class,p);
            pack.setDeliveryStatus(s);
            em.merge(pack);
            em.getTransaction().commit();
        }
    }

    public void delete(Package p)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
        }
    }
}

