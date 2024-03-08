package org.rest.task1;

import java.util.HashMap;
import java.util.Map;

public class DogDAO {
    private static Map<Integer, Dog> dogs = new HashMap<>(){{
        put(1,new Dog("Fido","Labrador",4));
        put(2,new Dog("Jack","Golden Retriever",1));
        put(3,new Dog("Hugo","Labrador",5));
        put(4,new Dog("Tinke","Tolder",8));
        put(5,new Dog("Luna","Labradoodle",11));
    }};
    private static DogDAO instance;
    public static DogDAO getInstance()
    {
        if(instance == null)
        {
            instance = new DogDAO();
        }
        return instance;
    }
    public Map<Integer, Dog> getAll()
    {
        return dogs;
    }
    public Dog getDogById(int id)
    {
        return dogs.get(id);
    }
    public void createDog(Dog dog)
    {
        dogs.put(dogs.size()+1, dog);
    }
}
