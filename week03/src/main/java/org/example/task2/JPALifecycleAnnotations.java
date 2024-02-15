package org.example.task2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.example.task1.HibernateConfig;

import java.util.List;

public class JPALifecycleAnnotations {
    private static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    public static void main(String[] args) {

        Student student = new Student("Lasse","Kjær","lasses@email.dk",31);
        createStudent(student);

        //Find student with id = 1 in DB - and print
        System.out.println(readStudent(1));

        //Update student with by object
        Student updatedStudent = new Student("Lasse","Hauerberg","lassesemail.com",31);
        updateStudent(updatedStudent);

        //deleteStudent(1);


    }
    //This method should create a new student and persist it to the database.
    public static void createStudent(Student student){
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
    public static Student readStudent(int id){
        try(EntityManager em = emf.createEntityManager()) {
            return em.find(Student.class, id);
        }
    }
    //This method should update an existing student in the database.
    public static Student updateStudent(Student updStd){
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
    public static void deleteStudent(int id){
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Student s = em.find(Student.class,1);
            em.remove(s);
            em.getTransaction().commit();
        }
    }
    //This method should retrieve all students from the database and return them as a list. Use a TypedQuery to retrieve all students.
    public static List<Student> readAllStudents(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Student> query = em.createQuery("select s from Student s", Student.class);
        return query.getResultList();
    }

}
