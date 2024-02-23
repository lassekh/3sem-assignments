package org.jpa.task4;

import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDateTime;

public class GLSDelivery {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("gls_delivery");
        Location l1 = new Location(11,22,"Lyngby");
        Location l2 = new Location(22,33,"Odense");
        Location l3 = new Location(33,44,"Aarhus");
        Shipment s1 = new Shipment(l1,l2, LocalDateTime.now());
        Shipment s2 = new Shipment(l2,l3,LocalDateTime.now());
        Package p1 = new Package("ERD234","Lasse","Rasmus");
        Package p2 = new Package("RTY123","Peter","Gudrun");
        Package p3 = new Package("POI321","Carsten","Lonni");
        Package p4 = new Package("LKJ456","Molly","Ida");
        Package p5 = new Package("GHJ098","Susanne","Henrik");

        PackageDAO dao = new PackageDAO(emf);
        //dao.save(p1);
        Package myPackage = dao.getByTrackingNumber("ERD234");
        myPackage.addShipment(s1);

    }
}
