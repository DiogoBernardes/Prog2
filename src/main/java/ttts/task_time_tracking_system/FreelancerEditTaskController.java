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
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FreelancerEditTaskController implements Initializable {
    @FXML
    private TextField nameTask;
    @FXML
    private TextField nameProject;
    @FXML
    private TextField nameClient;
    @FXML
    private TextField priceHour;
    @FXML
    private TextField startDate;
    @FXML
    private TextField endDate;
    @FXML
    private TextField stateTask;
    @FXML
    private ComboBox selectTask;
    @FXML
    private TextArea descriptionTask;
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
    void editTasks(ActionEvent event) throws IOException, ClassNotFoundException{
        Map<Integer, Tasks> tasks = RepositoryTasks.deserialize("src\\main\\resources\\ttts\\Data\\Tasks.txt");
        for (Tasks t : tasks.values()) {
            if (t.getIdTask() == actualTask.getIdTask()) {
                t.setName(nameTask.getText());
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Sucesso");
                alert.setHeaderText("Dados Editados!");
                alert.show();
            }
        }
        RepositoryTasks.getRepositoryTasks().serialize(tasks,"src\\main\\resources\\ttts\\Data\\Tasks.txt");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        priceHour.setEditable(false);
        nameClient.setEditable(false);
        startDate.setEditable(false);
        endDate.setEditable(false);
        stateTask.setEditable(false);
        try {
            for (Tasks t : RepositoryTasks.getRepositoryTasks().getTasks().values()) {
                if(SessionData.freelancer.getNIF().equals(t.getFreelancer().getNIF())) {
                    selectTask.getItems().addAll(t.getName());
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
                nameTask.setText(actualTask.getName());
                if(actualTask.getProjects()== null){
                    nameProject.setText("");
                }else {
                    nameProject.setText(actualTask.getProjects().getName());
                }
                priceHour.setText(Float.toString(actualTask.getPriceHour()));
                if(actualTask.getProjects()==null){
                    nameClient.setText("");
                }else {
                    nameClient.setText(actualTask.getProjects().getClient());
                }
                startDate.setText(actualTask.getTaskStartDate());
                endDate.setText(actualTask.getTaskEndDate());
                stateTask.setText(actualTask.getState().toString());
                descriptionTask.setText(actualTask.getDescription());
            }
        });
    }
}
