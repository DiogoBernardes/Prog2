package ttts.task_time_tracking_system;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProjectREP {
    public static void createProject(Projects projects,Freelancer freelancer) throws IOException, ClassNotFoundException{
        RepositoryProjects.getRepositoryProjects().getProjects().put(projects.getIdProjeto(), projects);
        RepositoryFreelancer.getRepositoryFreelancer().getFreelancer().get(freelancer.getNIF()).getProjects();
        System.out.println("Projeto criado com sucesso!!");
        Map<Integer, Projects> project = null;
        Map<String, Freelancer> freelancers = null;
        try {
            project = RepositoryProjects.getRepositoryProjects().deserialize("src\\main\\resources\\ttts\\Data\\Project.txt");
            if(project == null){
                project = new HashMap<>();
            }
            project.put(projects.getIdProjeto(), projects);
        }catch (IOException e){
            e.getMessage();
        }catch (ClassNotFoundException cE){
            cE.getMessage();
        }
        try {
            RepositoryProjects.getRepositoryProjects().serialize(project, "src\\main\\resources\\ttts\\Data\\Project.txt");
        }catch (IOException exIO){
            exIO.getMessage();
        }
    }
}
