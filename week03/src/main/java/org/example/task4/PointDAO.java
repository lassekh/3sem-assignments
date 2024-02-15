package org.example.task4;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class PointDAO {
    private int id;
    private int x;
    private int y;
    public void add1000Points(EntityManager em){
        em.getTransaction().begin();
        for (int i = 0; i < 1000; i++) {
            Point p = new Point(i, i);
            em.persist(p);
        }
        em.getTransaction().commit();
    }
    public long getTotalPoints(EntityManager em){
        Query q1 = em.createQuery("SELECT COUNT(p) FROM Point p");
        return (long) q1.getSingleResult();
    }
    public double getAvgXValue(EntityManager em){
        Query q2 = em.createQuery("SELECT AVG(p.x) FROM Point p");
        return (double) q2.getSingleResult();
    }
    public List<Point> getAllPoints(EntityManager em){
        TypedQuery<Point> query = em.createQuery("SELECT p FROM Point p", Point.class);
        List<Point> results = query.getResultList();
        return results;
    }
}
