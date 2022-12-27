package ttts.task_time_tracking_system;

import java.io.IOException;

public class ProjectREP {
    public static void createProject(Projects projects,Freelancer freelancer) throws IOException, ClassNotFoundException{
        RepositoryProjects.getRepositoryProjects().getProjects().put(projects.getIdProjeto(), projects);
        RepositoryFreelancer.getRepositoryFreelancer().getFreelancer().get(freelancer.getNIF()).getProjects();
        System.out.println("Projeto criado com sucesso!!");
        try {
            RepositoryProjects.getRepositoryProjects().serialize(projects, "src\\main\\resources\\ttts\\Data\\Project.txt");
        }catch (IOException exIO){
            exIO.getMessage();
        }
    }
}
