package ttts.task_time_tracking_system;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class RepositoryAdmin implements Serializable {
    //private static final long serialVersionUID = 2992983569523313607L;
        private static RepositoryAdmin repoAdmin = null;
        private Map<String, Admin> admin = new HashMap<>();

        public RepositoryAdmin(){};

        public Map<String, Admin> getAdmin() {
            return admin;}



        public static RepositoryAdmin getRepositoryAdmin() {
            RepositoryAdmin test;
            ReentrantLock lock = new ReentrantLock();

            lock.lock();
            if (repoAdmin == null)
                repoAdmin = new RepositoryAdmin();
            lock.unlock();
            return repoAdmin;
        }

        public static void serialize(Admin obj, String filename) throws IOException {

            try (FileOutputStream fos = new FileOutputStream(filename,true);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(obj);
                oos.flush();
            }
        }

        public static Admin deserialize(String filename)throws IOException, ClassNotFoundException{
            Admin result = null;
            try (FileInputStream fis = new FileInputStream(filename);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                result = (Admin) ois.readObject();
            }
            return result;
        }
    }


