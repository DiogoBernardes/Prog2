package ttts.task_time_tracking_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class FreelancerMenuController {

    @FXML
    private AnchorPane projetosMenuFreelancer;
    @FXML
    private AnchorPane tarefasMenuFreelancer;
    @FXML
    private AnchorPane editPerfilMenuFreelancer;

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
    void projectsMenu(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("projectsMenu.fxml"));
            Scene regCena = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Menu Projetos");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
