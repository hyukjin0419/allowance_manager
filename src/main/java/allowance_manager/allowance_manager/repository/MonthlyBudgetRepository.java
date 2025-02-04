package allowance_manager.allowance_manager.repository;

import allowance_manager.allowance_manager.domain.MonthlyBudget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.List;

@Repository
public interface MonthlyBudgetRepository extends JpaRepository<MonthlyBudget, Long> {
    List<MonthlyBudget> findByChildId(Long childId);
    List<MonthlyBudget> findByChildIdAndMonthYear(Long childId, YearMonth monthYear);
}
