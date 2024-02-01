package org.app.day2.task8;

import java.util.Map;

public class MemoryStorage<T> implements DataStorage<T>{
    private Map<Integer, T> data;
    private T simpleData;
    private static int id = 1;
    @Override
    public String store(T data) {
        //this.data.put(id++,data);
        this.simpleData = data;
        return "Stored en memory with ID: " + id++;
    }
    @Override
    public T retrieve(String source) {;
        return simpleData;
    }
}
