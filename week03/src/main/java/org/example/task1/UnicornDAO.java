package org.example.task1;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class UnicornDAO {
    private EntityManager em;
    public UnicornDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Unicorn u){
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
        em.close();
    }
    public void update(Unicorn u){
        em.getTransaction().begin();
        em.merge(u);
        em.getTransaction().commit();
        em.close();
    }
    public void delete(int id){
        em.remove(id);
    }
    public Unicorn findById(int id){
        return em.find(Unicorn.class,id);
    }
}
