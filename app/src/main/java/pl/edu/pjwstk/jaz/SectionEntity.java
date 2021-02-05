package pl.edu.pjwstk.jaz;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "section")
public class SectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;



    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "section_id",referencedColumnName = "id")
    private final Set<CategoryEntity> Categories = new HashSet<>();

    public Set<CategoryEntity> getCategories() {
        return Categories;
    }
    public SectionEntity() {

    }

    public SectionEntity(String name) {
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
