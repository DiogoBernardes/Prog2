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
import java.util.Map;
import java.util.ResourceBundle;

public class FreelancerListProjectController implements Initializable {

    @FXML
    private ComboBox selectProject;
    @FXML
    private TextField nameProject;
    @FXML
    private TextField clientProject;
    @FXML
    private TextField priceHourProject;
    @FXML
    private TextField stateProject;

    Projects actualProject;

    @FXML
    void backButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("projectsMenu.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Menu Projetos");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameProject.setEditable(false);
        clientProject.setEditable(false);
        priceHourProject.setEditable(false);
        stateProject.setEditable(false);
        try {
            for (Projects p : RepositoryProjects.getRepositoryProjects().getProjects().values()) {
                if(SessionData.freelancer.getNIF().equals(p.getFreelancer().getNIF())) {
                    selectProject.getItems().addAll(p.getName());
                }
                } }catch (IOException e){
            e.getMessage();
        }catch (ClassNotFoundException CE){
            CE.getMessage();
        }
            selectProject.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

                @Override
                public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                    try {
                        for (Projects p : RepositoryProjects.getRepositoryProjects().getProjects().values()) {
                            if (p.getName().equals(selectProject.getSelectionModel().getSelectedItem()))
                                actualProject = p;
                        }
                    }catch (IOException e) {
                        e.getMessage();
                    } catch (ClassNotFoundException cE) {
                        cE.getMessage();
                    }
                    nameProject.setText(actualProject.getName());
                    clientProject.setText(actualProject.getClient());
                    priceHourProject.setText(Float.toString(actualProject.getPriceHour()));
                    stateProject.setText(actualProject.getState().toString());
                }
            });
    }
}
