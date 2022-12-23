package ttts.task_time_tracking_system;

import java.io.IOException;

public class AdminREP {

    public static void createAdmin(Admin admin){
        RepositoryAdmin.getRepositoryAdmin().getAdmin().put(admin.getNIF(), admin);
        System.out.println("Administrador criado com sucesso!!");
        try {
            RepositoryAdmin.getRepositoryAdmin().serialize(admin, "src\\main\\resources\\ttts\\Data\\Admin.txt");
        }catch (IOException exIO){
            exIO.getMessage();
        }
        }
}
