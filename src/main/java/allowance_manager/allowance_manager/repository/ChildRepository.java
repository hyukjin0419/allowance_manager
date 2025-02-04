package allowance_manager.allowance_manager.repository;

import allowance_manager.allowance_manager.domain.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {
    List<Child> findByParent_Id(Long memberId);

    @Transactional
    @Modifying
    @Query("UPDATE Child c SET c.plannedBudget = :plannedBudget WHERE c.id = :childId")
    void updatePlannedBudget(Long childId, Long plannedBudget);
}
