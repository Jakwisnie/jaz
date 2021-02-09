package pl.edu.pjwstk.jaz.Requests;

import java.util.List;

public class SectionRequest {

    private String name;
    private List<String> categories;

    public SectionRequest(String name, List<String> categories) {
        this.name = name;
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public List<String> getCategories() {
        return categories;
    }
}