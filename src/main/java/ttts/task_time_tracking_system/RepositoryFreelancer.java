package ttts.task_time_tracking_system;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class RepositoryFreelancer implements Serializable {
    //private static final long serialVersionUID = 2992983569523313607L;
    private static RepositoryFreelancer repoFreelancer = null;
    private Map<String, Freelancer> freelancer = new HashMap<>();

    public RepositoryFreelancer(){};

    public Map<String, Freelancer> getFreelancer() {
        return freelancer;}



    public static RepositoryFreelancer getRepositoryFreelancer() {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        if (repoFreelancer == null)
            repoFreelancer = new RepositoryFreelancer();
        lock.unlock();
        return repoFreelancer;
    }

    public static void serialize(Freelancer obj, String filename) throws IOException {

        try (FileOutputStream fos = new FileOutputStream(filename,true);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(obj);
            oos.flush();
        }
    }

    public static Freelancer deserialize(String filename)throws IOException, ClassNotFoundException{
        Freelancer result = null;
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            result = (Freelancer) ois.readObject();
        }
        return result;
    }
}


