package org.rest.task2;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Patient {
    private String name;
    private String allergy;
    private String medication;
    private int numberOfVisits;
}
