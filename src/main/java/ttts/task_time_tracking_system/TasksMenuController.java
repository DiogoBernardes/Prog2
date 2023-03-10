package ttts.task_time_tracking_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TasksMenuController implements Initializable {

    @FXML
    void createTask(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("typeInsertTask.fxml"));
            Scene regCena = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Menu Freelancer");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void listTask(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("freelancerListTasks.fxml"));
            Scene regCena = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Listar Tarefas");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void listCourseTask(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("freelancerListCourseTasks.fxml"));
            Scene regCena = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Listar Tarefas em Curso");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void changeStateTask(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("changeStateTask.fxml"));
            Scene regCena = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Listar Tarefas em Curso");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void listEndTask(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("freelancerListEndTasks.fxml"));
            Scene regCena = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Listar Tarefas Finalizadas");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void editTask(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("editTasks.fxml"));
            Scene regCena = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Editar Tarefas");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void endTask(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("freelancerEndTask.fxml"));
            Scene regCena = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Terminar Tarefa");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void removeTask(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("freelancerRemoveTask.fxml"));
            Scene regCena = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Remover Tarefa");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        @FXML
        void backButton(ActionEvent event) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("freelancerMenu.fxml"));
                Scene regCena = new Scene (root);
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(regCena);
                stage.setTitle("Menu Freelancer");
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    @FXML
    void monthlyReportTask(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("monthlyReportTask.fxml"));
            Scene regCena = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Editar Tarefas");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

        }

}
