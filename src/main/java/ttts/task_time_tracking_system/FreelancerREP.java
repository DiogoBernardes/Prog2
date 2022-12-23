package ttts.task_time_tracking_system;

import java.io.IOException;

public class FreelancerREP {
    public static void createFreelancer(Freelancer freelancer){
            RepositoryFreelancer.getRepositoryFreelancer().getFreelancer().put(freelancer.getNIF(), freelancer);
            System.out.println("Freelancer criado com sucesso!!!");
        try {
            RepositoryFreelancer.getRepositoryFreelancer().serialize(freelancer,"src\\main\\resources\\ttts\\data\\Freelancer.txt");
        }catch (IOException exIO){
            exIO.getMessage();
        }
        }
}
