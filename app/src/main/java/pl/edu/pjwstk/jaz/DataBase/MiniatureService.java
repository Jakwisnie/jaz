package pl.edu.pjwstk.jaz.DataBase;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.pjwstk.jaz.dao.AuctionParameterRepository;
import pl.edu.pjwstk.jaz.dao.AuctionRepository;
import pl.edu.pjwstk.jaz.dao.CategoryRepository;
import pl.edu.pjwstk.jaz.dao.ParameterRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class MiniatureService {
    private static MiniatureEntity miniatureEntity;


    public MiniatureService( ) {

    }
    public static List<MiniatureEntity> getAuction(Long owner_id) {
       List<MiniatureEntity> miniatureEntities = new ArrayList<>();
            for (AuctionEntity auction : AuctionService.auctions(owner_id) ){
                miniatureEntity = new MiniatureEntity();
                AuctionEntity auctionEntity = AuctionService.findAuctionByOwnerId(auction.getId(),owner_id);
                PhotoEntity photoEntity = AuctionService.findPhotoByAuctionId(auctionEntity.getId(),1L);
                miniatureEntity.setId(auction.getId());
                miniatureEntity.setCategory_id(auction.getCategory_id());
                miniatureEntity.setDescription(auction.getDescription());
                miniatureEntity.setTitle(auction.getTitle());
                miniatureEntity.setPrice(auction.getPrice());
                miniatureEntity.setPhoto(photoEntity.getLink());
                miniatureEntities.add(miniatureEntity);
        }
        return miniatureEntities;
    }
}
