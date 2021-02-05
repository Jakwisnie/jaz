package pl.edu.pjwstk.jaz;



public class AuctionEditRequest {


    private Long auction_id;
    private String title;
    private int price;
    private String parameter;
    private String value;
    private String newTitle;
    private int newPrice;


    public AuctionEditRequest(Long auction_id, String title, int price, String parameter, String value, String newTitle, int newPrice) {
        this.auction_id = auction_id;
        this.title = title;
        this.price = price;
        this.parameter = parameter;
        this.value = value;
        this.newTitle = newTitle;
        this.newPrice = newPrice;
    }

    public Long getAuction_id() {
        return auction_id;
    }

    public void setAuction_id(Long auction_id) {
        this.auction_id = auction_id;
    }

    public String getNewTitle() {
        return newTitle;
    }

    public void setNewTitle(String newTitle) {
        this.newTitle = newTitle;
    }

    public int getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(int newPrice) {
        this.newPrice = newPrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
