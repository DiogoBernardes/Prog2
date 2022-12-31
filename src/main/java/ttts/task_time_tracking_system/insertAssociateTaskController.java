package ttts.task_time_tracking_system;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class insertAssociateTaskController implements Initializable {
    @FXML
    private ComboBox selectProject;
    @FXML
    private TextField nameTask;
    @FXML
    private TextField descriptionTask;

    Projects actualProject;

    @FXML
    void createTask(ActionEvent event){
        Date today = new Date();
        try {
            Tasks t1 = new Tasks();
            t1.setIdTask(RepositoryTasks.getRepositoryTasks().getTasks().size() + 1);
            t1.setName(nameTask.getText());
            t1.setDescription(descriptionTask.getText());
            t1.setProjects(actualProject);
            t1.setState(TaskState.CONFIRMADO);
            t1.setPriceHour(actualProject.getPriceHour());
            t1.setTaskStartDate(today.toString());
            t1.setFreelancer(SessionData.freelancer);
            TaskREP.createAssociateTask(t1, actualProject, SessionData.freelancer);
            Alert alertDatInv = new Alert(Alert.AlertType.INFORMATION);
            alertDatInv.setTitle("Registado");
            alertDatInv.setHeaderText("Tarefa Registada com sucesso!");
            alertDatInv.show();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("FreelancerMenu.fxml"));
                Scene regCena = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(regCena);
                stage.setTitle("Menu Freelancer");
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void backMenu(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FreelancerMenu.fxml"));
            Scene regCena = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Menu Freelancer");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url , ResourceBundle resourceBundle){
        try {
            for (Projects p : RepositoryProjects.getRepositoryProjects().getProjects().values()) {
                if(SessionData.freelancer.getNIF().equals(p.getFreelancer().getNIF())) {
                    selectProject.getItems().addAll(p.getName());
                }
            } }catch (IOException e){
            e.getMessage();
        }catch (ClassNotFoundException CE){
            CE.getMessage();
        }
        selectProject.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                try {
                    for (Projects p : RepositoryProjects.getRepositoryProjects().getProjects().values()) {
                        if (p.getName().equals(selectProject.getSelectionModel().getSelectedItem()))
                            actualProject = p;
                    }
                } catch (IOException e) {
                    e.getMessage();
                } catch (ClassNotFoundException cE) {
                    cE.getMessage();
                }
            }
        });
    }
}