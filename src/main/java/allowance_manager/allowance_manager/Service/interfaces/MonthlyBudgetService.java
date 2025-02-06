package allowance_manager.allowance_manager.Service.interfaces;

import allowance_manager.allowance_manager.domain.Child;
import allowance_manager.allowance_manager.domain.MonthlyBudget;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

public interface MonthlyBudgetService {
    public Long add(MonthlyBudget monthlyBudget);
    public void delete(Long monthlyBudgetId);
    public void updateChild(Long monthlyBudgetId,Long childId);
    public void updateMonthYear(Long monthlyBudgetId, YearMonth yearMonth);
    public void updateTotalBudget(Long monthlyBudgetId, Long totalBudget);
    public void updateRemainingBudget(Long monthlyBudgetId, Long RemainingBudget);
    public List<MonthlyBudget> findAllMonthlyBudgetByChildId(Long childId);
    public List<MonthlyBudget> findAllMonthlyBudgetByParentAndChildId(Long MemberId, Long childId);
//    public List<MonthlyBudget> findAllMonthlyBudgetByParentAndChildIdAndYearMonth(Long MemberId, Long childId, YearMonth yearMonth);
    public List<MonthlyBudget> findAllMonthlyBudget();
    public Optional<MonthlyBudget> findOneMonthlyBudget(Long monthlyBudgetId);
}
