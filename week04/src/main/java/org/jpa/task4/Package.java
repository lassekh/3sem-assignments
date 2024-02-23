package org.jpa.task4;

import jakarta.persistence.*;
import lombok.*;
import org.jpa.task1.Fee;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;
    @Column(name = "tracking_number", nullable = false, unique = true)
    private String trackingNumber;
    @Column(name = "sender_name", nullable = false)
    private String senderName;
    @Column(name = "receiver_name", nullable = false)
    private String receiverName;
    @Column(name = "delivery_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status deliveryStatus;
    @Column(name = "created_at")
    private LocalDateTime createdTime;
    @Column(name = "updated_at")
    private LocalDateTime updatedTime;
    @OneToMany(mappedBy = "aPackage",cascade = CascadeType.PERSIST)
    private Set<Shipment> shipments;

    public Package(String trackingNumber, String senderName, String receiverName) {
        this.trackingNumber = trackingNumber;
        this.senderName = senderName;
        this.receiverName = receiverName;
    }

    public enum Status{
        PENDING,
        IN_TRANSIT,
        DELIVERED
    }
    public void addShipment(Shipment shipment)
    {
        this.shipments.add(shipment);
        if(shipment != null)
        {
            shipment.setAPackage(this);
        }
    }
    @PrePersist
    public void timeCreated(){
        this.createdTime = LocalDateTime.now();
        this.updatedTime = LocalDateTime.now();
        this.deliveryStatus = Status.PENDING;
    }
    @PreUpdate
    public void timeUpdated(){
        this.updatedTime = LocalDateTime.now();
    }
}

