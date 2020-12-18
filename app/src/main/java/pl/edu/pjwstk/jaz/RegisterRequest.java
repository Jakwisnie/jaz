package pl.edu.pjwstk.jaz;

public class RegisterRequest {
    private String name;
    private String lastName;
    private String username;
    private String password;
    private String permission;

    public RegisterRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setPermission(String permission) { this.permission = permission; }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getPermission() { return permission; }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
