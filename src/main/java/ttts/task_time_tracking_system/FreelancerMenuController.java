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
    void MenuFreelancerMenuButton(ActionEvent event){
        try{
            projetosMenuFreelancer.setVisible(false);
            tarefasMenuFreelancer.setVisible(false);
            editPerfilMenuFreelancer.setVisible(false);
        }catch (Exception e ){
            e.printStackTrace();
        }
    }
    @FXML
    void projetosFreelancerMenuButton(ActionEvent event){
        try{
            projetosMenuFreelancer.setVisible(true);
            tarefasMenuFreelancer.setVisible(false);
            editPerfilMenuFreelancer.setVisible(false);
        }catch (Exception e ){
            e.printStackTrace();
        }
    }

    @FXML
    void tarefasFreelancerMenuButton(ActionEvent event) {
        try {
            tarefasMenuFreelancer.setVisible(true);
            projetosMenuFreelancer.setVisible(false);
            editPerfilMenuFreelancer.setVisible(false);
        }catch (Exception e ){
            e.printStackTrace();
        }
    }

    @FXML
    void editPerfilFreelancerMenuButton(ActionEvent event) {
        try {
            editPerfilMenuFreelancer.setVisible(true);
            projetosMenuFreelancer.setVisible(false);
            tarefasMenuFreelancer.setVisible(false);
        }catch (Exception e ){
            e.printStackTrace();
        }
    }

    @FXML
    void createProjectsFMenuButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("createProjects.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Criar Projetos - Freelancer");
            stage.show();
        }catch (IOException e){
            e.getMessage();
        }
    }
}
