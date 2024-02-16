package org.example.task5;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.task1.HibernateConfig;
import org.example.task2.Student;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class StudentDAO implements DAO{
    private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    public void create(Student student){
        //student is in transient state
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            //student is in managed state
            em.persist(student);
            em.getTransaction().commit();
            //student is in detached state
        }
    }
    //This method should read a student from the database using the student's id.
    public Student read(int id){
        try(EntityManager em = emf.createEntityManager()) {
            return em.find(Student.class, id);
        }
    }
    //This method should update an existing student in the database.
    public Student update(Student updStd){
        //updStd is in transient state
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            //updStd is in managed state
            Student s = em.merge(updStd);
            em.getTransaction().commit();
            //updStd is in detached state
            return s;
        }
    }
    //This method should delete a student from the database using the student's id.
    public void delete(int id){
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Student s = em.find(Student.class,1);
            em.remove(s);
            em.getTransaction().commit();
        }
    }
    //This method should retrieve all students from the database and return them as a list. Use a TypedQuery to retrieve all students.
    public List<Student> readAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Student> query = em.createQuery("select s from Student s", Student.class);
        return query.getResultList();
    }
}
