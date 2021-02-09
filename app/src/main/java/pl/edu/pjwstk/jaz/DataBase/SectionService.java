package pl.edu.pjwstk.jaz.DataBase;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.pjwstk.jaz.dao.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Transactional
@Service
public class SectionService {

    private final SectionRepository sectionRepository;



    public SectionService(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;

    }

    public void addSection(String sectionName) {
        SectionEntity sectionEntityNameCheck = sectionRepository.findByName(sectionName).orElseGet(SectionEntity::new);
        if(sectionEntityNameCheck.getName()!=null) {
                System.out.println("Same  section  name");
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        else{
            SectionEntity sectionEntity = new SectionEntity(sectionName);
            if (sectionName.equals("")) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            else {
                this.sectionRepository.save(sectionEntity);
             }
        }
    }

    public void editSection(String sectionName,String newSectionName){

        SectionEntity sectionEntity = sectionRepository.findByName(sectionName).orElseGet(SectionEntity::new);
        if(sectionEntity.getName() == null){
            System.out.println("No section name like that");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        else {
            sectionEntity.setName(newSectionName);
            if(newSectionName.equals(""))throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            else {
                this.sectionRepository.save(sectionEntity);
            }
        }
    }
}
