package org.example.task3;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "point")
@Getter
@ToString
@NoArgsConstructor
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;
    @Column(name = "x", nullable = false)
    private int x;
    @Column(name = "y", nullable = false)
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
