package org.app.json.task3;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Customer {
    private String firstName;
    private String lastName;
    private String birthDate;
    private Address address;
    private Account account;
}
