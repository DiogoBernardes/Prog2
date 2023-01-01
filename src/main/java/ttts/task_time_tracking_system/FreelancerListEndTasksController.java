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
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;

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
    private ListView tasksListView;

    Tasks actualTask;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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
        try {
            LocalDate inicio =firstDate.getValue();
            LocalDate fim = lastDate.getValue();

            for (Tasks t : RepositoryTasks.getRepositoryTasks().getTasks().values()) {
                if (t.getFreelancer().equals(SessionData.freelancer.getName()))
                    if (inicio.isBefore(ChronoLocalDate.from(formatter.parse(t.getTaskEndDate()))) && fim.isAfter(ChronoLocalDate.from(formatter.parse(t.getTaskEndDate()))))
                        tasksListView.getItems().addAll(t.getIdTask());
            }
        }catch (IOException e){
            e.getMessage();
        }catch (ClassNotFoundException CE){
            CE.getMessage();
        }catch(NullPointerException NE){
            NE.getMessage();
        }
        tasksListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>(){

            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                try{
                    for (Tasks t : RepositoryTasks.getRepositoryTasks().getTasks().values())  {
                        if(t.getFreelancer().equals(SessionData.freelancer)  && tasksListView.getSelectionModel().getSelectedItem().equals(t.getIdTask()))
                            actualTask = t;
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
                startDate.setText(actualTask.getTaskStartDate());
                endDate.setText(actualTask.getTaskEndDate());
                stateTask.setText(actualTask.getState().toString());
                descriptionTask.setText(actualTask.getDescription());
            }
        });
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        final Callback<DatePicker, DateCell> dayCellFactory =
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
