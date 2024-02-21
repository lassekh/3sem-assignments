package org.jpa.task1;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "person")
public class Fee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int amount;
    @Column(name = "pay_date")
    private LocalDate payDate;
    @ManyToOne
    private Person person;

    public Fee(int amount, LocalDate payDate) {
        this.amount = amount;
        this.payDate = payDate;
    }
}
