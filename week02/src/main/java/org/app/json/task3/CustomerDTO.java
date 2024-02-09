package org.app.json.task3;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerDTO {
    private String fullName;
    private String city;
    private int zipCode;
    private boolean isActive;

    public CustomerDTO() {
    }

    public CustomerDTO(String fullName, String city, int zipCode, boolean isActive) {
        this.fullName = fullName;
        this.city = city;
        this.zipCode = zipCode;
        this.isActive = isActive;
    }
}

