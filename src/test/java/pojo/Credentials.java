package pojo;

public class Credentials {
    private String username;
    private String password;

    public Credentials(String username, String password) {
        this.username = username; this.password = password; }

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
