package ttts.task_time_tracking_system;

import javafx.fxml.Initializable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class InvitationREP {
    public static void inviteGuestProject(Invitation inv) throws IOException, ClassNotFoundException{
    RepositoryInvites.getRepositoryInvites().getInvitation().put(inv.getIdInvite(),inv);
    System.out.println("Pedido Enviado");
    Map<Integer, Invitation> invitation = null;
    try {
        invitation = RepositoryInvites.getRepositoryInvites().deserialize("src\\main\\resources\\ttts\\Data\\Invites.txt");
        if(invitation == null){
            invitation = new HashMap<>();
        }
        invitation.put(inv.getIdInvite(),inv);
    }catch (IOException e){
        e.getMessage();
    }catch (ClassNotFoundException cE){
        cE.getMessage();
    }
    try {
        RepositoryInvites.getRepositoryInvites().serialize(invitation, "src\\main\\resources\\ttts\\Data\\Invites.txt");
    }catch (IOException exIO){
        exIO.getMessage();
    }
}
}
