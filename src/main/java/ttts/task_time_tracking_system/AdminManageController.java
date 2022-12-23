package ttts.task_time_tracking_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminManageController implements Initializable {

    @FXML
    void createAdminManage(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("adminCreateAdmin.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Criar Administrador");
            stage.show();
        }catch (IOException e){
            e.getMessage();
        }
    }
    @FXML
    void listAdminManage(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("adminListAdmins.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Criar Administrador");
            stage.show();
        }catch (IOException e){
            e.getMessage();
        }
    }
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

    }
}
