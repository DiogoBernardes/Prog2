package ttts.task_time_tracking_system;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FreelancerREP {
    public static void createFreelancer(Freelancer freelancer) throws ClassNotFoundException, IOException{
            RepositoryFreelancer.getRepositoryFreelancer().getFreelancer().put(freelancer.getNIF(), freelancer);
            System.out.println("Freelancer criado com sucesso!!!");
            Map<String, Freelancer> freelancers= null;
            try {
                freelancers = RepositoryFreelancer.getRepositoryFreelancer().deserialize("src\\main\\resources\\ttts\\Data\\Freelancer.txt");
                if(freelancers == null){
                    freelancers = new HashMap<>();
                }
                freelancers.put(freelancer.getNIF(), freelancer);
            }catch (IOException e){
                e.getMessage();
            }catch (ClassNotFoundException cE){
                cE.getMessage();
            }
        try {
            RepositoryFreelancer.getRepositoryFreelancer().serialize(freelancers,"src\\main\\resources\\ttts\\data\\Freelancer.txt");
        }catch (IOException exIO){
            exIO.getMessage();
        }
        }
}
