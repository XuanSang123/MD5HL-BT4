package ra.api.categy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.api.categy.entity.Category;

public interface ICategoryRepo extends JpaRepository<Category, Long> {
}
