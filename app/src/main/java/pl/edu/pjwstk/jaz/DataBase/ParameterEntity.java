package pl.edu.pjwstk.jaz.DataBase;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "parameter")
public class ParameterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "parameterEntity")
    Set<AuctionParameterEntity> auctionParameterEntities;

    public Set<AuctionParameterEntity> getAuctionParameterEntities() {
        return auctionParameterEntities;
    }

    public void setAuctionParameterEntities(Set<AuctionParameterEntity> auctionParameterEntities) {
        this.auctionParameterEntities = auctionParameterEntities;
    }

    public ParameterEntity(String name) {
        this.name = name;
    }

    public ParameterEntity() {
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
