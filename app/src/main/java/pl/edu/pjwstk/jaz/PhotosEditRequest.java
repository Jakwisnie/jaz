package pl.edu.pjwstk.jaz;



public class PhotosEditRequest {


   private Long position;
   private String newLink;
   private Long auction_id;

    public PhotosEditRequest(Long position, String newLink, Long auction_id) {
        this.position = position;
        this.newLink = newLink;
        this.auction_id = auction_id;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    public String getNewLink() {
        return newLink;
    }

    public void setNewLink(String newLink) {
        this.newLink = newLink;
    }

    public Long getAuction_id() {
        return auction_id;
    }

    public void setAuction_id(Long auction_id) {
        this.auction_id = auction_id;
    }
}
