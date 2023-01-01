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
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class FreelancerMonthlyReportController implements Initializable {
    @FXML
    private DatePicker selectMonth;
    @FXML
    private ListView<String>listTasks;
    @FXML
    private TextField listProject;
    @FXML
    private TextField listTotHours;
    @FXML
    private TextField listPriceHour;
    @FXML
    private TextField listTotPrice;
    Tasks actualTask;

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
    @Override
   public void initialize(URL url, ResourceBundle resourceBundle) {
        selectMonth.setShowWeekNumbers(false);
        selectMonth.setChronology(IsoChronology.INSTANCE);
        listProject.setEditable(false);
        listTotHours.setEditable(false);
        listPriceHour.setEditable(false);
        listTotPrice.setEditable(false);
        try{
        for(Tasks t : RepositoryTasks.getRepositoryTasks().getTasks().values()) {
            if (t.getFreelancer().getNIF().equals(SessionData.freelancer.getNIF()))
                if (t.getState().equals(TaskState.FINALIZADO)) {
                    listTasks.getItems().addAll(t.getName());
                }
        }
        }catch (IOException e){
            e.getMessage();
        }catch (ClassNotFoundException CE){
            CE.getMessage();
        }

        listTasks.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){

            @Override
            public void changed(ObservableValue<? extends String> observableValue, String string, String t1) {
                try{
                for(Tasks t : RepositoryTasks.getRepositoryTasks().getTasks().values())  {
                    if(t.getFreelancer().getNIF() == SessionData.freelancer.getNIF() && t.getName().equals(listTasks.getSelectionModel().getSelectedItem()))
                        actualTask = t;
                }
                }catch (IOException e){
                    e.getMessage();
                }catch (ClassNotFoundException CE){
                    CE.getMessage();
                }
                listProject.setText(actualTask.getProjects().getName());
                listTotHours.setText(Float.toString(actualTask.getHours()));
                listPriceHour.setText(Float.toString(actualTask.getPriceHour()));
                listTotPrice.setText(Float.toString(actualTask.totalPrice()));
            }
        });
    }
}