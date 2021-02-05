package pl.edu.pjwstk.jaz;



public class CategoryRequest {

    private final String name;
    private String newName;

    public CategoryRequest(String name, String newName) {
        this.name = name;
        this.newName = newName;
    }

    public String getName() {
        return name;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
}