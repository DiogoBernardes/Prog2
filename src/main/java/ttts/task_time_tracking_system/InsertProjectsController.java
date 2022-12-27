package ttts.task_time_tracking_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class InsertProjectsController implements Initializable {

    @FXML
    private TextField nameProjectTextField;
    @FXML
    private TextField nameClientTextField;
    @FXML
    private TextField priceProjectTextField;

    @FXML
    void createProject(ActionEvent event){
       try {
            Projects p1 = new Projects();
            p1.setFreelancer(SessionData.freelancer);
            p1.setIdProjeto(RepositoryProjects.getRepositoryProjects().getProjects().size() + 1);
            p1.setName(nameProjectTextField.getText());
            p1.setClient(nameClientTextField.getText());
            p1.setPriceHour(Float.parseFloat(priceProjectTextField.getText()));
            p1.setState(ProjectState.CONFIRMADO);

            ProjectREP.createProject(p1, SessionData.freelancer);

            try {
                Parent root = FXMLLoader.load(getClass().getResource("FreelancerMenu.fxml"));
                Scene regCena = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(regCena);
                stage.setTitle("Menu Cliente");
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void backMenu(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FreelancerMenu.fxml"));
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
    public void initialize(URL url , ResourceBundle resourceBundle){

    }
}
