package com.cognizant.testing.service;

import com.cognizant.testing.util.FileReader;
import com.cognizant.testing.util.FileWriter;

/**
 * FileService – plain service class used in Advanced Mockito Exercise 3.
 */
public class FileService {

    private final FileReader fileReader;
    private final FileWriter fileWriter;

    public FileService(FileReader fileReader, FileWriter fileWriter) {
        this.fileReader = fileReader;
        this.fileWriter = fileWriter;
    }

    public String processFile() {
        String content = fileReader.read();
        String result = "Processed " + content;
        fileWriter.write(result);
        return result;
    }
}
