package org.jpa.task1;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.jpa.HibernateConfig;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class DolphinRelationExercise {
    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("dolphin");


        Person lasse = new Person("Lasse");
        PersonalDetails lasseDetails = new PersonalDetails("SMV 3D",3460,"Birkerød",31);
        lasse.addPersonalDetails(lasseDetails);

        Fee fee1 = new Fee(1199, LocalDate.of(2024,2,2));
        Fee fee2 = new Fee(899, LocalDate.of(2024,1,20));
        lasse.addFee(fee1);
        lasse.addFee(fee2);

        Note note1 = new Note("This person is important", LocalDate.now());
        Note note2 = new Note("Add extra fee to person", LocalDate.now());
        lasse.addNote(note1);
        lasse.addNote(note2);

        DolphinDAO dao = new DolphinDAO(emf);
        dao.create(lasse);
        lasse.getPersonalDetails().setAge(40);
        dao.update(lasse);

        System.out.println(dao.getTotalAmountPaidById(lasse.getId()));

        dao.getAllNotesById(lasse.getId()).forEach(System.out::println);

        dao.getAllNotesWithNameAge().forEach(System.out::println);


    }
}
