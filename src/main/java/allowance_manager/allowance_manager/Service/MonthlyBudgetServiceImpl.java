package allowance_manager.allowance_manager.Service;

import allowance_manager.allowance_manager.Service.interfaces.MonthlyBudgetService;
import allowance_manager.allowance_manager.domain.MonthlyBudget;
import allowance_manager.allowance_manager.repository.ChildRepository;
import allowance_manager.allowance_manager.repository.MemberRepository;
import allowance_manager.allowance_manager.repository.MonthlyBudgetRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MonthlyBudgetServiceImpl implements MonthlyBudgetService {
    private final MemberRepository memberRepository;
    private final ChildRepository childRepository;
    private final MonthlyBudgetRepository monthlyBudgetRepository;


    @Override
    public Long add(MonthlyBudget monthlyBudget) {
        monthlyBudgetRepository.save(monthlyBudget);

        return monthlyBudget.getId();
    }

    @Override
    public void delete(Long monthlyBudgetId) {
        monthlyBudgetRepository.deleteById(monthlyBudgetId);
    }

    @Override
    public void update(Long monthlyBudgetId, YearMonth yearMonth, Long totalBudget) {
        MonthlyBudget monthlyBudget = monthlyBudgetRepository.findById(monthlyBudgetId)
                .orElseThrow(() -> new EntityNotFoundException("monthlyBudget is not found"));

        monthlyBudget.setYearMonth(yearMonth);
        monthlyBudget.setTotalBudget(totalBudget);
    }

    @Override
    public void updateChild(Long monthlyBudgetId, Long childId) {
        MonthlyBudget monthlyBudget = monthlyBudgetRepository.findById(monthlyBudgetId)
                .orElseThrow(() -> new EntityNotFoundException("Monthly Budget Not Found"));

        monthlyBudget.setChild(childRepository.findById(childId)
                .orElseThrow(() -> new EntityNotFoundException("Child Not Found")));
    }

    @Override
    public void updateMonthYear(Long monthlyBudgetId, YearMonth yearMonth) {
        MonthlyBudget monthlyBudget = monthlyBudgetRepository.findById(monthlyBudgetId)
                .orElseThrow(() -> new EntityNotFoundException("Monthly Budget Not Found"));

        monthlyBudget.setYearMonth(yearMonth);
    }

    @Override
    public void updateTotalBudget(Long monthlyBudgetId, Long totalBudget) {
        MonthlyBudget monthlyBudget = monthlyBudgetRepository.findById(monthlyBudgetId)
                .orElseThrow(() -> new EntityNotFoundException("Monthly Budget Not Found"));

        monthlyBudget.setTotalBudget(totalBudget);
    }

    @Override
    public void updateRemainingBudget(Long monthlyBudgetId, Long remainingBudget) {
        MonthlyBudget monthlyBudget = monthlyBudgetRepository.findById(monthlyBudgetId)
                .orElseThrow(() -> new EntityNotFoundException("Monthly Budget Not Found"));

        monthlyBudget.setRemainingBudget(remainingBudget);
    }

    @Override
    public List<MonthlyBudget> findAllMonthlyBudgetByChildId(Long childId) {
        return monthlyBudgetRepository.findByChild_Id(childId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MonthlyBudget> findAllMonthlyBudgetByParentAndChildId(Long memberId, Long childId) {

        return monthlyBudgetRepository.findByChild_Parent_IdAndChild_Id(memberId, childId);
    }

//    @Override
//    @Transactional(readOnly = true)
//    public List<MonthlyBudget> findAllMonthlyBudgetByParentAndChildIdAndYearMonth(Long memberId, Long childId, YearMonth yearMonth) {
//        return monthlyBudgetRepository.findByChild_Parent_IdAndChild_IdAndMonthYear(memberId, childId, yearMonth);
//    }

    @Override
    public List<MonthlyBudget> findAllMonthlyBudget() {
        return List.of();
    }

    @Override
    public Optional<MonthlyBudget> findOneMonthlyBudget(Long monthlyBudgetId) {
        return monthlyBudgetRepository.findById(monthlyBudgetId);
    }
}