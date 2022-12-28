package ttts.task_time_tracking_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TasksMenuController implements Initializable {

    @FXML
    void createTask(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("typeInsertTask.fxml"));
            Scene regCena = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Menu Freelancer");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        @FXML
        void backButton(ActionEvent event) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("freelancerMenu.fxml"));
                Scene regCena = new Scene (root);
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(regCena);
                stage.setTitle("Menu Freelancer");
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

        }

}
