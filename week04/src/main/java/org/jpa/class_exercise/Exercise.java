package org.jpa.class_exercise;

import jakarta.persistence.EntityManagerFactory;
import org.jpa.HibernateConfig;

public class Exercise {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("exercise");
    }
}
