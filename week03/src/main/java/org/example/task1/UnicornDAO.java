package org.example.task1;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UnicornDAO {
    private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    public void save(Unicorn u){
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
        }
    }
    public void update(Unicorn u){
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(u);
            em.getTransaction().commit();
        }
    }
    public void delete(int id){
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Unicorn u = findById(id);
            em.remove(u);
            em.getTransaction().commit();
        }
    }
    public Unicorn findById(int id){
        try(EntityManager em = emf.createEntityManager()) {
            return em.find(Unicorn.class, id);
        }
    }
    public List<Unicorn> findAll(){
        try(EntityManager em = emf.createEntityManager()) {
            TypedQuery query = em.createQuery("select u from Unicorn u", Unicorn.class);
            return query.getResultList();
        }
    }
}
