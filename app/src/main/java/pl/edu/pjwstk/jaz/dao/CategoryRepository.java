package pl.edu.pjwstk.jaz.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.jaz.DataBase.CategoryEntity;
import pl.edu.pjwstk.jaz.DataBase.SectionEntity;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {
    public Optional<CategoryEntity> findByName(String name);
}
