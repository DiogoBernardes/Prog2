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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class FreelancerChangeStateTaskController implements Initializable {
    @FXML
    private ComboBox selectTask;
    @FXML
    private ComboBox state;
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
    @FXML
    void changeStateTask(ActionEvent event) throws IOException, ClassNotFoundException{
        Map<Integer, Tasks> task = RepositoryTasks.deserialize("src\\main\\resources\\ttts\\Data\\Tasks.txt");
            for(Tasks t : task.values()){
                if(t.getIdTask() == actualTask.getIdTask()) {
                    t.setState((TaskState) state.getSelectionModel().getSelectedItem());
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Sucesso");
                    alert.setHeaderText("Dados Editados!");
                    alert.show();
                }
        }
        RepositoryTasks.getRepositoryTasks().serialize(task,"src\\main\\resources\\ttts\\Data\\Tasks.txt");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            List<TaskState> taskStateList = new ArrayList<TaskState>(EnumSet.allOf(TaskState.class));
            state.getItems().addAll(taskStateList);
            for (Tasks t : RepositoryTasks.getRepositoryTasks().getTasks().values()) {
                if(SessionData.freelancer.getNIF().equals(t.getFreelancer().getNIF())) {
                    selectTask.getItems().addAll(t.getName());
                    }
            } }catch (IOException e){
            e.getMessage();
        }catch (ClassNotFoundException CE){
            CE.getMessage();
        }catch (NullPointerException NE){
            NE.getMessage();
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
