package allowance_manager.allowance_manager.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    private YearMonth monthYear;

    private Long totalBudget;

    private Long remainingBudget;
}
