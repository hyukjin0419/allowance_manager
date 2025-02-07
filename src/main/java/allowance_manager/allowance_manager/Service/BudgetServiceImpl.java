package allowance_manager.allowance_manager.Service;

import allowance_manager.allowance_manager.Service.interfaces.BudgetService;
import allowance_manager.allowance_manager.domain.Budget;
import allowance_manager.allowance_manager.domain.MonthlyBudget;
import allowance_manager.allowance_manager.repository.BudgetRepository;
import allowance_manager.allowance_manager.repository.ChildRepository;
import allowance_manager.allowance_manager.repository.MemberRepository;
import allowance_manager.allowance_manager.repository.MonthlyBudgetRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetService {

    private final MemberRepository memberRepository;
    private final ChildRepository childRepository;
    private final MonthlyBudgetRepository monthlyBudgetRepository;
    private final BudgetRepository budgetRepository;

    @Override
    public Long create(Budget budget) {
        budgetRepository.save(budget);

        return budget.getId();
    }

    @Override
    public void delete(Long budgetId) {
        budgetRepository.deleteById(budgetId);
    }

    @Override
    public void updateMonthylyBudget(Long budgetId, MonthlyBudget monthlyBudget) {
        Budget budget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new EntityNotFoundException("No Budget Found"));

        budget.setMonthlyBudget(monthlyBudget);
    }

    @Override
    public void updatePrice(Long budgetId, Long price) {
        Budget budget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new EntityNotFoundException("No Budget Found"));

        budget.setPrice(price);
    }

    @Override
    public void updateIsPaid(Long budgetId) {
        Budget budget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new EntityNotFoundException("No Budget Found"));

        budget.setIsPaid(!budget.getIsPaid());
    }

    @Override
    public void updatePlannedPaidDate(Long budgetId, LocalDateTime localDateTime) {
        Budget budget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new EntityNotFoundException("No Budget Found"));

        budget.setPlannedPaidDate(localDateTime);
    }

    @Override
    public void updateActualPaidDate(Long budgetId, LocalDateTime localDateTime) {
        Budget budget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new EntityNotFoundException("No Budget Found"));

        budget.setActualPaidDate(localDateTime);
    }

    @Override
    public List<Budget> getBudgetsByMember(Long memberId) {
        return budgetRepository.findByMonthlyBudget_Child_Parent_Id(memberId);
    }

    @Override
    public List<Budget> getBudgetsByMemberAndChild(Long memberId, Long childId) {
        return budgetRepository.findByMonthlyBudget_Child_Parent_IdAndMonthlyBudget_Child_Id(memberId, childId);
    }

    @Override
    public List<Budget> getBudgetsByMonthlyBudgetId(Long monthlyBudgetId) {
        return budgetRepository.findByMonthlyBudget_Id(monthlyBudgetId);
    }

    @Override
    public List<Budget> getBudgetsByMemberChildAndMonthlyBudget(Long memberId, Long childId, Long monthlyBudgetId) {
        return budgetRepository.findByMonthlyBudget_Child_Parent_IdAndMonthlyBudget_Child_IdAndMonthlyBudget_Id(memberId, childId, monthlyBudgetId);
    }

    @Override
    public List<Budget> getBudgetsByMemberChildAndCategory(Long memberId, Long childId, Long categoryId) {
        return budgetRepository.findByMonthlyBudget_Child_Parent_IdAndMonthlyBudget_Child_IdAndCategory_Id(memberId, childId, categoryId);
    }

    @Override
    public List<Budget> getBudgetsByMemberChildMonthlyBudgetAndCategory(Long memberId, Long childId, Long monthlyBudgetId, Long categoryId) {
        return budgetRepository.findByMonthlyBudget_Child_Parent_IdAndMonthlyBudget_Child_IdAndMonthlyBudget_IdAndCategory_Id(memberId, childId, monthlyBudgetId, categoryId);
    }

    @Override
    public List<Budget> findAllBudgets() {
        return budgetRepository.findAll();
    }

    @Override
    public Optional<Budget> findOneBudget(Long budgetId) {
        return budgetRepository.findById(budgetId);
    }
}
