package org.jpa.task2;

import jakarta.persistence.EntityManagerFactory;
import org.jpa.task2.config.HibernateConfig;
import org.jpa.task2.dao.DriverDAOImpl;
import org.jpa.task2.dao.WasteTruckDAOImpl;

import java.math.BigDecimal;

public class RecyclingExercise {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("recycling");

        DriverDAOImpl driverDAO = new DriverDAOImpl(emf);
        WasteTruckDAOImpl truckDAO = new WasteTruckDAOImpl(emf);

        driverDAO.saveDriver("Lars","Olesen", BigDecimal.valueOf(8000));
        truckDAO.saveWasteTruck("Mercedes","BW34555",2);
    }
}
