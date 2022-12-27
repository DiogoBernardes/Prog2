package ttts.task_time_tracking_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class FreelancerEditProfileController implements Initializable {
    @FXML
    private TextField usernameFreelancer;
    @FXML
    private PasswordField passFreelancer;
    @FXML
    private TextField nameFreelancer;
    @FXML
    private TextField lastNameFreelancer;
    @FXML
    private TextField dailyHoursFreelancer;
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
    void editProfile(ActionEvent event) throws IOException, ClassNotFoundException{
            Map<String, Freelancer> freelancers = RepositoryFreelancer.deserialize("src\\main\\resources\\ttts\\Data\\Freelancer.txt");
            for (Freelancer f : freelancers.values()) {
                if (f.getNIF().equals(SessionData.freelancer.getNIF())) {
                    f.setName(nameFreelancer.getText());
                    f.setLastName(lastNameFreelancer.getText());
                    f.setUsername(usernameFreelancer.getText());
                    f.setPassword(passFreelancer.getText());
                    f.setWorkHours(Float.parseFloat(dailyHoursFreelancer.getText()));
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Sucesso");
                    alert.setHeaderText("Dados Editados!");
                    alert.show();
                }
            }
        RepositoryFreelancer.getRepositoryFreelancer().serialize(freelancers,"src\\main\\resources\\ttts\\data\\Freelancer.txt");
        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Map<String, Freelancer> freelancers = RepositoryFreelancer.deserialize("src\\main\\resources\\ttts\\Data\\Freelancer.txt");

            for (Freelancer f : freelancers.values()) {
                if (f.getNIF().equals(SessionData.freelancer.getNIF()))
                    actualFreelancer = f;
            }
            nameFreelancer.setText(actualFreelancer.getName());
            lastNameFreelancer.setText(actualFreelancer.getLastName());
            usernameFreelancer.setText(actualFreelancer.getUsername());
            passFreelancer.setText(actualFreelancer.getPassword());
            dailyHoursFreelancer.setText(Float.toString(actualFreelancer.getWorkHours()));
        }catch (IOException e){
            e.getMessage();
        }catch (ClassNotFoundException cE){
            cE.getMessage();
        }
    }
}

