/*package ttts.task_time_tracking_system;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class FreelancerListEndTasksController implements Initializable {
    @FXML
    private DatePicker firstDate;
    @FXML
    private DatePicker lastDate;
    @FXML
    private TextArea listTasks;

    @FXML
    void listEndTasks(ActionEvent event) throws IOException, ClassNotFoundException{
        Map<Integer, Tasks> task = RepositoryTasks.deserialize("src\\main\\resources\\ttts\\Data\\Tasks.txt");
        for (Tasks t : task.values()) {
            if (t.getFreelancer().getNIF().equals(SessionData.freelancer.getNIF())) {
                if()
                p.setName(nameProject.getText());
                p.setClient(clientProject.getText());
                p.setPriceHour(Float.parseFloat(priceHourProject.getText()));
                p.setState((ProjectState) stateProject.getSelectionModel().getSelectedItem());
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Sucesso");
                alert.setHeaderText("Dados Editados!");
                alert.show();
            }
        }
        RepositoryProjects.getRepositoryProjects().serialize(projects,"src\\main\\resources\\ttts\\Data\\Project.txt");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
*/