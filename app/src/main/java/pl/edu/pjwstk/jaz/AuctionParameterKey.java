package pl.edu.pjwstk.jaz;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class AuctionParameterKey implements Serializable {

//    @Column(name = "auction_id")
    Long auctionId;

//    @Column(name = "parameter_id")
    Long parameterId;

    public AuctionParameterKey(Long auctionId, Long parameterId) {
        this.auctionId = auctionId;
        this.parameterId = parameterId;
    }

    public AuctionParameterKey() {
    }

    public Long getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(Long auctionId) {
        this.auctionId = auctionId;
    }

    public Long getParameterId() {
        return parameterId;
    }

    public void setParameterId(Long parameterId) {
        this.parameterId = parameterId;
    }
}
