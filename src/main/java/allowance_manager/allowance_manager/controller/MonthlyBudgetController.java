package allowance_manager.allowance_manager.controller;

import allowance_manager.allowance_manager.Service.interfaces.ChildService;
import allowance_manager.allowance_manager.Service.interfaces.MonthlyBudgetService;
import allowance_manager.allowance_manager.controller.form.MonthlyBudgetForm;
import allowance_manager.allowance_manager.utility.SessionUtil;
import allowance_manager.allowance_manager.domain.Child;
import allowance_manager.allowance_manager.domain.MonthlyBudget;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MonthlyBudgetController {
    private final ChildService childService;
    private final SessionUtil sessionUtil;
    private final MonthlyBudgetService monthlyBudgetService;

    @GetMapping("/navigateToMonthlyBudget/{id}")
    public String navigateToMonthlyBudget(@PathVariable Long id) {
        if (sessionUtil.isMonthlyBudgetExist(id)) {
            return "redirect:/monthlyBudget/monthlyBudgetManager/" + id;
        } else {
            return "redirect:/monthlyBudget/createMonthlyBudgetForm/" + id;
        }
    }

    @GetMapping("/monthlyBudget/createMonthlyBudgetForm/{id}")
    public String createMonthlyBudgetForm(@PathVariable Long id, Model model) {
        Child child = childService.findChild(id)
                .orElseThrow(() -> new EntityNotFoundException("Child not found"));

        model.addAttribute("child", child);

        return "/monthlyBudget/createMonthlyBudgetForm";
    }

    @PostMapping("/monthlyBudget/createMonthlyBudgetForm")
    public String registerMonthlyBudget(@Valid MonthlyBudgetForm form, BindingResult result) {
        if (result.hasErrors()) {
            log.error("monthlyBudget register error");
            return "redirect:/child/childManager";
        }
        log.info("monthlyBudget register success");

        Child child = childService.findChild(form.getChildId())
                .orElseThrow(() -> new EntityNotFoundException("child not found"));
        MonthlyBudget monthlyBudget = MonthlyBudget.createMonthlyBudget(child, form.getYearMonth(), form.getTotalBudget());

        monthlyBudgetService.add(monthlyBudget);

        return "redirect:/navigateToMonthlyBudget/" + form.getChildId();
    }

    @GetMapping("/monthlyBudget/monthlyBudgetManager/{id}")
    public String showMonthlyBudget(@PathVariable Long id, Model model) {
        Child child = childService.findChild(id)
                .orElseThrow(() -> new EntityNotFoundException("Child not found"));

        List<MonthlyBudget> monthlyBudgets = monthlyBudgetService.findAllMonthlyBudgetByChildId(id);

        model.addAttribute("monthlyBudgets", monthlyBudgets);
        log.info("working fine");

        return "monthlyBudget/monthlyBudgetManager";
    }

}
