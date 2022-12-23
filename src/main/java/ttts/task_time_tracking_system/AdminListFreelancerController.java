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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminListFreelancerController implements Initializable {

    @FXML
    private ComboBox selectFreelancerAdmin;
    @FXML
    private TextField firstNameFreelancerAdmin;
    @FXML
    private TextField lastNameFreelancerAdmin;
    @FXML
    private TextField nifFreelancerAdmin;
    @FXML
    private TextField birthdateFreelancerAdmin;

    Freelancer actualFreelancer;
    @FXML
    void backButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("adminMenu.fxml"));
            Scene regCena = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Menu Administrador");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstNameFreelancerAdmin.setEditable(false);
        lastNameFreelancerAdmin.setEditable(false);
        nifFreelancerAdmin.setEditable(false);
        birthdateFreelancerAdmin.setEditable(false);

        for(Freelancer f : RepositoryFreelancer.getRepositoryFreelancer().getFreelancer().values()) {
            selectFreelancerAdmin.getItems().addAll(f.getUsername());
        }
        selectFreelancerAdmin.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){

            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

                for(Freelancer f : RepositoryFreelancer.getRepositoryFreelancer().getFreelancer().values()) {
                    if(f.getUsername().equals(selectFreelancerAdmin.getSelectionModel().getSelectedItem()))
                        actualFreelancer = f;
                }
                firstNameFreelancerAdmin.setText(actualFreelancer.getName());
                lastNameFreelancerAdmin.setText(actualFreelancer.getLastName());
                nifFreelancerAdmin.setText(actualFreelancer.getNIF());
                birthdateFreelancerAdmin.setText(actualFreelancer.getBirthDate().toString());
            }
        });
    }
}
