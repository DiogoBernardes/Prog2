package ttts.task_time_tracking_system;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import ttts.task_time_tracking_system.Invitation;
import ttts.task_time_tracking_system.Freelancer;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FreelancerGuestInvite implements Initializable {
    @FXML
    private ComboBox selectFreelancer;
    @FXML
    private ComboBox selectProject;
    Freelancer actualGuest;
    Projects actualProject;

    @FXML
    public void invite(javafx.event.ActionEvent event) {
        Freelancer owner = SessionData.freelancer;
        Freelancer guest = actualGuest;
        Projects projects = actualProject;
        System.out.println(guest.getName());
        System.out.println(projects.getName());
        owner.enviarConvite(guest,projects);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            for(Freelancer f : RepositoryFreelancer.getRepositoryFreelancer().getFreelancer().values()){
                selectFreelancer.getItems().addAll(f.getName());
            }
        }catch (IOException IE){
            IE.getMessage();
        }catch (ClassNotFoundException CE){
            CE.getMessage();
        }
        selectFreelancer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                try {
                    for(Freelancer f : RepositoryFreelancer.getRepositoryFreelancer().getFreelancer().values()){
                        if (f.getName().equals(selectFreelancer.getSelectionModel().getSelectedItem()))
                            actualGuest = f;
                    }
                }catch (IOException e) {
                    e.getMessage();
                } catch (ClassNotFoundException cE) {
                    cE.getMessage();
                }

            }
        });
       // ---------------------------
        try{
            for(Projects p : RepositoryProjects.getRepositoryProjects().getProjects().values()){
                selectProject.getItems().addAll(p.getName());
            }
        }catch (IOException IE){
            IE.getMessage();
        }catch (ClassNotFoundException CE){
            CE.getMessage();
        }
        selectProject.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                try {
                    for(Projects p : RepositoryProjects.getRepositoryProjects().getProjects().values()){
                        if (p.getName().equals(selectProject.getSelectionModel().getSelectedItem()))
                            actualProject = p;
                    }
                }catch (IOException e) {
                    e.getMessage();
                } catch (ClassNotFoundException cE) {
                    cE.getMessage();
                }

            }
        });
    }
    @FXML
    void backButton(javafx.event.ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("tasksMenu.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Menu Tarefas");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
