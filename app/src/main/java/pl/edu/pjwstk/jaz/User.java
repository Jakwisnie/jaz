package pl.edu.pjwstk.jaz;

import java.util.HashSet;
import java.util.Set;

public class User {
    private String name;
    private String lastName;
    private String username;
    private String password;
    private Set<String> authorities;



    public User(String username, String password) {
        this.username = username;
        this.password = password;
        authorities = new HashSet<>();
        authorities.add("Everyone");
    }

    public User(String username, String password,Set<String> roles) {
        this.username = username;
        this.password = password;
        authorities = roles;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }


    public void addAuthorities(String permission){
        authorities.add(permission);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
}
