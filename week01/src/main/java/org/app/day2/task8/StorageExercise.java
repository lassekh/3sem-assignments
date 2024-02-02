package org.app.day2.task8;

import java.io.File;

public class StorageExercise {
    public static void main(String[] args) {
        DataStorage<String> memoryStorage1 = new MemoryStorage<>();
        String result1 = memoryStorage1.store("First stored string");
        System.out.println(result1);
        //Retrieve source
        String retrievedMemory1 = memoryStorage1.retrieve("1");
        System.out.println(retrievedMemory1);

        DataStorage<String> memoryStorage2 = new MemoryStorage<>();
        String result2 = memoryStorage2.store("Second stored string");
        System.out.println(result2);
        String retrievedMemory2 = memoryStorage2.retrieve("2");
        System.out.println(retrievedMemory2);

        DataStorage<String> fileStorage1 = new FileStorage<>();
        String filename = fileStorage1.store("First line in file");
        System.out.println(filename);
        String retrieveFile = fileStorage1.retrieve(filename);
        System.out.println(retrieveFile);


    }
}
