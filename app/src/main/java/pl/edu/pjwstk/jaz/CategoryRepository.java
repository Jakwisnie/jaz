package pl.edu.pjwstk.jaz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.jaz.CategoryEntity;
import pl.edu.pjwstk.jaz.SectionEntity;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {
    public Optional<CategoryEntity> findByName(String name);
}
