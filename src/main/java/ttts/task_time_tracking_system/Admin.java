package ttts.task_time_tracking_system;
import java.io.Serializable;

public class Admin implements Serializable{
    private static final long serialVersionUID = 2992983569523313607L;
        private String name;
        private String birthDate;
        private String NIF;
        private String username;
        private String password;
        private String type;

        public Admin(){};

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getBirthDate() {
            return birthDate;
        }
        public void setBirthDate(String birthDate) {
            this.birthDate = birthDate;
        }
        public String getNIF() {
            return NIF;
        }
        public void setNIF(String NIF) {
            this.NIF = NIF;
        }
        public String getUsername() {
            return username;
        }
        public void setUsername(String username) {
            this.username = username;
        }
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }
        public String getType() {return type;}
        public void setType(String type) {this.type = "Admin";}
}


