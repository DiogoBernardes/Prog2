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

    public Map<Integer, Projects> getProjects() throws IOException, ClassNotFoundException {
        return deserialize("src\\main\\resources\\ttts\\Data\\Project.txt");
    }


    public static RepositoryProjects getRepositoryProjects() {
        if (repoProjects == null) {
            ReentrantLock lock = new ReentrantLock();
            lock.lock();
            if (repoProjects == null) {
                repoProjects = new RepositoryProjects();
            }
            lock.unlock();
        }
        return repoProjects;
    }

    public static void serialize(Map<Integer,Projects> obj, String filename) throws IOException {

        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(obj);
            oos.flush();
        }
    }

    public static Map<Integer, Projects> deserialize (String filename)throws IOException, ClassNotFoundException{
        Map<Integer, Projects> result = null;
        try {
            try (FileInputStream fis = new FileInputStream(filename);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                result = (Map<Integer, Projects>) ois.readObject();
            }
            return result;
        } catch (EOFException e) {
            return new HashMap<>();
        }
    }
}


