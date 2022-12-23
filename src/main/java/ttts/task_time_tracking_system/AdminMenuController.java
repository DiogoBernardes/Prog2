package ttts.task_time_tracking_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {

    @FXML
    void logout(ActionEvent event){
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Terminar Sessão");
            alert.setContentText("Deseja terminar sessão?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                    Scene regCena = new Scene (root);
                    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    stage.setScene(regCena);
                    stage.setTitle("Iniciar Sessão");
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void listFreelancers(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("adminListFreelancer.fxml"));
            Scene regCena = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Listar Freelancers");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void adminManagePaneButton(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("manageAdminsMenu.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Gerir Administradores");
            stage.show();
        }catch (IOException e){
            e.getMessage();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
