package allowance_manager.allowance_manager.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;
import java.time.YearMonth;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
public class MonthlyBudget {

    @Id
    @GeneratedValue
    @Column(name = "monthly_budget_id")
    private Long Id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "child_id")
    private Child child;

    private YearMonth yearMonth;

    private Long totalBudget;

    private Long spentBudget;

    private Long remainingBudget;

    //==생성자 메소드==/
    public static MonthlyBudget createMonthlyBudget(Child child, YearMonth yearMonth, Long totalBudget) {
        MonthlyBudget monthlyBudget = new MonthlyBudget();
        monthlyBudget.setChild(child);
        monthlyBudget.setYearMonth(yearMonth);
        monthlyBudget.setTotalBudget(totalBudget);
        monthlyBudget.setSpentBudget(0L);
        monthlyBudget.setRemainingBudget(totalBudget);

        return monthlyBudget;
    }
}
