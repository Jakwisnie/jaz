package pl.edu.pjwstk.jaz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.jaz.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    public Optional<UserEntity> findByUsername(String username);
}