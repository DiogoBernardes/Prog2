package ttts.task_time_tracking_system;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
public class answerInviteController implements Initializable {
    @FXML
    private ComboBox selectInvites;


    Invitation actualInvitation;

    public void acceptInvite() throws IOException, ClassNotFoundException {
        Map<Integer, Invitation> invitations = RepositoryInvites.deserialize("src\\main\\resources\\ttts\\Data\\Invites.txt");
        for (Invitation i : invitations.values()) {
            if (i.getGuest().getNIF().equals(SessionData.freelancer.getNIF())) {
                i.setAnswer(true);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Sucesso");
                alert.setHeaderText("Convite aceite");
                alert.show();
            }
            System.out.println(i.isAnswer());
        }
        RepositoryInvites.getRepositoryInvites().serialize(invitations,"src\\main\\resources\\ttts\\Data\\Invites.txt");
    }
    public void rejectInvite() throws IOException, ClassNotFoundException{
        Map<Integer, Invitation> invitations = RepositoryInvites.deserialize("src\\main\\resources\\ttts\\Data\\Invites.txt");
        for (Invitation i : invitations.values()) {
            if (i.getGuest().getNIF().equals(SessionData.freelancer.getNIF())) {
                i.setAnswer(false);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Sucesso");
                alert.setHeaderText("Convite aceite");
                alert.show();
            }
        }
        RepositoryInvites.getRepositoryInvites().serialize(invitations,"src\\main\\resources\\ttts\\Data\\Invites.txt");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            for(Invitation i : RepositoryInvites.getRepositoryInvites().getInvitation().values()){
                if(i.getGuest().getNIF().equals(SessionData.freelancer.getNIF()) && i.isAnswer()==false) {
                    selectInvites.getItems().addAll(i.getProject().getName());
                }
            }
        }catch (IOException IE){
            IE.getMessage();
        }catch (ClassNotFoundException CE){
            CE.getMessage();
        }
        selectInvites.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                try {
                    for(Invitation i : RepositoryInvites.getRepositoryInvites().getInvitation().values()){
                        if (i.getProject().equals(selectInvites.getSelectionModel().getSelectedItem())) {
                            actualInvitation = i;

                        }
                    }
                }catch (IOException e) {
                    e.getMessage();
                } catch (ClassNotFoundException cE) {
                    cE.getMessage();
                }

            }
        });
    }
    @FXML
    void backButton(javafx.event.ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("tasksMenu.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Menu Tarefas");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
