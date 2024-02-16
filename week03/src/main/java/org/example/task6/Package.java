package org.example.task6;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "package")
@NoArgsConstructor
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
    private Status deliveryStatus;
    @Column(name = "createdAt")
    private LocalDateTime createdTime;
    @Column(name = "updatedAt")
    private LocalDateTime updatedTime;
    enum Status{
        PENDING,
        IN_TRANSIT,
        DELIVERED
    }
    @PrePersist
    public void timeCreated(){
        this.createdTime = LocalDateTime.now();
        this.updatedTime = LocalDateTime.now();
    }
    @PreUpdate
    public void timeUpdated(){
        this.createdTime = LocalDateTime.now();
    }
}
