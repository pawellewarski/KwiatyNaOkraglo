package pl.pawellewarski.Kwiaty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pawellewarski.Kwiaty.model.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
