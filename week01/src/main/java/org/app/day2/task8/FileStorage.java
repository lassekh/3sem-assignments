package org.app.day2.task8;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileStorage<T> implements DataStorage<T>{
    private File file = new File("data.txt");
    private FileWriter fileWriter;

    @Override
    public String store(T data) {
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return this.file.getName();
    }

    @Override
    public T retrieve(String source) {
        return null;
    }
}
