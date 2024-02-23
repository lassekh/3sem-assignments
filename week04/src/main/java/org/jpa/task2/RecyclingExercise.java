package org.jpa.task2;

import jakarta.persistence.EntityManagerFactory;
import org.jpa.task2.config.HibernateConfig;
import org.jpa.task2.dao.DriverDAOImpl;
import org.jpa.task2.dao.WasteTruckDAOImpl;
import org.jpa.task2.model.Driver;

import java.math.BigDecimal;

public class RecyclingExercise {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("recycling");

        DriverDAOImpl driverDAO = new DriverDAOImpl(emf);
        WasteTruckDAOImpl truckDAO = new WasteTruckDAOImpl(emf);

        //generateDriversAndTrucks(driverDAO,truckDAO);

        //System.out.println(driverDAO.getDriverById("220224-LL-612N"));
        //@MapsId i Driver class
        driverDAO.deleteDriver("220224-JK-259N");
        System.out.println(driverDAO.calculateNumberOfDrivers());
        driverDAO.fetchAllDriversWithSalaryGreaterThan10000().forEach(System.out::println);
        Driver benjamin = driverDAO.getDriverById("220224-BJ-798N");
        benjamin.setSalary(BigDecimal.valueOf(18000));
        System.out.println(driverDAO.updateDriver(benjamin));
        System.out.println(driverDAO.fetchDriverWithHighestSalary());
        driverDAO.fetchFirstNameOfAllDrivers().forEach(System.out::println);
        System.out.println(driverDAO.fetchHighestSalary());
        driverDAO.findAllDriversEmployedAtTheSameYear("2024").forEach(System.out::println);
    }

    public static void generateDriversAndTrucks(DriverDAOImpl driverDAO, WasteTruckDAOImpl truckDAO){
        //Generate drivers
        driverDAO.saveDriver("Lars","Larsen", BigDecimal.valueOf(8800));
        driverDAO.saveDriver("James","Abild", BigDecimal.valueOf(9000));
        driverDAO.saveDriver("Carl","Olsen", BigDecimal.valueOf(10000));
        driverDAO.saveDriver("Jonas","Larsen", BigDecimal.valueOf(9800));
        driverDAO.saveDriver("Josef","Kristensen", BigDecimal.valueOf(10200));
        driverDAO.saveDriver("Maria","Petersen", BigDecimal.valueOf(11000));
        driverDAO.saveDriver("Benjamin","Jensen", BigDecimal.valueOf(15000));
        driverDAO.saveDriver("Lau","Andersen", BigDecimal.valueOf(15000));
        driverDAO.saveDriver("Emma","Gabriel", BigDecimal.valueOf(12000));
        driverDAO.saveDriver("Kurt","Thomsen", BigDecimal.valueOf(7500));

        //Generate trucks
        truckDAO.saveWasteTruck("Mercedes","QR34555",300);
        truckDAO.saveWasteTruck("Mercedes","HH23411",1200);
        truckDAO.saveWasteTruck("Mercedes","OO55332",2000);
        truckDAO.saveWasteTruck("BMW","PP34123",200);
        truckDAO.saveWasteTruck("BMW","DD32412",200);
        truckDAO.saveWasteTruck("Trucksy","SS13452",400);
        truckDAO.saveWasteTruck("Trucksy","CC23422",800);
        truckDAO.saveWasteTruck("Mercedes","RT23411",1000);
        truckDAO.saveWasteTruck("Mercedes","ER45577",1000);
        truckDAO.saveWasteTruck("Mercedes","DR44444",1000);
    }
}
