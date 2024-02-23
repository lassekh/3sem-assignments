package org.jpa.task4;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "package_id")
    private Package aPackage;
    @ManyToOne
    @JoinColumn(name = "source_location_id")
    private Location sourceLocation;
    @ManyToOne
    @JoinColumn(name = "destination_location_id")
    private Location destinationLocation;
    @Column(name = "shipment_date")
    private LocalDateTime shipmentDateTime;

    public Shipment(Location sourceLocation, Location destinationLocation, LocalDateTime shipmentDateTime) {
        this.sourceLocation = sourceLocation;
        this.destinationLocation = destinationLocation;
        this.shipmentDateTime = shipmentDateTime;
    }
}
