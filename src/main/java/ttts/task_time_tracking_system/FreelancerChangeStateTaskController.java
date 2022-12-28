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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class FreelancerChangeStateTaskController implements Initializable {
    @FXML
    private ComboBox selectTask;
    @FXML
    private ComboBox<TaskState> state;
    @FXML

    Tasks actualTask;
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

        try {
            for (Tasks t : RepositoryTasks.getRepositoryTasks().getTasks().values()) {
                if(SessionData.freelancer.getNIF().equals(t.getFreelancer().getNIF())) {
                    List<TaskState> taskStateList = Arrays.asList(TaskState.values());
                        selectTask.getItems().addAll(t.getName());
                        state.getItems().addAll(taskStateList);
                    }
            } }catch (IOException e){
            e.getMessage();
        }catch (ClassNotFoundException CE){
            CE.getMessage();
        }
        selectTask.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                try {
                    for (Tasks t : RepositoryTasks.getRepositoryTasks().getTasks().values()){
                        if (t.getName().equals(selectTask.getSelectionModel().getSelectedItem()))
                            actualTask = t;
                    }
                }catch (IOException e) {
                    e.getMessage();
                } catch (ClassNotFoundException cE) {
                    cE.getMessage();
                }
                state.setValue(actualTask.getState());

            }
        });
    }
}
