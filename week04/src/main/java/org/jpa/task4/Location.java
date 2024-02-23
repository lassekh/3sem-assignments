package org.jpa.task4;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private double latitude;
    @Column(nullable = false)
    private double longitude;
    @Column(nullable = false)
    private String address;
    @OneToMany(mappedBy = "sourceLocation", cascade = CascadeType.ALL)
    private Set<Shipment> sourceShipments;
    @OneToMany(mappedBy = "destinationLocation", cascade = CascadeType.ALL)
    private Set<Shipment> destinationShipments;

    public Location(double latitude, double longitude, String address) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }
    public void addShipments(Shipment source,Shipment destination){
        if(source != null && destination != null)
        {
            this.sourceShipments.add(source);
            this.destinationShipments.add(destination);
            source.setSourceLocation(this);
            destination.setDestinationLocation(this);
        }
    }
}
