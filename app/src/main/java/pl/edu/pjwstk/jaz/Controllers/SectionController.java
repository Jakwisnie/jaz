package pl.edu.pjwstk.jaz.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.DataBase.SectionService;
import pl.edu.pjwstk.jaz.Requests.CategoryRequest;
import pl.edu.pjwstk.jaz.Requests.SectionNameRequest;
import pl.edu.pjwstk.jaz.Requests.SectionRequest;


@RestController
public class SectionController {

    private final SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @PreAuthorize("hasAnyAuthority('Admin')")
    @PostMapping("/addSection")
    public void addNewSection(@RequestBody SectionRequest sectionRequest){
        sectionService.addSection(sectionRequest.getName());
    }

    @PreAuthorize("hasAnyAuthority('Admin')")
    @PostMapping("/addCategory")
    public void addNewCategory(@RequestBody SectionRequest sectionRequest){
        sectionService.addCategory(sectionRequest.getName(),sectionRequest.getCategories());
    }

    @PreAuthorize("hasAnyAuthority('Admin')")
    @PostMapping("/editSection")
    public void editSection(@RequestBody SectionNameRequest sectionRequest){
        sectionService.editSection(sectionRequest.getName(),sectionRequest.getNewName());
    }

    @PreAuthorize("hasAnyAuthority('Admin')")
    @PostMapping("/editCategory")
    public void editSection(@RequestBody CategoryRequest categoryRequest){
        sectionService.editCategory(categoryRequest.getName(),categoryRequest.getNewName());
    }
}