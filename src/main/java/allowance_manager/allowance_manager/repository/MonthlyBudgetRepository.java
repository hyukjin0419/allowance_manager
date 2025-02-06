package allowance_manager.allowance_manager.repository;

import allowance_manager.allowance_manager.domain.MonthlyBudget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.List;

@Repository
public interface MonthlyBudgetRepository extends JpaRepository<MonthlyBudget, Long> {
    List<MonthlyBudget> findByChild_Id(Long childId);
    List<MonthlyBudget> findByChild_Parent_Id(Long memberId);
    List<MonthlyBudget> findByChild_Parent_IdAndChild_Id(Long memberId, Long childId);
//    List<MonthlyBudget> findByChild_Parent_IdAndChild_IdAndMonthYear(Long memberId, Long childId, YearMonth monthYear);
}
