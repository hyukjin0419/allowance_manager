package allowance_manager.allowance_manager.controller.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter @Setter
public class BudgetForm {
    private Long id;

    private Long monthlyBudgetId;

    private Long categoryId;

    private Long price;

    private LocalDateTime plannedPaidDate;
}
