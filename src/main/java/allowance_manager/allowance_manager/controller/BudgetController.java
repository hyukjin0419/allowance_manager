package allowance_manager.allowance_manager.controller;

import allowance_manager.allowance_manager.Service.interfaces.BudgetService;
import allowance_manager.allowance_manager.Service.interfaces.MonthlyBudgetService;
import allowance_manager.allowance_manager.domain.Budget;
import allowance_manager.allowance_manager.domain.Child;
import allowance_manager.allowance_manager.domain.MonthlyBudget;
import allowance_manager.allowance_manager.utility.SessionUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/budget")
public class BudgetController {
    private final SessionUtil sessionUtil;
    private final MonthlyBudgetService monthlyBudgetService;
    private final BudgetService budgetService;

    @GetMapping("navigateToBudget/{id}")
    public String navigateToBudget(@PathVariable Long id) {
        if (sessionUtil.isBudgetExist(id)) {
            log.info("this");
            return "redirect:/budget/budgetManager/" + id;
        } else {
            log.info("or this");
            return "redirect:/budget/createBudgetForm/" + id;
        }
    }

    @GetMapping("/createBudgetForm/{id}")
    public String createBudget(@PathVariable Long id, Model model) {
        MonthlyBudget monthlyBudget = monthlyBudgetService.findOneMonthlyBudget(id)
                .orElseThrow(() -> new EntityNotFoundException("MonthlyBudget not found"));

        model.addAttribute("monthlyBudget", monthlyBudget);

        return "/budget/createBudgetForm";
    }

    //post

    @GetMapping("/budgetManager/{id}")
    public String showMonthlyBudget(@PathVariable Long id, Model model) {
        List<Budget> budgets = budgetService.getBudgetsByMonthlyBudgetId(id);

        model.addAttribute("budgets", budgets);

        return "/budget/budgetManager";
    }


}
