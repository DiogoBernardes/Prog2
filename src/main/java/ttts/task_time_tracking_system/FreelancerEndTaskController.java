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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;

public class FreelancerEndTaskController implements Initializable {
    @FXML
    private ComboBox selectTask;

    Tasks actualTask;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    Date today = new Date();
    String endDate = formatter.format(today);
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
    void endTasks(ActionEvent event) throws IOException, ClassNotFoundException{
        Map<Integer, Tasks> tasks = RepositoryTasks.deserialize("src\\main\\resources\\ttts\\Data\\Tasks.txt");
        for (Tasks t : tasks.values()) {
            if (t.getIdTask() == actualTask.getIdTask()) {
                t.setState(TaskState.FINALIZADO);
                t.setTaskEndDate(endDate);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Sucesso");
                alert.setHeaderText("Tarefa Finalizada! Parabéns!!");
                alert.show();
            }
        }
        RepositoryTasks.getRepositoryTasks().serialize(tasks,"src\\main\\resources\\ttts\\Data\\Tasks.txt");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            for (Tasks t : RepositoryTasks.getRepositoryTasks().getTasks().values()) {
                if(SessionData.freelancer.getNIF().equals(t.getFreelancer().getNIF())) {
                    if(!t.getState().equals(TaskState.FINALIZADO)) {
                        selectTask.getItems().addAll(t.getName());
                    }
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
            }
        });
    }
}