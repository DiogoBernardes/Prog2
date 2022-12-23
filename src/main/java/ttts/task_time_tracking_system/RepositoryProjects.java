package ttts.task_time_tracking_system;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class RepositoryProjects implements Serializable {
    //private static final long serialVersionUID = 2992983569523313607L;
    private static RepositoryProjects repoProjects = null;
    private Map<Integer, Projects> projects = new HashMap<>();

    public RepositoryProjects(){};

    public Map<Integer, Projects> getProjects() {return projects;}


    public static RepositoryProjects getRepositoryProjects() {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        if (repoProjects == null)
            repoProjects = new RepositoryProjects();
        lock.unlock();
        return repoProjects;
    }

    public static void serialize(Projects obj, String filename) throws IOException {

        try (FileOutputStream fos = new FileOutputStream(filename,true);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(obj);
            oos.flush();
        }
    }

    public static Projects deserialize(String filename)throws IOException, ClassNotFoundException{
        Projects result = null;
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            result = (Projects) ois.readObject();
        }
        return result;
    }
}


