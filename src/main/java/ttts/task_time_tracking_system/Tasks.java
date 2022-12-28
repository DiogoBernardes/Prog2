package ttts.task_time_tracking_system;

import java.io.Serializable;

public class Tasks implements Serializable {
    private int idTask;
    private String Nome;
    private Projects projects;
    private String taskStartDate;
    private String taskEndDate;
    private String Description;
    private float priceHour;
    private TaskState state;

    public Tasks(){}; //Construtor tarefas


    public int getIdTask() {
        return idTask;
    }
    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }
    public String getNome() {
        return Nome;
    }
    public void setNome(String nome) {
        Nome = nome;
    }
    public Projects getProjects() {
        return projects;
    }
    public void setProjects(Projects projects) {
        this.projects = projects;
    }
    public String getTaskStartDate() {
        return taskStartDate;
    }
    public void setTaskStartDate(String taskStartDate) {
        this.taskStartDate = taskStartDate;
    }
    public String getTaskEndDate() {
        return taskEndDate;
    }
    public void setTaskEndDate(String taskEndDate) {
        this.taskEndDate = taskEndDate;
    }
    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }
    public float getPriceHour() {
        return priceHour;
    }
    public void setPriceHour(float priceHour) {
        this.priceHour = priceHour;
    }

    public TaskState getState() {
        return state;
    }
    public void setState(TaskState state) {
        this.state = state;
    }
}
