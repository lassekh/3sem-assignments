package org.rest.task3.dao;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.rest.task3.config.HibernateConfig;
import org.rest.task3.dao.IDAO;

import java.util.List;

public abstract class DAO<T, ID> implements IDAO<T, ID> {
    private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    private Class<T> theClazz;
    public void setClazz(Class<T> aClazz)
    {
        this.theClazz = aClazz;
    }
    public List<T> getAll()
    {
        try(var em = emf.createEntityManager())
        {
            TypedQuery<T> query = em.createQuery("select t from " + theClazz + " t", theClazz);
            return query.getResultList();
        }
    }
    public T getById(ID id)
    {

    }
    public void create(T t)
    {

    }
    public T update(T t)
    {

    }
    public void delete(T t)
    {

    }
}
