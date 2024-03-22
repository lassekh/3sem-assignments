package org.rest.task3.dao;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.rest.task3.config.HibernateConfig;
import org.rest.task3.dao.IDAO;

import java.util.List;

public abstract class DAO<T, ID> implements IDAO<T, ID> {
    private static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    private Class<T> theClazz;
    public void setClazz(Class<T> aClazz)
    {
        this.theClazz = aClazz;
    }
    public List<T> getAll()
    {
        String entity = theClazz.getSimpleName();
        try(var em = emf.createEntityManager())
        {
            TypedQuery<T> query = em.createQuery("select t from " + entity + " t", theClazz);
            return query.getResultList();
        }
    }
    public T getById(ID id)
    {
        try(var em = emf.createEntityManager())
        {
            return em.find(theClazz, id);
        }
    }
    public void create(T t)
    {
        try(var em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
        }
    }
    public T update(T t)
    {
        try(var em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            T updatedObject = em.merge(t);
            em.getTransaction().commit();
            return updatedObject;
        }
    }
    public void delete(T t)
    {
        try(var em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            em.remove(t);
            em.getTransaction().commit();
        }
    }
}
