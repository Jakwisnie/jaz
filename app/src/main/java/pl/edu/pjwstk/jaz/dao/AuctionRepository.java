package pl.edu.pjwstk.jaz.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.jaz.DataBase.AuctionEntity;
import pl.edu.pjwstk.jaz.DataBase.RoleEntity;

import java.util.Optional;

@Repository
public interface AuctionRepository extends JpaRepository<AuctionEntity,Long> {
//    public Optional<AuctionEntity> findByTitleAndOwner_id(String title,Long owner_id);
}
