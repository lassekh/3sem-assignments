package org.example.task1;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class UnicornExercise {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        EntityManager em = emf.createEntityManager();

        //Save new unicorn
        UnicornDAO udao = new UnicornDAO(em);
        //udao.save(new Unicorn("Peter",30,567));
        udao.delete(1);
    }
}
