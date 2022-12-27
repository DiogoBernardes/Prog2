package ttts.task_time_tracking_system;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.util.Map;
import java.util.ResourceBundle;
import java.net.URL;

public class LoginController implements Initializable {
    @FXML
    private Button registarButton;

    @FXML
    private Label LoginError;
    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordTextField;
    @FXML
    private TextField usernameTextField;

    @Override
    public void initialize(URL url , ResourceBundle resourceBundle){

    }

    public void loginButtonOnAction(ActionEvent event){
        String user = usernameTextField.getText();
        String pass = passwordTextField.getText();
        boolean found=false;
        try {
            RepositoryAdmin repoAdmin;
            repoAdmin=RepositoryAdmin.getRepositoryAdmin();
            for(Admin a : repoAdmin.getAdmin().values()){
                if (user.equals(a.getUsername()) && pass.equals(a.getPassword())) {
                    found=true;
                    System.out.println("Login com Sucesso!");
                    Parent root = FXMLLoader.load(getClass().getResource("AdminMenu.fxml"));
                    Scene regCena = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(regCena);
                    stage.setTitle("Menu Cliente");
                    stage.show();
                }}

                Map<String, Freelancer> freelancers = RepositoryFreelancer.deserialize("src\\main\\resources\\ttts\\Data\\Freelancer.txt");
                for (Freelancer f : freelancers.values()) {
                if (user.equals(f.getUsername()) && pass.equals(f.getPassword())) {
                    found = true;
                    System.out.println("Login com Sucesso!");
                    SessionData.freelancer = f;
                    Parent root = FXMLLoader.load(getClass().getResource("FreelancerMenu.fxml"));
                    Scene regCena = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(regCena);
                    stage.setTitle("Menu Cliente");
                    stage.show();
                }
            }
            if (!found) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Utilizador/Password Errada!");
                alert.show();
            }
        }catch (Exception ex){
            ex.getMessage();
        }
    }


    public void register(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
            Scene regCena = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Registar");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void validateLogin(){

    }
}