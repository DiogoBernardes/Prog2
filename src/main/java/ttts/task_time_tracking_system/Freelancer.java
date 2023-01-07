package ttts.task_time_tracking_system;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Freelancer implements Serializable {

    private static final long serialVersionUID = 2992983569523313607L;
        private String name;
        private String lastName;
        private String birthDate;
        private String gener;
        private String username;
        private String password;
        private String NIF;
        private String type;

        private float workHours;
        private List<Projects> projects;
        private List<Tasks> tasks;
        private List<Invitation> invitations;

        public Freelancer(){this.projects = new ArrayList<>();} //Construtor

        public List<Projects> getProjects(){return projects;} //getter dos projetos
        public void setProjects(List<Projects> projects) {
        this.projects = projects;
    }
        public List<Tasks> getTasks() {
        return tasks;
    }
        public void setTasks(List<Tasks> tasks) {
        this.tasks = tasks;
    }
        public List<Invitation> getInvitations() {
        return invitations;
    }
        public void setInvitations(List<Invitation> invitations) {
        this.invitations = invitations;
    }

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getLastName() {
            return lastName;
        }
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
        public String getBirthDate() {
            return birthDate;
        }
        public void setBirthDate(String birthDate) {
            this.birthDate = birthDate;
        }
        public String getGener() {
            return gener;
        }
        public void setGener(String gener) {
            this.gener = gener;
        }
        public String getUsername() {
            return username;
        }
        public void setUsername(String username) {
            this.username = username;
        }
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }
        public String getNIF() {
            return NIF;
        }
        public void setNIF(String NIF) {
            this.NIF = NIF;
        }
        public String getType() {
            return type;
        }
        public void setType(String type) {
            this.type = type;
        }
        public float getWorkHours() {
        return workHours;
    }
        public void setWorkHours(float workHours) {
        this.workHours = workHours;
    }

    public void enviarConvite(Freelancer guest, Projects project){
            Invitation invite = new Invitation(this,guest,project);
            try {
                InvitationREP.inviteGuestProject(invite);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Sucesso");
                alert.setHeaderText("Convite Enviado!");
                alert.show();
            }catch (IOException IE){
                IE.getMessage();
            }catch (ClassNotFoundException CE){
                CE.getMessage();
            }
    }
}



