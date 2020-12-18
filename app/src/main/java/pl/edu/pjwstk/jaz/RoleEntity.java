package pl.edu.pjwstk.jaz;


import javax.persistence.*;

@Entity
@Table(name = "userroles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_role")
    private String role;

    public RoleEntity() {

    }
    public RoleEntity(String role) {
        super();
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}