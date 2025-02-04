package allowance_manager.allowance_manager.Service.interfaces;


import allowance_manager.allowance_manager.domain.Budget;
import allowance_manager.allowance_manager.domain.Member;
import allowance_manager.allowance_manager.domain.MonthlyBudget;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BudgetService {
    //기본 CD
    public Long create(Budget budget);
    public void delete(Long budgetId);

    //응용 U
    public void updateMonthylyBudget(Long budgetId, MonthlyBudget monthlyBudget);

    public void updatePrice(Long budgetId, Long price);

    public void updateIsPaid(Long budgetId);

    public void updatePlannedPaidDate(Long budgetId, LocalDateTime localDateTime);

    public void updateActualPaidDate(Long budgetId, LocalDateTime localDateTime);
    //<--update 카테고리-->//

    //조회
    public List<Budget> getBudgetsByMember(Long memberId);

    public List<Budget> getBudgetsByMemberAndChild(Long memberId, Long childId);

    public List<Budget> getBudgetsByMemberChildAndMonthlyBudget(Long memberId, Long childId, Long monthlyBudgetId);

    //기본 전체 조회
    public List<Budget> findAllBudgets();
    public Optional<Budget> findOneBudget(Long budgetId);
}
