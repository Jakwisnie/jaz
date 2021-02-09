package pl.edu.pjwstk.jaz.DataBase;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.pjwstk.jaz.dao.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final SectionRepository sectionRepository;


    public CategoryService(SectionRepository sectionRepository ,CategoryRepository categoryRepository) {
        this.sectionRepository = sectionRepository;
        this.categoryRepository = categoryRepository;
    }



    public void addCategory(String sectionName, List<String> categories) {

        SectionEntity sectionEntity = sectionRepository.findByName(sectionName).orElseGet(SectionEntity::new);
        if(sectionEntity.getName() == null){
            System.out.println("No section like that");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        else {
            for (String auto : categories) {
                System.out.println("Added auction : " + auto);
            }

            for (String category : categories) {
                CategoryEntity categoryEntityNameCheck = categoryRepository.findByName(category).orElseGet(CategoryEntity::new);
                if(categoryEntityNameCheck.getName()!=null){
                    System.out.println("Same auction name ");
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

                }
                else {
                    if(category.equals(""))throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                    else {
                        CategoryEntity categoryEntity = new CategoryEntity(category);
                        sectionEntity.getCategories().add(categoryEntity);
                    }
                }
            }
        }
    }


    public void editCategory(String categoryName,String newCategoryName ){

        CategoryEntity categoryEntity = categoryRepository.findByName(categoryName).orElseGet(CategoryEntity::new);

        if(categoryEntity.getName() == null){
            System.out.println("No category name like that");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        else {
            categoryEntity.setName(newCategoryName);
            if(newCategoryName.equals(""))throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            else {
            this.categoryRepository.save(categoryEntity);
            }

        }
    }















}
