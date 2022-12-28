package ttts.task_time_tracking_system;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class RepositoryTasks implements Serializable{
        private static RepositoryTasks repoTasks = null;
        private Map<Integer, Tasks> Tasks = new HashMap<>();

        public RepositoryTasks(){};

        public Map<Integer, Tasks> getTasks() throws IOException, ClassNotFoundException {
            return deserialize("src\\main\\resources\\ttts\\Data\\Tasks.txt");
        }


        public static RepositoryTasks getRepositoryTasks() {
            if (repoTasks == null) {
                ReentrantLock lock = new ReentrantLock();
                lock.lock();
                if (repoTasks == null) {
                    repoTasks = new RepositoryTasks();
                }
                lock.unlock();
            }
            return repoTasks;
        }

        public static void serialize(Map<Integer,Tasks> obj, String filename) throws IOException {

            try (FileOutputStream fos = new FileOutputStream(filename);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(obj);
                oos.flush();
            }
        }

        public static Map<Integer, Tasks> deserialize (String filename)throws IOException, ClassNotFoundException{
            Map<Integer, Tasks> result = null;
            try {
                try (FileInputStream fis = new FileInputStream(filename);
                     ObjectInputStream ois = new ObjectInputStream(fis)) {
                    result = (Map<Integer, Tasks>) ois.readObject();
                }
                return result;
            } catch (EOFException e) {
                return new HashMap<>();
            }
        }
}

