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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class FreelancerRemoveProjectController implements Initializable {
    @FXML
    private ComboBox selectProject;

    Projects actualProject;
    @FXML
    void backButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("freelancerMenu.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Menu Freelancer");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void removeProject(ActionEvent event) throws IOException, ClassNotFoundException{
        Map<Integer, Projects> projects = RepositoryProjects.deserialize("src\\main\\resources\\ttts\\Data\\Project.txt");
        for (Projects p : projects.values()) {
            if (p.getFreelancer().getNIF().equals(SessionData.freelancer.getNIF())) {
                projects.remove(actualProject.getIdProjeto());
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Sucesso");
                alert.setHeaderText("Dados Removidos!");
                alert.show();
            }
        }
        RepositoryProjects.getRepositoryProjects().serialize(projects,"src\\main\\resources\\ttts\\Data\\Project.txt");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
                }catch (IOException e) {
                    e.getMessage();
                } catch (ClassNotFoundException cE) {
                    cE.getMessage();
                }
            }
        });
    }
}
