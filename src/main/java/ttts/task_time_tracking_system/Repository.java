package ttts.task_time_tracking_system;

import javafx.beans.value.ObservableObjectValue;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class Repository implements Serializable {

    private static Repository repo = null;


    private Map<String, Projects> project = new HashMap<>();
    private Map<String, Tasks> task = new HashMap<>();

    public Repository(){};


    public Map<String, Projects> getProject() {
        return project;
    }
    public Map<String, Tasks> getTask() {
        return task;
    }


    public static Repository getRepository(){

        ReentrantLock lock = new ReentrantLock();

        lock.lock();
        if (repo == null)
            repo = new Repository();
        lock.unlock();

        return repo;
    }

    public void serialize(String filename){

        try{
            FileOutputStream fileOut = new FileOutputStream(filename,true);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in " + filename + "\n");
        } catch(IOException ex){
            System.out.println("ErrorSerialize: " + ex.getMessage());
        }
    }

    public static void deserialize(String filename){

        try{
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            repo = (Repository) in.readObject();
            in.close();
            fileIn.close();
        } catch(IOException ex){
            System.out.println("ErrorDeserialize: " + ex.getMessage());
        } catch(ClassNotFoundException ex){
            System.out.println("Repository class not found. " + ex.getMessage());
        }
    }
}
