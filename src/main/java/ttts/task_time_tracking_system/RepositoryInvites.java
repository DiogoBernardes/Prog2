package ttts.task_time_tracking_system;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class RepositoryInvites implements Serializable {
    private static RepositoryInvites repoInvites = null;
    private Map<Integer, Invitation> invitation = new HashMap<>();

    public RepositoryInvites(){};

    public Map<Integer, Invitation> getInvitation() throws IOException, ClassNotFoundException {
        return deserialize("src\\main\\resources\\ttts\\Data\\Invites.txt");
    }
    public static RepositoryInvites getRepositoryInvites() {
        if (repoInvites == null) {
            ReentrantLock lock = new ReentrantLock();
            lock.lock();
            if (repoInvites == null) {
                repoInvites = new RepositoryInvites();
            }
            lock.unlock();
        }
        return repoInvites;
    }

    public static void serialize(Map<Integer,Invitation> obj, String filename) throws IOException {

        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
             oos.writeObject(obj);
             oos.flush();
        }
    }

    public static Map<Integer, Invitation> deserialize (String filename)throws IOException, ClassNotFoundException{
        Map<Integer, Invitation> result = null;
        try {
            try (FileInputStream fis = new FileInputStream(filename);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                result = (Map<Integer, Invitation>) ois.readObject();
            }
            return result;
        } catch (EOFException e) {
            return new HashMap<>();
        }
    }
}
