package org.jpa.task1;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Set;

public class DolphinDAO {
    private static EntityManagerFactory emf;
    private static DolphinDAO instance;
    //singleton

    public static DolphinDAO getInstance(EntityManagerFactory _emf)
    {
        if(instance == null)
        {
            emf = _emf;
            instance = new DolphinDAO();
        }
        return instance;
    }

    public void create(Person person)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        }
    }

    public Person findById(int id)
    {
        EntityManager em = emf.createEntityManager();
        return em.find(Person.class, id);
    }
    //public List<Person> findAll();
    public void update(Person person)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            em.merge(person);
            em.getTransaction().commit();
        }
    }
    public long getTotalAmountPaidById(int id)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            Query q = em.createQuery("select SUM(f.amount) from Fee f join f.person p where p.id = ?1");
            q.setParameter(1,id);
            return (long) q.getSingleResult();
        }
    }

    public Set<Note> getAllNotesById(int id)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            Person p = findById(id);
            return p.getNotes();
        }
    }

    public List<NoteNameDTO> getAllNotesWithNameAge()
    {
        try(EntityManager em = emf.createEntityManager())
        {
            TypedQuery<NoteNameDTO> query = em.createQuery("select new org.jpa.task1.NoteNameDTO(n,p.name) from Note n join n.person p", NoteNameDTO.class);
            return query.getResultList();
        }
    }
}
