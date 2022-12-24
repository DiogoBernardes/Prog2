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

public class FreelancerEditProfileController implements Initializable {
    @FXML
    private TextField nameEditProfileFreelancer;
    @FXML
    private TextField lastNameEditProfileFreelancer;
    @FXML
    private TextField nifEditProfileFreelancer;
    @FXML
    private TextField birthDateEditProfileFreelancer;

    Freelancer actualFreelancer;
    @FXML
    void backButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("freelancerMenu.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Menu Administrador");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void editProfile(ActionEvent event) {
       /* SessionData sd = new SessionData();
        for (Freelancer f : RepositoryFreelancer.getRepositoryFreelancer().getFreelancer().values()) {
            if (f.getUsername().equals(sd.freelancer.getUsername()))
                actualFreelancer = f;
        }
        nameEditProfileFreelancer.setText(actualFreelancer.getName());
        lastNameEditProfileFreelancer.setText(actualFreelancer.getLastName());
        nifEditProfileFreelancer.setText(actualFreelancer.getNIF());
        birthDateEditProfileFreelancer.setText(actualFreelancer.getBirthDate());*/
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

