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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminListAdminController implements Initializable {

    @FXML
    private ComboBox selectAdminListManage;
    @FXML
    private TextField nameAdminListManage;
    @FXML
    private TextField usernameAdminListManage;
    @FXML
    private TextField nifAdminListManage;
    @FXML
    private TextField birthdateAdminListManage;

    Admin actualAdmin;

    @FXML
    void backButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("adminMenu.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Menu Administrador");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameAdminListManage.setEditable(false);
        usernameAdminListManage.setEditable(false);
        nifAdminListManage.setEditable(false);
        birthdateAdminListManage.setEditable(false);
        try {
            for (Admin a : RepositoryAdmin.getRepositoryAdmin().getAdmin().values()) {
                selectAdminListManage.getItems().addAll(a.getUsername());
            }
        }catch (IOException e){
            e.getMessage();
        }catch (ClassNotFoundException CE){
            CE.getMessage();
        }
        selectAdminListManage.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                try {
                for (Admin a : RepositoryAdmin.getRepositoryAdmin().getAdmin().values()) {
                    if (a.getUsername().equals(selectAdminListManage.getSelectionModel().getSelectedItem()))
                        actualAdmin = a;
                }
                }catch (IOException e){
                    e.getMessage();
                }catch (ClassNotFoundException CE){
                    CE.getMessage();
                }
                nameAdminListManage.setText(actualAdmin.getName());
                usernameAdminListManage.setText(actualAdmin.getUsername());
                nifAdminListManage.setText(actualAdmin.getNIF());
                birthdateAdminListManage.setText(actualAdmin.getBirthDate());
            }
        });
    }
}

