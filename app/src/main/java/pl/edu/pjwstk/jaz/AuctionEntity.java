package pl.edu.pjwstk.jaz;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "auction")
public class AuctionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "owner_id")
    private Long owner_id;

    @Column(name = "category_id")
    private Long category_id;

    @Column(name = "price")
    private int price;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "auction_id", referencedColumnName = "id")
    private final Set<PhotoEntity> photos = new HashSet<>();


    @OneToMany(mappedBy = "auctionEntity")
    Set<AuctionParameterEntity> auctionParameterEntities = new HashSet<>();

    public Set<AuctionParameterEntity> getAuctionParameterEntities() {
        return auctionParameterEntities;
    }

    public void setAuctionParameterEntities(Set<AuctionParameterEntity> auctionParameterEntities) {
        this.auctionParameterEntities = auctionParameterEntities;
    }

    public void addAuctionParameter(AuctionParameterEntity auctionParameterEntity){
        auctionParameterEntities.add(auctionParameterEntity);
    }

    public Set<PhotoEntity> getPhotos() {
        return photos;
    }

    public AuctionEntity() {
    }

    public AuctionEntity(String title, String description, Long owner_id, int price) {
        this.title = title;
        this.description = description;
        this.owner_id = owner_id;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Long owner_id) {
        this.owner_id = owner_id;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
