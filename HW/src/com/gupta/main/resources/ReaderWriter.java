package com.gupta.main.resources;

import com.gupta.main.java.Survey;
import com.gupta.main.java.Test;
import com.gupta.main.resources.inputOutput.Output;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReaderWriter implements Serializable {
    private static final long serialVersionUID = 6216604663640880744L;

    public void delete(String fileName) throws IOException{
        try{
            Files.deleteIfExists(Paths.get((fileName+".dat")));
        }
        catch(NoSuchFileException e){new Output().display(e);
        }
    }

    public boolean exits(String fileName) throws IOException{
        boolean counter = false;
        try{
            counter =  Files.exists(Paths.get((fileName+".dat")));
        }
        catch(Exception e){new Output().display(e);}

        return counter;
    }

    public void write(Survey survey, String name) throws IOException {
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(name)))){
            objectOutputStream.writeObject(survey);
        }
        catch (IOException e){
            new Output().display(e);
        }
    }

    public void write(Test test, String name) throws IOException {
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(name)))){
            objectOutputStream.writeObject(test);
        }
        catch (IOException e){
            new Output().display(e);
        }
    }

    public Survey read(String name, String type) throws IOException{
        Object object = null;
        boolean counter = true;
        while(counter){
            try(ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(Paths.get(name)))){
                object = objectInputStream.readObject();
                counter = false;
            }
            catch(IOException | ClassNotFoundException e){
                new Output().display(name + " File not found");
                return null;
            }
        }
        if (type.equalsIgnoreCase("survey")) return (Survey) object;
        else if (type.equalsIgnoreCase("test")) return (Test) object;

        return null;
    }

    public List<String> listAllFiles(){
        List<String> finalFileNames = new ArrayList<>();

        File curDir = new File(".");
        File[] files = curDir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".dat");
            }
        });

        List<File> listOfFiles = Arrays.asList(files);
        for (File file: listOfFiles){
            String fileName = file.getName();
            finalFileNames.add(fileName.replaceAll(".dat", ""));
        }

        return finalFileNames;
    }

    public Integer getNumber(String find){
        List<String> finalFileNames = new ArrayList<>();
        Integer count = 1;
        File curDir = new File(".");
        File[] files = curDir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".dat");
            }
        });

        List<File> listOfFiles = Arrays.asList(files);
        for (File file: listOfFiles){
            String fileName = file.getName();
            if (fileName.contains(find)) count++;
        }

        return count;
    }


}
