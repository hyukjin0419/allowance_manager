package allowance_manager.allowance_manager.repository;

import allowance_manager.allowance_manager.domain.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    //부모로 조회
    List<Budget> findByChild_Parent_Id(Long memberId);
    //부모, 자식으로 조회
    List<Budget> findByChild_Parent_IdAndChild_Id(Long memberId, Long childId);
    //부모 자식, MonthlyBudget으로 조회
    List<Budget> findByChild_Parent_IdAndChild_IdAndMonthlyBudget_Id(Long memberId, Long childId, Long monthlyBudgetId);

    //부모, 자식, 카테고리로 조회
    List<Budget> findByChild_Parent_IdAndChild_IdAndCategory_Id(Long memberId, Long childId, Long categoryId);
    //부모, 자식, MonthlyBudget, Category로 조회
    List<Budget> findByChild_Parent_IdAndChild_IdAndMonthlyBudget_IdAndCategory_Id(Long memberId, Long childId, Long monthlyBudgetId, Long categoryId);

}
