package allowance_manager.allowance_manager.controller.form;

import allowance_manager.allowance_manager.domain.Child;
import lombok.Getter;
import lombok.Setter;

import java.time.YearMonth;

@Getter @Setter
public class MonthlyBudgetForm {
    private Long childId;

    private YearMonth yearMonth;

    private Long totalBudget;
}
