package org.app.day2.task8;

import java.io.*;
import java.util.Scanner;

public class FileStorage<T> implements DataStorage<T>{
    File file = new File("/Users/lassekh/Documents/Datamatiker/3-semester/3sem-assignments/week01/src/main/java/org/app/day2/task8/data.txt");
    @Override
    public String store(T data) {
        /*try(FileWriter fileWriter = new FileWriter(file,true)) {
            fileWriter.write(data.toString() + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return this.file.getName();*/

        try(FileOutputStream fos = new FileOutputStream(file,true)){
            try(ObjectOutputStream output = new ObjectOutputStream(fos)){
                output.writeObject(data);
            }
            return this.file.getAbsolutePath();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T retrieve(String source) {
        try{
            FileInputStream fis = new FileInputStream(source);
            ObjectInputStream input = new ObjectInputStream(fis);
            T data = (T) input.readObject();
            return data;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
