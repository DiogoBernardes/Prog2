package ttts.task_time_tracking_system;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.net.URL;
public class RegisterController implements Initializable {

    @FXML
    private TextField registerFirstNameField;
    @FXML
    private TextField registerLastNameField;
    @FXML
    private TextField registerUsernameField;
    @FXML
    private TextField registerPasswordField;
    @FXML
    private TextField registerNIFField;
    @FXML
    private DatePicker registerDataField;
    @FXML
    private ChoiceBox<String> registerGeneroField;
    @FXML
    private Label Error;




    @Override
    public void initialize(URL url, ResourceBundle rb) {
        registerGeneroField.getItems().addAll("Masculino", "Feminino");
    }

    @FXML
     void registerFreelancer(ActionEvent event) throws LoginException {
        if(registerNIFField.getLength()==9) {
            if(!RepositoryFreelancer.getRepositoryFreelancer().getFreelancer().containsKey(registerNIFField.getText())){
                Freelancer f1 = new Freelancer();
                f1.setNIF(registerNIFField.getText());
                f1.setName(registerFirstNameField.getText());
                f1.setLastName(registerLastNameField.getText());
                f1.setUsername(registerUsernameField.getText());
                f1.setPassword(registerPasswordField.getText());
                f1.setBirthDate(registerDataField.getValue().toString());
                f1.setGener(registerGeneroField.getValue());
                f1.setType("freelancer");
                f1.setWorkHours(0);
                FreelancerREP.createFreelancer(f1);
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
    public void backButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene regCena = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Login");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
