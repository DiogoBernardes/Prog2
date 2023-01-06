package ttts.task_time_tracking_system;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class FreelancerListTasksProjectController implements Initializable {

    @FXML
    private ComboBox selectProject;
    @FXML
    private TextArea listTask;

    @FXML
    void backButton(ActionEvent event) {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listTask.setEditable(false);
        try {
            for (Projects p : RepositoryProjects.getRepositoryProjects().getProjects().values()) {
                if(SessionData.freelancer.getNIF().equals(p.getFreelancer().getNIF())) {
                    if(!p.getTasks().equals(null)){
                    selectProject.getItems().addAll(p.getName());
                }
                }
            }
        }catch (IOException e){
            e.getMessage();
        }catch (ClassNotFoundException CE){
            CE.getMessage();
        }
        selectProject.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                try {
                    for (Projects p : RepositoryProjects.getRepositoryProjects().getProjects().values()) {
                        if (p.getName().equals(selectProject.getSelectionModel().getSelectedItem())){
                            Map<Integer, Tasks> task = RepositoryTasks.getRepositoryTasks().deserialize("src\\main\\resources\\ttts\\Data\\Tasks.txt");
                            for(Tasks t : task.values())
                                    listTask.setText(t.getName());
                            }
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
