package allowance_manager.allowance_manager.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Budget {

    @Id
    @GeneratedValue
    @Column(name = "budget_id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "monthly_budget_id")
    private MonthlyBudget monthlyBudget;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private Long price;

    private Boolean isPaid;

    private LocalDateTime createdAt;

    private LocalDateTime plannedPaidDate;

    private LocalDateTime actualPaidDate;
}
