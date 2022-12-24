package ttts.task_time_tracking_system;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FreelancerREP {
    public static void createFreelancer(Freelancer freelancer){
            RepositoryFreelancer.getRepositoryFreelancer().getFreelancer().put(freelancer.getNIF(), freelancer);
            System.out.println("Freelancer criado com sucesso!!!");

            Map<String, Freelancer> freelancers = new HashMap<>();
            //Objeto dummy para exemplificar o comportamento quando inserimos dois freelancers no ficheiro.
            //O objetivo é mostrar que ao deserializar com dois objetos ele vá ler dois, enquanto que no percurso normal
            //ele só vai buscar o primeiro do ficheiro-
            //Para mostrar o percurso normal que o registo deve ter, retirar este objeto para inserir somente 1
            Freelancer f1 = new Freelancer();
             f1.setNIF("111111111");
             f1.setName("Diogo");
             f1.setLastName("Bernardes");
             f1.setUsername("Diogo");
             f1.setPassword("123");
             f1.setBirthDate("2000-04-06");
             f1.setGener("Masculino");
             f1.setType("freelancer");
             f1.setWorkHours(0);
             freelancers.put(f1.getNIF(),f1);
             freelancers.put(freelancer.getNIF(), freelancer);

        try {
            RepositoryFreelancer.getRepositoryFreelancer().serialize(freelancers,"src\\main\\resources\\ttts\\data\\Freelancer.txt");
        }catch (IOException exIO){
            exIO.getMessage();
        }
        }
}
