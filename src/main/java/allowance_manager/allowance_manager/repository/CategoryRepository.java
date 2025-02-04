package allowance_manager.allowance_manager.repository;

import allowance_manager.allowance_manager.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
