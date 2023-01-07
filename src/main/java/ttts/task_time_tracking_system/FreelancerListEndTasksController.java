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
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FreelancerListEndTasksController implements Initializable {
    @FXML
    private DatePicker firstDate;
    @FXML
    private DatePicker lastDate;
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
    private TextField hoursTask;
    @FXML
    private TextField totalPriceTask;
    @FXML
    private TextArea descriptionTask;
    @FXML
    private ComboBox tasksList;
    Tasks actualTask;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");


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
    void listTasks(ActionEvent event) {
       LocalDate startDateTask = firstDate.getValue();
       LocalDate endDateTask = lastDate.getValue();
       List<Tasks> tasksInRange = new ArrayList<>();
       try {
           for (Tasks t : RepositoryTasks.getRepositoryTasks().getTasks().values()) {
               if(t.getFreelancer().getNIF().equals(SessionData.freelancer.getNIF()) && t.getState().equals(TaskState.FINALIZADO)) {
                   if (t.getEndDate().isAfter(startDateTask) && t.getEndDate().isBefore(endDateTask)) {
                       tasksInRange.add(t);
                       tasksList.getItems().addAll(t.getName());
                   }
               }
           }
       }catch (IOException e) {
           e.getMessage();
       } catch (ClassNotFoundException cE) {
           cE.getMessage();
       }
       tasksList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1)  {
                try{
                    for (Tasks t : RepositoryTasks.getRepositoryTasks().getTasks().values())  {
                        System.out.println(tasksList.getSelectionModel().getSelectedItem().toString());
                        System.out.println(t.getName());
                        System.out.println("-----------------------------------");
                        if(t.getFreelancer().getNIF().equals(SessionData.freelancer.getNIF())  && t.getName().equals(tasksList.getSelectionModel().getSelectedItem())) {
                            System.out.println("SOU LINDO");
                            actualTask = t;
                        }
                    }
                } catch (IOException e) {
                    e.getMessage();
                } catch (ClassNotFoundException cE) {
                    cE.getMessage();
                }
                nameTask.setText(actualTask.getName());
                nameProject.setText(actualTask.getProjects().getName());
                nameClient.setText(actualTask.getProjects().getClient());
                priceHour.setText(Float.toString(actualTask.getPriceHour()));
                hoursTask.setText(Float.toString(actualTask.getHours()));
                totalPriceTask.setText(Float.toString(actualTask.totalPrice()));
                startDate.setText(actualTask.getTaskStartDate());
                endDate.setText(actualTask.getTaskEndDate());
                stateTask.setText(actualTask.getState().toString());
                descriptionTask.setText(actualTask.getDescription());
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {      final Callback<DatePicker, DateCell> dayCellFactory =
            new Callback<DatePicker, DateCell>() {
                @Override
                public DateCell call(final DatePicker datePicker) {
                    return new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);

                            if (item.isBefore(
                                    firstDate.getValue().plusDays(1))
                            ) {
                                setDisable(true);
                                setStyle("-fx-background-color: #ffc0cb;");
                            }
                        }
                    };
                }
            };
        lastDate.setDayCellFactory(dayCellFactory);
    }
}

