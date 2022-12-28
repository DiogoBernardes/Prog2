package ttts.task_time_tracking_system;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class RepositoryAdmin implements Serializable {
    //private static final long serialVersionUID = 2992983569523313607L;
    private static RepositoryAdmin repoAdmin = null;
    private Map<String, Admin> admin = new HashMap<>();

    public RepositoryAdmin() {
    }

    public Map<String, Admin> getAdmin() throws IOException, ClassNotFoundException {
        return deserialize("src\\main\\resources\\ttts\\Data\\Admin.txt");
    }


    public static RepositoryAdmin getRepositoryAdmin() {
        if (repoAdmin == null) {
            ReentrantLock lock = new ReentrantLock();
            lock.lock();
            if (repoAdmin == null) {
                repoAdmin = new RepositoryAdmin();
            }
            lock.unlock();
        }
        return repoAdmin;
    }

    public static void serialize(Map<String, Admin> obj, String filename) throws IOException {

        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(obj);
            oos.flush();
        }
    }

    public static Map<String, Admin> deserialize(String filename) throws IOException, ClassNotFoundException {
        Map<String, Admin> result = null;
        try {
            try (FileInputStream fis = new FileInputStream(filename);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                result = (Map<String, Admin>) ois.readObject();
            }
            return result;
        } catch (EOFException e) {
            return new HashMap<>();
        }
    }
}
