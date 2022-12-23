package ttts.task_time_tracking_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class adminRegisteAdminController implements Initializable {
    @FXML
    private TextField usernameRegisterAdmin;
    @FXML
    private TextField passRegisterAdmin;
    @FXML
    private TextField nameRegisterAdmin;
    @FXML
    private TextField nifRegisterAdmin;
    @FXML
    private DatePicker birthdateRegisterAdmin;


    @FXML
    void registerAdmin(ActionEvent event) throws LoginException{
        if(nifRegisterAdmin.getLength()==9) {
            if(!RepositoryAdmin.getRepositoryAdmin().getAdmin().containsKey(nifRegisterAdmin.getText())){
                Admin admin = new Admin();
                admin.setNIF(nifRegisterAdmin.getText());
                admin.setName(nameRegisterAdmin.getText());
                admin.setUsername(usernameRegisterAdmin.getText());
                admin.setPassword(passRegisterAdmin.getText());
                admin.setBirthDate(birthdateRegisterAdmin.getValue().toString());
                admin.setType("Admin");
                AdminREP.createAdmin(admin);
                Alert alertDatInv = new Alert(Alert.AlertType.INFORMATION);
                alertDatInv.setTitle("Sucesso");
                alertDatInv.setHeaderText("O admin foi registado com sucesso!");
                alertDatInv.show();
            }else {
                Alert alertDatInv = new Alert(Alert.AlertType.ERROR);
                alertDatInv.setTitle("Erro");
                alertDatInv.setHeaderText("Esse utilizador já está registado no sistema");
                alertDatInv.show();
                throw new LoginException("Esse utilizador já está registado no sistema");
            }
        }
        else{
            Alert alertDatInv = new Alert(Alert.AlertType.ERROR);
            alertDatInv.setTitle("Erro");
            alertDatInv.setHeaderText("O NIF deve conter 9 digitos!");
            alertDatInv.show();
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
