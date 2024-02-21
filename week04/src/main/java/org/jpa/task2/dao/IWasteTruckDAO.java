package org.jpa.task2.dao;

import org.jpa.task2.model.Driver;
import org.jpa.task2.model.WasteTruck;

import java.util.List;

public interface IWasteTruckDAO {
    int saveWasteTruck(String brand, String registrationNumber, int capacity);
    WasteTruck getWasteTruckById(int id);
    void setWasteTruckAvailable(WasteTruck wasteTruck, boolean available);
    void deleteWasteTruck(int id);
    void addDriverToWasteTruck(WasteTruck wasteTruck, Driver driver);
    void removeDriverFromWasteTruck(WasteTruck wasteTruck, String id);
    List<WasteTruck> getAllAvailableTrucks();
}
