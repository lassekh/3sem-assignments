package org.app.day2.task8;

import java.util.*;

public class MemoryStorage<T> implements DataStorage<T>{
    Map<Integer, T> memoryData = new HashMap<>();
    //T simpleData;
    private static int id = 1;
    @Override
    public String store(T data) {
        memoryData.put(id,data);
        id++;
        //this.simpleData = data;
        return "Stored en memory with ID: " + id;
    }
    @Override
    public T retrieve(String source) {
        return memoryData.get(Integer.parseInt(source));
    }
}
