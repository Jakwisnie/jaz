package pl.edu.pjwstk.jaz.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.jaz.DataBase.RoleEntity;



@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
}
