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
public class AuctionService {

    private static EntityManager entityManager;
    private final CategoryRepository categoryRepository;
    private final AuctionRepository auctionRepository;
    private final ParameterRepository parameterRepository;

    private final AuctionParameterRepository auctionParameterRepository;


    public AuctionService( CategoryRepository categoryRepository, AuctionRepository auctionRepository, ParameterRepository parameterRepository, EntityManager entityManager, AuctionParameterRepository auctionParameterRepository) {

        this.categoryRepository = categoryRepository;
        this.auctionRepository = auctionRepository;
        this.parameterRepository = parameterRepository;
        this.entityManager = entityManager;
        this.auctionParameterRepository = auctionParameterRepository;
    }

    public void addAuction(String categoryName, String title, String description,int price,Long owner_id,List<String> photos,List<String> values,List<String> parameters) {
        if(categoryName.equals("") || title.equals("") || parameters.size()!=values.size())throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        else {
            CategoryEntity categoryEntity = categoryRepository.findByName(categoryName).orElseGet(CategoryEntity::new);
            if (categoryEntity.getName() == null) {
                System.out.println("No category like that");
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            } else {

                AuctionEntity auctionEntity = new AuctionEntity(title, description, owner_id, price);
                categoryEntity.getAuctions().add(auctionEntity);
                Long position = (long) 1;
                for (String photo : photos) {
                    PhotoEntity photoEntity = new PhotoEntity(photo, position++);
                    auctionEntity.getPhotos().add(photoEntity);
                }

                for (int i = 0; i < parameters.size(); i++) {
                    if (values.get(i).equals("") || parameters.get(i).equals("") )
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                    ParameterEntity parameterEntity = parameterRepository.findByName(parameters.get(i)).orElseGet(ParameterEntity::new);
                    if (parameterEntity.getName() == null) {
                        parameterEntity.setName(parameters.get(i));
                        this.parameterRepository.save(parameterEntity);
                    } else {
                        System.out.println("Parameter already exists ");
                    }

                    this.auctionRepository.save(auctionEntity);
                    this.parameterRepository.save(parameterEntity);
                    AuctionParameterEntity auctionParameterEntity = new AuctionParameterEntity(new AuctionParameterKey(auctionEntity.getId(), parameterEntity.getId()), values.get(i));
                    auctionParameterEntity.setAuctionEntity(auctionEntity);
                    auctionParameterEntity.setParameterEntity(parameterEntity);
                    auctionEntity.addAuctionParameter(auctionParameterEntity);
                    entityManager.merge(auctionParameterEntity);
                }

            }
        }
    }

    public void editAuction(Long auction_id,Long owner_id,String parameter,String newValue,String newTitle,int newPrice) {

        ParameterEntity parameterEntity = parameterRepository.findByName(parameter).orElseGet(ParameterEntity::new);
        if(newTitle.equals("") || newValue.equals(""))throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        else {
            if (parameterEntity.getId() == null) {
                System.out.println("His/Her id : " + owner_id);
                AuctionEntity auctionEntity = findAuctionByOwnerId(auction_id, owner_id);
                if (auctionEntity.getId() == null) {
                    System.out.println("You dont have auction like that");
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                } else {
                    System.out.println("Creating new parameter for auction_id : " + auction_id);
                    parameterEntity.setName(parameter);
                    this.parameterRepository.save(parameterEntity);
                    auctionEntity.setPrice(newPrice);
                    auctionEntity.setTitle(newTitle);
                    this.auctionRepository.save(auctionEntity);
                    AuctionParameterEntity auctionParameterEntity = new AuctionParameterEntity(new AuctionParameterKey(auction_id, parameterEntity.getId()), newValue);
                    auctionParameterEntity.setAuctionEntity(auctionEntity);
                    auctionParameterEntity.setParameterEntity(parameterEntity);
                    auctionEntity.addAuctionParameter(auctionParameterEntity);
                    entityManager.merge(auctionParameterEntity);
                }
            } else {
                System.out.println("His/Her id : " + owner_id);
                AuctionEntity auctionEntity = findAuctionByOwnerId(auction_id, owner_id);
                if (auctionEntity.getId() == null) {
                    System.out.println("You dont have auction");
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                } else {
                    auctionEntity.setPrice(newPrice);
                    auctionEntity.setTitle(newTitle);
                    this.auctionRepository.save(auctionEntity);
                    AuctionParameterEntity auctionParameterEntity = findAuctionParameterByAuctionIdAndParameterId(auctionEntity.getId(), parameterEntity.getId());
                    if (auctionParameterEntity.getValue() == null) {
                        System.out.println("No auction_parameter like this");
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                    } else {
                        auctionParameterEntity.setValue(newValue);
                        this.auctionParameterRepository.save(auctionParameterEntity);
                    }
                }
            }
        }
    }

    public void editPhoto(Long position, String newLink, Long owner_id,Long auction_id){
        try{
            AuctionEntity auctionEntity = findAuctionByOwnerId(auction_id,owner_id);
            PhotoEntity photoEntity = findPhotoByAuctionId(auctionEntity.getId(),position);
            photoEntity.setLink(newLink);
            entityManager.merge(photoEntity);
        }catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }

    public void addPhoto(String newlink, Long owner_id,Long auction_id){
        try{
            AuctionEntity auctionEntity = findAuctionByOwnerId(auction_id,owner_id);
            PhotoEntity photoEntity = new PhotoEntity(newlink, photos(auction_id));
            auctionEntity.getPhotos().add(photoEntity);
        }catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public static AuctionEntity findAuctionByOwnerId(Long auction_id, Long owner_id) {
        try {
            return entityManager.createQuery("select ue from AuctionEntity ue where ue.owner_id = :owner_id and ue.id =:auction_id", AuctionEntity.class)
                    .setParameter("owner_id", owner_id)
                    .setParameter("auction_id", auction_id)
                    .getSingleResult();
        }catch (Exception exception){
            System.out.println("No auction found like this");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
    public AuctionParameterEntity findAuctionParameterByAuctionIdAndParameterId(Long auction_id, Long parameter_id) {
        try {
            return entityManager.createQuery("select ue from AuctionParameterEntity ue where ue.auction_parameter_id.auctionId = :auction_id and ue.auction_parameter_id.parameterId = : parameter_id", AuctionParameterEntity.class)
                    .setParameter("auction_id", auction_id)
                    .setParameter("parameter_id", parameter_id)
                    .getSingleResult();
        }catch (Exception exception){
            System.out.println("No auction_id with parameter_id found");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public static PhotoEntity findPhotoByAuctionId(Long auction_id, Long position) {
        try {
            return entityManager.createQuery("select ue from PhotoEntity ue where  ue.auction_id =:auction_id and ue.position =: position", PhotoEntity.class)
                    .setParameter("auction_id", auction_id)
                    .setParameter("position", position)
                    .getSingleResult();
        }catch (Exception exception){
            System.out.println("No photo with that auction_id or position");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
   public Long photos(Long auction_id){
       return (Long) entityManager.createQuery("select count(ue) from PhotoEntity ue where ue.auction_id =: auction_id")
               .setParameter("auction_id",auction_id)
               .getSingleResult();
   }

    public static List<AuctionEntity> auctions(Long owner_id){
        return entityManager.createQuery("select ue from AuctionEntity ue where ue.owner_id =: owner_id",AuctionEntity.class)
                .setParameter("owner_id",owner_id)
                .getResultList();
    }



}
