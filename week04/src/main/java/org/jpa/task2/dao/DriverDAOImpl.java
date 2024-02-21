package org.jpa.task2.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.jpa.task1.Person;
import org.jpa.task2.model.Driver;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class DriverDAOImpl implements IDriverDAO{
    EntityManagerFactory emf;

    public DriverDAOImpl(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    public String saveDriver(String name, String surname, BigDecimal salary)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            em.persist(new Driver(name,surname,salary));
            em.getTransaction().commit();
            return "Driver with " + name + " persisted";
        }
    }
    public Driver getDriverById(String id)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            return em.find(Driver.class,id);
        }
    }
    public Driver updateDriver(Driver driver)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            Driver updDriver = em.merge(driver);
            em.getTransaction().commit();
            return updDriver;
        }
    }
    public void deleteDriver(String id)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            em.remove(id);
            em.getTransaction().commit();
        }
    }
    public List<Driver> findAllDriversEmployedAtTheSameYear(String year)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            //TypedQuery<Driver> q = em.createQuery("select d from Driver d where d.employmentDate between ?1 and ?2",Driver.class);
            TypedQuery<Driver> q2 = em.createQuery("select d from Driver d where extract(year from d.employmentDate) = ?1",Driver.class);
            q2.setParameter(1,year);
            //q.setParameter(1, LocalDate.of(Integer.parseInt(year), 1,1));
            //q.setParameter(1, LocalDate.of(Integer.parseInt(year), 12,31));
            return q2.getResultList();
        }
    }
    public List<Driver> fetchAllDriversWithSalaryGreaterThan10000()
    {
        try(EntityManager em = emf.createEntityManager())
        {
            TypedQuery<Driver> query = em.createQuery("select d from Driver d where d.salary > 10000", Driver.class);
            return query.getResultList();
        }
    }
    public double fetchHighestSalary()
    {
        try(EntityManager em = emf.createEntityManager())
        {
            return (double) em.createQuery("select MAX(d.salary) from Driver d").getSingleResult();
        }
    }
    public List<String> fetchFirstNameOfAllDrivers()
    {
        try(EntityManager em = emf.createEntityManager())
        {
            return em.createQuery("select d.name from Driver d",String.class).getResultList();
        }
    }
    public long calculateNumberOfDrivers()
    {
        try(EntityManager em = emf.createEntityManager())
        {
            return (long) em.createQuery("select count(d) from Driver d").getSingleResult();
        }
    }
    public Driver fetchDriverWithHighestSalary()
    {
        try(EntityManager em = emf.createEntityManager())
        {
            return em.createQuery("select d, max(d.salary) from Driver d",Driver.class).getSingleResult();
        }
    }
}
