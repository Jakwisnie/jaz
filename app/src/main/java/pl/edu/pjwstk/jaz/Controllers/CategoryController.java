package pl.edu.pjwstk.jaz.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.DataBase.CategoryService;
import pl.edu.pjwstk.jaz.Requests.CategoryRequest;
import pl.edu.pjwstk.jaz.Requests.SectionRequest;



@RestController
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PreAuthorize("hasAnyAuthority('Admin')")
    @PostMapping("/addCategory")
    public void addNewCategory(@RequestBody SectionRequest sectionRequest){
        categoryService.addCategory(sectionRequest.getName(),sectionRequest.getCategories());
    }

    @PreAuthorize("hasAnyAuthority('Admin')")
    @PostMapping("/editCategory")
    public void editCategory(@RequestBody CategoryRequest categoryRequest){
        categoryService.editCategory(categoryRequest.getName(),categoryRequest.getNewName());
    }
}