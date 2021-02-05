package pl.edu.pjwstk.jaz;


public class MiniatureEntity {
    private Long id;
    private String title;
    private String description;
    private Long category_id;
    private int price;
    private String photo;

    public MiniatureEntity() {
    }

    public MiniatureEntity(Long id, String title, String description, Long category_id, int price, String photo) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category_id = category_id;
        this.price = price;
        this.photo = photo;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
