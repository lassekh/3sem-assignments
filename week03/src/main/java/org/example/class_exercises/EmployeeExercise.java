package org.example.class_exercises;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.example.task1.HibernateConfig;

import java.util.List;

public class EmployeeExercise {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

        //Write a JPQL query to select all employees.
        try(EntityManager em = emf.createEntityManager()){
            TypedQuery<Employee> query = em.createNamedQuery("Employee.getAll", Employee.class);
            List<Employee> allEmployees = query.getResultList();
            for(Employee e : allEmployees){
                System.out.println(e.getFirstName() + " " + e.getLastName());
            }

        }


        //Write a JPQL query to select employees with a salary greater than a certain value.
        try(EntityManager em = emf.createEntityManager()){
            TypedQuery<Employee> query = em.createQuery("select e from Employee e where e.salary > 60000", Employee.class);
            List<Employee> employeesSalaryGreaterThan60000 = query.getResultList();
            for (Employee e : employeesSalaryGreaterThan60000){
                System.out.println(e.getFirstName() + " " + e.getLastName() + ": " + e.getSalary());
            }
        }

        //Write a JPQL query to select employees from a specific department.
        try(EntityManager em = emf.createEntityManager()){
            TypedQuery<Employee> query = em.createQuery("select e from Employee e where e.department = 'HR'", Employee.class);
            List<Employee> employeesByDepartment = query.getResultList();
            for (Employee e : employeesByDepartment){
                System.out.println(e.getFirstName() + " " + e.getLastName() + ": " + e.getDepartment());
            }
        }


        //Write a JPQL query to select employees whose first name starts with a certain letter.
        try(EntityManager em = emf.createEntityManager()){
            TypedQuery<Employee> query = em.createQuery("select e from Employee e where e.firstName like 'J%'", Employee.class);
            List<Employee> employeesStartWith = query.getResultList();
            for (Employee e : employeesStartWith){
                System.out.println(e.getFirstName());
            }
        }
        //Write a JPQL query to update the salary of an employee using a named parameter.
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.createQuery("update Employee e set e.salary = 80000 where e.salary >= 70000").executeUpdate();
            em.getTransaction().commit();
        }
        //Write a JPQL query to update the department of an employee using positional parameters.
        String department = "Branding";
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            Query query = em.createQuery("update Employee e set e.department = ?1 where e.id = 10");
            query.setParameter(1,department);
            query.executeUpdate();
            em.getTransaction().commit();
        }
        //Write a JPQL query to calculate the average salary of all employees.
        try(EntityManager em = emf.createEntityManager()){
            Query query = em.createQuery("select AVG(e.salary) from Employee e");
            double avgSalary = (double) query.getSingleResult();
            System.out.println(avgSalary);
        }
        //Write a JPQL query to calculate the total salary of all employees.
        try(EntityManager em = emf.createEntityManager()){
            Query query = em.createQuery("select SUM(e.salary) from Employee e");
            long totalSalary = (long) query.getSingleResult();
            System.out.println(totalSalary);
        }
    }
}
