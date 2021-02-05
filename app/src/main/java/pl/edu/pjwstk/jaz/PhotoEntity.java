package pl.edu.pjwstk.jaz;

import liquibase.pro.packaged.C;

import javax.persistence.*;

@Entity
@Table(name = "photo")
public class PhotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "auction_id")
    private Long auction_id;

    @Column(name = "link")
    private String link;

    @Column(name = "position")
    private Long position;

    public PhotoEntity() {
    }

    public PhotoEntity(String link, Long position) {
        this.link = link;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public Long getAuction_id() {
        return auction_id;
    }

    public void setAuction_id(Long auction_id) {
        this.auction_id = auction_id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }
}
