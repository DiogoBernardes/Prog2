package ttts.task_time_tracking_system;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AdminREP {

    public static void createAdmin(Admin admin) throws IOException, ClassNotFoundException{
        RepositoryAdmin.getRepositoryAdmin().getAdmin().put(admin.getNIF(), admin);
        System.out.println("Administrador criado com sucesso!!");
        Map<String, Admin> admins = null;
        try{
            admins = RepositoryAdmin.getRepositoryAdmin().deserialize("src\\main\\resources\\ttts\\Data\\Admin.txt");
            if(admins == null){
                admins = new HashMap<>();
            }
            admins.put(admin.getNIF(), admin);
        }catch (IOException e){
            e.getMessage();
        }catch (ClassNotFoundException cE){
            cE.getMessage();
        }
        try {
            RepositoryAdmin.getRepositoryAdmin().serialize(admins, "src\\main\\resources\\ttts\\Data\\Admin.txt");
        }catch (IOException exIO){
            exIO.getMessage();
        }
        }
}
