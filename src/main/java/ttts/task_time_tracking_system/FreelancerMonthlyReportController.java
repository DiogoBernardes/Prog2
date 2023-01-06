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
import java.util.ResourceBundle;

public class FreelancerMonthlyReportController implements Initializable {
    @FXML
    private ComboBox selectMonth;
    @FXML
    private ComboBox listTasks;
    @FXML
    private TextField listProject;
    @FXML
    private TextField listTotHours;
    @FXML
    private TextField listPriceHour;
    @FXML
    private TextField listTotPrice;
    @FXML
    private TextArea reportArea;

    Tasks actualTask;
    float totPriceMonth = 0;
    float totHoursMonth = 0;
    int totTasksRealized = 0;
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
        listProject.setEditable(false);
        listTotHours.setEditable(false);
        listPriceHour.setEditable(false);
        listTotPrice.setEditable(false);
        selectMonth.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12);

        selectMonth.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>(){
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                try{
                    listTasks.getItems().clear();
                    reportArea.setText("");
                    listProject.setText("");
                    listTotHours.setText("");
                    listPriceHour.setText("");
                    listTotPrice.setText("");
                    totPriceMonth = 0;
                    totHoursMonth = 0;
                    totTasksRealized = 0;
                    for(Tasks t : RepositoryTasks.getRepositoryTasks().getTasks().values()) {
                        if (t.getFreelancer().getNIF().equals(SessionData.freelancer.getNIF()))
                        if (t.getStartDate().getMonth().getValue() == (int) selectMonth.getValue() && t.getState().equals(TaskState.FINALIZADO)) {
                            listTasks.getItems().addAll(t.getName());
                            totPriceMonth = totPriceMonth + t.totalPrice();
                            totHoursMonth = totHoursMonth + t.getHours();
                            totTasksRealized = totTasksRealized + 1;
                        }
                    }
                }catch (IOException e){
                    e.getMessage();
                }catch (ClassNotFoundException CE){
                    CE.getMessage();
                }
            }
        });
        listTasks.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String string, String t1) {
                try{
                    for(Tasks t : RepositoryTasks.getRepositoryTasks().getTasks().values()) {
                        if (t.getName().equals(listTasks.getSelectionModel().getSelectedItem())) {
                            actualTask = t;
                        }
                    }
                }catch (IOException e){
                    e.getMessage();
                }catch (ClassNotFoundException CE){
                    CE.getMessage();
                }
                if(actualTask.getProjects()== null){
                    listProject.setText("");
                }else {
                    listProject.setText(actualTask.getProjects().getName());
                }
                listTotHours.setText(Float.toString(actualTask.getHours()));
                listPriceHour.setText(Float.toString(actualTask.getPriceHour()));
                listTotPrice.setText(Float.toString(actualTask.totalPrice()));



                reportArea.setText("Total de Tarefas realizadas: " + totTasksRealized + "\n Montante total Obtido: " + totPriceMonth + "\n Horas despendidas: " + totHoursMonth);
            }
        });
    }
}