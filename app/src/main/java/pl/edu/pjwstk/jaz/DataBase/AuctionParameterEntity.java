package pl.edu.pjwstk.jaz.DataBase;

import javax.persistence.*;

@Entity
@Table(name = "auction_parameter")
public class AuctionParameterEntity {

    @EmbeddedId
    AuctionParameterKey auction_parameter_id;

    @ManyToOne
    @MapsId("auctionId")
    @JoinColumn(name = "auction_id")
    AuctionEntity auctionEntity;

    @ManyToOne
    @MapsId("parameterId")
    @JoinColumn(name = "parameter_id")
    ParameterEntity parameterEntity;

    @Column(name = "value")
    private String value;


    public Long getAuctionId(AuctionEntity auctionEntity){
        return auctionEntity.getId();
    }

    public AuctionParameterEntity(AuctionParameterKey auction_parameter_id,String value) {
        this.value = value;
        this.auction_parameter_id = auction_parameter_id;
    }

    public AuctionParameterEntity() {
    }

    public AuctionParameterKey getAuction_parameter_id() {
        return auction_parameter_id;
    }

    public void setAuction_parameter_id(AuctionParameterKey auction_parameter_id) {
        this.auction_parameter_id = auction_parameter_id;
    }

    public AuctionEntity getAuctionEntity() {
        return auctionEntity;
    }

    public void setAuctionEntity(AuctionEntity auctionEntity) {
        this.auctionEntity = auctionEntity;
    }

    public ParameterEntity getParameterEntity() {
        return parameterEntity;
    }

    public void setParameterEntity(ParameterEntity parameterEntity) {
        this.parameterEntity = parameterEntity;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
