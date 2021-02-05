package pl.edu.pjwstk.jaz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.jaz.AuctionParameterEntity;

import java.util.Optional;

@Repository
public interface AuctionParameterRepository extends JpaRepository<AuctionParameterEntity,Long> {
   public Optional<AuctionParameterEntity> findByAuctionEntity_Id(Long id);
}
