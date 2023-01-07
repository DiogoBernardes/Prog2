package ttts.task_time_tracking_system;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Invitation implements Serializable {
    private int idInvite;
    private Freelancer owner;
    private Freelancer guest;
    private Projects project;
    private boolean answer;

    public Invitation(Freelancer owner,Freelancer guest, Projects project){
        this.idInvite += 1;
        this.owner = owner;
        this.guest = guest;
        this.project = project;
        this.answer = false;
    }

    public int getIdInvite() {
        return idInvite;
    }
    public void setIdInvite(int idInvite) {
        this.idInvite = idInvite;
    }
    public Freelancer getOwner() {
        return owner;
    }
    public void setOwner(Freelancer owner) {
        this.owner = owner;
    }
    public Freelancer getGuest() {
        return guest;
    }
    public void setGuest(Freelancer guest) {
        this.guest = guest;
    }
    public Projects getProject() {
        return project;
    }
    public void setProject(Projects project) {
        this.project = project;
    }
    public boolean isAnswer() {
        return answer;
    }
    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

}
