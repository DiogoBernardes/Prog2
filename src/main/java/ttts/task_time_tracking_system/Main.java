package ttts.task_time_tracking_system;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 525, 328);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
    //TESTE DE GITHUB
    public static void main(String[] args) throws IOException, ClassNotFoundException{

       RepositoryAdmin repoAdmin;
        //Se existir o admin default não insere, caso contrário bota la pa dentro
        boolean empty=false;
        try {
            Map<String, Admin> data = RepositoryAdmin.deserialize("src\\main\\resources\\ttts\\Data\\Admin.txt");
            if(data.size() == 0) {
                Admin c1 = new Admin();
                c1.setName("Administrador");
                c1.setBirthDate("2000-10-15");
                c1.setNIF("123456789");
                c1.setType("Admin");
                c1.setUsername("admin");
                c1.setPassword("pass");
                AdminREP.createAdmin(c1);
            }
        }catch (IOException ex){
            ex.getMessage();
        }
         launch();
    }
}