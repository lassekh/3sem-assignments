package org.example.task1;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class UnicornExercise {
    public static void main(String[] args) {

        //Save new unicorn
        UnicornDAO udao = new UnicornDAO();
        udao.save(new Unicorn("Lis",10,27));


        //get Unicorn by id from DB
        Unicorn u = udao.findById(4);
        System.out.println(u);

        //Update unicorn
        u.setPowerStrength(10);
        udao.update(u);

        //delete unicorn from DB by id
        udao.delete(3);

        //get all Unicorn from
        udao.findAll().forEach(System.out::println);
    }
}
