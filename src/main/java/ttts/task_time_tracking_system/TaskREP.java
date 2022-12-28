package ttts.task_time_tracking_system;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TaskREP {
    public static void createAssociateTask(Tasks tasks,Projects projects,Freelancer freelancer) throws IOException, ClassNotFoundException{
        RepositoryTasks.getRepositoryTasks().getTasks().put(tasks.getIdTask(),tasks);
        RepositoryProjects.getRepositoryProjects().getProjects().get(projects.getIdProjeto()).getTasks();
        RepositoryFreelancer.getRepositoryFreelancer().getFreelancer().get(freelancer.getNIF()).getTasks();
        System.out.println("Tarefa criada com sucesso!!");
        Map<Integer, Tasks> task = null;
        try {
            task = RepositoryTasks.getRepositoryTasks().deserialize("src\\main\\resources\\ttts\\Data\\Tasks.txt");
            if(task == null){
                task = new HashMap<>();
            }
            task.put(tasks.getIdTask(),tasks);
        }catch (IOException e){
            e.getMessage();
        }catch (ClassNotFoundException cE){
            cE.getMessage();
        }
        try {
            RepositoryTasks.getRepositoryTasks().serialize(task, "src\\main\\resources\\ttts\\Data\\Tasks.txt");
        }catch (IOException exIO){
            exIO.getMessage();
        }
    }
    public static void createNotAssociateTask(Tasks tasks,Freelancer freelancer) throws IOException, ClassNotFoundException{
        RepositoryTasks.getRepositoryTasks().getTasks().put(tasks.getIdTask(),tasks);
        RepositoryFreelancer.getRepositoryFreelancer().getFreelancer().get(freelancer.getNIF()).getTasks();
        System.out.println("Tarefa criada com sucesso!!");
        Map<Integer, Tasks> task = null;
        try {
            task = RepositoryTasks.getRepositoryTasks().deserialize("src\\main\\resources\\ttts\\Data\\Tasks.txt");
            if(task == null){
                task = new HashMap<>();
            }
            task.put(tasks.getIdTask(),tasks);
        }catch (IOException e){
            e.getMessage();
        }catch (ClassNotFoundException cE){
            cE.getMessage();
        }
        try {
            RepositoryTasks.getRepositoryTasks().serialize(task, "src\\main\\resources\\ttts\\Data\\Tasks.txt");
        }catch (IOException exIO){
            exIO.getMessage();
        }
    }
}
