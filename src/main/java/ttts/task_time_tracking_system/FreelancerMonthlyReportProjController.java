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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class FreelancerMonthlyReportProjController implements Initializable {
    @FXML
    private ComboBox selectMonth;
    @FXML
    private ComboBox listProject;
    @FXML
    private TextField listClient;
    @FXML
    private TextField listHours;
    @FXML
    private TextField listPriceHour;
    @FXML
    private TextField listTotPrice;
    @FXML
    private TextArea listTasks;
    @FXML
    private TextArea report;

    Projects actualProject;
    float totPriceMonth = 0;
    float totHoursMonth = 0;
    int totProjectsRealized = 0;
    int totTasksRealized=0;
    @FXML
    void backButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("freelancerMenu.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Menu Freelancer");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listClient.setEditable(false);
        listHours.setEditable(false);
        listPriceHour.setEditable(false);
        listTotPrice.setEditable(false);
        selectMonth.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12);

        selectMonth.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>(){
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                try{
                    listProject.getItems().clear();
                    report.setText("");
                    listTasks.setText("");
                    listClient.setText("");
                    listHours.setText("");
                    listPriceHour.setText("");
                    listTotPrice.setText("");
                    totPriceMonth = 0;
                    totHoursMonth = 0;
                    totTasksRealized = 0;
                    totProjectsRealized = 0;
                    for(Projects p : RepositoryProjects.getRepositoryProjects().getProjects().values()) {
                        if (p.getFreelancer().getNIF().equals(SessionData.freelancer.getNIF()))
                            System.out.println("MES PROJ" + p.getStartDateProj().getMonth().getValue());
                            System.out.println(selectMonth.getValue());
                           if (p.getStartDateProj().getMonth().getValue() == (int) selectMonth.getValue() && p.getState().equals(ProjectState.FINALIZADO)) {
                               listProject.getItems().addAll(p.getName());
                               totProjectsRealized = totProjectsRealized + 1;
                               for (Tasks t : RepositoryTasks.getRepositoryTasks().getTasks().values()) {
                                   if(t.getProjects().getIdProjeto() == p.getIdProjeto())
                                   totPriceMonth = totPriceMonth + t.totalPrice();
                                   totHoursMonth = totHoursMonth + t.getHours();
                                   totTasksRealized = totTasksRealized + 1;
                               }
                           }
                    }
                }catch (IOException e){
                    e.getMessage();
                }catch (ClassNotFoundException CE){
                    CE.getMessage();
                }catch (NullPointerException NE){
                    NE.getMessage();
                }
            }
        });
        listProject.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String string, String t1) {
                try{
                    for(Projects p : RepositoryProjects.getRepositoryProjects().getProjects().values()) {
                        if (p.getName().equals(listProject.getSelectionModel().getSelectedItem())) {
                            actualProject = p;
                        }
                    }
                }catch (IOException e){
                    e.getMessage();
                }catch (ClassNotFoundException CE){
                    CE.getMessage();
                }
                if(actualProject.getTasks()== null){
                    listTasks.setText("");
                }else {
                    try {
                        Map<Integer, Tasks> task = RepositoryTasks.getRepositoryTasks().deserialize("src\\main\\resources\\ttts\\Data\\Tasks.txt");
                        for (Tasks t : task.values())
                            listTasks.setText(t.getName());
                    }catch (ClassNotFoundException CE){
                        CE.getMessage();
                }catch (IOException IE){
                        IE.getMessage();
                    }
                }
                listClient.setText(actualProject.getClient());
                listHours.setText(Float.toString(totHoursMonth));
                listPriceHour.setText(Float.toString(actualProject.getPriceHour()));
                listTotPrice.setText(Float.toString(totPriceMonth));

                report.setText("Total de Projetos realizados: "+ totProjectsRealized + "Total de Tarefas realizadas: " +
                        totTasksRealized + "\n Montante total Obtido: " + totPriceMonth + "\n Horas despendidas: " + totHoursMonth);
            }
        });
    }
}

